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

import org.opensubsystems.chronicle.data.Entry;
import org.opensubsystems.core.error.OSSException;
import org.opensubsystems.core.persist.ModifiableDataFactory;

/**
 * Interface defining methods to create, retrieve and manipulate entries in the 
 * persistence store.
 * 
 * @author bastafidli
 */
public interface EntryFactory extends ModifiableDataFactory
{
   /**
    * Get all entries from blog.
    *
    * @param  lBlogId - Id of the blog to get entry from
    * @return List - list of entries sorted from the most recent to the oldest 
    *                one or null if none exists
    * @throws OSSException - an error has occurred
    */
   List getAll(
      long lBlogId
   ) throws OSSException;

   /**
    * Get last added entry added to the specified blog.
    *
    * @param  lBlogId - id of the blog to get entry from
    * @return Entry - last added entry or null if no entry could be found
    * @throws OSSException - an error has occurred
    */
   Entry getLast(
      long lBlogId
   ) throws OSSException;
}
