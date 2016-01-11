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
      return new Blog(lId, lDomainId, creationTimestamp, modificationTimestamp, 
                      strField1, strField2, strField3);
   }

   // Tests ////////////////////////////////////////////////////////////////////

}
