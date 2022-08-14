/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:10:02
 */
package hry.exchange.account.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.vo.DigitalmoneyAccountAndProduct;
import hry.exchange.product.model.ExProduct;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:10:02
 */
public interface ExDigitalmoneyAccountService extends
		BaseService<ExDigitalmoneyAccount, Long> {
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

	public ExDigitalmoneyAccount findByOrderId(Long id);
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
	 * 是否允许更新到数据库  0，不允许更新到数据库，null或者1更新到数据库
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param id
	 * @param:    @param payMoney
	 * @param:    @param transactionNum
	 * @param:    @param remark
	 * @param:    @param isculAppAccount
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2017年7月17日 下午3:13:58   
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
	public String[] updateAccount(ExDigitalmoneyAccount account);

	/**
	 * 
	 * <p> 所有币净资产</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: HashMap<String,BigDecimal> 
	 * @Date :          2016年5月24日 下午4:10:18   
	 * @throws:
	 */
	public HashMap<String,BigDecimal> getAllNetAsset(Long customerId, String currencyType, String website);
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
	 public HashMap<String,BigDecimal>  getAllLendMoney(Long customerId, String currencyType, String website);
	/**
	 * 
	 * <p> 得到某币净资产</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: HashMap<String,BigDecimal> 
	 * @Date :          2016年5月24日 下午5:02:34   
	 * @throws:
	 */
	public HashMap<String, BigDecimal> getByTypeNetAsset(Long customerId, String coinCode, String currencyType, String website);
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
	 public BigDecimal  getRMBLendMoneySum(Long customerId, String currencyType, String website);
	 
		
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
     public BigDecimal getRMBNetAsset(Long customerId, String currencyType, String website);

		
		/**
		 * 
		 * <p> 折合成某币净资产</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @return
		 * @return: HashMap<String,BigDecimal> 
		 * @Date :          2016年5月24日 下午4:10:18   
		 * @throws:
		 */
      public BigDecimal getCoinNetAsset(Long customerId, String coinCode, String currencyType, String website);
      /**
       * 
       * <p> 可提现金额</p>
       * @author:         Gao Mimi
       * @param:    @param customerId
       * @param:    @param currencyType
       * @param:    @return
       * @return: BigDecimal 
       * @Date :          2016年5月26日 下午3:32:27   
       * @throws:
       */
  	 public BigDecimal getCanWithdrawMoney(Long customerId, String coinCode, String currencyType, String website);
  	 /**
  	  * 
  	  * <p> 可提现币</p>
  	  * @author:         Gao Mimi
  	  * @param:    @param customerId
  	  * @param:    @param coinCode
  	  * @param:    @return
  	  * @return: BigDecimal 
  	  * @Date :          2016年5月26日 下午3:34:39   
  	  * @throws:
  	  */
  	 public BigDecimal getCanWithdrawCoin(Long customerId, String coinCode, String currencyType, String website);
 	
		/**
		 * 
		 * <p> 所欠利息折合成RMB</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @return
		 * @return: HashMap<String,BigDecimal> 
		 * @Date :          2016年5月24日 下午4:10:18   
		 * @throws:
		 */
     public BigDecimal getRMBLendMoneyInteretSum(Long customerId, String currencyType, String website);
     /**
 	 * 
 	 * <p> 所欠利息</p>
 	 * @author:         Gao Mimi
 	 * @param:    @param customerId
 	 * @param:    @param coinCode
 	 * @param:    @return
 	 * @return: HashMap<String,BigDecimal> 
 	 * @Date :          2016年5月24日 下午5:02:34   
 	 * @throws:
 	 */
 	public HashMap<String, BigDecimal> getByAllInteret(Long customerId, String coinCode, String currencyType, String website);
     public List<ExDigitalmoneyAccount> getByCustomerId(Long customerId, String currencyType, String website);
	    /**
	     * 
	     * <p> 资产/杠杆：比例</p>
	     * @author:         Gao Mimi
	     * @param:    @param customerId
	     * @param:    @return
	     * @return: BigDecimal 
	     * @Date :          2016年6月27日 下午5:38:18   
	     * @throws:
	     */
	    public BigDecimal netAsseToLend(Long customerId, String currencyType, String website);

	    /**
		 * 
		 * 查询用户的名字查询用户虚拟账号包括产品的信息
		 * 
		 * @author:    Wu Shuiming
		 * @version:   V1.0 
		 * @date:      2016年8月1日 下午6:59:54
		 */
		public List<DigitalmoneyAccountAndProduct> findDigitalAndProduct(String website,
                                                                         String cutomerName, Integer isMarket);

		/**
		 * <p>后台分页查询</p>
		 * @author:         Liu Shilei
		 * @param:    @param filter
		 * @param:    @return
		 * @return: PageResult 
		 * @Date :          2016年8月13日 下午6:04:33   
		 * @throws:
		 */
		public PageResult findPageBySql(QueryFilter filter);
		
		public  List<ExDigitalmoneyAccount> getlistByCustomerId(Long customerId);
		/**
		 * 禁用
		 * <p> TODO</p>
		 * @author:         Zeng Hao
		 * @param:    @param id
		 * @param:    @param disableMoey
		 * @param:    @return
		 * @return: JsonResult 
		 * @Date :          2016年11月25日 下午5:29:21   
		 * @throws:
		 */
		public JsonResult disableMoney(Long id, String disableMoey);
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

		public void saveMoney(BigDecimal money, String agentName, String website, String currencyType, String operator, String fixPriceCoinCode);
		
		
		
		public BigDecimal getCoinClod(Long customerId, String currencyType, String website);
		
		/**
		 * 注册送币，邀请送币
		 * //记账，增加订单，增加流水
		 * @param eda
		 * @param type 1注册，2邀请
		 */
		public void saveRecord(ExDigitalmoneyAccount eda, int type);

		public void saveMoney(BigDecimal money, Long custromerId, String fixPriceCoinCode);

		/**
		 * 新sql查询功能
		 * @param filter
		 * @return
		 */
		PageResult findPageBySqlList(QueryFilter filter);

    void saveRecordAfterAudit(ExDigitalmoneyAccount digitalmoneyAccount, int i, ExProduct exProduct);
}
