/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:10:02
 */
package hry.exchange.account.service;

import hry.account.fund.model.FeeWithdrawalsRecord;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;


public interface FeeWithdrawalsRecordService extends BaseService<FeeWithdrawalsRecord, Long> {

	PageResult findPageBySql(QueryFilter filter);
	
}
