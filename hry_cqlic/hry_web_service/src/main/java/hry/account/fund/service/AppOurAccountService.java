/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service;

import java.math.BigDecimal;
import java.util.List;

import hry.account.fund.model.AppOurAccount;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.person.model.AppPersonInfo;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
public interface AppOurAccountService extends BaseService<AppOurAccount, Long>{

	/**
	 * 返回我方账户的唯一一个账户 
	 * (用户充值账户 )
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午2:06:32
	 */
	public AppOurAccount getAppOurAccount(String website, String currencyType);

	/**
	 * 给我方账户减少一笔钱
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午2:22:11
	 */
	public void postMoneyToAgent(BigDecimal money, String website, String currencyType);

	
	/**
	 * 给我方账号增加一笔钱
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月8日 下午9:03:51
	 */
	public void setMoneyToAgent(BigDecimal money, String website, String currencyType);

	/**
	 * 添加一个我方账户的方法  (包括币的账户)
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月19日 下午3:30:16
	 */
	public JsonResult saveOurAccount(AppOurAccount appOurAccount, Integer i);

	/**
	 * 查询我方账户 
	 * 
	 * type : 0 表示充值    1 表示提现
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午4:10:39
	 */
	public AppOurAccount getAppOurAccount(String website, String currencyType,
                                          Integer type);

	
	/**
	 * 给 我方币种账号 增加 或 减少一笔钱
	 * 
	 * type = 1 增加 
	 * type = 2 减少 
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午5:00:51
	 */
	public Boolean changeBitForOurAccount(BigDecimal bitCount,
                                          AppOurAccount ourAccount);

	/**
	 * 给 我方提币账号 增加 
	 * 
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午5:00:51
	 */
	public Boolean changeBitForOurWithdrawAccount(BigDecimal bitCount,
                                                  AppOurAccount ourAccount);
	
	/**
	 * 分页查找我方账户
	 * 
	 */
	public PageResult findPageResultToOurAccount(QueryFilter filter);

	/**
	 * 给推荐人推荐奖励（用户买币达到阈值）
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param orderInfo           交易信息
	 * @param:    @param agentsCustromerId	   卖币人的推荐人
	 * @param:    @param needGiveNum
	 * @return: void 
	 * @Date :          2017年3月9日 上午11:22:37   
	 * @throws:
	 */
	public boolean sendCoinToAgent(ExOrderInfo orderInfo, Long agentsCustromerId, int needGiveNum, String coinCode);

	/**
	 * 用户注册实名后给用户奖励一个币
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @return: void 
	 * @Date :          2017年3月9日 下午3:10:25   
	 * @throws:
	 */
	public void sendAuthRewardToCustomer(Long customerId, String coinCode);

	/**
	 * 卖币人给推荐人或者省市县返佣
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param personInfo    给哪个人返
	 * @param:    @param money         返多少
	 * @param:    @param orderInfo     交易信息
	 * @return: void 
	 * @Date :          2017年3月9日 下午3:38:16   
	 * @throws:
	 */
	public void sendTradefeeToAgent(AppPersonInfo personInfo, BigDecimal money, ExOrderInfo orderInfo);

	
	/**
	 * 金科比地址生成成功后，再判断一下实名返币是否成功，没成功继续返
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    
	 * @return: void 
	 * @Date :          2017年3月23日 下午7:39:13   
	 * @throws:
	 */
	public void judgeAuthRewardState(String userName, Long exdmaccountId);
	
	public void tradeIncomeFee(String orderNum);
	
	
}
