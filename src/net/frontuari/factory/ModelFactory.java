package net.frontuari.factory;


import net.frontuari.base.FTUModelFactory;
import net.frontuari.model.X_I_FTUOrder;

public class ModelFactory extends FTUModelFactory {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		//registerModel(MProductionLine.Table_Name, FTUMProductionLine.class);
		registerModel(X_I_FTUOrder.Table_Name, X_I_FTUOrder.class);
	}
	
	

}
