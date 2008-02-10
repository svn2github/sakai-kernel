/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007 The Sakai Foundation.
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

package org.sakaiproject.util;

import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.db.api.SqlReader;

/**
 * @author ieb
 */
public class DbSingleStorageReader implements SqlReader
{

	private static final Log log = LogFactory.getLog(DbSingleStorageReader.class);

	private DbSingleStorage storage;

	public DbSingleStorageReader(DbSingleStorage storage)
	{
		this.storage = storage;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sakaiproject.db.api.SqlReader#readSqlResultRecord(java.sql.ResultSet)
	 */
	public Object readSqlResultRecord(ResultSet result)
	{
		try
		{
			if (storage instanceof BaseDbBinarySingleStorage)
			{
				return ((BaseDbBinarySingleStorage) storage).readResource(result
						.getBytes(1));
			}
			else if (storage instanceof BaseDbDualSingleStorage)
			{
				return ((BaseDbDualSingleStorage) storage).readResource(result
						.getString(1), result.getBytes(2));
			}
			else
			{
				return ((BaseDbSingleStorage) storage).readResource(result.getString(1));
			}
		}
		catch (Exception ex)
		{
			log.error("Failed to read entity Record ");
		}
		return null;
	}
}
