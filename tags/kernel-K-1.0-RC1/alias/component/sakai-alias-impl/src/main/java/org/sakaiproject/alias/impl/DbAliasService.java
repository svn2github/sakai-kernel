/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.alias.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.alias.api.Alias;
import org.sakaiproject.alias.api.AliasEdit;
import org.sakaiproject.db.api.SqlReader;
import org.sakaiproject.db.api.SqlService;
import org.sakaiproject.entity.api.ResourceProperties;
import org.sakaiproject.entity.api.ResourcePropertiesEdit;
import org.sakaiproject.time.api.Time;
import org.sakaiproject.util.BaseDbFlatStorage;
import org.sakaiproject.util.BaseDbSingleStorage;
import org.sakaiproject.util.StorageUser;
import org.sakaiproject.util.StringUtil;
import org.sakaiproject.util.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * <p>
 * DbAliasService is an extension of the BaseAliasService with a database storage. Fields are fully relational. Full properties are not yet supported - core ones are. Code to find and convert records from before, from the XML based CHEF_ALIAS table is
 * included.
 * </p>
 */
public abstract class DbAliasService extends BaseAliasService
{
	/** Our logger. */
	private static Log M_log = LogFactory.getLog(DbAliasService.class);

	/** Table name for aliases. */
	protected String m_tableName = "SAKAI_ALIAS";

	/** Table name for properties. */
	protected String m_propTableName = "SAKAI_ALIAS_PROPERTY";

	/** ID field. */
	protected String m_idFieldName = "ALIAS_ID";

	/** All fields. */
	protected String[] m_fieldNames = { "ALIAS_ID", "TARGET", "CREATEDBY", "MODIFIEDBY", "CREATEDON", "MODIFIEDON" };

	/** If true, we do our locks in the remote database, otherwise we do them here. */
	protected boolean m_useExternalLocks = true;

	/** Set if we are to run the from-old conversion. */
	protected boolean m_convertOld = false;

	/**
	 * Configuration: run the from-old conversion.
	 * 
	 * @param value
	 *        The conversion desired value.
	 */
	public void setConvertOld(String value)
	{
		m_convertOld = new Boolean(value).booleanValue();
	}

	/**********************************************************************************************************************************************************************************************************************************************************
	 * Dependencies
	 *********************************************************************************************************************************************************************************************************************************************************/

	/**
	 * @return the MemoryService collaborator.
	 */
	protected abstract SqlService sqlService();

	/**********************************************************************************************************************************************************************************************************************************************************
	 * Configuration
	 *********************************************************************************************************************************************************************************************************************************************************/

	/**
	 * Configuration: set the external locks value.
	 * 
	 * @param value
	 *        The external locks value.
	 */
	public void setExternalLocks(String value)
	{
		m_useExternalLocks = new Boolean(value).booleanValue();
	}

	/** Configuration: check the old table, too. */
	protected boolean m_checkOld = false;

	/**
	 * Configuration: set the locks-in-db
	 * 
	 * @param value
	 *        The locks-in-db value.
	 */
	public void setCheckOld(String value)
	{
		m_checkOld = new Boolean(value).booleanValue();
	}

	/** Configuration: to run the ddl on init or not. */
	protected boolean m_autoDdl = false;

	/**
	 * Configuration: to run the ddl on init or not.
	 * 
	 * @param value
	 *        the auto ddl value.
	 */
	public void setAutoDdl(String value)
	{
		m_autoDdl = new Boolean(value).booleanValue();
	}

	/**********************************************************************************************************************************************************************************************************************************************************
	 * Init and Destroy
	 *********************************************************************************************************************************************************************************************************************************************************/

	/**
	 * Final initialization, once all dependencies are set.
	 */
	public void init()
	{
		try
		{
			// if we are auto-creating our schema, check and create
			if (m_autoDdl)
			{
				sqlService().ddl(this.getClass().getClassLoader(), "sakai_alias");
			}

			super.init();

			M_log.info("init(): table: " + m_tableName + " external locks: " + m_useExternalLocks + " checkOld: " + m_checkOld);

			// convert?
			if (m_convertOld)
			{
				m_convertOld = false;
				convertOld();
			}

			// do a count which might find no old records so we can ignore old!
			if (m_checkOld)
			{
				m_storage.count();
			}
		}
		catch (Throwable t)
		{
			M_log.warn("init(): ", t);
		}
	}

