/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.calculate.remote.settlement;

import java.util.Date;
import java.util.List;

import hry.calculate.settlement.model.AppOrderDistributionVo;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;


/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月23日 下午6:44:50
 */

public  interface  RemoteAppReportSettlementService {
	/**
	 * 
	 * <p> 是否结算单</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @param currencyType
	 * @param:    @param website
	 * @param:    @return
	 * @return: Integer 
	 * @Date :          2016年8月10日 下午7:02:30   
	 * @throws:
	 */
		public Integer getIsShowExSettlementFinance(Long customerId, String currencyType, String website);
		/**
		 * 
		 * <p> 确认结算单</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @param currencyType
		 * @param:    @param website
		 * @param:    @return
		 * @return: Integer 
		 * @Date :          2016年8月10日 下午7:02:36   
		 * @throws:
		 */
		public Integer confirmExSettlementFinance(Long settid, String currencyType, String website);
		
	  
	    
	/*	*//**
		 * 
		 * <p> 查询结算单</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @param currencyType
		 * @param:    @param website
		 * @param:    @return
		 * @return: Integer 
		 * @Date :          2016年8月10日 下午7:02:36   
		 * @throws:
		 */
		public AppReportSettlement selectExSettlementFinance(Long customerId, String currencyType, String website);
		
		/**
		 * 
		 * <p> 查询结算单币</p>
		 * @author:         Gao Mimi
		 * @param:    @param customerId
		 * @param:    @param currencyType
		 * @param:    @param website
		 * @param:    @return
		 * @return: Integer 
		 * @Date :          2016年8月10日 下午7:02:36   
		 * @throws:
		 */
		public AppReportSettlementcoin selectExSettlementcoinBycoincode(
                Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString);
		public List<AppReportSettlementcoin> selectExSettlementcoin(
                Long customerId, String currencyType, String website, String beginDateString, String endDateString);
		 /***
		  * 
		  * <p> 结算单最后一条数据</p>
		  * @author:         Gao Mimi
		  * @param:    @return
		  * @return: AppReportSettlement 
		  * @Date :          2016年9月6日 上午11:56:26   
		  * @throws:
		  */
		 public AppReportSettlement getLastSettlementByCustomerId(Long customerId,
                                                                  String currencyType, String website);
		 
			/**
			 * 
			 * <p> 产品如果改变了收盘时间就触发这五个发放</p>
			 * @author:         Gao Mimi
			 * @param:    @param openAndclosePlateTime
			 * @return: void 
			 * @Date :          2016年8月11日 上午10:15:11   
			 * @throws:
			 */
			public void changeClosePlateTime(String openAndclosePlateTime);
			/**
			 * 
			 * <p> 产品如果改变了闭市时间就触发这五个发放</p>
			 * @author:         Gao Mimi
			 * @param:    @param closeTime
			 * @return: void 
			 * @Date :          2016年8月11日 上午10:15:16   
			 * @throws:
			 */
			public void changeCloseTime(String closeTime);
			
			public AppReportSettlement get(Long id);
			
			/**
			 * 查询平台总的成交量
			 * 
			 * @return
			 */
			public List<AppOrderDistributionVo> findOrderDistribution();

			/**
			 * 查询平台历史成交总量
			 * 
			 * @return
			 */
			public Integer findTotalMoney(String website);
			
			

}
