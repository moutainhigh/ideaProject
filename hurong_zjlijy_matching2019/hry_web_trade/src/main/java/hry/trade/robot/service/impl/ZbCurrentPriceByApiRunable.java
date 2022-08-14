package hry.trade.robot.service.impl;

import hry.trade.robot.model.ExRobot;
import hry.trade.robot.service.ConmonApiService;
import hry.util.sys.ContextUtil;

public class ZbCurrentPriceByApiRunable implements Runnable {
	
	
	private ExRobot exRobot;
	public ZbCurrentPriceByApiRunable(ExRobot exRobot){
		this.exRobot=exRobot;
	}
	@Override
	public void run() {
		ConmonApiService  conmonApiService=(ConmonApiService) ContextUtil.getBean("conmonApiService");
		conmonApiService.getzbCurrentPriceByApi(exRobot);
	
	}
	
}
