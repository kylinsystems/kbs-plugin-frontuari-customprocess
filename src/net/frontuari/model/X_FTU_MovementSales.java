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

/** Generated Model for FTU_MovementSales
 *  @author iDempiere (generated) 
 *  @version Release 3.1 - $Id$ */
public class X_FTU_MovementSales extends PO implements I_FTU_MovementSales, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200624L;

    /** Standard Constructor */
    public X_FTU_MovementSales (Properties ctx, int FTU_MovementSales_ID, String trxName)
    {
      super (ctx, FTU_MovementSales_ID, trxName);
      /** if (FTU_MovementSales_ID == 0)
        {
			setFTU_MovementSales_ID (0);
			setIsSOTrx (false);
        } */
    }

    /** Load Constructor */
    public X_FTU_MovementSales (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FTU_MovementSales[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Route.
		@param BSCA_Route_ID Route	  */
	public void setBSCA_Route_ID (int BSCA_Route_ID)
	{
		if (BSCA_Route_ID < 1) 
			set_Value (COLUMNNAME_BSCA_Route_ID, null);
		else 
			set_Value (COLUMNNAME_BSCA_Route_ID, Integer.valueOf(BSCA_Route_ID));
	}

	/** Get Route.
		@return Route	  */
	public int getBSCA_Route_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BSCA_Route_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set FTU Movement Sales.
		@param FTU_MovementSales_ID FTU Movement Sales	  */
	public void setFTU_MovementSales_ID (int FTU_MovementSales_ID)
	{
		if (FTU_MovementSales_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FTU_MovementSales_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FTU_MovementSales_ID, Integer.valueOf(FTU_MovementSales_ID));
	}

	/** Get FTU Movement Sales.
		@return FTU Movement Sales	  */
	public int getFTU_MovementSales_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FTU_MovementSales_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set FTU_MovementSales_UU.
		@param FTU_MovementSales_UU FTU_MovementSales_UU	  */
	public void setFTU_MovementSales_UU (String FTU_MovementSales_UU)
	{
		set_ValueNoCheck (COLUMNNAME_FTU_MovementSales_UU, FTU_MovementSales_UU);
	}

	/** Get FTU_MovementSales_UU.
		@return FTU_MovementSales_UU	  */
	public String getFTU_MovementSales_UU () 
	{
		return (String)get_Value(COLUMNNAME_FTU_MovementSales_UU);
	}

	/** Set Sales Transaction.
		@param IsSOTrx 
		This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_ValueNoCheck (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_MovementLine getM_MovementLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_MovementLine)MTable.get(getCtx(), org.compiere.model.I_M_MovementLine.Table_Name)
			.getPO(getM_MovementLine_ID(), get_TrxName());	}

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
	}

	/** Get Move Line.
		@return Inventory Move document Line
	  */
	public int getM_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}