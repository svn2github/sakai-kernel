package org.sakaiproject.kernel.model;

/** Class _ContentResourceLock was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _ContentResourceLock extends org.apache.cayenne.CayenneDataObject {

    public static final String ASSET_ID_PROPERTY = "assetId";
    public static final String DATE_ADDED_PROPERTY = "dateAdded";
    public static final String DATE_REMOVED_PROPERTY = "dateRemoved";
    public static final String IS_ACTIVE_PROPERTY = "isActive";
    public static final String IS_SYSTEM_PROPERTY = "isSystem";
    public static final String QUALIFIER_ID_PROPERTY = "qualifierId";
    public static final String REASON_PROPERTY = "reason";

    public static final String ID_PK_COLUMN = "id";

    public void setAssetId(String assetId) {
        writeProperty("assetId", assetId);
    }
    public String getAssetId() {
        return (String)readProperty("assetId");
    }
    
    
    public void setDateAdded(java.util.Date dateAdded) {
        writeProperty("dateAdded", dateAdded);
    }
    public java.util.Date getDateAdded() {
        return (java.util.Date)readProperty("dateAdded");
    }
    
    
    public void setDateRemoved(java.util.Date dateRemoved) {
        writeProperty("dateRemoved", dateRemoved);
    }
    public java.util.Date getDateRemoved() {
        return (java.util.Date)readProperty("dateRemoved");
    }
    
    
    public void setIsActive(Boolean isActive) {
        writeProperty("isActive", isActive);
    }
    public Boolean getIsActive() {
        return (Boolean)readProperty("isActive");
    }
    
    
    public void setIsSystem(Boolean isSystem) {
        writeProperty("isSystem", isSystem);
    }
    public Boolean getIsSystem() {
        return (Boolean)readProperty("isSystem");
    }
    
    
    public void setQualifierId(String qualifierId) {
        writeProperty("qualifierId", qualifierId);
    }
    public String getQualifierId() {
        return (String)readProperty("qualifierId");
    }
    
    
    public void setReason(String reason) {
        writeProperty("reason", reason);
    }
    public String getReason() {
        return (String)readProperty("reason");
    }
    
    
}
