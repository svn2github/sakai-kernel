/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2007 Timefields ltd.
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

package org.sakaiproject.jcr.jackrabbit.sakai;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.jackrabbit.core.persistence.db.DatabasePersistenceManager;
import org.sakaiproject.component.cover.ComponentManager;

/**
 * @author ieb
 */
public class SakaiPersistanceManager extends DatabasePersistenceManager
{

	private DataSource dataSource = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.jackrabbit.core.persistence.db.DatabasePersistenceManager#getConnection()
	 */
	@Override
	protected Connection getConnection() throws Exception
	{
		if (dataSource == null)
		{
			dataSource = (DataSource) ComponentManager.get(DataSource.class.getName());
			if (dataSource == null)
			{
				throw new IllegalStateException(
						"Unable to locate DataSource for DatabasePersistanceManager ");
			}
		}
		return dataSource.getConnection();
	}

}