	/**********************************************************************************************************************************************************************************************************************************************************
	 * BaseAliasService extensions
	 *********************************************************************************************************************************************************************************************************************************************************/

	/**
	 * Construct a Storage object.
	 * 
	 * @return The new storage object.
	 */
	protected Storage newStorage()
	{
		return new DbStorage(this);

	} // newStorage

	/**********************************************************************************************************************************************************************************************************************************************************
	 * Storage implementation
	 *********************************************************************************************************************************************************************************************************************************************************/

	/**
	 * Covers for the BaseXmlFileStorage, providing User and AliasEdit parameters
	 */
	protected class DbStorage extends BaseDbFlatStorage implements Storage, SqlReader
	{
		/** A prior version's storage model. */
		protected Storage m_oldStorage = null;

		/**
		 * Construct.
		 * 
		 * @param user
		 *        The StorageUser class to call back for creation of Resource and Edit objects.
		 */
		public DbStorage(StorageUser user)
		{
			super(m_tableName, m_idFieldName, m_fieldNames, m_propTableName, m_useExternalLocks, null, sqlService());
			m_reader = this;
			setCaseInsensitivity(true);

			// setup for old-new stradling
			if (m_checkOld)
			{
				m_oldStorage = new DbStorageOld(user);
			}

		} // DbStorage

		public boolean check(String id)
		{
			boolean rv = super.checkResource(id);

			// if not, check old
			if (m_checkOld && (!rv))
			{
				rv = m_oldStorage.check(id);
			}

			return rv;
		}

		public AliasEdit get(String id)
		{
			AliasEdit rv = (AliasEdit) super.getResource(id);

			// if not, check old
			if (m_checkOld && (rv == null))
			{
				rv = m_oldStorage.get(id);
			}
			return rv;
		}

		public List getAll()
		{
			// if we have to be concerned with old stuff, we cannot let the db do the range selection
			if (m_checkOld)
			{
				List all = super.getAllResources();

				// add in any additional defined in old
				Set merge = new HashSet();
				merge.addAll(all);

				// add those in the old not already (id based equals) in all
				List more = m_oldStorage.getAll();
				merge.addAll(more);

				all.clear();
				all.addAll(merge);

				return all;
			}

			// let the db do range selection
			List all = super.getAllResources();
			return all;
		}

		public List getAll(int first, int last)
		{
			// if we have to be concerned with old stuff, we cannot let the db do the range selection
			if (m_checkOld)
			{
				List all = super.getAllResources();

				// add in any additional defined in old
				Set merge = new HashSet();
				merge.addAll(all);

				// add those in the old not already (id based equals) in all
				List more = m_oldStorage.getAll();
				merge.addAll(more);

				all.clear();
				all.addAll(merge);

				Collections.sort(all);

				// subset by position
				if (first < 1) first = 1;
				if (last >= all.size()) last = all.size();

				all = all.subList(first - 1, last);
				return all;
			}

			// let the db do range selection
			List all = super.getAllResources(first, last);
			return all;
		}

		public int count()
		{
			// if we have to be concerned with old stuff, we cannot let the db do all the counting
			if (m_checkOld)
			{
				int count = super.countAllResources();
				count += m_oldStorage.count();

				return count;
			}

			return super.countAllResources();
		}

		public List getAll(String target)
		{
			Object[] fields = new Object[1];
			fields[0] = target;

			// if we have to be concerned with old stuff, we cannot let the db do the range selection
			if (m_checkOld)
			{
				List all = super.getSelectedResources("TARGET = ?", fields);

				// add in any additional defined in old
				Set merge = new HashSet();
				merge.addAll(all);

				// add those in the old not already (id based equals) in all
				List more = m_oldStorage.getAll(target);
				merge.addAll(more);

				all.clear();
				all.addAll(merge);

				return all;
			}

			List all = super.getSelectedResources("TARGET = ?", fields);
			return all;
		}

