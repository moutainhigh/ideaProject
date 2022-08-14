/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:18:14
 */
package hry.account.remote;

import hry.account.fund.model.AppColdAccountRecord;
import hry.account.fund.service.AppColdAccountRecordService;

import javax.annotation.Resource;

/**
 * 
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月6日 下午12:15:34
 */
public class RemoteAppColdAccountRecordServiceImpl implements  RemoteAppColdAccountRecordService {
	
	@Resource
	private AppColdAccountRecordService appColdAccountRecordService;
	
	@Override
	public void save(AppColdAccountRecord appColdAccountRecord) {
		appColdAccountRecordService.save(appColdAccountRecord);
	}
	
	
	
	

}
