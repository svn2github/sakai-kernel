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

package org.sakaiproject.content.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * EntityCounter
 *
 */
public class EntityCounter
{
	protected Map<String, Integer> values = new HashMap<String, Integer>();
	public void decrement(String key)
	{
		Integer val = values.get(key);
		if(val == null)
		{
			values.put(key, new Integer(-1));
		}
		else
		{
			values.put(key, new Integer(val.intValue() - 1));
		}
	}
	
	public int getValue(String key)
	{
		Integer val = values.get(key);
		if(val == null)
		{
			val = new Integer(0);
		}
		return val.intValue();
	}
	
	public void increment(String key)
	{
		Integer val = values.get(key);
		if(val == null)
		{
			values.put(key, new Integer(1));
		}
		else
		{
			values.put(key, new Integer(val.intValue() + 1));
		}
	}
}