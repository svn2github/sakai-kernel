/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright 2007, 2008 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 *
 *       http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.util;


/**
 * methods for accessing single storage data in an oracle database.
 */
public class MultiSingleStorageSqlOracle extends MultiSingleStorageSqlDefault
{
	/**
	 * @param storage_fields
	 */
	public MultiSingleStorageSqlOracle(String storage_fields)
	{
		super(storage_fields);
	}

	/**
	 * returns the sql statement which retrieves the xml field from the specified table and limits the result set.
	 */
	public String getXmlSql(String field, String table, int first, int last)
	{
		return "select "+storageFields+" from (select "+storageFields+" , RANK() OVER (order by " + field + ") as rank from " + table + " order by " + field
				+ " asc) where rank between ? and ?";
	}

	/**
	 * returns the SQL statement which retrieves a limited number of rows selected by a particular value for a specified field 
	 * (the selectBy parameter) and returned in ascending order by another specified field (the orderBy parameter).  The limit
	 * on the number of rows is specified by values for the first item to be retrieved (indexed from 0) and the maxCount.
	 * @param selectBy The name of a field to be used in a where clause with the value provided separately.
	 * @param orderBy The name of a field to be used in an order-by clause
	 * @param tableName The table on which the query is to operate
	 * @param first A non-negative integer indicating the first record to return, indexed from 0.
	 * @param maxCount A positive integer indicating the maximum number of records to return
	 * */
	public String getXmlWhereLimitSql(String selectBy, String orderBy, String tableName, int first, int maxCount) 
	{
		// consider using RANK() as in getXmlSql(String field, String table, int first, int last) above
		// if this has performance issues
		return "select * from ( select yrqr.*, rownum rnum from ( select " + storageFields + " from " + tableName 
			+ " where ( " + selectBy + " = ? ) order by " + orderBy + " asc) yrqr where rownum <= " 
			+ (first + maxCount) + ") where rnum > " + first;
	}

	/**
	 * returns an array of objects needed for the getXmlSql statement with limits.
	 */
	public Object[] getXmlFields(int first, int last)
	{
		Object[] fields = new Object[2];
		fields[0] = new Long(first);
		fields[1] = new Long(last);

		return fields;
	}
}
