/**********************************************************************************
 * $URL:  $
 * $Id:  $
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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

package org.sakaiproject.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.sakaiproject.component.api.ServerConfigurationService;
import org.sakaiproject.component.api.ServerConfigurationService.ConfigHistory;
import org.sakaiproject.component.api.ServerConfigurationService.ConfigItem;
import org.sakaiproject.util.BasicConfigHistory;
import org.sakaiproject.util.BasicConfigItem;


/**
 * 
 * 
 * @author Aaron Zeckoski (azeckoski @ unicon.net) (azeckoski @ vt.edu)
 */
public class ConfigItemImpl extends BasicConfigItem implements ConfigItem, Comparable<ConfigItem> {
    /**
     * the type of the value (string, int, boolean, array)
     */
    public String type = ServerConfigurationService.TYPE_STRING;
    /**
     * the number of times the config value was requested
     */
    public int requested = 0;
    /**
     * the number of times this config value was changed
     */
    public int changed = 0;
    /**
     * history of the source:value at each change (comma separated)
     */
    public List<ConfigHistory> history = null;
    /**
     * indicates is this config is registered (true) or if it is only requested (false)
     * (requested means someone asked for it but the setting has not been stored in the config service)
     */
    public boolean registered = false;
    /**
     * indicates is this config is has a default value defined
     * (this can only be known for items which are requested)
     */
    public boolean defaulted = false;
    /**
     * indicates is this config value should not be revealed because there are security implications
     */
    public boolean secured = false;


    /**
     * Use this constructor for making requested (unregistered) config items,
     * set the defaultValue manually if it is known
     */
    public ConfigItemImpl(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Config item name must be set");
        }
        this.name = name;
        this.registered = false;
        this.secured = false;
        this.history = new ArrayList<ConfigHistory>();
    }

    /**
     * Only use this if you do not know the source
     */
    public ConfigItemImpl(String name, Object value) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Config item name must be set");
        }
        this.name = name;
        this.value = value;
        this.type = setValue(value);
        this.registered = true;
        this.secured = false;
        this.history = new ArrayList<ConfigHistory>();
    }

    /**
     * MAIN constructor
     */
    public ConfigItemImpl(String name, Object value, String source) {
        this(name, value);
        this.source = source;
    }

    /**
     * Allows overriding the type detection
     */
    public ConfigItemImpl(String name, Object value, String type, String source) {
        this(name, value);
        this.type = type;
        this.source = source;
    }

    /**
     * FULL (really just for copy and testing)
     */
    public ConfigItemImpl(String name, Object value, String type, String source, Object defaultValue, 
            int requested, int changed, List<ConfigHistory> history, boolean registered, boolean defaulted, boolean secured) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.source = source;
        this.defaultValue = defaultValue;
        this.requested = requested;
        this.changed = changed;
        this.history = history;
        this.registered = registered;
        this.defaulted = defaulted;
        this.secured = secured;
    }

    @Override
    public int requested() {
        this.requested = this.requested + 1;
        return this.requested;
    }

    @Override
    public int changed(Object value, String source) {
        if (source != null) {
            this.source = source;
        } else {
            source = ServerConfigurationService.UNKNOWN;
        }
        BasicConfigHistory ch = new BasicConfigHistory(getVersion(), source, value);
        ch.setSecured(this.isSecured());
        this.history.add( ch );

        this.changed = this.changed + 1;
        this.value = value;
        return this.changed;
    }

    @SuppressWarnings("unchecked")
    public <T> T getTypedValue() {
        return (T) this.value;
    }

    /**
     * Duplicate this config item
     * This is mostly used to ensure we do not send the internal objects out where they could be changed
     * @return a config item with all the same values
     */
    @Override
    public ConfigItem copy() {
        ConfigItem ci = new ConfigItemImpl(this.name, this.value, this.type, this.source, this.defaultValue, 
                this.requested, this.changed, this.history, this.registered, this.defaulted, this.secured);
        return ci;
    }

    protected String setValue(Object value) {
        return setObjectValue(value, false);
    }

    protected void setSource(String source) {
        this.source = source;
    }

    protected String setDefaultValue(Object value) {
        if (value == null) {
            this.defaulted = false;
        } else {
            this.defaulted = true;
        }
        return setObjectValue(value, true);
    }

    private String setObjectValue(Object value, boolean isDefault) {
        String type = ServerConfigurationService.TYPE_STRING;
        if (value != null) {
            if (value.getClass().isArray()) {
                if (isDefault) {
                    this.defaultValue = value;
                } else {
                    this.value = value;
                }
                type = ServerConfigurationService.TYPE_ARRAY;
            } else if (value instanceof Number) {
                int num = ((Number) value).intValue();
                if (isDefault) {
                    this.defaultValue = num;
                } else {
                    this.value = num;
                }
                type = ServerConfigurationService.TYPE_INT;
            } else if (value instanceof Boolean) {
                boolean bool = ((Boolean) value).booleanValue();
                if (isDefault) {
                    this.defaultValue = bool;
                } else {
                    this.value = bool;
                }
                type = ServerConfigurationService.TYPE_BOOLEAN;
            } else if (value instanceof String) {
                if (isDefault) {
                    this.defaultValue = value;
                } else {
                    this.value = value;
                }
                type = ServerConfigurationService.TYPE_STRING;
            } else {
                if (isDefault) {
                    this.defaultValue = value;
                } else {
                    this.value = value;
                }
                type = ServerConfigurationService.UNKNOWN;
            }
        } else {
            if (isDefault) {
                this.defaultValue = null;
            } else {
                this.value = null;
            }
        }
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConfigItem other = (ConfigItem) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String ciVal = secured ? "**SECURITY**" : (String) this.value;
        if (this.value == null && this.defaultValue != null) {
            ciVal = (secured ? "**SECURITY**" : (String) this.defaultValue) + " (D)";
        }
        return name + " => "+ciVal+"  "+(registered?"R":"U")+","+(defaulted?"D":"N")+":(" + type + " [" + source + "] " + requested + ", " + changed + ", "+history+")";
    }

    public int compareTo(ConfigItem ci) {
        return this.name.compareToIgnoreCase(ci.getName());
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getRequested() {
        return requested;
    }

    @Override
    public int getChanged() {
        return changed;
    }

    @Override
    public int getVersion() {
        return this.changed + 1;
    }

    @Override
    public ConfigHistory[] getHistory() {
        return history == null ? new ConfigHistory[0] : history.toArray(new ConfigHistory[history.size()]);
    }

    @Override
    public boolean isRegistered() {
        return registered;
    }

    @Override
    public boolean isDefaulted() {
        return defaulted;
    }

    @Override
    public boolean isSecured() {
        return secured;
    }

}