		public List getAll(String target, int first, int last)
		{
			Object[] fields = new Object[1];
			fields[0] = target;

			// if we have to be concerned with old stuff, we cannot let the db do the range selection
			if (m_checkOld)
			{
				List all = super.getSelectedResources("TARGET = ?", fields);

				// add in any additional defined in old
				Set merge = new HashSet();
				merge.addAll(all);

				// add those in the old not already (id based equals) in all
				List more = m_oldStorage.getAll(target);
				merge.addAll(more);

				all.clear();
				all.addAll(merge);

				Collections.sort(all);

				// subset by position
				if (first < 1) first = 1;
				if (last >= all.size()) last = all.size();

				all = all.subList(first - 1, last);
				return all;
			}

			List all = super.getSelectedResources("TARGET = ?", fields, first, last);
			return all;
		}

		public AliasEdit put(String id)
		{
			// check for already exists (new or old)
			if (check(id)) return null;

			BaseAliasEdit rv = (BaseAliasEdit) super.putResource(id, fields(id, null, false));
			if (rv != null) rv.activate();
			return rv;
		}

		public AliasEdit edit(String id)
		{
			BaseAliasEdit rv = (BaseAliasEdit) super.editResource(id);

			// if not found, try from the old (convert to the new)
			if (m_checkOld && (rv == null))
			{
				// this locks the old table/record
				rv = (BaseAliasEdit) m_oldStorage.edit(id);
				if (rv != null)
				{
					// create the record in new, also locking it into an edit
					rv = (BaseAliasEdit) super.putResource(id, fields(id, rv, false));

					// delete the old record
					m_oldStorage.remove(rv);
				}
			}

			if (rv != null) rv.activate();
			return rv;
		}

		public void commit(AliasEdit edit)
		{
			super.commitResource(edit, fields(edit.getId(), edit, true), edit.getProperties());
		}

		public void cancel(AliasEdit edit)
		{
			super.cancelResource(edit);
		}

		public void remove(AliasEdit edit)
		{
			super.removeResource(edit);
		}

		public List search(String criteria, int first, int last)
		{
			// if we have to be concerned with old stuff, we cannot let the db do the search
			if (m_checkOld)
			{
				List all = getAll();
				List rv = new Vector();

				for (Iterator i = all.iterator(); i.hasNext();)
				{
					Alias a = (Alias) i.next();
					if (StringUtil.containsIgnoreCase(a.getId(), criteria)
							|| StringUtil.containsIgnoreCase(a.getTarget(), criteria))
					{
						rv.add(a);
					}
				}

				Collections.sort(rv);

				// subset by position
				if (first < 1) first = 1;
				if (last >= rv.size()) last = rv.size();

				rv = rv.subList(first - 1, last);

				return rv;
			}

			Object[] fields = new Object[2];
			fields[0] = "%" + criteria + "%";
			fields[1] = fields[0];
			List all = super.getSelectedResources("UPPER(ALIAS_ID) LIKE UPPER(?) OR UPPER(TARGET) LIKE UPPER(?)", fields, first,
					last);

			return all;
		}

		public int countSearch(String criteria)
		{
			// if we have to be concerned with old stuff, we cannot let the db do the search and count
			if (m_checkOld)
			{
				List all = getAll();
				List rv = new Vector();

				for (Iterator i = all.iterator(); i.hasNext();)
				{
					Alias a = (Alias) i.next();
					if (StringUtil.containsIgnoreCase(a.getId(), criteria)
							|| StringUtil.containsIgnoreCase(a.getTarget(), criteria))
					{
						rv.add(a);
					}
				}

				return rv.size();
			}

			Object[] fields = new Object[2];
			fields[0] = "%" + criteria + "%";
			fields[1] = fields[0];
			int rv = super.countSelectedResources("UPPER(ALIAS_ID) LIKE UPPER(?) OR UPPER(TARGET) LIKE UPPER(?)", fields);

			return rv;
		}

		/**
		 * Read properties from storage into the edit's properties.
		 * 
		 * @param edit
		 *        The user to read properties for.
		 */
		public void readProperties(AliasEdit edit, ResourcePropertiesEdit props)
		{
			super.readProperties(edit, props);
		}

