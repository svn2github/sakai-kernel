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

package org.sakaiproject.event.api;

/**
 * <p>
 * Event represents a single significant activity by the end-user.
 * </p>
 */
public interface Event
{
	/**
	 * Enumerations that implement this contain event types
	 * @author ieb
	 *
	 */
	public interface Type {

	}

	/**
	 * Access the event id string
	 * 
	 * @return The event id string.
	 */
	String getEvent();

	/**
	 * Access the resource reference.
	 * 
	 * @return The resource reference string.
	 */
	String getResource();

	/**
	 * Access the UsageSession id. If null, check for a User id.
	 * 
	 * @return The UsageSession id string.
	 */
	String getSessionId();

	/**
	 * Access the User id. If null, check for a session id.
	 * 
	 * @return The User id string.
	 */
	String getUserId();

	/**
	 * Is this event one that caused a modify to the resource, or just an access.
	 * 
	 * @return true if the event caused a modify to the resource, false if it was just an access.
	 */
	boolean getModify();

	/**
	 * Access the event's notification priority.
	 * 
	 * @return The event's notification priority.
	 */
	int getPriority();
}
