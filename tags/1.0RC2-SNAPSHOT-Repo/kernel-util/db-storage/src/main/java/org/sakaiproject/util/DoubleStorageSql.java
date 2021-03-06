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

import org.sakaiproject.javax.PagingPosition;

/**
 * database methods.
 */
public interface DoubleStorageSql
{
	public String getDeleteSql(String table, String idField);

	public String getDelete2Sql(String table, String idField1, String idField2);

	public String getDeleteLocksSql();

	public String getInsertSql(String table, String fieldList);

	public String getInsertSql2();

	public String getInsertSql3(String table, String fieldList, String params);

	public String getRecordId(String recordId);
    
    public String getCountSql(String table, String idField);

	public String getSelect1Sql(String table, String idField);

	public String getSelect9Sql(String table, String idField);

	public String getSelectIdSql(String table, String idField1, String idField2);

	public String getSelectXml1Sql(String table);

	public String getSelectXml2Sql(String table, String idField);

	public String getSelectXml3Sql(String table, String idField, String ref);

	public String getSelectXml4Sql(String table, String idField1, String idField2);

	public String getSelectXml5Sql(String table, String idField, String orderField, boolean asc);
   
	public String getSelectXml5filterSql(String table, String idField, String orderField, boolean asc, String filter);

	public String getSelectXml6Sql(String table, String idField1, String idField2, String id, String ref);

	public String getUpdateSql(String table, String idField);

	public String getUpdate2Sql(String table, String idField1, String idField2, String fieldList);

	public String addLimitToQuery(String inSql, int startRec, int endRec);

	public String addTopToQuery(String inSql, int endRec);
        
}
