/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:36:36
 */
package hry.exchange.account.service.impl;


import hry.util.sys.ContextUtil;
import hry.exchange.account.model.ExDmColdAccountRecord;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.exchange.account.service.ExDmColdAccountRecordService;
import hry.exchange.account.service.ExDmHotAccountRecordService;

import org.apache.log4j.Logger;

/**
 * 
 * @author gaomm
 *
 */
public class DmColdRecordRunable implements Runnable {
	
	private final static Logger log = Logger.getLogger(AccountHotRecordRunable.class);
	
	private ExDmColdAccountRecord exDmColdAccountRecord; //请求参数
	
	

	public DmColdRecordRunable( ExDmColdAccountRecord exDmColdAccountRecord){
		this.exDmColdAccountRecord = exDmColdAccountRecord;
	}
	
	@Override
	public void run() {
		
		long start = System.currentTimeMillis();
		
		ExDmColdAccountRecordService exDmColdAccountRecordService =(ExDmColdAccountRecordService)ContextUtil.getBean("exDmColdAccountRecordService");
		exDmColdAccountRecord.setRemark(exDmColdAccountRecord.getRemark()+"(thread)");
		exDmColdAccountRecordService.save(exDmColdAccountRecord);
		long end = System.currentTimeMillis();
	//	System.out.println("保存记录dm冷时：" + (end - start) + "毫秒");
	}
}
