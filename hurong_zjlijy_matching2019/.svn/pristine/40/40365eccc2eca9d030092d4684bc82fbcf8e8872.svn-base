package hry.trade.model;

import java.util.List;

import hry.trade.entrust.model.ExOrderInfo;

public class SetSelfOneRunable implements Runnable {
	
	
	private List<ExOrderInfo> exorderlist;
	public SetSelfOneRunable(List<ExOrderInfo> exorderlist){
		this.exorderlist=exorderlist;
	}
	@Override
	public void run() {
		RedisLastKLine.savePeriodLastKLineList(this.exorderlist);
	}
	
}
