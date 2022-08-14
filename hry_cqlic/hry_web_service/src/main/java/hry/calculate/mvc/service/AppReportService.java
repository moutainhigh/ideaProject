/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月5日 下午3:05:58
 */
package hry.calculate.mvc.service;

import hry.calculate.mvc.po.TotalAccountForReport;
import hry.calculate.mvc.po.TotalCurrencyForReport;
import hry.calculate.mvc.po.TotalCustomerForReport;
import hry.calculate.mvc.po.TotalEarningsForReport;
import hry.calculate.settlement.model.AppReportSettlement;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author Wu shuiming
 * @date 2016年9月5日 下午3:05:58
 */
public interface AppReportService {

	public TotalCustomerForReport findTotalCustomerForReport(String beginTime,
                                                             String endTime);

	public List<TotalCurrencyForReport> findTotalCurrencyForReport(String beginTime,
                                                                   String endTime);
    
	// 平台币种结算报表
	public TotalEarningsForReport findTotalEarningsForReport(String beginTime,
                                                             String endTime);

	public TotalAccountForReport findTotalAccountForReport(String beginTime,
                                                           String endTime, Integer customerType);

	// 平台用户盈亏结算报表
	public List<AppReportSettlement> findTotalCustomerProfitForReport(String date, Integer i);

	/**
	 * 获取一种币的总量（可用和）
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月13日 下午7:08:04   
	 * @throws:
	 */
	public BigDecimal getTotalCoinNum(String coinCode);
	
	
	
	
	
}
