package hry.calculate.listener;

import hry.calculate.mvc.StartupSettlement;
import hry.core.listener.StartupService;

public class StartupManage implements StartupService {
	
	public final static String AppName = "hurong_calculate";
	public final static String AppKey = "calculate";
	
	@Override
	public void start() {

		StartupSettlement.SettlementJob();
	}
	
	
	
	
}
