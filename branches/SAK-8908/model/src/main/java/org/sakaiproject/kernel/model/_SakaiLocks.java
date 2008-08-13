package org.sakaiproject.kernel.model;

/** Class _SakaiLocks was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _SakaiLocks extends org.apache.cayenne.CayenneDataObject {

    public static final String LOCK_TIME_PROPERTY = "lockTime";
    public static final String RECORD_ID_PROPERTY = "recordId";
    public static final String TABLE_NAME_PROPERTY = "tableName";
    public static final String USAGE_SESSION_ID_PROPERTY = "usageSessionId";

    public static final String ID_PK_COLUMN = "id";

    public void setLockTime(java.util.Date lockTime) {
        writeProperty("lockTime", lockTime);
    }
    public java.util.Date getLockTime() {
        return (java.util.Date)readProperty("lockTime");
    }
    
    
    public void setRecordId(String recordId) {
        writeProperty("recordId", recordId);
    }
    public String getRecordId() {
        return (String)readProperty("recordId");
    }
    
    
    public void setTableName(String tableName) {
        writeProperty("tableName", tableName);
    }
    public String getTableName() {
        return (String)readProperty("tableName");
    }
    
    
    public void setUsageSessionId(String usageSessionId) {
        writeProperty("usageSessionId", usageSessionId);
    }
    public String getUsageSessionId() {
        return (String)readProperty("usageSessionId");
    }
    
    
}
