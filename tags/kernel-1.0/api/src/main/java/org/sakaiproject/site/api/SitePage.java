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
 *       http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.site.api;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.sakaiproject.entity.api.Edit;
import org.sakaiproject.tool.api.Tool;

/**
 * <p>
 * A Site Page is grouping of tools in a Site.
 * </p>
 */
public interface SitePage extends Edit, Serializable
{
	/** Layout value for a single column layout. */
	public static final int LAYOUT_SINGLE_COL = 0;

	/** Layout value for a double column layout. */
	public static final int LAYOUT_DOUBLE_COL = 1;

	public static final String PAGE_CATEGORY_PROP = "sitePage.pageCategory";

	/** @return The human readable Title of this SitePage. */
	public String getTitle();

	/** @return the layout for this page. */
	public int getLayout();

	/** @return the layout title for this page. */
	public String getLayoutTitle();

	/** @return The List (ToolConfiguration) of tools on this page. */
	public List getTools();

	/**
	 * @return The List (ToolConfiguration) of tools on this column (0 based) of
	 *         this page.
	 */
	public List getTools(int col);

	/**
	 * Get all the tools placed in the site on this page that are of any of
	 * these tool ids.
	 * 
	 * @param tooldIds
	 *        The tool id array (String, such as sakai.chat, not a tool
	 *        configuration / placement uuid) to search for.
	 * @return A Collection (ToolConfiguration) of all the tools placed in the
	 *         site on this page that are of this tool id (may be empty).
	 */
	Collection getTools(String[] toolIds);

	/** @return the skin to use for this page. */
	public String getSkin();

	/** @return the site id for this page. */
	public String getSiteId();

	/** @return true if page should open in new window. */
	public boolean isPopUp();

	/**
	 * Access a tool on this page by id.
	 * 
	 * @param id
	 *        The tool id.
	 * @return The tool on this page with this id, or null if not found.
	 */
	public ToolConfiguration getTool(String id);

	/**
	 * Access the site in which this page lives.
	 * 
	 * @return the site in which this page lives.
	 */
	public Site getContainingSite();

	/**
	 * Set the display title of this page.
	 * 
	 * @param title
	 *        The new title.
	 */
	public void setTitle(String title);

	/**
	 * Set the layout for this page.
	 * 
	 * @param layout
	 *        The new layout.
	 */
	public void setLayout(int layout);

	/**
	 * Set the popup status for this page.
	 * 
	 * @param popup
	 *        The new popup status.
	 */
	public void setPopup(boolean popup);

	/**
	 * Add a new tool to the page.
	 * 
	 * @return the ToolConfigurationEdit object for the new tool.
	 */
	public ToolConfiguration addTool();

	/**
	 * Add a new tool to the page, initialized to the tool registration
	 * information provided.
	 * 
	 * @param reg
	 *        The tool registration information used to initialize the tool.
	 * @return the ToolConfigurationEdit object for the new tool.
	 */
	public ToolConfiguration addTool(Tool reg);

	/**
	 * Add a new tool to the page, initialized to the tool id provided.
	 * 
	 * @param toolId
	 *        The tool id for this tool.
	 * @return the ToolConfigurationEdit object for the new tool.
	 */
	public ToolConfiguration addTool(String toolId);

	/**
	 * Remove a tool from this page.
	 * 
	 * @param tool
	 *        The tool to remove.
	 */
	public void removeTool(ToolConfiguration tool);

	/**
	 * Move this page one step towards the start of the order of pages in this
	 * site.
	 */
	public void moveUp();

	/**
	 * Move this page one step towards the end of the order of pages in this
	 * site.
	 */
	public void moveDown();

	/**
	 * Move this page to a specific (0 based index) position within the site's
	 * pages.
	 */
	public void setPosition(int pos);

	/**
	 * get the 0 based index position of the page within the site's pages.
	 */
	public int getPosition();

	public void setupPageCategory(String toolId);
}
