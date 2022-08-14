package hry.trade.robot.service.impl;

import java.util.List;


import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.robot.model.ExRobot;
import hry.trade.robot.service.ConmonApiService;
import hry.trade.websoketContext.PushData;
import hry.util.sys.ContextUtil;

public class HryCurrentPriceByApiRunable implements Runnable {
	
	
	private ExRobot exRobot;
	public HryCurrentPriceByApiRunable(ExRobot exRobot){
		this.exRobot=exRobot;
	}
	@Override
	public void run() {
		ConmonApiService  conmonApiService=(ConmonApiService) ContextUtil.getBean("conmonApiService");
		conmonApiService.getHryCurrentPriceByApi(exRobot);
	
	}
	
}
