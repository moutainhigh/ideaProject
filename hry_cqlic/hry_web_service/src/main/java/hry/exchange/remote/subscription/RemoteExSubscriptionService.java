package hry.exchange.remote.subscription;

import java.util.List;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.util.RemoteQueryFilter;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.exchange.subscription.model.ExSubscriptionRecord;

public interface RemoteExSubscriptionService {

	/**
	 * 查询是否有正在认购的计划
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @return
	 * @return: ExSubscriptionPlan 
	 * @Date :          2016年12月12日 下午7:34:03   
	 * @throws:
	 */
	public ExSubscriptionPlan exPlanList(String state);
	/**
	 * 查询认购计划标
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @return
	 * @return: ExSubscriptionPlan 
	 * @Date :          2016年12月2日 下午4:05:30   
	 * @throws:
	 */
	public ExSubscriptionPlan remoteListPlan(Long customerId, String website, String currencyType);
	/**
	 * 新增认购记录
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param customerId
	 * @param:    @param planId
	 * @param:    @param currentPrice
	 * @param:    @param buyTotalNum
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年12月4日 下午3:00:42   
	 * @throws:
	 */
	public JsonResult remoteAddRecord(Long customerId, Long planId, String currentPrice, String buyTotalNum, String currencyType, String website, String accountPassword);
	/**
	 * 查询用户认购记录
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param filter
	 * @param:    @param customer
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年12月4日 下午3:53:07   
	 * @throws:
	 */
	public PageResult findListRecordPage(RemoteQueryFilter filter, Long customerId);
	/**
	 * 查询用户回购记录
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param filter
	 * @param:    @param customer
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年12月5日 下午7:26:26   
	 * @throws:
	 */
	public PageResult findListBackRecordPage(RemoteQueryFilter filter, Long customerId);
	/**
	 * 回购申请
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param request
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年12月5日 下午2:25:55   
	 * @throws:
	 */
	public JsonResult buyBackRecord(String id, String buyBackNum);
	/**
	 * 用户撤销回购
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param customreId
	 * @param:    @param backId
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年12月5日 下午6:39:35   
	 * @throws:
	 */
	public JsonResult buyBackRevoke(Long customreId, Long backId);
	/**
	 * 查询发行量。已售量
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @return
	 * @return: ExSubscriptionPlan 
	 * @Date :          2016年12月10日 上午11:09:41   
	 * @throws:
	 */
	public List<ExSubscriptionRecord> remotePlanNum();
}
