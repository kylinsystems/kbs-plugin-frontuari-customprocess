package net.frontuari.factory;

import net.frontuari.process.Aging;
import net.frontuari.process.ImportPriceList;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

public class FTUCustomProcessFactory implements IProcessFactory {

	@Override
	public ProcessCall newProcessInstance(String className) {
		if(className.equalsIgnoreCase(Aging.class.getName())) 
			return new Aging();
		else if(className.equalsIgnoreCase(ImportPriceList.class.getName())) 
			return new ImportPriceList();
		
		return null;
	}

}
