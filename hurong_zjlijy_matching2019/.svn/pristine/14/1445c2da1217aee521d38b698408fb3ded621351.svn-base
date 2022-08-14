package hry.trade.model;

import java.util.List;

import hry.trade.entrust.model.ExOrderInfo;

public class SetBuyOneRunable implements Runnable {
	
	
	private List<ExOrderInfo> exorderlist;
	public SetBuyOneRunable(List<ExOrderInfo> exorderlist){
		this.exorderlist=exorderlist;
	}
	@Override
	public void run() {
		RedisLastKLine.savePeriodLastKLineList(this.exorderlist);
	}
	
}
