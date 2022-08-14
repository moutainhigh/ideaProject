/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-22 18:36:28 
 */
package hry.exchange.subscription.dao;

import java.util.List;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.exchange.subscription.model.ExSubscriptionRecord;


/**
 * <p> ExSubscriptionRecordDao </p>
 * @author:         zenghao
 * @Date :          2016-11-22 18:36:28  
 */
public interface ExSubscriptionRecordDao extends  BaseDao<ExSubscriptionRecord, Long> {
	/**
	 * 当前认购数量
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param planId
	 * @param:    @return
	 * @return: List<ExSubscriptionRecord> 
	 * @Date :          2016年12月2日 下午6:49:13   
	 * @throws:
	 */
	public List<ExSubscriptionRecord> sumCurrenNum(Long planId);
	public List<ExSubscriptionRecord> subTotalNum(Long planId, Long customerId);
	public List<ExSubscriptionRecord> remotePlanNum();
}
