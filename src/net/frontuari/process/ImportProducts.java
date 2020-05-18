/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz - globalqss                                           *
**********************************************************************/
package net.frontuari.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.X_I_PriceList;
import org.compiere.model.X_M_ProductPriceVendorBreak;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Import Price Lists from I_PriceList
 *
 * 	@author 	Carlos Ruiz
 *  @author 	Jorge Colmenarez, <jlct.master@gmail.com>, 2020-04-11 08:51
 */
public class ImportProducts extends SvrProcess
{
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;
	
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		StringBuilder sql = null;
		int no = 0;
		StringBuilder clientCheck = new StringBuilder(" AND AD_Client_ID=").append(m_AD_Client_ID);
		
		int m_discountschema_id = DB.getSQLValue(get_TrxName(),
				"SELECT MIN(M_DiscountSchema_ID) FROM M_DiscountSchema WHERE DiscountType='P' AND IsActive='Y' AND AD_Client_ID=?",
				m_AD_Client_ID);
		if (m_discountschema_id <= 0)
			throw new AdempiereUserError("Price List Schema not configured");

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuilder("DELETE I_FTUProduct "
				+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (log.isLoggable(Level.INFO)) log.info("Delete Old Impored =" + no);
		}

		//	Set Client, Org, IsActive, Created/Updated, EnforcePriceLimit, IsSOPriceList, IsTaxIncluded, PricePrecision
		sql = new StringBuilder("UPDATE I_FTUProduct ")
			.append("SET AD_Client_ID = COALESCE (AD_Client_ID, ").append(m_AD_Client_ID).append("),")
			.append(" AD_Org_ID = COALESCE (AD_Org_ID, 0),")
			.append(" IsActive = COALESCE (IsActive, 'Y'),")
			.append(" Created = COALESCE (Created, SysDate),")
			.append(" CreatedBy = COALESCE (CreatedBy, 0),")
			.append(" Updated = COALESCE (Updated, SysDate),")
			.append(" UpdatedBy = COALESCE (UpdatedBy, 0),")
			.append(" I_ErrorMsg = ' ',")
			.append(" I_IsImported = 'N' ")
			.append("WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.INFO)) log.info("Reset=" + no);

		//	Set Product
		sql = new StringBuilder ("UPDATE I_FTUProduct ")
			.append("SET M_Product_ID=(SELECT M_Product_ID FROM M_Product p")
			.append(" WHERE I_FTUProduct.SKU=p.SKU AND I_FTUProduct.AD_Client_ID=p.AD_Client_ID) ")
			.append("WHERE M_Product_ID IS NULL AND SKU IS NOT NULL")
			.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());

		sql = new StringBuilder ("UPDATE I_FTUProduct ")
			  .append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Not Found Product, ' ")
			  .append("WHERE M_Product_ID IS NULL AND SKU IS NOT NULL AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.INFO)) log.info("Product=" + no);
		if (no != 0)
			log.warning ("Invalid Value Product=" + no);
		//set conversion type
		sql = new StringBuilder ("UPDATE I_FTUProduct ")
		.append("SET C_ConversionType_ID=(SELECT C_ConversionType_ID FROM C_ConversionType p")
			.append(" WHERE I_FTUProduct.ConversionTypeValue=p.Value AND I_FTUProduct.AD_Client_ID=p.AD_Client_ID) ")
			.append("WHERE C_ConversionType_ID IS NULL AND ConversionTypeValue IS NOT NULL")
			.append(" AND I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (log.isLoggable(Level.INFO)) log.info("ConversionType=" + no);
		sql = new StringBuilder ("UPDATE I_FTUProduct ")
		  .append("SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Not Found ConversionType, ' ")
		  .append("WHERE C_ConversionType_ID IS NULL AND ConversionTypeValue IS NOT NULL AND I_IsImported<>'Y'").append (clientCheck);
	no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning("Invalid ConversionType=" + no);

		commitEx();
		
		//	-------------------------------------------------------------------
		int noUpdatepp = 0;

		//	Go through Records
		log.fine("start inserting/updating ...");
		sql = new StringBuilder ("SELECT * FROM I_FTUProduct WHERE  M_Product_ID > 0 AND C_ConversionType_ID > 0 AND BaseLimitPrice IS NOT NULL AND I_IsImported='N'")
			.append(clientCheck);
		PreparedStatement pstmt_setImported = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			//	Set Imported = Y
			/*pstmt_setImported = DB.prepareStatement
				("UPDATE I_FTUProduct SET I_IsImported='Y',=?, M_PriceList_Version_ID=?, "
				+ "Updated=SysDate, Processed='Y' WHERE I_PriceList_ID=?", get_TrxName());
			*/
			//
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{	//
				MProduct prod = new MProduct(getCtx(),rs.getInt("M_Product_ID"),get_TrxName());
				prod.set_ValueOfColumn("C_ConversionType_ID", rs.getInt("C_ConversionType_ID"));
				prod.set_ValueOfColumn("BaseLimitPrice", rs.getBigDecimal("BaseLimitPrice"));
				prod.set_ValueOfColumn("isregulateprice", "Y");
				
				prod.saveEx(get_TrxName());
				noUpdatepp++;
				
				sql = new StringBuilder ("UPDATE I_FTUProduct ")
				  .append("SET I_IsImported='Y', Processed='Y'")
				  .append("WHERE I_FTUProduct_ID="+rs.getInt("I_FTUProduct_ID")).append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
				commitEx();
				
			}	//	for all I_PriceList
			//
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
			//DB.close(pstmt_setImported);
			//pstmt_setImported = null;
		}

		//	Set Error to indicator to not imported
		sql = new StringBuilder ("UPDATE I_FTUProduct ")
			.append("SET I_IsImported='N', Updated=SysDate ")
			.append("WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		addLog (0, null, new BigDecimal (noUpdatepp), "Product : @Updated@");
		return "";
	}	//	doIt

}	//	ImportProduct
