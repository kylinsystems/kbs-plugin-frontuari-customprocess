/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package net.frontuari.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for I_FTUProduct
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_I_FTUProduct extends PO implements I_I_FTUProduct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200509L;

    /** Standard Constructor */
    public X_I_FTUProduct (Properties ctx, int I_FTUProduct_ID, String trxName)
    {
      super (ctx, I_FTUProduct_ID, trxName);
      /** if (I_FTUProduct_ID == 0)
        {
			setBaseLimitPrice (Env.ZERO);
// 0
			setI_FTUProduct_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_FTUProduct (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_I_FTUProduct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Base Limit Price.
		@param BaseLimitPrice 
		Base Limit Price
	  */
	public void setBaseLimitPrice (BigDecimal BaseLimitPrice)
	{
		set_Value (COLUMNNAME_BaseLimitPrice, BaseLimitPrice);
	}

	/** Get Base Limit Price.
		@return Base Limit Price
	  */
	public BigDecimal getBaseLimitPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_BaseLimitPrice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_ConversionType getC_ConversionType() throws RuntimeException
    {
		return (org.compiere.model.I_C_ConversionType)MTable.get(getCtx(), org.compiere.model.I_C_ConversionType.Table_Name)
			.getPO(getC_ConversionType_ID(), get_TrxName());	}

	/** Set Currency Type.
		@param C_ConversionType_ID 
		Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID)
	{
		if (C_ConversionType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ConversionType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ConversionType_ID, Integer.valueOf(C_ConversionType_ID));
	}

	/** Get Currency Type.
		@return Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ConversionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Currency Type Key.
		@param ConversionTypeValue 
		Key value for the Currency Conversion Rate Type
	  */
	public void setConversionTypeValue (String ConversionTypeValue)
	{
		set_Value (COLUMNNAME_ConversionTypeValue, ConversionTypeValue);
	}

	/** Get Currency Type Key.
		@return Key value for the Currency Conversion Rate Type
	  */
	public String getConversionTypeValue () 
	{
		return (String)get_Value(COLUMNNAME_ConversionTypeValue);
	}

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set I_FTUProduct.
		@param I_FTUProduct_ID I_FTUProduct	  */
	public void setI_FTUProduct_ID (int I_FTUProduct_ID)
	{
		if (I_FTUProduct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_FTUProduct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_FTUProduct_ID, Integer.valueOf(I_FTUProduct_ID));
	}

	/** Get I_FTUProduct.
		@return I_FTUProduct	  */
	public int getI_FTUProduct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_FTUProduct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set I_FTUProduct_UU.
		@param I_FTUProduct_UU I_FTUProduct_UU	  */
	public void setI_FTUProduct_UU (String I_FTUProduct_UU)
	{
		set_Value (COLUMNNAME_I_FTUProduct_UU, I_FTUProduct_UU);
	}

	/** Get I_FTUProduct_UU.
		@return I_FTUProduct_UU	  */
	public String getI_FTUProduct_UU () 
	{
		return (String)get_Value(COLUMNNAME_I_FTUProduct_UU);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Product Name.
		@param ProductName 
		Name of the Product
	  */
	public void setProductName (String ProductName)
	{
		set_ValueNoCheck (COLUMNNAME_ProductName, ProductName);
	}

	/** Get Product Name.
		@return Name of the Product
	  */
	public String getProductName () 
	{
		return (String)get_Value(COLUMNNAME_ProductName);
	}

	/** Set SKU.
		@param SKU 
		Stock Keeping Unit
	  */
	public void setSKU (String SKU)
	{
		set_ValueNoCheck (COLUMNNAME_SKU, SKU);
	}

	/** Get SKU.
		@return Stock Keeping Unit
	  */
	public String getSKU () 
	{
		return (String)get_Value(COLUMNNAME_SKU);
	}
}