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

package org.sakaiproject.util;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * <p>
 * IteratorEnumeration is an enumeration over an iterator.
 * </p>
 */
public class IteratorEnumeration implements Enumeration
{
	/** The iterator over which this enumerates. */
	protected Iterator m_iterator = null;

	public IteratorEnumeration(Iterator i)
	{
		m_iterator = i;
	}

	public boolean hasMoreElements()
	{
		return m_iterator.hasNext();
	}

	public Object nextElement()
	{
		return m_iterator.next();
	}
}