		/**
		 * Get the fields for the database from the edit for this id, and the id again at the end if needed
		 * 
		 * @param id
		 *        The resource id
		 * @param edit
		 *        The edit (may be null in a new)
		 * @param idAgain
		 *        If true, include the id field again at the end, else don't.
		 * @return The fields for the database.
		 */
		protected Object[] fields(String id, AliasEdit edit, boolean idAgain)
		{
			Object[] rv = new Object[idAgain ? 7 : 6];
			rv[0] = caseId(id);
			if (idAgain)
			{
				rv[6] = rv[0];
			}

			if (edit == null)
			{
				String current = sessionManager().getCurrentSessionUserId();
				if (current == null) current = "";

				Time now = timeService().newTime();
				rv[1] = "";
				rv[2] = current;
				rv[3] = current;
				rv[4] = now;
				rv[5] = now;
			}

			else
			{
				rv[1] = edit.getTarget();
				ResourceProperties props = edit.getProperties();
				rv[2] = StringUtil.trimToZero(((BaseAliasEdit) edit).m_createdUserId);
				rv[3] = StringUtil.trimToZero(((BaseAliasEdit) edit).m_lastModifiedUserId);
				rv[4] = edit.getCreatedTime();
				rv[5] = edit.getModifiedTime();
			}

			return rv;
		}

		/**
		 * Read from the result one set of fields to create a Resource.
		 * 
		 * @param result
		 *        The Sql query result.
		 * @return The Resource object.
		 */
		public Object readSqlResultRecord(ResultSet result)
		{
			try
			{
				String id = result.getString(1);
				String target = result.getString(2);
				String createdBy = result.getString(3);
				String modifiedBy = result.getString(4);
				Time createdOn = timeService().newTime(result.getTimestamp(5, sqlService().getCal()).getTime());
				Time modifiedOn = timeService().newTime(result.getTimestamp(6, sqlService().getCal()).getTime());

				// create the Resource from these fields
				return new BaseAliasEdit(id, target, createdBy, createdOn, modifiedBy, modifiedOn);
			}
			catch (SQLException ignore)
			{
				return null;
			}
		}

	} // DbStorage

	/**
	 * This is how to access the old chef_alias table (CTools through 2.0.7)
	 */
	protected class DbStorageOld extends BaseDbSingleStorage implements Storage
	{
		/**
		 * Construct.
		 * 
		 * @param user
		 *        The StorageUser class to call back for creation of Resource and Edit objects.
		 */
		public DbStorageOld(StorageUser user)
		{
			super("CHEF_ALIAS", "ALIAS_ID", null, false, "alias", user, sqlService());
			setCaseInsensitivity(true);

		} // DbStorage

		public boolean check(String id)
		{
			return super.checkResource(id);
		}

		public AliasEdit get(String id)
		{
			return (AliasEdit) super.getResource(id);
		}

		public List getAll(int first, int last)
		{
			return super.getAllResources(first, last);
		}

		public List getAll()
		{
			return super.getAllResources();
		}

		public int count()
		{
			int rv = super.countAllResources();

			// if we find no more records in the old table, we can start ignoring it...
			// Note: this means once they go away they cannot come back (old versions cannot run in the cluster
			// and write to the old cluster table). -ggolden
			if (rv == 0)
			{
				m_checkOld = false;
				M_log.info(" ** starting to ignore old");
			}
			return rv;
		}

		public AliasEdit put(String id)
		{
			return (AliasEdit) super.putResource(id, null);
		}

		public AliasEdit edit(String id)
		{
			return (AliasEdit) super.editResource(id);
		}

		public void commit(AliasEdit edit)
		{
			super.commitResource(edit);
		}

		public void cancel(AliasEdit edit)
		{
			super.cancelResource(edit);
		}

		public void remove(AliasEdit edit)
		{
			super.removeResource(edit);
		}

		public List getAll(String target)
		{
			List all = super.getAllResources();

			// pick out from all those that are for this target
			List found = new Vector();
			for (Iterator iAll = all.iterator(); iAll.hasNext();)
			{
				BaseAliasEdit a = (BaseAliasEdit) iAll.next();
				if (a.getTarget().equals(target)) found.add(a);
			}

			return found;
		}

