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

package org.opensubsystems.chronicle.data;

import java.sql.Timestamp;
import java.util.EnumSet;

import org.opensubsystems.core.data.DataObject;
import org.opensubsystems.core.data.impl.DataDescriptorImpl;
import org.opensubsystems.core.data.impl.ModifiableDataObjectImpl;
import org.opensubsystems.core.error.OSSException;

/**
 * Blog is a list of entries. Simples blog has name, description and set of 
 * entries added over the time.
 *
 * @author bastafidli
 */
public class Blog extends ModifiableDataObjectImpl
{
	// Inner classes ////////////////////////////////////////////////////////////
	
	public static class BlogDataDescriptor extends DataDescriptorImpl<BlogDataDescriptor.BlogFields>
	{
      // Constants ////////////////////////////////////////////////////////////////
      
		/**
		 * Desired value for the data type code. This can be reconfigured if there
		 * are multiple data objects which desire the same value. The rest of the
		 * constants in this class can safely use the desired value since they are
		 * valid only in the context of the data type and therefore it doesn't matter
		 * what the real value is.
		 * Protected since it can be reconfigured by the framework and the real value
		 * can be different.
		 */
		protected static final int BLOG_DATA_TYPE_DESIRED_VALUE = 2;

		/**
		 * Displayable name for specified data type code object.
		 * Protected since it can be customized and therefore code should use method
		 * exposing it rather than the constants.
		 */
		protected static final String BLOG_DATA_TYPE_NAME = "Blog";

		/**
		 * Logical name identifying the default view for the specified data
		 * type object. Data type objects can be displayed in multiple various ways
		 * called views. This constant identifies the default one. This constant
		 * should have a value, that can be used to construct various identifiers,
		 * which means no special characters, no spaces, etc.
		 * Protected since it can be customized and therefore code should use method
		 * exposing it rather than the constants.
		 */
		protected static final String BLOG_TYPE_VIEW = "blog";

		/**
		 * Definition of all fields that represent meaningful data for users.
		 * The order is important since it is used to retrieve all data from the
		 * persistence store efficiently so do not modify it unless you make
		 * changes to other places as well.
		 * Protected since derived classes can add more attributes and therefore code
		 * should use method exposing it rather than the constants.
		 */
		public enum BlogFields {
         // Fields from DataObject
			BLOG_ID(BLOG_DATA_TYPE_DESIRED_VALUE + 1),
         // Fields from BasicDataObject
			BLOG_DOMAIN_ID(BLOG_DATA_TYPE_DESIRED_VALUE + 2),
			BLOG_FROM_PERSISTANCE_STORE(BLOG_DATA_TYPE_DESIRED_VALUE + 3),
			BLOG_CREATION_DATE(BLOG_DATA_TYPE_DESIRED_VALUE + 4),
         // Fields from ModifiableDataObject
			BLOG_MODIFICATION_DATE(BLOG_DATA_TYPE_DESIRED_VALUE + 5),
         // Fields from this class
			BLOG_FOLDER(BLOG_DATA_TYPE_DESIRED_VALUE + 6),
			BLOG_CAPTION(BLOG_DATA_TYPE_DESIRED_VALUE + 7),
			BLOG_COMMENTS(BLOG_DATA_TYPE_DESIRED_VALUE + 8),
			;

			private final int iValue;
			BlogFields(int id) { this.iValue = id; }
			public int getValue() { return iValue; }
		}

      // Constructors //////////////////////////////////////////////////////////
      
		public BlogDataDescriptor() throws OSSException
		{
			super(BLOG_DATA_TYPE_DESIRED_VALUE, BLOG_DATA_TYPE_NAME, BLOG_TYPE_VIEW, 
					EnumSet.allOf(BlogFields.class));
		}
	}

	// Constants ////////////////////////////////////////////////////////////////

	// Cached values ////////////////////////////////////////////////////////////
	
   /**
    * Flag signaling if the text contains formatting or not. Example of such 
    * formatting is a newline character.
    */
   protected Boolean m_bIsPreformated = null;

   // Attributes ///////////////////////////////////////////////////////////////
   
   /**
    * Generated serial version id for this class.
    */
   private static final long serialVersionUID = -6839950852321644561L;

   /**
    * Folder allows to organize blogs within folders.
    */
   protected String m_strFolder;
   
   /**
    * Caption is more descriptive name of the blog.
    */
   protected String m_strCaption;
   
