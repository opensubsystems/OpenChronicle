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
 * Entry within a blog. Entry has caption, text and can have an image associated 
 * with it. Entry can also contain separate URL associated with the image.
 *
 * @author bastafidli
 */
public class Entry extends ModifiableDataObjectImpl
{
	// Inner classes ////////////////////////////////////////////////////////////
	
	public static class EntryDataDescriptor extends DataDescriptorImpl<EntryDataDescriptor.EntryFields>
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
		protected static final int ENTRY_DATA_TYPE_DESIRED_VALUE = 1002;

		/**
		 * Displayable name for specified data type code object.
		 * Protected since it can be customized and therefore code should use method
		 * exposing it rather than the constants.
		 */
		protected static final String ENTRY_DATA_TYPE_NAME = "Entry";

		/**
		 * Logical name identifying the default view for the specified data
		 * type object. Data type objects can be displayed in multiple various ways
		 * called views. This constant identifies the default one. This constant
		 * should have a value, that can be used to construct various identifiers,
		 * which means no special characters, no spaces, etc.
		 * Protected since it can be customized and therefore code should use method
		 * exposing it rather than the constants.
		 */
		protected static final String ENTRY_TYPE_VIEW = "entry";

		/**
		 * Definition of all fields that represent meaningful data for users.
		 * The order is important since it is used to retrieve all data from the
		 * persistence store efficiently so do not modify it unless you make
		 * changes to other places as well.
		 * Protected since derived classes can add more attributes and therefore code
		 * should use method exposing it rather than the constants.
		 */
		public enum EntryFields {
         // Fields from DataObject
			ENTRY_ID(ENTRY_DATA_TYPE_DESIRED_VALUE + 1),
         // Fields from BasicDataObject
			ENTRY_DOMAIN_ID(ENTRY_DATA_TYPE_DESIRED_VALUE + 2),
			ENTRY_FROM_PERSISTANCE_STORE(ENTRY_DATA_TYPE_DESIRED_VALUE + 3),
			ENTRY_CREATION_DATE(ENTRY_DATA_TYPE_DESIRED_VALUE + 4),
         // Fields from ModifiableDataObject
			ENTRY_MODIFICATION_DATE(ENTRY_DATA_TYPE_DESIRED_VALUE + 5),
         // Fields from this class
			ENTRY_FOLDER(ENTRY_DATA_TYPE_DESIRED_VALUE + 6),
			ENTRY_CAPTION(ENTRY_DATA_TYPE_DESIRED_VALUE + 7),
			ENTRY_COMMENTS(ENTRY_DATA_TYPE_DESIRED_VALUE + 8),
			ENTRY_IMAGEURL(ENTRY_DATA_TYPE_DESIRED_VALUE + 9),
			ENTRY_TARGETURL(ENTRY_DATA_TYPE_DESIRED_VALUE + 10),
			;

			private final int iValue;
			EntryFields(int id) { this.iValue = id; }
			public int getValue() { return iValue; }
		}

      // Constructors //////////////////////////////////////////////////////////
      
