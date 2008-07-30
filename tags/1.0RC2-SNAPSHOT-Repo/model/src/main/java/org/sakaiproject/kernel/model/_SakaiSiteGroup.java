package org.sakaiproject.kernel.model;

import java.util.List;

/** Class _SakaiSiteGroup was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _SakaiSiteGroup extends org.apache.cayenne.CayenneDataObject {

    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String TITLE_PROPERTY = "title";
    public static final String SAKAI_SITE_GROUP_PROPERTY_ARRAY_PROPERTY = "sakaiSiteGroupPropertyArray";
    public static final String TO_SAKAI_SITE_PROPERTY = "toSakaiSite";

    public static final String ID_PK_COLUMN = "ID";

    public void setDescription(String description) {
        writeProperty("description", description);
    }
    public String getDescription() {
        return (String)readProperty("description");
    }
    
    
    public void setTitle(String title) {
        writeProperty("title", title);
    }
    public String getTitle() {
        return (String)readProperty("title");
    }
    
    
    public void addToSakaiSiteGroupPropertyArray(org.sakaiproject.kernel.db.model.SakaiSiteGroupProperty obj) {
        addToManyTarget("sakaiSiteGroupPropertyArray", obj, true);
    }
    public void removeFromSakaiSiteGroupPropertyArray(org.sakaiproject.kernel.db.model.SakaiSiteGroupProperty obj) {
        removeToManyTarget("sakaiSiteGroupPropertyArray", obj, true);
    }
    public List getSakaiSiteGroupPropertyArray() {
        return (List)readProperty("sakaiSiteGroupPropertyArray");
    }
    
    
    public void setToSakaiSite(org.sakaiproject.kernel.db.model.SakaiSite toSakaiSite) {
        setToOneTarget("toSakaiSite", toSakaiSite, true);
    }

    public org.sakaiproject.kernel.db.model.SakaiSite getToSakaiSite() {
        return (org.sakaiproject.kernel.db.model.SakaiSite)readProperty("toSakaiSite");
    } 
    
    
}
