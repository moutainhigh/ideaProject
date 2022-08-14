/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-22 18:36:28 
 */
package hry.exchange.subscription.service;

import hry.account.fund.model.AppAccount;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.exchange.subscription.model.ExSubscriptionRecord;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



/**
 * <p> ExSubscriptionRecordService </p>
 * @author:         zenghao
 * @Date :          2016-11-22 18:36:28  
 */
public interface ExSubscriptionRecordService  extends BaseService<ExSubscriptionRecord, Long>{

	/**
	 * 回购
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param req
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年11月24日 下午3:39:00   
	 * @throws:
	 */
	public JsonResult saveBuyBack(String id, String buyBackNum);
	/**
	 * 得到当前认购数量
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param planId
	 * @param:    @return
	 * @return: List<ExSubscriptionRecord> 
	 * @Date :          2016年12月2日 下午6:48:25   
	 * @throws:
	 */
	public List<ExSubscriptionRecord> sumCurrenNum(Long planId);
	/**
	 * 用户已认购数量
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @return
	 * @return: List<ExSubscriptionRecord> 
	 * @Date :          2016年12月2日 下午7:44:32   
	 * @throws:
	 */
	public List<ExSubscriptionRecord> subTotalNum(Long planId, Long customerId);
	/**
	 * 新增认购记录
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param plan
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年12月4日 下午3:17:49   
	 * @throws:
	 */
	public JsonResult saveSubRecord(ExSubscriptionPlan plan, AppAccount appaccount, String currentPrice, String buyTotalNum, String transactionNum);
	/**
	 * 发行量。已售量
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @return
	 * @return: ExSubscriptionPlan 
	 * @Date :          2016年12月10日 上午11:12:21   
	 * @throws:
	 */
	public List<ExSubscriptionRecord> remotePlanNum();
}