   /**
    * Comments is any additional description of the blog.
    */
   protected String m_strComments;
   
   // Constructors /////////////////////////////////////////////////////////////
   
   /**
    * Empty blog initialized to default parameters
	 * 
	 * @throws OSSException - an error has occurred
    */
   public Blog(
   ) throws OSSException
   {
      this(DataObject.NEW_ID, DataObject.NEW_ID, null, null, "", "", "");
   }
   
   /**
    * Empty blog for a specified domain initialized to default parameters
    *
    * @param lDomainId - Id of the domain this blog belongs to 
	 * @throws OSSException - an error has occurred
    */
   public Blog(
      long lDomainId
   ) throws OSSException
   {
      this(DataObject.NEW_ID, lDomainId, null, null, "", "", "");
   }
   
   /**
    * Create blog from a given parameters.
    *
    * @param lId - Unique ID identifying this Blog
    * @param lDomainId - Id of the domain this blog belongs to 
    * @param creationTimestamp - Timestamp when the Blog was created
    * @param modificationTimestamp - Timestamp when the Blog was last time modified
    * @param strBlogFolder - Folder allows to categorize Blogs to groups
    * @param strCaption - More descriptive name of the Blog
    * @param strComments - Any additional description of the Blog
	 * @throws OSSException - an error has occurred
    */ 
   public Blog(
      long      lId,
      long      lDomainId,
      Timestamp creationTimestamp,
      Timestamp modificationTimestamp,
      String    strBlogFolder,
      String    strCaption,
      String    strComments
   ) throws OSSException
   {
      super(lId, BlogDataDescriptor.class, lDomainId, creationTimestamp, 
			   modificationTimestamp);
      
      m_strFolder   = strBlogFolder.trim();
      m_strCaption  = strCaption;
      m_strComments = strComments;
   }
   
   // Public methods ///////////////////////////////////////////////////////////
   
   /**
    * Folder allows to organize blogs within folders.
    *
    * @return String
    */
   public String getFolder(
   )
   {
      return m_strFolder;
   }
   
   /**
    * Caption is more descriptive name of the blog.
    *
    * @return String
    */
   public String getCaption(
   )
   {
      return m_strCaption;
   }
   
   /**
    * Comments is any additional description of the blog.
    *
    * @return String
    */
   public String getComments(
   )
   {
      return m_strComments;
   }
   
   /**
    * Flag signaling if the text contains formatting or not. Example of such 
    * formatting is a newline character.
    * 
    * @return boolean
    */
	@SuppressWarnings({ "BoxedValueEquality" })
   public boolean getIsPreformated()
   {
      if (m_bIsPreformated == null)
      {
         if (m_strComments == null)
         {
            m_bIsPreformated = Boolean.FALSE;
         }
         else
         {
            m_bIsPreformated = ((m_strComments.indexOf('\n') != -1) ? Boolean.TRUE 
                                                                    : Boolean.FALSE);
         }
      }
      
      return (m_bIsPreformated == Boolean.TRUE);
   }

   /**
    * {@inheritDoc}
	 * 
	 * @param oObject {@inheritDoc}
	 * @return {@inheritDoc}
    */
	@Override
   public boolean isSame(
      Object oObject
   )
   {
      boolean bReturn = false;
      Blog    data;

      if (oObject == this)
      {
         bReturn = true;
      }
      else
      {
         if (oObject != null && oObject instanceof Blog)
         {
            data = (Blog) oObject;
            bReturn = ((data.getFolder() == null && m_strFolder == null)
                           || data.getFolder().equals(m_strFolder))
                     && ((data.getCaption() == null && m_strCaption == null)
                           || data.getCaption().equals(m_strCaption))
                     && ((data.getComments() == null && m_strComments == null)
                           || data.getComments().equals(m_strComments));
         }
      }
      return bReturn;
   }
   
   /**
    * {@inheritDoc}
    */
   @Override
   public void toString(
      StringBuilder sb,
      int           ind
   )
   {
      append(sb, ind + 0, "Blog[", true);
      append(sb, ind + 1, "m_strFolder = ", m_strFolder);
      append(sb, ind + 1, "m_strCaption = ", m_strCaption);
      append(sb, ind + 1, "m_strComments = ", m_strComments);
      super.toString(sb, ind + 1);
      append(sb, ind + 0, "]", true);
   }
}
