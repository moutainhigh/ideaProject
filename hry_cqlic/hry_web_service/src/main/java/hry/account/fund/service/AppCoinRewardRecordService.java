package hry.account.fund.service;

import hry.account.fund.model.AppCoinRewardRecord;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 下午1:45:43
 */
public interface AppCoinRewardRecordService extends BaseService<AppCoinRewardRecord, Long>{

	PageResult findPageBySql(QueryFilter filter);
	
}
