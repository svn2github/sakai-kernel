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

package org.sakaiproject.memory.api;

/**
 * <p>
 * MemoryService is the interface for the Sakai Memory service.
 * </p>
 * <p>
 * This tracks memory users (cachers), runs a periodic garbage collection to keep memory available, and can be asked to report memory usage.
 * </p>
 */
public interface MemoryService
{
	/**
	 * Report the amount of available memory.
	 *
	 * @return the amount of available memory.
	 */
	long getAvailableMemory();

	/**
	 * Cause less memory to be used by clearing any optional caches.
	 *
	 * @throws SecurityException if the current user does not have permission to do this.
	 */
	void resetCachers();

	/**
	 * Evict all expired objects from the in-memory caches
	 *
	 * @throws SecurityException if the current user does not have permission to do this.
	 */
	void evictExpiredMembers();

	/**
	 * Construct a Cache. Attempts to keep complete on Event notification by calling the refresher.
	 *
	 * @param cacheName Load a defined bean from ComponentManager or create a default cache with this name.
	 * @param refresher
	 *        The object that will handle refreshing of event notified modified or added entries.
	 * @param pattern
	 *        The "startsWith()" string for all resources that may be in this cache - if null, don't watch events for updates.
	 */
	Cache newCache(String cacheName, CacheRefresher refresher, String pattern); // used in NotificationCache, AssignmentService(3), BaseContentService, BaseMessage(3)

	/**
	 * Construct a Cache. Attempts to keep complete on Event notification by calling the refresher.
	 *
	 * @param cacheName Load a defined bean from ComponentManager or create a default cache with this name.
	 * @param pattern
	 *        The "startsWith()" string for all resources that may be in this cache - if null, don't watch events for updates.
	 */
	Cache newCache(String cacheName, String pattern); // used in BaseAliasService, SiteCacheImpl, BaseUserDirectoryService (2), BaseCalendarService(3), ShareUserCacheImpl

	/**
	 * Construct a Cache. No automatic refresh handling.
	 * @param cacheName Load a defined bean from ComponentManager or create a defaulted cache with this name.
	 */
	Cache newCache(String cacheName);

	/**
	 * Construct a multi-ref Cache. No automatic refresh: expire only, from time and events.
	 *
	 * @param cacheName Load a defined bean from ComponentManager or create a default cache with this name.
	 */
	GenericMultiRefCache newGenericMultiRefCache(String cacheName);

	/**
	 * Get a status report of memory users.
	 *
	 * @return A status report of memory users.
	 */
	String getStatus();

}
