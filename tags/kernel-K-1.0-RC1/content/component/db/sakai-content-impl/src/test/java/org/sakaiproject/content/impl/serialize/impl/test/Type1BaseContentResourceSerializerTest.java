/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007 The Sakai Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.content.impl.serialize.impl.test;

import org.sakaiproject.content.impl.serialize.impl.Type1BaseContentCollectionSerializer;
import org.sakaiproject.content.impl.serialize.impl.Type1BaseContentResourceSerializer;
import org.sakaiproject.entity.api.serialize.EntityParseException;

import junit.framework.TestCase;

/**
 * @author ieb
 *
 */
public class Type1BaseContentResourceSerializerTest extends TestCase
{

	public Type1BaseContentResourceSerializerTest(String name)
	{
		super(name);
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	protected void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link org.sakaiproject.content.impl.serialize.impl.Type1BaseContentCollectionSerializer#parse(org.sakaiproject.entity.api.serialize.SerializableEntity, java.lang.String)}.
	 * @throws Exception 
	 */
	public final void testParse() throws Exception
	{
		Type1BaseContentResourceSerializer t1 = new Type1BaseContentResourceSerializer();
		t1.setTimeService(new MockTimeService());
		MockSerializableResourceAcccess sc = new MockSerializableResourceAcccess();
		byte[] serialized = t1.serialize(sc);
		t1.parse(sc, serialized);
		sc.check();
	}

	/**
	 * Test method for {@link org.sakaiproject.content.impl.serialize.impl.Type1BaseContentCollectionSerializer#serialize(org.sakaiproject.entity.api.serialize.SerializableEntity)}.
	 * @throws Exception 
	 */
	public final void testSerialize() throws Exception
	{
		Type1BaseContentResourceSerializer t1 = new Type1BaseContentResourceSerializer();
		t1.setTimeService(new MockTimeService());
		MockSerializableResourceAcccess sc = new MockSerializableResourceAcccess();
		byte[] s = t1.serialize(sc);
		MockSerializableCollectionAcccess sr = new MockSerializableCollectionAcccess();
		try {
			byte[] s1 = t1.serialize(sr);
			fail("Should have refused to MockSerializableCollectionAcccess a ResourceAccess Object ");
		} catch ( EntityParseException epe ) {
			
		}
	}

	/**
	 * Test method for {@link org.sakaiproject.content.impl.serialize.impl.Type1BaseContentCollectionSerializer#accept(java.lang.String)}.
	 */
	public final void testAccept()
	{
		Type1BaseContentResourceSerializer t1 = new Type1BaseContentResourceSerializer();
		
		assertEquals(true,t1.accept((Type1BaseContentResourceSerializer.BLOB_ID+"the rest of the  blob").getBytes()));
		assertEquals(false,t1.accept((Type1BaseContentCollectionSerializer.BLOB_ID+"the rest of the  blob").getBytes()));
		assertEquals(false,t1.accept(("0"+Type1BaseContentResourceSerializer.BLOB_ID+"the rest of the  blob").getBytes()));
		assertEquals(false,t1.accept(null));
		assertEquals(false,t1.accept(("0somethisdfjsdkjfs dfjsldkf").getBytes()));
	}

}
