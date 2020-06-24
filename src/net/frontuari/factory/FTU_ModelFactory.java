package net.frontuari.factory;

import java.sql.ResultSet;

import net.frontuari.model.X_FTU_MovementSales;
import net.frontuari.model.X_I_FTUOrder;
import net.frontuari.model.X_I_FTUProduct;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;

public class FTU_ModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		if(tableName.equalsIgnoreCase(X_I_FTUOrder.Table_Name)) 
			return X_I_FTUOrder.class;
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		if(tableName.equalsIgnoreCase(X_I_FTUOrder.Table_Name)) 
			return new X_I_FTUOrder(Env.getCtx(),Record_ID, trxName);
		else if(tableName.equalsIgnoreCase(X_I_FTUProduct.Table_Name)) 
			return new X_I_FTUProduct(Env.getCtx(),Record_ID, trxName);		
		else if(tableName.equalsIgnoreCase(X_FTU_MovementSales.Table_Name)) 
			return new X_FTU_MovementSales(Env.getCtx(),Record_ID, trxName);		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		if(tableName.equalsIgnoreCase(X_I_FTUOrder.Table_Name)) 
			return new X_I_FTUOrder(Env.getCtx(),rs, trxName);
		else if(tableName.equalsIgnoreCase(X_I_FTUProduct.Table_Name)) 
			return new X_I_FTUProduct(Env.getCtx(),rs, trxName);
		else if(tableName.equalsIgnoreCase(X_FTU_MovementSales.Table_Name)) 
			return new X_FTU_MovementSales(Env.getCtx(),rs, trxName);
		return null;
	}

}
