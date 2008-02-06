/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2007 The Sakai Foundation.
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * methods for accessing flat storage data in a mysql database.
 */
public class FlatStorageSqlMySql extends FlatStorageSqlDefault
{

	public String getIdField(String table)
	{
		return "";
	}

	public String getRecordId(String recordId)
	{
		return (recordId == null ? "null" : recordId.hashCode() + " - " + recordId);
	}

	public String getSelectFieldsSql1(String table, String fieldList, String idField, String sortField1, String sortField2, int begin, int end)
	{
		return "select " + fieldList + " from " + table + " order by " + table + "." + sortField1
				+ (sortField2 == null ? "" : ", " + table + "." + sortField2) + " limit " + end + " offset " + begin;
	}

	public String getSelectFieldsSql3(String table, String fieldList, String idField, String sortField1, String sortField2, int begin, int end,
			String join, String where, String order)
	{
		return "select " + fieldList + " from " + table + ((join == null) ? "" : ("," + join))
				+ (((where != null) && (where.length() > 0)) ? (" where " + where) : "") + " order by " + order + "," + table + "." + sortField1
				+ (sortField2 == null ? "" : "," + table + "." + sortField2) + " limit " + end + " offset " + begin;
	}
}
