package hry.trade.model;

import org.apache.log4j.Logger;

import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.entrust.service.impl.ExOrderInfoServiceImpl;
import hry.trade.websoketContext.PushData;
import hry.util.sys.ContextUtil;

public class ExEntrutKlineRunable implements Runnable {
	
	private final Logger log = Logger.getLogger(ExOrderInfoServiceImpl.class);
	private String coinCode;
	private String fixPriceCoinCode;
	private String header;
	public ExEntrutKlineRunable(String coinCode,String fixPriceCoinCode,String header){
		this.coinCode=coinCode;
		this.fixPriceCoinCode=fixPriceCoinCode;
		this.header=header;
	}
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		ExEntrustService  entrustService=(ExEntrustService) ContextUtil.getBean("exEntrustService");
		ExOrderService  exOrderService=(ExOrderService) ContextUtil.getBean("exOrderService");
		
		// 委托-----行情板块
		String[] rn=entrustService.getExEntrustChangeMarket(coinCode,fixPriceCoinCode, 18,100);
		PushData.pushEntrusMarket(rn[0],header);
		PushData.pushEntrusMarket100(rn[1],header);
		long end1 = System.currentTimeMillis();
		//log.info(coinCode+"_"+fixPriceCoinCode+"定时委单深度：" + (end1 - start) + "毫秒");
		// 成交订单
		PushData.pushNewListRecordMarketDesc(exOrderService.getNewListMarket(header, "desc"), header);
		long end2 = System.currentTimeMillis();
		//log.info(coinCode+"_"+fixPriceCoinCode+"定时成交：" + (end2 - end1) + "毫秒");
	
	}
	
}
