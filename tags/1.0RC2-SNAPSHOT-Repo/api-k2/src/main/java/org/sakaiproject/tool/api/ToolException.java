/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2005, 2006 The Sakai Foundation.
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

package org.sakaiproject.tool.api;

import javax.servlet.ServletException;

/**
 * <p>
 * ToolException is the ServletException that Sakai Tools throw (or use to wrap) when they run into throw-up-my-hands trouble.
 * </p>
 */
public class ToolException extends ServletException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7463051749220985926L;

	public ToolException()
	{
		super();
	}

	public ToolException(String message)
	{
		super(message);
	}

	public ToolException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ToolException(Throwable cause)
	{
		super(cause);
	}
}