		public List getAll(String target, int first, int last)
		{
			List all = super.getAllResources();

			// pick out from all those that are for this target
			List found = new Vector();
			for (Iterator iAll = all.iterator(); iAll.hasNext();)
			{
				BaseAliasEdit a = (BaseAliasEdit) iAll.next();
				if (a.getTarget().equals(target)) found.add(a);
			}

			// sort for position check
			Collections.sort(found);

			// subset by position
			if (first < 1) first = 1;
			if (last >= found.size()) last = found.size();

			found = found.subList(first - 1, last);

			return found;
		}

		/**
		 * Search for aliases with id or target matching criteria, in range.
		 * 
		 * @param criteria
		 *        The search criteria.
		 * @param first
		 *        The first record position to return.
		 * @param last
		 *        The last record position to return.
		 * @return The List (BaseAliasEdit) of all alias.
		 */
		public List search(String criteria, int first, int last)
		{
			List all = super.getAllResources();

			List rv = new Vector();
			for (Iterator i = all.iterator(); i.hasNext();)
			{
				Alias a = (Alias) i.next();
				if (StringUtil.containsIgnoreCase(a.getId(), criteria) || StringUtil.containsIgnoreCase(a.getTarget(), criteria))
				{
					rv.add(a);
				}
			}

			Collections.sort(rv);

			// subset by position
			if (first < 1) first = 1;
			if (last >= rv.size()) last = rv.size();

			rv = rv.subList(first - 1, last);

			return rv;
		}

		/**
		 * Count all the aliases with id or target matching criteria.
		 * 
		 * @param criteria
		 *        The search criteria.
		 * @return The count of all aliases with id or target matching criteria.
		 */
		public int countSearch(String criteria)
		{
			List all = super.getAllResources();

			Vector rv = new Vector();
			for (Iterator i = all.iterator(); i.hasNext();)
			{
				Alias a = (Alias) i.next();
				if (StringUtil.containsIgnoreCase(a.getId(), criteria) || StringUtil.containsIgnoreCase(a.getTarget(), criteria))
				{
					rv.add(a);
				}
			}

			return rv.size();
		}

		/**
		 * Read properties from storage into the edit's properties.
		 * 
		 * @param edit
		 *        The user to read properties for.
		 */
		public void readProperties(AliasEdit edit, ResourcePropertiesEdit props)
		{
			M_log.warn("readProperties: should not be called.");
		}
	}

	/**
	 * Create a new table record for all old table records found, and delete the old.
	 */
	protected void convertOld()
	{
		M_log.info("convertOld");

		try
		{
			// get a connection
			final Connection connection = sqlService().borrowConnection();
			boolean wasCommit = connection.getAutoCommit();
			connection.setAutoCommit(false);

			// read all alias ids
			String sql = "select ALIAS_ID, XML from CHEF_ALIAS";
			sqlService().dbRead(connection, sql, null, new SqlReader()
			{
				private int count = 0;

				public Object readSqlResultRecord(ResultSet result)
				{
					try
					{
						// create the Resource from the db xml
						String id = result.getString(1);
						String xml = result.getString(2);

						// read the xml
						Document doc = Xml.readDocumentFromString(xml);

						// verify the root element
						Element root = doc.getDocumentElement();
						if (!root.getTagName().equals("alias"))
						{
							M_log.warn("convertOld: XML root element not alias: " + root.getTagName());
							return null;
						}
						AliasEdit a = new BaseAliasEdit(root);

						// pick up the fields
						Object[] fields = ((DbStorage) m_storage).fields(id, a, false);

						// insert the record
						boolean ok = ((DbStorage) m_storage).insertResource(id, fields, connection);
						if (!ok)
						{
							M_log.warn("convertOld: failed to insert: " + id);
						}

						// delete the old record
						String statement = "delete from CHEF_ALIAS where ALIAS_ID = ?";
						fields = new Object[1];
						fields[0] = id;
						ok = sqlService().dbWrite(connection, statement, fields);
						if (!ok)
						{
							M_log.warn("convertOld: failed to delete: " + id);
						}

						// m_logger.info(" ** alias converted: " + id);

						return null;
					}
					catch (Throwable ignore)
					{
						return null;
					}
				}
			});

			connection.commit();
			connection.setAutoCommit(wasCommit);
			sqlService().returnConnection(connection);
		}
		catch (Throwable t)
		{
			M_log.warn("convertOld: failed: " + t);
		}

		M_log.info("convertOld: done");
	}

}
