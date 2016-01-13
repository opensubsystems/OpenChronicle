/*
 * Copyright (C) 2016 OpenSubsystems.com/net/org and its owners. All rights reserved.
 * 
 * This file is part of OpenSubsystems.
 *
 * OpenSubsystems is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 */

package org.opensubsystems.chronicle.data;

import java.sql.Timestamp;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import org.opensubsystems.core.data.ModifiableDataObject;
import org.opensubsystems.core.data.ModifiableDataObjectTest;
import org.opensubsystems.core.error.OSSException;

/**
 * Tests for Blog class.
 * 
 * @author bastafidli
 */
public class BlogTest extends ModifiableDataObjectTest
{
   // Constructors /////////////////////////////////////////////////////////////

   /**
    * Constructor for BlogTest.
	 * 
    * @param strName - name of the test
    * @throws OSSException - an error has occurred
    */
   public BlogTest(
      String strName
   ) throws OSSException
   {
      super(strName, Blog.BlogDataDescriptor.class,
            Blog.BlogDataDescriptor.BLOG_DATA_TYPE_DESIRED_VALUE);
   }

   /**
    * Constructor for BlogTest.
	 * 
    * @param strName - name of the test
    * @param clsDataDescriptor - data descriptor for class created in createTestDataObject
    * @param iDesiredDataType - desired data type for class created in createTestDataObject
    * @throws OSSException - an error has occurred
    */
   public BlogTest(
      String strName,
      Class  clsDataDescriptor,
      int    iDesiredDataType
   ) throws OSSException
   {
      super(strName, clsDataDescriptor, iDesiredDataType);
   }
   
   // Helper methods ///////////////////////////////////////////////////////////
   
   /**
    * Create test data from a given parameters.
    *
    * @param lId - Unique ID identifying this test data
    * @param lDomainId - Unique ID identifying domain for this test data
    * @param creationTimestamp - creation timestamp for this test data
    * @param modificationTimestamp - modification timestamp for this test data
    * @param strBlogFolder - Folder allows to categorize Blogs to groups
    * @param strCaption - More descriptive name of the Blog
    * @param strComments - Any additional description of the Blog
    * @return Blog - data to use for testing
	 * @throws OSSException - an error has occurred
    */ 
   protected Blog createTestBlog(
      long      lId,
      long      lDomainId,
      Timestamp creationTimestamp,
      Timestamp modificationTimestamp,
      String    strBlogFolder,
      String    strCaption,
      String    strComments
   ) throws OSSException
   {
      return new Blog(lId, lDomainId, creationTimestamp, modificationTimestamp, 
                      strBlogFolder, strCaption, strComments);
   }

   /**
    * Create test data from a given parameters.
    *
    * @param lId - Unique ID identifying this test data
    * @param lDomainId - Unique ID identifying domain for this test data
    * @param creationTimestamp - creation timestamp for this test data
    * @param modificationTimestamp - modification timestamp for this test data
    * @param strField1 - first field of the test data
    * @param strField2 - second field of the test data
    * @param strField3 - third field of the test data
    * @return ModifiableDataObject - data to use for testing
	 * @throws OSSException - an error has occurred
    */ 
   @Override
   protected ModifiableDataObject createTestDataObject(
      long      lId,
      long      lDomainId,
      Timestamp creationTimestamp,
      Timestamp modificationTimestamp,
      String    strField1,
      String    strField2,
      String    strField3
   ) throws OSSException
   {
      return createTestBlog(lId, lDomainId, creationTimestamp, modificationTimestamp, 
                            strField1, strField2, strField3);
   }

   // Tests ////////////////////////////////////////////////////////////////////

   /**
    * Test getFolder method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testGetFolder(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Blog data1 = createTestBlog(1, 11, now, now, "1value1", 
                                  "1value2", "1value3");
		Blog data2 = createTestBlog(2, 22, later, evenlater, "2value1", 
                                  "2value2", "2value3");
		Blog data3 = createTestBlog(3, 33, later, evenlater, null, null, null);
		
		assertEquals("Blog folder doesn't match", "1value1", data1.getFolder());
		assertEquals("Blog folder doesn't match", "2value1", data2.getFolder());
		assertNull("Blog folder doesn't match", data3.getFolder());
   }

   /**
    * Test getCaption method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testGetCaption(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Blog data1 = createTestBlog(1, 11, now, now, "1value1", 
                                  "1value2", "1value3");
		Blog data2 = createTestBlog(2, 22, later, evenlater, "2value1", 
                                  "2value2", "2value3");
		Blog data3 = createTestBlog(3, 33, later, evenlater, null, null, null);
		
		assertEquals("Blog caption doesn't match", "1value2", data1.getCaption());
		assertEquals("Blog caption doesn't match", "2value2", data2.getCaption());
		assertNull("Blog caption doesn't match", data3.getCaption());
   }

   /**
    * Test getComments method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testGetComments(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Blog data1 = createTestBlog(1, 11, now, now, "1value1", 
                                  "1value2", "1value3");
		Blog data2 = createTestBlog(2, 22, later, evenlater, "2value1", 
                                  "2value2", "2value3");
		Blog data3 = createTestBlog(3, 33, later, evenlater, null, null, null);
		
		assertEquals("Blog comments doesn't match", "1value3", data1.getComments());
		assertEquals("Blog comments doesn't match", "2value3", data2.getComments());
		assertNull("Blog comments doesn't match", data3.getComments());
   }


   /**
    * Test getIsPreformated method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testGetIsPreformated(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Blog data1 = createTestBlog(1, 11, now, now, "1value1", 
                                  "1value2", "1value3");
		Blog data2 = createTestBlog(2, 22, later, evenlater, "2value1", 
                                  "2value2", "2value3");
		Blog data3 = createTestBlog(3, 33, later, evenlater, null, null, null);
		Blog data4 = createTestBlog(4, 44, now, now, "4value1", 
                                  "4value2", "4value3\n");
		Blog data5 = createTestBlog(5, 55, now, now, "5value1", 
                                  "5value2", "\n5value3");
		Blog data6 = createTestBlog(6, 66, now, now, "6value1", 
                                  "6value2", "6value3\n6value3");
		
		assertFalse("Is preformatted flag doesn't match", data1.getIsPreformated());
		assertFalse("Is preformatted flag doesn't match", data2.getIsPreformated());
		assertFalse("Is preformatted flag doesn't match", data3.getIsPreformated());
		assertTrue("Is preformatted flag doesn't match", data4.getIsPreformated());
		assertTrue("Is preformatted flag doesn't match", data5.getIsPreformated());
		assertTrue("Is preformatted flag doesn't match", data6.getIsPreformated());
   }
}
