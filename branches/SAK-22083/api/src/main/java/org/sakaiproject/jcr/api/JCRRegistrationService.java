/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007, 2008 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.opensource.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.jcr.api;

import java.io.InputStream;

/**
 * This allows other projects outside the Sakai JCR Package to add nodetypes in
 * the backing implementations xml format, as well as register additional
 * namespaces.
 * 
 * @author Steve Githens
 */
public interface JCRRegistrationService
{

	/**
	 * Redister node types using an XML input stream
	 * @param xml
	 */
	public void registerNodetypes(InputStream xml);

	/**
	 * Register a namespace.
	 * You should register all namespaces in your input stream prior to 
	 * registering the node types
	 * @param prefix
	 * @param url
	 */
	public void registerNamespace(String prefix, String url);

}