		public EntryDataDescriptor() throws OSSException
		{
			super(ENTRY_DATA_TYPE_DESIRED_VALUE, ENTRY_DATA_TYPE_NAME, ENTRY_TYPE_VIEW, 
					EnumSet.allOf(EntryFields.class));
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
   private static final long serialVersionUID = -2560701905097962342L;

   /**
    * Parent ID is the ID of the blog this entry belongs to.
    */
   protected long m_lParentId;

   /**
    * Caption is more descriptive name of the entry.
    */
   protected String m_strCaption;

   /**
    * Comments is any additional text for the entry.
    */
   protected String m_strComments;

   /**
    * Image URL of image associated with the entry. 
    */
   protected String m_strImageURL;

   /**
    * Target URL for the image when user clicks on the image.
    */
   protected String m_strTargetURL;

   // Constructors /////////////////////////////////////////////////////////////
   
   /**
    * Empty entry initialized to default parameters
    * 
	 * @throws OSSException - an error has occurred
    */
   public Entry(
   ) throws OSSException
   {
      this(DataObject.NEW_ID, DataObject.NEW_ID, null, null, 
           DataObject.NEW_ID,"", "", "", "");
   }

   /**
    * Empty entry for a specified domain initialized to default parameters
    *
    * @param lDomainId - Id of the domain this domain belongs to 
	 * @throws OSSException - an error has occurred
    */
   public Entry(
      long lDomainId
   ) throws OSSException
   {
      this(DataObject.NEW_ID, lDomainId, null, null, 
           DataObject.NEW_ID, "", "", "", "");
   }

   /**
    * Empty entry for a specified domain and parent initialized to default 
    * parameters
    * 
    * @param lDomainId - Id of the domain this domain belongs to 
    * @param lParentId - Id of the parent this entry belongs to
	 * @throws OSSException - an error has occurred
    */
   public Entry(
      long lDomainId,
      long lParentId
   ) throws OSSException
   {
      this(DataObject.NEW_ID, lDomainId, null, null, lParentId, "", "", "", "");
   }

   /**
    * Create entry from a given parameters.
    *
    * @param lId - Id of the entry 
    * @param lDomainId - Id of the domain this domain belongs to 
    * @param creationTimestamp - Timestamp when the entry was created
    * @param modificationTimestamp - Timestamp when the entry was last time modified
    * @param lParentId - Id of the parent this entry belongs to
    * @param strCaption - More descriptive name of the entry
    * @param strComments - Any additional text of the entry
    * @param strImageURL - Image URL of image associated with the entry
    * @param strTargetURL - Target URL for the image when user clicks on the image
	 * @throws OSSException - an error has occurred
    */
   public Entry(
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
      super(lId, EntryDataDescriptor.class, lDomainId, creationTimestamp, 
            modificationTimestamp);

      m_lParentId    = lParentId;
      m_strCaption   = strCaption;
      m_strComments  = strComments;
      m_strImageURL  = strImageURL;
      m_strTargetURL = strTargetURL;
   }

   // Accessors ////////////////////////////////////////////////////////////////

   /**
    * Id of the parent this entry belongs to.
    *
    * @return long
    */
   public long getParentId(
   )
   {
      return m_lParentId;
   }

   /**
    * Id of the parent this entry belongs to.
    *
    * @param lParentId - id of the parent this entry belongs to
    */
   public void setParentId(
      long lParentId
   )
   {
      m_lParentId = lParentId;
   }

   /**
    * Caption is more descriptive name of the entry.
    *
    * @return String
    */
   public String getCaption(
   )
   {
      return m_strCaption;
   }

   /**
    * Comments is any additional description of the entry.
    *
    * @return String
    */
   public String getComments(
   )
   {
      return m_strComments;
   }

   /**
    * Image URL of image associated with the entry. 
    *
    * @return String
    */
   public String getImageURL(
   )
   {
      return m_strImageURL;
   }

   /**
    * Target URL to display when user clicks on the image associated with this 
    * entry.
    *
    * @return String
    */
   public String getTargetURL(
   )
   {
      return m_strTargetURL;
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
      Entry   data;

      if (oObject == this)
      {
         bReturn = true;
      }
      else
      {
         if (oObject != null && oObject instanceof Entry)
         {
            data = (Entry) oObject;
            bReturn = data.getParentId() == m_lParentId
                     && ((data.getCaption() == null && m_strCaption == null)
                           || data.getCaption().equals(m_strCaption))
                     && ((data.getComments() == null && m_strComments == null)
                           || data.getComments().equals(m_strComments))
                     && ((data.getImageURL() == null && m_strImageURL == null)
                           || data.getImageURL().equals(m_strImageURL))
                     && ((data.getTargetURL() == null && m_strTargetURL == null)
                           || data.getTargetURL().equals(m_strTargetURL));
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
      append(sb, ind + 0, "Entry[", true);
      append(sb, ind + 1, "m_lParentId = ", m_lParentId);
      append(sb, ind + 1, "m_strCaption = ", m_strCaption);
      append(sb, ind + 1, "m_strComments = ", m_strComments);
      append(sb, ind + 1, "m_strImageURL = ", m_strImageURL);
      append(sb, ind + 1, "m_strTargetURL = ", m_strTargetURL);
      super.toString(sb, ind + 1);
      append(sb, ind + 0, "]", true);
   }
}
