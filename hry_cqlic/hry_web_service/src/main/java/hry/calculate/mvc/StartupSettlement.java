/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年8月10日 下午8:06:46
 */
package hry.calculate.mvc;

import tk.mybatis.mapper.util.StringUtil;


import hry.calculate.mvc.service.AppReportSettlementService;
import hry.util.sys.ContextUtil;
import hry.trade.entrust.DifCustomer;
import hry.web.remote.RemoteAppConfigService;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年8月10日 下午8:06:46 
 */
public class StartupSettlement {
	public static void SettlementJob(){
		//判断是否需要开启闭盘
		if(DifCustomer.isClosePlate()){
			AppReportSettlementService appReportSettlementService=(AppReportSettlementService)ContextUtil.getBean("appReportSettlementService");
			//收盘时间往后延2分钟  处理交易数
			RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
			String openAndclosePlateTime=remoteAppConfigService.getFinanceByKey("openAndclosePlateTime");
			if(!StringUtil.isEmpty(openAndclosePlateTime)){
				appReportSettlementService.changeClosePlateTime(openAndclosePlateTime);
			}
		  }
	}
}
