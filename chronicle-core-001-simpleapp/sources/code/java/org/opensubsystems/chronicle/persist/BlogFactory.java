/*
 * Copyright (C) 2003 - 2016 OpenSubsystems.com/net/org and its owners. All rights reserved.
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
 
package org.opensubsystems.chronicle.persist;

import java.util.List;

import org.opensubsystems.chronicle.data.Blog;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.persist.ModifiableDataFactory;

/**
 * Interface defining methods to create, retrieve and manipulate blogs in the 
 * persistence store.
 * 
 * @author bastafidli
 */
public interface BlogFactory extends ModifiableDataFactory
{   
   /**
    * Get blog data knowing just the folder where it's entries are displayed.
    *
    * @param strFolder - folder where entries for given folder are displayed
    * @return Blog - specified blog or null if not found
    * @throws OSSException - an error has occurred
    */
   Blog get(
      String strFolder
   ) throws OSSException;

   /**
    * Return collection of all blogs in the persistence store.
    *
    * @return List  - list of Blogs objects sorted alphabetically
    * @throws OSSException - an error has occurred
    */
   List getAll(
   ) throws OSSException;
}
