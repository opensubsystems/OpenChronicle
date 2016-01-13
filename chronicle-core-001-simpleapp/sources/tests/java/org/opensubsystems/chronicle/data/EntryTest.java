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
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import org.opensubsystems.core.data.DataObject;
import org.opensubsystems.core.data.ModifiableDataObject;
import org.opensubsystems.core.data.ModifiableDataObjectTest;
import org.opensubsystems.core.error.OSSException;

/**
 * Tests for Entry class.
 * 
 * @author bastafidli
 */
public class EntryTest extends ModifiableDataObjectTest
{
   // Constructors /////////////////////////////////////////////////////////////

   /**
    * Constructor for EntryTest.
	 * 
    * @param strName - name of the test
    * @throws OSSException - an error has occurred
    */
   public EntryTest(
      String strName
   ) throws OSSException
   {
      super(strName, Entry.EntryDataDescriptor.class,
            Entry.EntryDataDescriptor.ENTRY_DATA_TYPE_DESIRED_VALUE);
   }

   /**
    * Constructor for EntryTest.
	 * 
    * @param strName - name of the test
    * @param clsDataDescriptor - data descriptor for class created in createTestDataObject
    * @param iDesiredDataType - desired data type for class created in createTestDataObject
    * @throws OSSException - an error has occurred
    */
   public EntryTest(
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
    * @param lParentId - Id of the parent this entry belongs to
    * @param strCaption - More descriptive name of the entry
    * @param strComments - Any additional text of the entry
    * @param strImageURL - Image URL of image associated with the entry
    * @param strTargetURL - Target URL for the image when user clicks on the image
    * @return Entry - data to use for testing
	 * @throws OSSException - an error has occurred
    */ 
   protected Entry createTestEntry(
      long      lId,
      long      lDomainId,
      Timestamp creationTimestamp,
      Timestamp modificationTimestamp,
      long      lParentId,
      String    strCaption,
      String    strComments,
      String    strImageURL,
      String    strTargetURL
   ) throws OSSException
   {
      return new Entry(lId, lDomainId, creationTimestamp, modificationTimestamp, 
                       lParentId, strCaption, strComments, strImageURL, strTargetURL);
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
      return createTestEntry(lId, lDomainId, creationTimestamp, modificationTimestamp, 
                             // Have to use contact value for parent id for the isSame
                             // test to pass
                             1000, strField1, strField2, strField3,
                             strField2 + strField3);
   }

   // Tests ////////////////////////////////////////////////////////////////////

   /**
    * Test getParentId method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testGetParentId(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Entry data1 = createTestEntry(1, 11, now, now, 1111, "1value1", 
                                   "1value2", "1value3", "1value4");
		Entry data2 = createTestEntry(2, 22, later, evenlater, 2222, "2value1", 
                                   "2value2", "2value3", "2value4");
		Entry data3 = createTestEntry(3, 33, later, evenlater, DataObject.NEW_ID, 
                                    null, null, null, null);
		
		assertEquals("Entry parent id doesn't match", 1111, data1.getParentId());
		assertEquals("Entry parent id doesn't match", 2222, data2.getParentId());
		assertEquals("Entry parent id doesn't match", DataObject.NEW_ID, data3.getParentId());
   }


   /**
    * Test setParentId method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testSetParentId(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Entry data1 = createTestEntry(1, 11, now, now, 1111, "1value1", 
                                   "1value2", "1value3", "1value4");
		Entry data2 = createTestEntry(2, 22, later, evenlater, 2222, "2value1", 
                                   "2value2", "2value3", "2value4");
		Entry data3 = createTestEntry(3, 33, later, evenlater, DataObject.NEW_ID, 
                                    null, null, null, null);
		
		assertEquals("Entry parent id doesn't match", 1111, data1.getParentId());
		assertEquals("Entry parent id doesn't match", 2222, data2.getParentId());
		assertEquals("Entry parent id doesn't match", DataObject.NEW_ID, data3.getParentId());

      data1.setParentId(1212);
      data2.setParentId(2323);
      data3.setParentId(3434);
              
		assertEquals("Entry parent id doesn't match", 1212, data1.getParentId());
		assertEquals("Entry parent id doesn't match", 2323, data2.getParentId());
		assertEquals("Entry parent id doesn't match", 3434, data3.getParentId());

      data2.setParentId(DataObject.NEW_ID);
      data3.setParentId(DataObject.NEW_ID);

      assertEquals("Entry parent id doesn't match", DataObject.NEW_ID, data2.getParentId());
      assertEquals("Entry parent id doesn't match", DataObject.NEW_ID, data3.getParentId());
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
		Entry data1 = createTestEntry(1, 11, now, now, 1111, "1value1", 
                                   "1value2", "1value3", "1value4");
		Entry data2 = createTestEntry(2, 22, later, evenlater, 2222, "2value1", 
                                   "2value2", "2value3", "2value4");
		Entry data3 = createTestEntry(3, 33, later, evenlater, 3333, null, null, 
                                    null, null);
		
		assertEquals("Entry caption doesn't match", "1value1", data1.getCaption());
		assertEquals("Entry caption doesn't match", "2value1", data2.getCaption());
		assertNull("Entry caption doesn't match", data3.getCaption());
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
		Entry data1 = createTestEntry(1, 11, now, now, 1111, "1value1", 
                                   "1value2", "1value3", "1value4");
		Entry data2 = createTestEntry(2, 22, later, evenlater, 2222, "2value1", 
                                   "2value2", "2value3", "2value4");
		Entry data3 = createTestEntry(3, 33, later, evenlater, 3333, null, null, 
                                    null, null);
		
		assertEquals("Entry comments doesn't match", "1value2", data1.getComments());
		assertEquals("Entry comments doesn't match", "2value2", data2.getComments());
		assertNull("Entry comments doesn't match", data3.getComments());
   }

   /**
    * Test getImageURL method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testGetImageURL(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Entry data1 = createTestEntry(1, 11, now, now, 1111, "1value1", 
                                   "1value2", "1value3", "1value4");
		Entry data2 = createTestEntry(2, 22, later, evenlater, 2222, "2value1", 
                                   "2value2", "2value3", "2value4");
		Entry data3 = createTestEntry(3, 33, later, evenlater, 3333, null, null, 
                                    null, null);
		
		assertEquals("Entry image URL doesn't match", "1value3", data1.getImageURL());
		assertEquals("Entry image URL doesn't match", "2value3", data2.getImageURL());
		assertNull("Entry image URL doesn't match", data3.getImageURL());
   }

   /**
    * Test getTargetURL method 
    * 
    * @throws Exception - and error has occurred  
    */
   public void testGetTargetURL(
   ) throws Exception
   {
      Timestamp now = new Timestamp((new Date()).getTime());
      Timestamp later = new Timestamp((new Date()).getTime() + 1000);
      Timestamp evenlater = new Timestamp((new Date()).getTime() + 2000);
		Entry data1 = createTestEntry(1, 11, now, now, 1111, "1value1", 
                                   "1value2", "1value3", "1value4");
		Entry data2 = createTestEntry(2, 22, later, evenlater, 2222, "2value1", 
                                   "2value2", "2value3", "2value4");
		Entry data3 = createTestEntry(3, 33, later, evenlater, 3333, null, null, 
                                    null, null);
		
		assertEquals("Entry target URL doesn't match", "1value4", data1.getTargetURL());
		assertEquals("Entry target URL doesn't match", "2value4", data2.getTargetURL());
		assertNull("Entry target URL doesn't match", data3.getTargetURL());
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
		Entry data1 = createTestEntry(1, 11, now, now, 1111, "1value1", 
                                   "1value2", "1value3", "1value4");
		Entry data2 = createTestEntry(2, 22, later, evenlater, 2222, "2value1", 
                                   "2value2", "2value3", "2value4");
		Entry data3 = createTestEntry(3, 33, later, evenlater, 3333, null, null, 
                                    null, null);
		Entry data4 = createTestEntry(4, 44, now, now, 4444, "4value1", 
                                  "4value2\n", "4value3", "4value4");
		Entry data5 = createTestEntry(5, 55, now, now, 5555, "5value1", 
                                  "\n5value2", "5value2", "5value3");
		Entry data6 = createTestEntry(6, 66, now, now, 6666, "6value1", 
                                  "6value3\n6value3", "6value2", "6value3");
		
		assertFalse("Is preformatted flag doesn't match", data1.getIsPreformated());
		assertFalse("Is preformatted flag doesn't match", data2.getIsPreformated());
		assertFalse("Is preformatted flag doesn't match", data3.getIsPreformated());
		assertTrue("Is preformatted flag doesn't match", data4.getIsPreformated());
		assertTrue("Is preformatted flag doesn't match", data5.getIsPreformated());
		assertTrue("Is preformatted flag doesn't match", data6.getIsPreformated());
   }
}
