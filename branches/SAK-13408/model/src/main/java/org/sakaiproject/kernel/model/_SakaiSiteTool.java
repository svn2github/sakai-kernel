package org.sakaiproject.kernel.model;

import java.util.List;

/** Class _SakaiSiteTool was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _SakaiSiteTool extends org.apache.cayenne.CayenneDataObject {

    public static final String LAYOUT_HINTS_PROPERTY = "layoutHints";
    public static final String PAGE_ORDER_PROPERTY = "pageOrder";
    public static final String REGISTRATION_PROPERTY = "registration";
    public static final String TITLE_PROPERTY = "title";
    public static final String SAKAI_SITE_TOOL_PROPERTY_ARRAY_PROPERTY = "sakaiSiteToolPropertyArray";
    public static final String TO_SAKAI_SITE_PROPERTY = "toSakaiSite";
    public static final String TO_SAKAI_SITE_PAGE_PROPERTY = "toSakaiSitePage";

    public static final String ID_PK_COLUMN = "ID";

    public void setLayoutHints(String layoutHints) {
        writeProperty("layoutHints", layoutHints);
    }
    public String getLayoutHints() {
        return (String)readProperty("layoutHints");
    }
    
    
    public void setPageOrder(Integer pageOrder) {
        writeProperty("pageOrder", pageOrder);
    }
    public Integer getPageOrder() {
        return (Integer)readProperty("pageOrder");
    }
    
    
    public void setRegistration(String registration) {
        writeProperty("registration", registration);
    }
    public String getRegistration() {
        return (String)readProperty("registration");
    }
    
    
    public void setTitle(String title) {
        writeProperty("title", title);
    }
    public String getTitle() {
        return (String)readProperty("title");
    }
    
    
    public void addToSakaiSiteToolPropertyArray(org.sakaiproject.kernel.db.model.SakaiSiteToolProperty obj) {
        addToManyTarget("sakaiSiteToolPropertyArray", obj, true);
    }
    public void removeFromSakaiSiteToolPropertyArray(org.sakaiproject.kernel.db.model.SakaiSiteToolProperty obj) {
        removeToManyTarget("sakaiSiteToolPropertyArray", obj, true);
    }
    public List getSakaiSiteToolPropertyArray() {
        return (List)readProperty("sakaiSiteToolPropertyArray");
    }
    
    
    public void setToSakaiSite(org.sakaiproject.kernel.db.model.SakaiSite toSakaiSite) {
        setToOneTarget("toSakaiSite", toSakaiSite, true);
    }

    public org.sakaiproject.kernel.db.model.SakaiSite getToSakaiSite() {
        return (org.sakaiproject.kernel.db.model.SakaiSite)readProperty("toSakaiSite");
    } 
    
    
    public void setToSakaiSitePage(org.sakaiproject.kernel.db.model.SakaiSitePage toSakaiSitePage) {
        setToOneTarget("toSakaiSitePage", toSakaiSitePage, true);
    }

    public org.sakaiproject.kernel.db.model.SakaiSitePage getToSakaiSitePage() {
        return (org.sakaiproject.kernel.db.model.SakaiSitePage)readProperty("toSakaiSitePage");
    } 
    
    
}
