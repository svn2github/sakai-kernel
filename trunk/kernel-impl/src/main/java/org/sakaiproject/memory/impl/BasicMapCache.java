/******************************************************************************
 * $URL$
 * $Id$
 ******************************************************************************
 *
 * Copyright (c) 2003-2014 The Apereo Foundation
 *
 * Licensed under the Educational Community License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *       http://opensource.org/licenses/ecl2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *****************************************************************************/

package org.sakaiproject.memory.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Contains general common implementation info related to a cache.
 * No support for listener or cache loader.
 * Should only be used for general testing of POC.
 *
 * @author Aaron Zeckoski (azeckoski @ unicon.net) (azeckoski @ gmail.com)
 */
public class BasicMapCache extends BasicCache {
    final Log log = LogFactory.getLog(BasicMapCache.class);

    /**
     * Underlying cache implementation
     * Simple and naive basic implementation of caching... not meant to be used
     */
    private Map<String, Object> cache;

    /**
     * Construct the Cache
     * Set the listeners and cache refreshers later
     *
     * @param name                 the name for this cache
     */
    public BasicMapCache(String name) {
        super(name);
        this.cache = new ConcurrentHashMap<String, Object>();
    }

    @Override
    public void put(String key, Object payload) {
        cache.put(key, payload);
    }

    @Override
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    } // containsKey

    @Override
    public Object get(String key) {
        return cache.get(key);
    } // get

    @Override
    public void clear() {
        cache.clear();
    } // clear

    @Override
    public void close() {
        clear();
        this.cache = new ConcurrentHashMap<String, Object>();
    }

    @Override
    public <T> T unwrap(Class<T> clazz) {
        //noinspection unchecked
        return (T) cache;
    }

    @Override
    public boolean remove(String key) {
        Object o = cache.remove(key);
        return (o != null);
    } // remove

    @Override
    public String getDescription() {
        return "BasicMap ("+getName()+"): mapSize="+cache.size();
    }

}
