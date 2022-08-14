/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月30日 下午3:10:39
 */
package hry.change.remote.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import hry.bean.PageResult;
import hry.util.RemoteQueryFilter;
import hry.exchange.account.model.AppAccountDisable;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.vo.DigitalmoneyAccountAndProduct;
import hry.exchange.transaction.model.ExDmCustomerPublicKey;
import hry.pakgclass.OrderParameter;

/**
 * <p>
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月30日 下午3:10:39
 */
public interface RemoteExDigitalmoneyAccountService {
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
	 * <p> 并发保存</p>
	 * @author:         Gao Mimi
	 * @param:    @param account
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年5月12日 下午7:00:48   
	 * @throws:
	 */
	public String[] updateAccount(ExDigitalmoneyAccount account);
	
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @param currencyType
	 * @param:    @return
	 * @return: ExDigitalmoneyAccount 
	 * @Date :          2016年4月20日 下午4:41:30   
	 * @throws:
	 */
	public ExDigitalmoneyAccount getByCustomerIdAndType(Long customerId, String coinCode, String currencyType, String website);

	// 根据公钥查询用户的虚拟账户的信息
	public ExDigitalmoneyAccount findByString(String s);

	// 根据用户的id查询虚拟账户的信息
	public ExDigitalmoneyAccount findByCustomerType(Long id, String coinCode, String currencyType, String website);

	// 查询用户的虚拟币账户的信息
	public List<ExDigitalmoneyAccount> findExDigitalmoneyAccountById(Long id);

	// 保存一条虚拟币账户的信息
	public void save(ExDigitalmoneyAccount exDigitalmoneyAccount);

	// 提交充值订单
	public String putOrder(OrderParameter orderParameter);

	// 提交提现订单
	public String setOrder(OrderParameter orderParameter);
	// 提交提现订单
	public String setOrderAppAccount(OrderParameter orderParameter);
	// 保存冻结账户流水
	public void setColdRecord(OrderParameter orderParameter, Integer i,
                              String orderId);

	// 保存可用账户流水
	public void setHotRecord(OrderParameter orderParameter, Integer i,
                             String orderId);

	// 查询订单的详细信息
	public PageResult findBtcRecordByString(RemoteQueryFilter Rfilter, Long id);

	// 保存一条提现账户
	public String savePublicManage(ExDmCustomerPublicKey e);

	// 通过用户id查询它所对应的所有的提现账户
	public List<ExDmCustomerPublicKey> getPublicByCustomerId(Long l);

	// 判断当前公钥是否存在
	public boolean judgePublicBypublicKey(String s, String customerName);

	// 通过用户名或者id查询当前用户的所有的添加的虚拟账户
	public List<ExDmCustomerPublicKey> findPublicByType(Long id, String type);
	

	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param Rfilter
	 * @param:    @param id
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年5月18日 上午11:24:20   
	 * @throws:
	 */
	public PageResult findRecord(RemoteQueryFilter Rfilter);
	/**
	 * 
	 * <p> 撤销提现记录</p>
	 * @author:         Gao Mimi
	 * @param:    @param filter
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年5月18日 上午11:41:36   
	 * @throws:
	 */
	public String[] cancelRecord(RemoteQueryFilter filter);
	/**
	 * 
	 * <p> 得到所有币净资产</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: HashMap<String,BigDecimal> 
	 * @Date :          2016年5月24日 下午4:10:18   
	 * @throws:
	 */
	public HashMap<String,BigDecimal> getAllNetAsset(Long customerId, String coinCode, String currencyType, String website);
	/**
	 * 
	 * <p> 得到某币净资产</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @param currencyType
	 * @param:    @return
	 * @return: HashMap<String,BigDecimal> 
	 * @Date :          2016年5月24日 下午5:02:34   
	 * @throws:
	 */
	public HashMap<String, BigDecimal> getByTypeNetAsset(Long customerId, String coinCode, String currencyType, String website);
	/**
	 * 
	 * <p> 所有币能借多少</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @param currencyType
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2016年5月25日 上午10:18:03   
	 * @throws:
	 */
	 public HashMap<String,BigDecimal> getAllCanLendMoney(Long customerId, String coinCode, String currencyType, String website);
	 /**
	  * 
	  * <p> 所有币种已借入</p>
	  * @author:         Gao Mimi
	  * @param:    @param customerId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2016年5月25日 上午10:19:10   
	  * @throws:
	  */
	 public HashMap<String,BigDecimal>  getAllLendMoney(Long customerId, String coinCode, String currencyType, String website);
	/**
	 * 
	 * <p> 某个币种能借多少</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @param currencyType
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2016年5月25日 上午10:18:03   
	 * @throws:
	 */
	 public BigDecimal getCanLendMoney(Long customerId, String coinCode, String currencyType, String website);
	
