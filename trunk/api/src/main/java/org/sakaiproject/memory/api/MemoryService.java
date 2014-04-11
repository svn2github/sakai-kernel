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

import java.util.Properties;

/**
 * MemoryService is the primary interface for the Sakai caching management system
 * This allows for cache management and can be thought of as the Sakai CacheManager
 *
 * Based on https://github.com/jsr107/jsr107spec/blob/master/src/main/java/javax/cache/CacheManager.java
 * See https://jira.sakaiproject.org/browse/KNL-1162
 * Send questions to Aaron Zeckoski
 * @author Aaron Zeckoski (azeckoski @ unicon.net) (azeckoski @ gmail.com)
 */
public interface MemoryService // CacheManager
{
    //JSR-107 CachingProvider getCachingProvider();
    //JSR-107 URI getURI();

    /**
     * Get the {@link ClassLoader} used by the CacheManager.
     *
     * @return  the {@link ClassLoader} used by the CacheManager
     */
    ClassLoader getClassLoader();

    /**
     * Get the {@link java.util.Properties} that were used to create this CacheManager.
     * <p>
     * Implementations are not required to re-configure the
     * CacheManager should modifications to the returned
     * {@link java.util.Properties} be made.
     *
     * @return the Properties used to create the CacheManager
     */
    Properties getProperties();

    //<K, V, C extends Configuration<K, V>> Cache createCache(String cacheName, C configuration) throws IllegalArgumentException;
    //<K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType, Class<V> valueType);

    /**
     * Looks up a managed {@link Cache} given its name.
     * <p>
     * This method may only be used to acquire {@link Cache}s that were
     * configured without runtime key and value types, or were configured
     * to use Object.class key and value types.
     * <p>
     * Use the getCache(String, Class, Class) method to acquire
     * {@link Cache}s that were configured with specific runtime types.
     * <p>
     * Implementations must check if key and value types were configured
     * for the requested {@link Cache}. If either the keyType or valueType of the
     * configured {@link Cache} were specified (other than <code>Object.class</code>)
     * an {@link IllegalArgumentException} will be thrown.
     * <p>
     * Implementations that support declarative mechanisms for pre-configuring
     * {@link Cache}s may return a pre-configured {@link Cache} instead of
     * <code>null</code>.
     *
     * @param cacheName the name of the cache to look for
     * @return the Cache or null if it does exist or can't be pre-configured
     * @throws IllegalStateException    if the CacheManager is closed
     * @throws IllegalArgumentException if the {@link Cache} was configured with
     *                                  specific types, this method cannot be used
     * @throws SecurityException        when the operation could not be performed
     *                                  due to the current security settings
     */
    Cache getCache(String cacheName); // <K, V> Cache<K, V> getCache(String cacheName);

    /**
     * Obtains an {@link Iterable} over the names of {@link Cache}s managed by the
     * CacheManager.
     * <p>
     * {@link java.util.Iterator}s returned by the {@link Iterable} are immutable.
     * Any modification of the {@link java.util.Iterator}, including remove, will
     * raise an {@link IllegalStateException}.  If the {@link Cache}s managed by
     * the CacheManager change, the {@link Iterable} and
     * associated {@link java.util.Iterator}s are not affected.
     * <p>
     * {@link java.util.Iterator}s returned by the {@link Iterable} may not provide
     * all of the {@link Cache}s managed by the CacheManager.  For example:
     * Internally defined or platform specific {@link Cache}s that may be accessible
     * by a call to {@link #getCache(String)} or getCache(String, Class,
     * Class) may not be present in an iteration.
     *
     * @return an {@link Iterable} over the names of managed {@link Cache}s.
     * @throws IllegalStateException if the CacheManager is closed
     * @throws SecurityException     when the operation could not be performed
     *                               due to the current security settings
     */
    Iterable<String> getCacheNames();

    /**
     * Destroys a specifically named and managed {@link Cache}.  Once destroyed
     * a new {@link Cache} of the same name but with a different
     * Configuration may be configured.
     * <p>
     * This is equivalent to the following sequence of method calls:
     * <ol>
     * <li>{@link Cache#clear()}</li>
     * <li>{@link Cache#close()}</li>
     * </ol>
     * followed by allowing the name of the {@link Cache} to be used for other
     * {@link Cache} configurations.
     * <p>
     * From the time this method is called, the specified {@link Cache} is not
     * available for operational use. An attempt to call an operational method on
     * the {@link Cache} will throw an {@link IllegalStateException}.
     *
     * @param cacheName the cache to destroy
     * @throws IllegalStateException if the CacheManager is closed
     * @throws NullPointerException  if cacheName is null
     * @throws SecurityException     when the operation could not be performed
     *                               due to the current security settings
     */
    void destroyCache(String cacheName);

