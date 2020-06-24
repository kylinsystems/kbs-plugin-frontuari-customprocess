package net.frontuari.process;


import java.util.ArrayList;
import java.util.List;


import org.compiere.model.MOrder;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;


public class CompleteOrdersEcommerce extends SvrProcess {

	private List<MOrder> orders = new ArrayList<MOrder>();
	private String docAction = null;
	int err = 0 ;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			if (para[i].getParameterName().equals("DocAction")) {
				docAction = para[i].getParameterAsString();
			}
		}
		

		/** 	Orders	 **/
		String where = " C_Order_ID IN "
			+ " (SELECT T_Selection_ID FROM T_Selection WHERE AD_PInstance_ID="
			+getAD_PInstance_ID()+")";
		orders = MOrder.get(
				MOrder.Table_Name, getCtx(), where, "", get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		
		for(MOrder order:orders) {
			
			if(order.processIt(docAction)){
				order.setSalesRep_ID(getAD_User_ID());
				order.setAD_User_ID(getAD_User_ID());
				order.saveEx(get_TrxName());
			}else {
				err++;
			}
			
			
		}
		return "Procesado Ordenes:"+orders+", Errores:"+err;
	}

}
