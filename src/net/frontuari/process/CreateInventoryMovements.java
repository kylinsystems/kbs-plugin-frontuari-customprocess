package net.frontuari.process;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.frontuari.model.X_FTU_MovementSales;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.omg.CORBA.CTX_RESTRICT_SCOPE;


public class CreateInventoryMovements extends SvrProcess {

	private MMovement movementCreated = null;
	private String docAction = null;
	
	private int AD_Org_ID = 0;

	private int AD_OrgTarget_ID = 0;

	private int M_Warehouse_ID = 0;
	
	private int M_WarehouseTarget_ID = 0;

	private int M_Locator_ID = 0;

	private int M_LocatorTo_ID = 0;

	private int BSCA_Route_ID = 0;

	private int C_DocType_ID = 0;
	
	int err = 0 ;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			if (para[i].getParameterName().equals("DocAction")) {
				docAction = para[i].getParameterAsString();
			}else if (para[i].getParameterName().equals("AD_Org_ID")) {
				AD_Org_ID = para[i].getParameterAsInt();
			}else if (para[i].getParameterName().equals("AD_OrgTarget_ID")) {
				AD_OrgTarget_ID = para[i].getParameterAsInt();
			}else if (para[i].getParameterName().equals("M_Warehouse_ID")) {
				M_Warehouse_ID = para[i].getParameterAsInt();
			}else if (para[i].getParameterName().equals("M_WarehouseTarget_ID")) {
				M_WarehouseTarget_ID = para[i].getParameterAsInt();
			}else if (para[i].getParameterName().equals("M_Locator_ID")) {
				M_Locator_ID = para[i].getParameterAsInt();
			}else if (para[i].getParameterName().equals("M_LocatorTo_ID")) {
				M_LocatorTo_ID = para[i].getParameterAsInt();
			}else if (para[i].getParameterName().equals("BSCA_Route_ID")) {
				BSCA_Route_ID = para[i].getParameterAsInt();
			}else if (para[i].getParameterName().equals("C_DocType_ID")) {
				C_DocType_ID = para[i].getParameterAsInt();
			}
		}
	
	}

	@Override
	protected String doIt() throws Exception {
	
		String whereClause = "";
		if(BSCA_Route_ID>0)
			whereClause += "bsca_route_id="+BSCA_Route_ID;
		else
			whereClause += "AD_Org_ID="+AD_OrgTarget_ID;
		
		String sql = "SELECT * FROM ftu_rv_routemovementsource where "+whereClause;
				
		
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sqlid = "SELECT ad_sequence_id FROM ad_sequence WHERE name = 'FTU_MovementSales'";
		int sequenceId = DB.getSQLValue(get_TrxName(), sqlid);
		String sqli = null;
		try{
			
			pstm = DB.prepareStatement(sql, get_TrxName());
			rs = pstm.executeQuery();
			MMovement movement = new MMovement(getCtx(),0,get_TrxName());
			movement.setAD_Org_ID(AD_Org_ID);
			movement.set_ValueOfColumn("AD_OrgTarget_ID", AD_OrgTarget_ID);
			movement.setC_DocType_ID(C_DocType_ID);
			//movement.setMovementDate();
			//movement.set_ValueOfColumn("M_Locator_ID", M_Locator_ID);
			//movement.set_ValueOfColumn("M_LocatorTo_ID", M_LocatorTo_ID);
			movement.setAD_User_ID(getAD_User_ID());
			movement.saveEx(get_TrxName());
			
			
			int oldProductId = 0;
			MMovementLine line = null;
			//int productValueId = 0;
			int cuomId = 0;
			
			while (rs.next()){
				
				int productId = rs.getInt("m_product_id");		
				BigDecimal movementQty = rs.getBigDecimal("qtyordered");
				int routeId = rs.getInt("bsca_route_id");
				int orderLineId = rs.getInt("c_orderline_id");
					
				if(productId!=oldProductId){
					
					if(sqli!=null)
						DB.executeUpdate(sqli,get_TrxName());
					
					BigDecimal sumMovementQty = rs.getBigDecimal("cqtyordered");
					cuomId = rs.getInt("c_uom_id");
					
					if(line==null){
						
						line = new MMovementLine(movement);
						line.setM_Locator_ID(M_Locator_ID);
						line.setM_LocatorTo_ID(M_LocatorTo_ID);
						line.setM_Product_ID(productId);
						line.setMovementQty(sumMovementQty);
						//line.set_ValueOfColumn("BSCA_ProductValue_ID", productValueId);
						line.set_ValueOfColumn("C_UOM_ID", cuomId);
						line.saveEx(get_TrxName());
						
					}else{
						
						line = new MMovementLine(movement);
						line.setM_Locator_ID(M_Locator_ID);
						line.setM_LocatorTo_ID(M_LocatorTo_ID);
						line.setM_Product_ID(productId);
						line.setMovementQty(sumMovementQty);
						//line.set_ValueOfColumn("BSCA_ProductValue_ID", productValueId);
						line.set_ValueOfColumn("C_UOM_ID", cuomId);
						line.saveEx(get_TrxName());
					}
					
					oldProductId = productId;
					//productValueId = rs.getInt("");
					sqli =  "INSERT INTO FTU_MovementSales (ftu_movementsales_id,ftu_movementsales_uu,created,updated,createdby,updatedby,isactive,ad_client_id,ad_org_id,bsca_route_id,c_orderline_id,m_movementline_id,qty,issotrx)"
							+"VALUES (nextid("+sequenceId+",'N'),generate_uuid(),now(),now(),"+getAD_User_ID()+","+getAD_User_ID()+",'Y',"+getAD_Client_ID()+","+AD_Org_ID+","+routeId+","+orderLineId+","+line.getM_MovementLine_ID()+","+movementQty+",'Y')";
					
					
				}			
				//int movementSalesId = DB.getNextID(getAD_Client_ID(), "FTU_MovementSales", get_TrxName());
				/*X_FTU_MovementSales mSales = new X_FTU_MovementSales(getCtx(),0,get_TrxName());
				mSales.setAD_Org_ID(AD_Org_ID);
				mSales.setBSCA_Route_ID(routeId);
				mSales.setC_OrderLine_ID(orderLineId);
				mSales.setM_MovementLine_ID(line.getM_MovementLine_ID());
				mSales.setQty(movementQty);
				mSales.setIsSOTrx(true);
				
				mSales.saveEx(get_TrxName());/*/		
				sqli += ", (nextid("+sequenceId+",'N'),generate_uuid(),now(),now(),"+getAD_User_ID()+","+getAD_User_ID()+",'Y',"+getAD_Client_ID()+","+AD_Org_ID+","+routeId+","+orderLineId+","+line.getM_MovementLine_ID()+","+movementQty+",'Y')";
				
			}
			
			movement.processIt(docAction);
			movement.saveEx(get_TrxName());
			movementCreated=movement;
			
		}catch(Exception e){
			
			throw new AdempiereException(e);
			
		}finally{
			DB.close(rs);
			rs = null;
			pstm = null;
		}
				

		return movementCreated.getDocumentNo();
	}

}
