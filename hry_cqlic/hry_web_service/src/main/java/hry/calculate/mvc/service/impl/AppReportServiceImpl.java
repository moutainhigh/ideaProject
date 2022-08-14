/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月5日 下午3:11:11
 */
package hry.calculate.mvc.service.impl;

import hry.calculate.mvc.dao.AppReportDao;
import hry.calculate.mvc.po.TotalAccountForReport;
import hry.calculate.mvc.po.TotalCurrencyForReport;
import hry.calculate.mvc.po.TotalCustomerForReport;
import hry.calculate.mvc.po.TotalEarningsForReport;
import hry.calculate.mvc.service.AppReportService;
import hry.calculate.settlement.model.AppReportSettlement;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * @author Wu shuiming
 * @date 2016年9月5日 下午3:11:11
 * 
 */
@Service(value="appReportService")
public class AppReportServiceImpl implements AppReportService {

	@Resource(name="appReportDao")
	public AppReportDao appReportDao;
	
	
	/**
	 * 查询平台用户的说有信息 
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月5日 下午3:36:35
	 */
	@Override
	public TotalCustomerForReport findTotalCustomerForReport(String beginTime,String endTime){
		
		TotalCustomerForReport totalCustomerForReport = appReportDao.findTotalCustomer(beginTime, endTime);
		
		return totalCustomerForReport;
	}
	
	
	/**
	 * 查询 平台币种的详细信息 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月5日 下午3:37:18
	 */
	@Override
	public List<TotalCurrencyForReport> findTotalCurrencyForReport(String beginTime,String endTime){
		List<TotalCurrencyForReport> totalCurrencyForReport = appReportDao.findTotalCurrency(beginTime, endTime);
		return totalCurrencyForReport;
	}
	
	/**
	 * 查询平台资金收益日结算报表
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月5日 下午3:43:08
	 */
	@Override
	public TotalEarningsForReport findTotalEarningsForReport(String beginTime,String endTime){
		TotalEarningsForReport totalEarningsForReport = appReportDao.findTotalMoney(beginTime, endTime);
		return totalEarningsForReport;
	}
	
	
	/**
	 * 查询平台资金单日结算报表
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月5日 下午3:50:35
	 */
	@Override
	public TotalAccountForReport findTotalAccountForReport(String beginTime,String endTime,Integer custromerType){
		TotalAccountForReport totalAccountForReport = appReportDao.findTotalAccount(beginTime, endTime,custromerType);
		return totalAccountForReport;
	}
            	
	@Override
	public List<AppReportSettlement> findTotalCustomerProfitForReport(
			String date,Integer customerType) {
			if(null != date){
				date = date+"%";
				List<AppReportSettlement> totalCustomerProfitForReport = appReportDao.findTotalCustomerProfitForReport(date,customerType);
				return totalCustomerProfitForReport;
			}else{
				return null;
		}
	}


	@Override
	public BigDecimal getTotalCoinNum(String coinCode) {
		// TODO Auto-generated method stub
		return appReportDao.getTotalCoinNum(coinCode);
	}


	
	
	
}
