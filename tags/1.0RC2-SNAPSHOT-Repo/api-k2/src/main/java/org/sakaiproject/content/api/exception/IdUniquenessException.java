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

package org.sakaiproject.content.api.exception;

/**
 * <p>
 * IdUniquenessException is thrown whenever a method that is attempting to find a unique resource id as a variation on a requested resource id cannot find a unique id before a specified limit is reached.
 * </p>
 * <p>
 * A string indicating the final attempt to find a unique id is available as part of the exception.
 * </p>
 */
public class IdUniquenessException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8727171005095104308L;
	private String m_id = null;

	public IdUniquenessException(String id)
	{
		m_id = id;
	}

	public String toString()
	{
		return super.toString() + " id=" + m_id;
	}

	public String getMessage()
	{
		return m_id;
	}

}
