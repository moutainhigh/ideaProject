/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:36:36
 */
package hry.exchange.account.service.impl;

import hry.account.fund.model.AppHotAccountRecord;
import hry.account.fund.service.AppHotAccountRecordService;

import hry.util.sys.ContextUtil;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.exchange.account.service.ExDmHotAccountRecordService;

import org.apache.log4j.Logger;

/**
 * 
 * @author gaomm
 *
 */
public class AccountHotRecordRunable implements Runnable {
	
	private final static Logger log = Logger.getLogger(AccountHotRecordRunable.class);
	
	private AppHotAccountRecord hotAccountRecord; 
	
	

	public AccountHotRecordRunable(AppHotAccountRecord hotAccountRecord){
		this.hotAccountRecord = hotAccountRecord;
	}
	
	@Override
	public void run() {
	
		long start = System.currentTimeMillis();
		
		AppHotAccountRecordService appHotAccountRecordService =(AppHotAccountRecordService)ContextUtil.getBean("appHotAccountRecordService");
		hotAccountRecord.setRemark(hotAccountRecord.getRemark()+"(thread)");
		appHotAccountRecordService.save(hotAccountRecord);
		long end = System.currentTimeMillis();
		//System.out.println("保存记录app热时：" + (end - start) + "毫秒");
	}
}
