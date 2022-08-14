/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:36:36
 */
package hry.trade.account.service;

import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author gaomm
 *
 */
public class DmHotRecordRunable implements Runnable {
	
	private final static Logger log = Logger.getLogger(AccountHotRecordRunable.class);
	
	private ExDmHotAccountRecord exDmHotAccountRecord; 
	
	

	public DmHotRecordRunable( ExDmHotAccountRecord exDmHotAccountRecord){
		this.exDmHotAccountRecord = exDmHotAccountRecord;
	}
	
	@Override
	public void run() {
		
		long start = System.currentTimeMillis();
		ExDmHotAccountRecordService exDmHotAccountRecordService =(ExDmHotAccountRecordService)ContextUtil.getBean("exDmHotAccountRecordService");
		exDmHotAccountRecord.setRemark(exDmHotAccountRecord.getRemark()+"(thread)");
		exDmHotAccountRecordService.save(exDmHotAccountRecord);
		long end = System.currentTimeMillis();
		//LogFactory.info("保存记录dm热时：" + (end - start) + "毫秒");
	}
}
