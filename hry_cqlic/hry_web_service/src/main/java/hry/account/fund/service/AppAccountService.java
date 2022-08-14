/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service;

import java.math.BigDecimal;
import java.util.List;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.vo.AppAccountVo;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.account.model.ExDigitalmoneyAccount;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
public interface AppAccountService extends BaseService<AppAccount, Long>{
	/**
	 * 
	 * <p> 冻结金额（从自己的热钱包转到自己的冷钱包）</p>
	 * @author:         Gao Mimi
	 * @param:    @param appAccount
	 * @param:    @param freezeMoney
	 * @param:    @param transactionNum
	 * @param:    @param remark
	 * @return: void 
	 * @Date :          2016年4月20日 下午5:37:13   
	 * @throws:
	 */
	public String[] freezeAccountSelf(Long id, BigDecimal freezeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
     /**
      * 
      * <p> 解冻金额到自己的账户（从自己的冷钱包转到自己的热钱包）</p>
      * @author:         Gao Mimi
      * @param:    @param appAccount
      * @param:    @param freezeMoney
      * @param:    @param transactionNum
      * @param:    @param remark
      * @return: void 
      * @Date :          2016年4月20日 下午5:37:22   
      * @throws:
      */
	public String[] unfreezeAccountSelf(Long id, BigDecimal unfreezeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
	/**
	 * 
	 * <p> 解冻金额到别人的账户（从自己的冷钱包转到别人的热钱包）</p>
	 * @author:         Gao Mimi
	 * @param:    @param appAccount
	 * @param:    @param freezeMoney
	 * @param:    @param transactionNum
	 * @param:    @param remark
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年4月21日 上午10:44:08   
	 * @throws:
	 */
	public String[] unfreezeAccountThem(Long id, BigDecimal unfreezeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
	/**
	 * 
	 * <p> 收入一笔金额(转入热钱包)</p>
	 * @author:         Gao Mimi
	 * @param:    @param appAccount
	 * @param:    @param unfreezeMoney
	 * @param:    @param transactionNum
	 * @param:    @param remark
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年4月21日 上午10:52:11   
	 * @throws:
	 */
	
	public String[] inComeToHotAccount(Long id, BigDecimal incomeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
    /**
     * 
     * <p> 支出一笔金额(从热钱包转出)</p>
     * @author:         Gao Mimi
     * @param:    @param appAccount
     * @param:    @param unfreezeMoney
     * @param:    @param transactionNum
     * @param:    @param remark
     * @param:    @return
     * @return: String[] 
     * @Date :          2016年4月21日 上午10:54:51   
     * @throws:
     */
	public String[] payFromHotAccount(Long id, BigDecimal payMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
	
	 /**
     * 
     * <p> 慎用！！！支出一笔金额(从热钱包转出),允许账户为负数，应用：交易手续费</p>
     * @author:         Gao Mimi
     * @param:    @param appAccount
     * @param:    @param unfreezeMoney
     * @param:    @param transactionNum
     * @param:    @param remark
     * @param:    @return
     * @return: String[] 
     * @Date :          2016年4月21日 上午10:54:51   
     * @throws:
     */
	public String[] payFromHotAccountNegative(Long id, BigDecimal payMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
	/**
	 * 
	 * <p> 并发保存</p>
	 * @author:         Gao Mimi
	 * @param:    @param account
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年5月12日 下午7:00:48   
	 * @throws:
	 */
	public String[] updateAccount(AppAccount appAccount);
	
	
	/**
	 * 
	 * 给某个人保存派发一条佣金
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午5:32:57
	 */
	public void saveMoney(BigDecimal money, String agentName, String website,
                          String currencyType, String operator);
	
	/**
	 * <p>后台分页查询</p>
	 * @author:         Liu Shilei
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年8月13日 下午4:53:32   
	 * @throws:
	 */
	public PageResult findPageBySql(QueryFilter filter);
	
	 /**
     * 
     * <p> 解冻到热账户,允许账户为负数，应用：撤销委托</p>
     * @author:         Gao Mimi
     * @param:    @param appAccount
     * @param:    @param unfreezeMoney
     * @param:    @param transactionNum
     * @param:    @param remark
     * @param:    @return
     * @return: String[] 
     * @Date :          2016年4月21日 上午10:54:51   
     * @throws:
     */
	public String[] unfreezeAccountSelfCancelExEntrust(Long id, BigDecimal unfreezeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);

	/**
	 * 
	 * <p> 专门给给成交用的！！！！；解冻金额到别人的账户（从自己的冷钱包转到别人的热钱包）</p>
	 * @author:         Gao Mimi
	 * @param:    @param appAccount
	 * @param:    @param freezeMoney
	 * @param:    @param transactionNum
	 * @param:    @param remark
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年4月21日 上午10:44:08   
	 * @throws:
	 */
	public String[] unfreezeAccountThemBuyTranstion(Long id, BigDecimal unfreezeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
	
	
	
	/**
	 * 传一个用户名和一个站点信息 查询这个人的资金账户信息 
	 * 
	 * @param userName
	 * @param website
	 */
	public AppAccountVo findAccountByUserName(String userName, String website);
	  /**
     * 
     * <p> 解冻金额到lendMoney</p>
     * @author:         Gao Mimi
     * @param:    @param appAccount
     * @param:    @param freezeMoney
     * @param:    @param transactionNum
     * @param:    @param remark
     * @return: void 
     * @Date :          2016年4月20日 下午5:37:22   
     * @throws:
     */
	public String[] unfreezeAccountSelfLendMoney(Long id, BigDecimal unfreezeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);
	  /**
     * 
     * <p> 释放保证金</p>
     * @author:         Gao Mimi
     * @param:    @param appAccount
     * @param:    @param freezeMoney
     * @param:    @param transactionNum
     * @param:    @param remark
     * @return: void 
     * @Date :          2016年4月20日 下午5:37:22   
     * @throws:
     */
	public String[] LendMoneyToHotMoneyAccountSelf(Long id, BigDecimal unfreezeMoney, String transactionNum, String remark, Integer isculAppAccount, Integer isImmediatelySaveRecord);

	/**
	  * 金科添加，查找所有的代理商
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2017年3月11日 上午10:37:41   
	 * @throws:
	 */
	public PageResult findAgentPageBySql(QueryFilter filter);
	
	
	public void saveMoney(BigDecimal money, Long custromerId, String fixPriceCoinCode);
	void saveMoneyCoin(BigDecimal money, Long custromerId, String fixPriceCoinCode);
	
	/**
	 * 新sql查询功能
	 * @param filter
	 * @return
	 */
	PageResult findPageBySqlList(QueryFilter filter);
}
