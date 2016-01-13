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

package org.opensubsystems.chronicle;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.opensubsystems.chronicle.data.BlogTest;
import org.opensubsystems.chronicle.data.EntryTest;
import org.opensubsystems.core.persist.jdbc.test.DatabaseTestSetup;
import org.opensubsystems.core.persist.jdbc.test.DatabaseTestSuite;
import org.opensubsystems.core.util.test.Tests;

/**
 * Test for classes included in Chronicle package.
 *
 * @author bastafidli
 */
public final class ChronicleTests extends Tests
{
   // Constructors /////////////////////////////////////////////////////////////
   
   /** 
    * Constructor
    */
   public ChronicleTests(
   )
   {
      // Do nothing
   }
   
   // Logic ////////////////////////////////////////////////////////////////////
   
   /**
    * Create suite of all core tests.
    * 
    * @return Test - suite of tests to run
    */
   public static Test suite(
   )
   {
      TestSuite suite = new DatabaseTestSuite("Test for chronicle");
      try
      {
         addGenericTests(suite);
      }
      catch (Throwable thr)
      {
         System.out.println(thr);
         System.out.println(thr.getCause());
         thr.getCause().printStackTrace(System.out);
      }

      // Here we are using DatabaseTestSetup instead of ApplicationTestSetup
      // since we are just directly testing  database functionality without
      // accessing any business logic functionality packaged into application 
      // modules
      TestSetup wrapper = new DatabaseTestSetup(suite);

      return wrapper;
   }

   /**
    * Add all generic database tests to given suite.
    * 
    * @param suite - suite of tests to run
    */
   public static void addGenericTests(
      TestSuite suite
   ) 
   {
      suite.addTestSuite(BlogTest.class);
      suite.addTestSuite(EntryTest.class);
   }   
}
