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

package org.sakaiproject.db.impl;

import java.sql.SQLException;

/**
 * methods for accessing sql service methods in an oracle database.
 */
public class BasicSqlServiceSqlOracle extends BasicSqlServiceSqlDefault
{
	/**
	 * returns whether the the database supports reading and updating blobs.
	 */
	public boolean canReadAndUpdateBlob()
	{
		return true;
	}

	/**
	 * returns whether the sql exception indicates that a record already exists in a table.
	 */
	public boolean getRecordAlreadyExists(SQLException ex)
	{
		return ex.getErrorCode() == 1;
	}

	/**
	 * returns the sql statement which returns the next number in a sequence. <br/><br/>
	 * 
	 * @param table
	 *        name of table to read the sequence number from.
	 */
	public String getNextSequenceSql(String table)
	{
		return "SELECT " + table + ".NEXTVAL FROM DUAL";
	}
}
