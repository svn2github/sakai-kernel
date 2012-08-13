package org.sakaiproject.kernel.model;

import java.util.List;

/** Class _SakaiSitePage was generated by Cayenne.
  * It is probably a good idea to avoid changing this class manually, 
  * since it may be overwritten next time code is regenerated. 
  * If you need to make any customizations, please use subclass. 
  */
public class _SakaiSitePage extends org.apache.cayenne.CayenneDataObject {

    public static final String LAYOUT_PROPERTY = "layout";
    public static final String POPUP_PROPERTY = "popup";
    public static final String SITE_ORDER_PROPERTY = "siteOrder";
    public static final String TITLE_PROPERTY = "title";
    public static final String SAKAI_SITE_PAGE_PROPERTY_ARRAY_PROPERTY = "sakaiSitePagePropertyArray";
    public static final String SAKAI_SITE_TOOL_ARRAY_PROPERTY = "sakaiSiteToolArray";
    public static final String TO_SAKAI_SITE_PROPERTY = "toSakaiSite";

    public static final String ID_PK_COLUMN = "ID";

    public void setLayout(String layout) {
        writeProperty("layout", layout);
    }
    public String getLayout() {
        return (String)readProperty("layout");
    }
    
    
    public void setPopup(String popup) {
        writeProperty("popup", popup);
    }
    public String getPopup() {
        return (String)readProperty("popup");
    }
    
    
    public void setSiteOrder(Integer siteOrder) {
        writeProperty("siteOrder", siteOrder);
    }
    public Integer getSiteOrder() {
        return (Integer)readProperty("siteOrder");
    }
    
    
    public void setTitle(String title) {
        writeProperty("title", title);
    }
    public String getTitle() {
        return (String)readProperty("title");
    }
    
    
    public void addToSakaiSitePagePropertyArray(org.sakaiproject.kernel.db.model.SakaiSitePageProperty obj) {
        addToManyTarget("sakaiSitePagePropertyArray", obj, true);
    }
    public void removeFromSakaiSitePagePropertyArray(org.sakaiproject.kernel.db.model.SakaiSitePageProperty obj) {
        removeToManyTarget("sakaiSitePagePropertyArray", obj, true);
    }
    public List getSakaiSitePagePropertyArray() {
        return (List)readProperty("sakaiSitePagePropertyArray");
    }
    
    
    public void addToSakaiSiteToolArray(org.sakaiproject.kernel.db.model.SakaiSiteTool obj) {
        addToManyTarget("sakaiSiteToolArray", obj, true);
    }
    public void removeFromSakaiSiteToolArray(org.sakaiproject.kernel.db.model.SakaiSiteTool obj) {
        removeToManyTarget("sakaiSiteToolArray", obj, true);
    }
    public List getSakaiSiteToolArray() {
        return (List)readProperty("sakaiSiteToolArray");
    }
    
    
    public void setToSakaiSite(org.sakaiproject.kernel.db.model.SakaiSite toSakaiSite) {
        setToOneTarget("toSakaiSite", toSakaiSite, true);
    }

    public org.sakaiproject.kernel.db.model.SakaiSite getToSakaiSite() {
        return (org.sakaiproject.kernel.db.model.SakaiSite)readProperty("toSakaiSite");
    } 
    
    
}
