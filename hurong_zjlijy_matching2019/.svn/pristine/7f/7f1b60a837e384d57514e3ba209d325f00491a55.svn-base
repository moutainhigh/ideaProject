/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:36:36
 */
package hry.trade.account.service;

import hry.account.fund.model.AppColdAccountRecord;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

/**
 * 
 * @author gaomm
 *
 */
public class AccountColdRecordRunable implements Runnable {
	
	private final static Logger log = Logger.getLogger(AccountColdRecordRunable.class);
	
	public AppColdAccountRecord coldAccountRecord; //请求参数
	
	

	public AccountColdRecordRunable(AppColdAccountRecord coldAccountRecord){
		this.coldAccountRecord = coldAccountRecord;
	}
	
	@Override
	public void run() {
		long end = System.currentTimeMillis();
		AppColdAccountRecordService appColdAccountRecordService =(AppColdAccountRecordService)ContextUtil.getBean("appColdAccountRecordService");
		coldAccountRecord.setRemark(coldAccountRecord.getRemark()+"(thread)");
		appColdAccountRecordService.save(coldAccountRecord);
		long start = System.currentTimeMillis();
		//LogFactory.info("保存记录app冷时：" + (end - start) + "毫秒");
	}
}
