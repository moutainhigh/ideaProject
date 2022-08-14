/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月23日 下午9:13:09
 */
package hry.calculate.mvc.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import hry.calculate.mvc.po.AccountFundInfo;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.user.model.AppCustomer;

/**
 * @author Wu shuiming
 * @date 2016年8月23日 下午9:13:09
 */
public interface AppReportSettlementService extends BaseService<AppReportSettlement, Long>{
	
	public String[] settlementByCustomerId(AppCustomer l, Date comeDate, Date nowDate, Date endDate
            , String currencyType, String website);

 
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param date
	 * @return: void 
	 * @Date :          2016年8月10日 下午10:05:04   
	 * @throws:
	 */
public void settlementByCustomerIds(String[] ids);
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
  	 * <p> 客户确认结算单</p>
  	 * @author:         Gao Mimi
  	 * @param:    @param customerId
  	 * @param:    @param currencyType
  	 * @param:    @param website
  	 * @param:    @return
  	 * @return: Integer 
  	 * @Date :          2016年8月10日 下午7:02:36   
  	 * @throws:
  	 */
  	public Integer confirmExSettlementFinance(Long customerId, String currencyType, String website);
 	/**
  	 * 
  	 * <p> 平台确认结算单</p>
  	 * @author:         Gao Mimi
  	 * @param:    @param customerId
  	 * @param:    @param currencyType
  	 * @param:    @param website
  	 * @param:    @return
  	 * @return: Integer 
  	 * @Date :          2016年8月10日 下午7:02:36   
  	 * @throws:
  	 */
  	public Integer platformconfirmExSettlement(String currencyType, String website);
/*  	*//**
  	 * 
  	 * <p> 平台确认资金</p>
  	 * @author:         Gao Mimi
  	 * @param:    @param currencyType
  	 * @param:    @param website
  	 * @param:    @return
  	 * @return: Integer 
  	 * @Date :          2016年9月9日 下午6:28:46   
  	 * @throws:
  	 *//*
  	public void platformconfirmExSettlementfinace(String currencyType, String website);*/
 /* *//**
   * 
   * <p> 平台确认币</p>
   * @author:         Gao Mimi
   * @param:    @param currencyType
   * @param:    @param website
   * @param:    @return
   * @return: Integer 
   * @Date :          2016年9月9日 下午6:28:49   
   * @throws:
   *//*
  	public void platformconfirmExSettlementcoin(String coinCode,String currencyType, String website);
  	*/





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
	 public void postAuditByCustomer(String[] ids);
	 public AppReportSettlementcoin selectExSettlementcoinBycoincode(
             Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString);
	 public List<AppReportSettlementcoin> selectExSettlementcoin(
             Long customerId, String currencyType, String website, String beginDateString, String endDateString);
	 public String haveProblemAccount();
	 
	 
	//start核算
	 public List<Map<String,Object>>  culAccountByCustomersErrorInfosureold(String[] ids, Boolean iserrorright);
	 public void culAccountByCustomerssureold(String[] ids);
	//start核算
	 /**
	  * 分页查询settlementcoin
	  * 
	  * @param filter
	  * @param i  为null 查询的是  stutus 不为1 的数据 
	  * @return
	  */
	 public PageResult findPageByStates(QueryFilter filter, Integer i, String userName);
	 
	// public void culAccountAllCustomersureold();
    // public void culAccountByCustomers(String[] ids);
    //	 public Map<String,Object> culAccountByCustomer(Long customerId,String currencyType, String website,Boolean isSave);
   //	 public List<Map<String,Object>>   culAccountAllCustomerErrorInfo(Integer days);
   //	 public List<Map<String,Object>>  culAccountByCustomersErrorInfo(String[] ids);
//	 public Map<String,Object>  culAccountByCustomerErrorInfo(Long customerId,String currencyType, String website);

}
