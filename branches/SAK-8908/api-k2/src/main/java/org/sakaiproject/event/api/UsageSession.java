/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright 2003, 2004, 2005, 2006, 2008 Sakai Foundation
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

package org.sakaiproject.event.api;

import org.sakaiproject.time.api.Time;

/**
 * <p>
 * UsageSession models an end user's usage session.
 * </p>
 */
public interface UsageSession extends Comparable {
	/** String constants for major browser types */
	public static enum UserAgent {
		MAC_IE("Mac-InternetExplorer"),

		MAC_NN("Mac-NetscapeNavigator"),

		MAC_CM("Mac-Camino"),

		MAC_SF("Mac-Safari"),

		MAC_MZ("Mac-Mozilla"),

		WIN_IE("Win-InternetExplorer"),

		WIN_NN("Win-NetscapeNavigator"),

		WIN_MZ("Win-Mozilla"),

		LIN_MZ("Lin-Mozilla"),

		UNKNOWN("UnknownBrowser");

		private final String userAgent;

		private UserAgent(String userAgent) {
			this.userAgent = userAgent;
		}

		@Override
		public String toString() {
			return userAgent;
		}

	}

	/**
	 * Access the unique id for this session.
	 * 
	 * @return the unique id for this session.
	 */
	String getId();

	/**
	 * Access the server id which is hosting this session.
	 * 
	 * @return the server id which is hosting this session.
	 */
	String getServer();

	/**
	 * Access the user id for this session.
	 * 
	 * @return the user id for this session.
	 */
	String getUserId();

	/**
	 * Access the user eid for this session, if known - fallback to the id if
	 * not.
	 * 
	 * @return The user eid for this session, or the use id if the eid cannot be
	 *         found.
	 */
	String getUserEid();

	/**
	 * Access the user display id for this session, if known - fallback to the
	 * id if not.
	 * 
	 * @return The user display id for this session, or the use id if the user
	 *         cannot be found.
	 */
	String getUserDisplayId();

	/**
	 * Access the IP Address from which this session originated.
	 * 
	 * @return the IP Address from which this session originated.
	 */
	String getIpAddress();

	/**
	 * Access the User Agent string describing the browser used in this session.
	 * 
	 * @return the User Agent string describing the browser used in this
	 *         session.
	 */
	String getUserAgent();

	/**
	 * Access a short string describing the class of browser used in this
	 * session.
	 * 
	 * @return the short ID describing the browser used in this session.
	 */
	String getBrowserId();

	/**
	 * Is this session closed?
	 * 
	 * @return true if the session is closed, false if open.
	 */
	boolean isClosed();

	/**
	 * Access the start time of the session
	 * 
	 * @return The time the session started.
	 */
	Time getStart();

	/**
	 * Access the end time of the session.
	 * 
	 * @return The time the session ended. If still going, this will .equals()
	 *         the getStart() value.
	 */
	Time getEnd();
}
