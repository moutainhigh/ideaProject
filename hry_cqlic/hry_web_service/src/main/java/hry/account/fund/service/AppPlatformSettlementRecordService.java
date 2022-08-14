package hry.account.fund.service;

import hry.account.fund.model.AppPlatformSettlementRecord;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

import java.math.BigDecimal;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 下午1:45:43
 */
public interface AppPlatformSettlementRecordService extends BaseService<AppPlatformSettlementRecord, Long>{

	PageResult findPageBySql(QueryFilter filter);

	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	BigDecimal getRechargeMoney(String beginDate, String endDate);
	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	BigDecimal getWithdrawalsMoney(String beginDate, String endDate);
	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	BigDecimal getTradeFeeMoney(String beginDate, String endDate);
	/**
	 * 一段时间内的充值总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	BigDecimal getTranFeeMoney(String beginDate, String endDate);
	
}
