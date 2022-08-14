/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.calculate.remote.settlement;

import java.util.List;

import hry.calculate.mvc.service.AppReportSettlementService;
import hry.calculate.mvc.service.AppReportSettlementcoinService;
import hry.calculate.mvc.service.CalculateAppTransactionService;
import hry.calculate.settlement.model.AppOrderDistributionVo;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;
import hry.util.QueryFilter;

import javax.annotation.Resource;
/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月23日 下午6:44:50
 */

public class RemoteAppReportSettlementServiceImpl implements RemoteAppReportSettlementService {

	@Resource
	private AppReportSettlementService appReportSettlementService;
	@Resource
	private  AppReportSettlementcoinService appReportSettlementcoinService;
	@Resource
	private CalculateAppTransactionService calculateAppTransactionService;
	
	@Override
	public Integer getIsShowExSettlementFinance(Long customerId,String currencyType,String website) {
		// TODO Auto-generated method stub
		return appReportSettlementService.getIsShowExSettlementFinance(customerId, currencyType, website);
	}

	@Override
	public Integer confirmExSettlementFinance(Long settid,
			String currencyType, String website) {
		// TODO Auto-generated method stub
		return appReportSettlementService.confirmExSettlementFinance(settid, currencyType, website);
	}

	
	
	@Override
	public AppReportSettlement selectExSettlementFinance(Long customerId,
			String currencyType, String website) {
		QueryFilter filter=new QueryFilter(AppReportSettlement.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		filter.addFilter("stutus=", 0);
		List<AppReportSettlement> list=appReportSettlementService.find(filter);
		if(null!=list&&list.size()>0){
			return list.get(list.size()-1);
		}else{
			return null;
		}
	}

	@Override
	public AppReportSettlementcoin selectExSettlementcoinBycoincode(
			Long customerId, String coinCode,String currencyType, String website,String beginDateString,String endDateString) {
		QueryFilter filter=new QueryFilter(AppReportSettlementcoin.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		filter.addFilter("startSettleDate=", beginDateString);
		filter.addFilter("endSettleDate=", endDateString);
		if(null!=coinCode){
			filter.addFilter("coinCode=", coinCode);
		}
		List<AppReportSettlementcoin> list=appReportSettlementcoinService.find(filter);
		if(null!=list&&list.size()>0){
			return list.get(list.size()-1);
		}else{
			return null;
		}
	}
	@Override
	public List<AppReportSettlementcoin> selectExSettlementcoin(
			Long customerId, String currencyType, String website,String beginDateString,String endDateString) {
		QueryFilter filter=new QueryFilter(AppReportSettlementcoin.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		filter.addFilter("startSettleDate=", beginDateString);
		filter.addFilter("endSettleDate=", endDateString);
		List<AppReportSettlementcoin> list=appReportSettlementcoinService.find(filter);
		return list;
	}

	@Override
	public AppReportSettlement getLastSettlementByCustomerId(Long customerId,
			String currencyType, String website) {
		// TODO Auto-generated method stub
		return appReportSettlementService.getLastSettlementByCustomerId(customerId,
				currencyType, website);
	}

	/* (non-Javadoc)
	 * @see hry.calculate.remote.settlement.RemoteAppReportSettlementService#changeClosePlateTime(java.lang.String)
	 */
	@Override
	public void changeClosePlateTime(String openAndclosePlateTime) {
		appReportSettlementService.changeClosePlateTime(openAndclosePlateTime);
		
	}

	/* (non-Javadoc)
	 * @see hry.calculate.remote.settlement.RemoteAppReportSettlementService#changeCloseTime(java.lang.String)
	 */
	@Override
	public void changeCloseTime(String closeTime) {
		appReportSettlementService.changeCloseTime(closeTime);
		
	}


	@Override
	public AppReportSettlement get(Long id) {
		// TODO Auto-generated method stub
		return appReportSettlementService.get(id);
	}

	@Override
	public List<AppOrderDistributionVo> findOrderDistribution(){
		return calculateAppTransactionService.findOrderDistribution();
	}

	/**
	 * 查询平台历史成交总额 
	 * 
	 */
	@Override
	public Integer findTotalMoney(String website) {
		Integer a = calculateAppTransactionService.findTotalMoney(website);
		return a;
	}
	
	
	
	


}
