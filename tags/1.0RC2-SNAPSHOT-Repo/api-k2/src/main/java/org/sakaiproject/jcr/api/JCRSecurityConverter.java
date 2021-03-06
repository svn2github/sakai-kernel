/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c)  2007 Timefields Ltd.
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

package org.sakaiproject.jcr.api;

/**
 * @author ieb
 */
public interface JCRSecurityConverter
{

	/**
	 * @param lock
	 * @param internalPath
	 * @return
	 */
	String convertLock(String lock, String internalPath);

	/**
	 * @param internalPath
	 * @return
	 */
	String convertRealm(String internalPath);

}
