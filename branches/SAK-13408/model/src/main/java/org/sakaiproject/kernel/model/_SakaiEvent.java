package org.sakaiproject.kernel.model;

/** Class _SakaiEvent was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _SakaiEvent extends org.apache.cayenne.CayenneDataObject {

    public static final String EVENT_PROPERTY = "event";
    public static final String EVENT_CODE_PROPERTY = "eventCode";
    public static final String EVENT_DATE_PROPERTY = "eventDate";
    public static final String REF_PROPERTY = "ref";
    public static final String SESSION_ID_PROPERTY = "sessionId";

    public static final String ID_PK_COLUMN = "ID";

    public void setEvent(String event) {
        writeProperty("event", event);
    }
    public String getEvent() {
        return (String)readProperty("event");
    }
    
    
    public void setEventCode(String eventCode) {
        writeProperty("eventCode", eventCode);
    }
    public String getEventCode() {
        return (String)readProperty("eventCode");
    }
    
    
    public void setEventDate(java.util.Date eventDate) {
        writeProperty("eventDate", eventDate);
    }
    public java.util.Date getEventDate() {
        return (java.util.Date)readProperty("eventDate");
    }
    
    
    public void setRef(String ref) {
        writeProperty("ref", ref);
    }
    public String getRef() {
        return (String)readProperty("ref");
    }
    
    
    public void setSessionId(String sessionId) {
        writeProperty("sessionId", sessionId);
    }
    public String getSessionId() {
        return (String)readProperty("sessionId");
    }
    
    
}
