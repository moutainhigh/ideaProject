package hry.trade.model.affiliated;

import hry.core.thread.ThreadPool;
import hry.trade.entrust.model.ExOrderInfo;
import hry.util.properties.PropertiesUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

public class AffiliatedMain {
	public static void affiliated(List<ExOrderInfo> listExOrderInfo){
		String isculFee=PropertiesUtils.APP.getProperty("app.isculFee");
		if(!StringUtil.isEmpty(isculFee)&&isculFee.equals("true")){
			CulFeeRunable culFeeRunable = new CulFeeRunable(listExOrderInfo);
			ThreadPool.exe(culFeeRunable);
		}
		
	} 
}
