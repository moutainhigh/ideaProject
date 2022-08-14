/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service;

import java.math.BigDecimal;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppHotAccountRecord;
import hry.core.mvc.service.base.BaseService;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
public interface AppHotAccountRecordService extends BaseService<AppHotAccountRecord, Long>{

	
	/**
	 * 给某个账户添加一条流水  
	 * 
	 * type 表示怎加或减少
	 * 
	 * money 表示改变的钱数
	 * 
	 * states 0处理中 5成功 10失败 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午1:41:34
	 */
	public void addHotRecord(BigDecimal money, AppAccount appAccount, int type,
                             int states);

}
