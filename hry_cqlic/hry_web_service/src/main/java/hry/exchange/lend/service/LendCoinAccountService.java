/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:10:02
 */
package hry.exchange.lend.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.vo.DigitalmoneyAccountAndProduct;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:10:02
 */
public interface LendCoinAccountService  {
	public  String getRMBCode();
	public String  getCoinCodeForRmb();
	public  BigDecimal getCurrentExchangPrice(String coinCode, String fixPriceCoinCode);
	/**
	 * 
	 * <p> 折合成基础币净资产</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: HashMap<String,BigDecimal> 
	 * @Date :          2016年5月24日 下午4:10:18   
	 * @throws:
	 */
 public BigDecimal getRMBNetAsset(Long customerId, String coinCodeForRmb, String currencyType, String website);


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
	 public BigDecimal getCanLendMoney(Long customerId, String coinCodeForRmb, String coinCode, String currencyType, String website);
	
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
	 public BigDecimal  getRMBLendMoneySum(Long customerId, String coinCodeForRmb, String currencyType, String website);
	 
	 /**
	  * 
	  * <p> 某币总负债</p>
	  * @author:         Gao Mimi
	  * @param:    @param customerId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2016年5月25日 上午10:19:10   
	  * @throws:
	  */
	 public BigDecimal  getCodeLendMoneySum(Long customerId, String coinCodeForRmb, String coinCode, String currencyType, String website);
		
	
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
      public BigDecimal getCoinNetAsset(Long customerId, String coinCodeForRmb, String coinCode, String currencyType, String website);
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
  	 public BigDecimal getCanWithdrawMoney(Long customerId, String coinCodeForRmb, String coinCode, String currencyType, String website);
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
  	 public BigDecimal getCanWithdrawCoin(Long customerId, String coinCodeForRmb, String coinCode, String currencyType, String website);
 	
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
     public BigDecimal getRMBLendMoneyInteretSum(Long customerId, String coinCodeForRmb, String currencyType, String website);
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
	    public BigDecimal netAsseToLend(Long customerId, String coinCodeForRmb, String currencyType, String website);

	

	
	    /**
		 * 
		 * <p> 得到基础币总资产</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @param coinCode
		 * @param:    @return
		 * @return: HashMap<String,BigDecimal> 
		 * @Date :          2016年5月24日 下午5:02:34   
		 * @throws:
		 */
		public BigDecimal getRMBSum(Long customerId, String coinCodeForRmb, String currencyType, String website);
		 /**
		 * 
		 * <p> 得到某币总资产</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @param coinCode
		 * @param:    @return
		 * @return: HashMap<String,BigDecimal> 
		 * @Date :          2016年5月24日 下午5:02:34   
		 * @throws:
		 */
		public BigDecimal getCoinCodeSum(Long customerId, String coinCodeForRmb, String coinCode, String currencyType, String website);
     /**
      * 
      * @return
      */
        public  int culDecimal(String coinCode);
}