    //void enableManagement(String cacheName, boolean enabled);
    //void enableStatistics(String cacheName, boolean enabled);
    //void close();
    //boolean isClosed();

    /**
     * Provides a standard mechanism to access the underlying concrete caching
     * implementation to provide access to further, proprietary features.
     * <p>
     * If the provider's implementation does not support the specified class,
     * the {@link IllegalArgumentException} is thrown.
     *
     * @param clazz the proprietary class or interface of the underlying concrete
     *              CacheManager. It is this type that is returned.
     * @return an instance of the underlying concrete CacheManager
     * @throws IllegalArgumentException if the caching provider doesn't support the
     *                                  specified class.
     * @throws SecurityException        when the operation could not be performed
     *                                  due to the current security settings
     */
    <T> T unwrap(java.lang.Class<T> clazz);


    // SAKAI specific methods (non JSR-107)

    /**
     * Report the amount of unused and available memory for the JVM
     *
     * @return the amount of available memory.
     */
    public long getAvailableMemory();

    /**
     * Cause less memory to be used by clearing any and all caches.
     *
     * @throws SecurityException if the current user is not a super admin
     */
    void resetCachers();

    /**
     * Evict all expired objects from the in-memory caches
     *
     * @throws SecurityException if the current user is not a super admin
     */
    void evictExpiredMembers();

    /**
     * Construct a Cache with the given name (often this is the fully qualified classpath of the api
     * for the service that is being cached or the class if there is no api) or retrieve the one
     * that already exists with this name,
     * this will operate on system defaults
     *
     * @param cacheName Load a defined bean from the application context with this name or create a default cache with this name
     * @return a cache which can be used to store objects
     * @see #getCache(String)
     */
    public Cache newCache(String cacheName);

    /**
     * Get a status report of memory cache usage
     * @return A string representing the current status of all caches
     */
    public String getStatus();


    // DEPRECATED METHODS BELOW - Remove for Sakai 11

    /**
     * Construct a Cache. Attempts to keep complete on Event notification by calling the refresher.
     *
     * @param cacheName Load a defined bean from ComponentManager or create a default cache with this name.
     * @param refresher
     *        The object that will handle refreshing of event notified modified or added entries.
     * @param pattern
     *        The "startsWith()" string for all resources that may be in this cache - if null, don't watch events for updates.
     *        If this is set then it enables automatic removal of the matching cache entry key (to the event reference value)
     *        when the event reference starts with this pattern string.
     * @deprecated since Sakai 2.9, pattern matching no longer needed or supported, 07/Oct/2007 -AZ
     */
    @SuppressWarnings("deprecation") // TODO remove this
    Cache newCache(String cacheName, CacheRefresher refresher, String pattern); // used in NotificationCache, AssignmentService(3), BaseContentService, BaseMessage(3)

    /**
     * Construct a Cache. Attempts to keep complete on Event notification by calling the refresher.
     *
     * @param cacheName Load a defined bean from ComponentManager or create a default cache with this name.
     * @param pattern
     *        The "startsWith()" string for all resources that may be in this cache - if null, don't watch events for updates.
     *        If this is set then it enables automatic removal of the matching cache entry key (to the event reference value)
     *        when the event reference starts with this pattern string.
     * @deprecated since Sakai 2.9, pattern matching no longer needed or supported, 07/Oct/2007 -AZ
     */
    Cache newCache(String cacheName, String pattern); // used in BaseAliasService, SiteCacheImpl, BaseUserDirectoryService (2), BaseCalendarService(3), ShareUserCacheImpl

    /**
     * Construct a multi-ref Cache. No automatic refresh: expire only, from time and events.
     * NOT Cluster safe
     *
     * @param cacheName Load a defined bean from ComponentManager or create a default cache with this name.
     * @deprecated since Sakai 2.9, this should no longer be used, it is not cluster safe or JSR-107 compatible
     */
    @SuppressWarnings("deprecation") // TODO remove this
    GenericMultiRefCache newGenericMultiRefCache(String cacheName); // used in BaseAliasService and SecurityService

}
