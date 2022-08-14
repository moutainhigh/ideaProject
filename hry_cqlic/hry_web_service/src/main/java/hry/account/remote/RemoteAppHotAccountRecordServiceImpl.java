/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月6日 下午3:40:23
 */
package hry.account.remote;

import javax.annotation.Resource;

import hry.account.fund.model.AppHotAccountRecord;
import hry.account.fund.service.AppHotAccountRecordService;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月6日 下午3:40:23 
 */
public class RemoteAppHotAccountRecordServiceImpl implements RemoteAppHotAccountRecordService{
	
	@Resource
	private AppHotAccountRecordService appHotAccountRecordService;
	
	@Override
	public void save(AppHotAccountRecord appHotAccountRecord) {
		appHotAccountRecordService.save(appHotAccountRecord);
	}

}
