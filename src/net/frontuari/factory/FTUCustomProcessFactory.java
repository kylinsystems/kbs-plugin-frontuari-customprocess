package net.frontuari.factory;

import net.frontuari.process.Aging;
import net.frontuari.process.CreateInventoryMovements;
import net.frontuari.process.ImportOrder;
import net.frontuari.process.EnableBPartnerToTrx;
import net.frontuari.process.ImportPayment;
import net.frontuari.process.ImportPriceList;
import net.frontuari.process.ImportProducts;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

public class FTUCustomProcessFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		if(className.equalsIgnoreCase(Aging.class.getName())) 
			return new Aging();
		else if(className.equalsIgnoreCase(ImportPriceList.class.getName())) 
			return new ImportPriceList();
		else if(className.equalsIgnoreCase(ImportOrder.class.getName())) 
			return new ImportOrder();
		else if(className.equalsIgnoreCase(ImportPayment.class.getName())) 
			return new ImportPayment();
		else if(className.equalsIgnoreCase(EnableBPartnerToTrx.class.getName())) 
			return new EnableBPartnerToTrx();
		else if(className.equalsIgnoreCase(ImportProducts.class.getName())) 
			return new ImportProducts();
		else if(className.equalsIgnoreCase(CreateInventoryMovements.class.getName())) 
			return new CreateInventoryMovements();
		return null;
	}

}
