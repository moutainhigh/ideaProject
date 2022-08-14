/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年3月24日 上午11:43:46
 */
package hry.exchange.account.dao;

import hry.account.fund.model.FeeWithdrawalsRecord;
import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;


public interface FeeWithdrawalsRecordDao extends BaseDao<FeeWithdrawalsRecord, Long>{

	List<FeeWithdrawalsRecord> findPageBySql(Map<String, Object> map);

}
