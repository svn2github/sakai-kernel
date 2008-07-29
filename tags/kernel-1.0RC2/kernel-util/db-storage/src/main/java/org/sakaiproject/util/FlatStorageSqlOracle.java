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
 * methods for accessing flat storage data in an oracle database.
 */
public class FlatStorageSqlOracle extends FlatStorageSqlDefault
{
	public String getIdField(String table)
	{
		return "," + table + "_SEQ.NEXTVAL";
	}

	public String getSelectFieldsSql1(String table, String fieldList, String idField, String sortField1, String sortField2, int begin, int end)
	{
		return "select * from (select " + fieldList + " , RANK() OVER (order by " + table + "." + sortField1
				+ (sortField2 == null ? "" : "," + table + "." + sortField2) + "," + table + "." + idField + ") as rank from " + table + " order by "
				+ table + "." + sortField1 + (sortField2 == null ? "" : "," + table + "." + sortField2) + "," + table + "." + idField
				+ ") where rank between ? and ?";
	}

	public String getSelectFieldsSql3(String table, String fieldList, String idField, String sortField1, String sortField2, int begin, int end,
			String join, String where, String order)
	{
		return "select * from (select " + fieldList + " ,RANK() OVER (order by " + order + "," + table + "." + idField + ") as rank" + " from "
				+ table + ((join == null) ? "" : ("," + join)) + (((where != null) && (where.length() > 0)) ? (" where " + where) : "")
				+ " order by " + order + "," + table + "." + idField + " ) where rank between ? and ?";
	}

	public Object[] getSelectFieldsFields(int first, int last)
	{
		Object[] fields = new Object[2];

		fields[0] = new Long(first);
		fields[1] = new Long(last);

		return fields;
	}
}
