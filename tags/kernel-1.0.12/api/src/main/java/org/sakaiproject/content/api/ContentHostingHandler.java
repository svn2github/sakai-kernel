/**
 * 
 */
package org.sakaiproject.content.api;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.sakaiproject.exception.ServerOverloadException;

/**
 * @author ieb
 */
public interface ContentHostingHandler
{

	/**
	 * Cancel an edit to a collection, if this needs to be done in the impl.
	 * 
	 * @param edit
	 */
	void cancel(ContentCollectionEdit edit);

	/**
	 * cancel an edit to a resource ( if this needs to be done )
	 * 
	 * @param edit
	 */
	void cancel(ContentResourceEdit edit);

	/**
	 * commit a collection
	 * 
	 * @param edit
	 */
	void commit(ContentCollectionEdit edit);

	/**
	 * commit a resource
	 * 
	 * @param edit
	 */
	void commit(ContentResourceEdit edit);

	/**
	 * commit a deleted resource
	 * 
	 * @param edit
	 * @param uuid
	 */
	void commitDeleted(ContentResourceEdit edit, String uuid);

	/**
	 * get a list of collections contained within the supplied collection
	 * 
	 * @param collection
	 * @return
	 */
	List getCollections(ContentCollection collection);

	/**
	 * get a ContentCollectionEdit for the ID, creating it if necessary, this should not persist until commit is invoked
	 * 
	 * @param id
	 * @return
	 */
	ContentCollectionEdit getContentCollectionEdit(String id);

	/**
	 * get a content resource edit for the supplied ID, creating it if necesary. This sould not persist until commit is invoked
	 * 
	 * @param id
	 * @return
	 */
	ContentResourceEdit getContentResourceEdit(String id);

	/**
	 * get a list of string ids of all resources below this point
	 * 
	 * @param ce
	 * @return
	 */
	List getFlatResources(ContentEntity ce);

	/**
	 * get the resource body
	 * 
	 * @param resource
	 * @return
	 * @throws ServerOverloadException
	 */
	byte[] getResourceBody(ContentResource resource) throws ServerOverloadException;

	/**
	 * get a list of resource ids as strings within the collection
	 * 
	 * @param collection
	 * @return
	 */
	List getResources(ContentCollection collection);

	/**
	 * Convert the passed-in ContentEntity into a virtual Content Entity. The implementation should check that the passed in entity is managed by this content handler before performing the translation. Additionally it must register the content handler
	 * with the newly proxied ContentEntity so that subsequent invocations are routed back to the correct ContentHostingHandler implementation
	 * 
	 * @param edit
	 * @return
	 */
	ContentEntity getVirtualContentEntity(ContentEntity edit, String finalId);

	/**
	 * perform a wastebasket operation on the names id, if the implementation supports the operation otherwise its safe to ignore.
	 * 
	 * @param id
	 * @param uuid
	 * @param userId
	 * @return
	 */
	ContentResourceEdit putDeleteResource(String id, String uuid, String userId);

	/**
	 * remove the supplied collection
	 * 
	 * @param edit
	 */
	void removeCollection(ContentCollectionEdit edit);

	/**
	 * remove the resource
	 * 
	 * @param edit
	 */
	void removeResource(ContentResourceEdit edit);

	/**
	 * stream the body of the resource
	 * 
	 * @param resource
	 * @return
	 * @throws ServerOverloadException
	 */
	InputStream streamResourceBody(ContentResource resource) throws ServerOverloadException;

	/**
	 * get the number of members
	 * @param ce
	 * @return
	 */
	int getMemberCount(ContentEntity ce);

	/**
	 * @param ce
	 * @return
	 */
	Collection<String> getMemberCollectionIds(ContentEntity ce);

	/**
	 * @param ce
	 * @return
	 */
	Collection<String> getMemberResourceIds(ContentEntity ce);

	/**
	 * @param thisResource
	 * @param new_id
	 * @return
	 */
	String moveResource(ContentResourceEdit thisResource, String new_id);

	/**
	 * @param thisCollection
	 * @param new_folder_id
	 * @return
	 */
	String moveCollection(ContentCollectionEdit thisCollection, String new_folder_id);

	/**
	 * @param resourceId
	 * @param uuid
	 * @return
	 */
	 void setResourceUuid(String resourceId, String uuid);

	/**
	 * @param id
	 */
	void getUuid(String id);

}