/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2006 The Sakai Foundation.
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

package org.sakaiproject.event.impl;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.db.api.SqlService;
import org.sakaiproject.event.api.UsageSessionService;
import org.sakaiproject.time.api.TimeService;
import org.sakaiproject.tool.api.SessionManager;

/**
 * <p>
 * EventTrackingTest extends the cluster event tracking service providing the dependency injectors for testing.
 * </p>
 */
public class EventTrackingTest extends ClusterEventTracking
{
	/**
	 * @return the UsageSessionService collaborator.
	 */
	protected UsageSessionService usageSessionService()
	{
		return null;
	}

	/**
	 * @return the SessionManager collaborator.
	 */
	protected SessionManager sessionManager()
	{
		return null;
	}

	/**
	 * @return the MemoryService collaborator.
	 */
	protected SqlService sqlService()
	{
		return null;
	}

	/**
	 * @return the ServerConfigurationService collaborator.
	 */
	protected ServerConfigurationService serverConfigurationService()
	{
		return null;
	}

	/**
	 * @return the TimeService collaborator.
	 */
	protected TimeService timeService()
	{
		return null;
	}
}
