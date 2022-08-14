/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:17:16
 */
package hry.account.remote;

import java.math.BigDecimal;
import java.util.List;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.vo.AppAccountVo;
import hry.bean.JsonResult;
import hry.util.RemoteQueryFilter;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.user.model.AppCustomer;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月1日 上午11:17:16 
 */
public interface RemoteAppAccountService {
	
	/**
	 * 开通银行账户
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param appAccount
	 * @return: JsonResult 
	 * @Date :          2016年4月1日 上午11:25:29   
	 * @throws:
	 */
	public JsonResult openAccount(AppCustomer appCustomer, AppPersonInfo appPersonInfo, String currencyType, String website);

	/**
	 * <p>查询客户下的所有账户列表</p>
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @param:    @return
	 * @return: List<AppAccount> 
	 * @Date :          2016年4月1日 下午4:48:23   
	 * @throws:
	 */
	public List<AppAccount> findByCustomerId(Long customerId);
	
	/**
	 * <p>获得客户下的对应的账户</p>
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @param:    @return
	 * @return: List<AppAccount> 
	 * @Date :          2016年4月1日 下午4:48:23   
	 * @throws:
	 */
	public AppAccount getByCustomerIdAndType(Long customerId, String currencyType, String website);

	/**
	 * <p>更新</p>
	 * @author:         Liu Shilei
	 * @param:    @param appAccount
	 * @return: void 
	 * @Date :          2016年4月6日 下午1:54:13   
	 * @throws:
	 */
	public void update(AppAccount appAccount);
	
	
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
     * <p> 支出一笔金额(从热钱包转出),允许账户为负数，应用：交易手续费</p>
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
	 * <p> 移除热账号记录</p>
	 * @author:         Gao Mimi
	 * @param:    @param account
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年5月12日 下午7:00:48   
	 * @throws:
	 */
	public void removeHotRecord(Long id);
	/**
	 * 
	 * <p> 移除冷账号记录</p>
	 * @author:         Gao Mimi
	 * @param:    @param account
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年5月12日 下午7:00:48   
	 * @throws:
	 */
	public void removeColdRecord(Long id);
	

	/**
	 * 
	 * 给某个代理商派发一条佣金  
	 * 
	 * operator 操作人 
	 * 
	 * agentName 代理商名称
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午5:44:07
	 */
	/*Boolean postCommission(BigDecimal money, String agentName, String website,
			String currencyType, String operator, String fixPriceCoinCode);*/

	
	/**
	 * 根据id 查询用户的账户表
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月9日 下午12:08:41
	 */
	AppAccount findById(Long id);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param filter
	 * @param:    @return
	 * @return: AppAccount 
	 * @Date :          2016年10月17日 下午6:45:31   
	 * @throws:
	 */
	public AppAccount get(RemoteQueryFilter filter);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param account
	 * @return: void 
	 * @Date :          2016年10月17日 下午6:48:20   
	 * @throws:
	 */
	public void save(AppAccount account);

	
	/**
	 * 
	 * 传一个用户名 和一个中国站信息 查询这个对象的资金表  
	 * 
	 * @param userName
	 * @param website
	 */
	public AppAccountVo findAccountByUserName(String userName, String website);

	/**
	 * 通过id获取对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param accountId
	 * @param:    @return
	 * @return: AppAccount 
	 * @Date :          2017年4月18日 上午11:17:45   
	 * @throws:
	 */
	public AppAccount get(Long accountId);

	public Boolean postCommission(BigDecimal money, String customerName, String website, String currencyType,
                                  String userName, String fixPriceCoinCode);

	public Boolean postCommissionNew(BigDecimal money, Long custromerId, String fixPriceCoinCode, Integer fixPriceType);

	
	
	
	
	
	

}