	 /**
	  * 
	  * <p> 总负责折合成RMB</p>
	  * @author:         Gao Mimi
	  * @param:    @param customerId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2016年5月25日 上午10:19:10   
	  * @throws:
	  */
	 public BigDecimal  getRMBLendMoneySum(Long customerId, String coinCode, String currencyType, String website);
		
		/**
		 * 
		 * <p> 折合成RMB净资产</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @return
		 * @return: HashMap<String,BigDecimal> 
		 * @Date :          2016年5月24日 下午4:10:18   
		 * @throws:
		 */
  public BigDecimal getRMBNetAsset(Long customerId, String coinCode, String currencyType, String website);
  
  /**
   * 根据传入一个钱  和一个用户的id 判断用户的所提现的钱 与用户实际可取的钱是否小于或等于这个数
   * 
   * @author:    Wu Shuiming
   * @version:   V1.0 
   * @date:      2016年7月23日 上午10:03:35
   */
  public Boolean judgeMoneyByCustromerId(BigDecimal money, Long customerId, String coinCode, String currencyType, String website);

  /**
   * 根据用户的id 以及所传的币的类型 返回用户是否可以提现某个币的数量
   * @author:    Wu Shuiming
   * @version:   V1.0 
   * @date:      2016年7月23日 上午10:38:16
   */
  public Boolean judgeCurrencyByCustromerId(BigDecimal currencyNum, Long customerId, String coinCode);

  /**
   * 通过用的名字以及站信息  查询用户的虚拟币账号  以及产品的信息 
   * 
   * @author:    Wu Shuiming
   * @version:   V1.0 
   * @date:      2016年8月1日 下午7:12:51
   */
  public List<DigitalmoneyAccountAndProduct> findDigtalAndProductByWebsite(String webstile, String customerName, Integer isMarket);
  
  public  List<ExDigitalmoneyAccount> getlistByCustomerId(Long customerId);
  
  public List<ExDigitalmoneyAccount> find(RemoteQueryFilter remoteQueryFilter);

  // 删除某个人所绑定的公钥   
  public Boolean removePublickey(String publicKey, String customerName);
  
  /**
   * 
   * <p> 获取详细信息 通过帐号 和 地址</p>
   * @author:         Yuan Zhicheng
   * @param:    @param remoteQueryFilter
   * @param:    @return
   * @return: ExDigitalmoneyAccount 
   * @Date :          2016年11月15日 下午10:00:33   
   * @throws:
   */
  public ExDigitalmoneyAccount getInfoByAccountAndAddress(String account, String address);
  /**
   * 
   * <p>持仓汇总</p>
   * @author:         Yuan Zhicheng
   * @param:    @param remoteQueryFilter
   * @param:    @return
   * @return: ExDigitalmoneyAccount 
   * @Date :          2016年11月15日 下午10:00:33   
   * @throws:
   */
  public List<ExDigitalmoneyAccount> positionSummary(Long CustomerId);
  
  /**
   * 
   * <p>持仓汇总</p>
   * @author:         Yuan Zhicheng
   * @param:    @param remoteQueryFilter
   * @param:    @return
   * @return: ExDigitalmoneyAccount 
   * @Date :          2016年11月15日 下午10:00:33   
   * @throws:
   */
  public List<AppAccountDisable> findAppAccountDisable(RemoteQueryFilter remoteQueryFilter);
  

List<ExDigitalmoneyAccount> findByCustomerId(Long customerId);

public BigDecimal getCoinCold(Long customerId, String coinCode, String currencyType, String website);

}
