package hry.trade.robot.service.impl;

import hry.trade.robot.model.ExRobot;
import hry.trade.robot.service.ConmonApiService;
import hry.util.sys.ContextUtil;

public class BittrexCurrentPriceByApiRunable implements Runnable {
	
	
	private ExRobot exRobot;
	public BittrexCurrentPriceByApiRunable(ExRobot exRobot){
		this.exRobot=exRobot;
	}
	@Override
	public void run() {
		ConmonApiService  conmonApiService=(ConmonApiService) ContextUtil.getBean("conmonApiService");
		conmonApiService.getBittrexCurrentPriceByApi(exRobot);
	
	}
	
}
