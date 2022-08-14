package hry.account.fund.dao;

import hry.account.fund.model.AppPlatformSettlementRecord;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 下午1:56:09
 */
public interface AppPlatformSettlementRecordDao extends BaseDao<AppPlatformSettlementRecord , Long> {

	/**
	 * <p>通过sql分页查询</p>
	 * @author:         Liu Shilei
	 * @param:    @param string
	 * @param:    @param string2
	 * @param:    @param i
	 * @return: void 
	 * @Date :          2016年4月21日 下午2:43:17   
	 * @throws:
	 */
	List<AppPlatformSettlementRecord> findPageBySql(Map<String, Object> map);
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
	BigDecimal getRechargeMoney(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
	/**
	 * 一段时间内的提现总金额
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	BigDecimal getWithdrawalsMoney(@Param("beginDate") String beginDate,@Param("endDate") String endDate);
	/**
	 * 交易买手续费+交易卖手续费
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	BigDecimal getTradeFeeMoney(@Param("beginDate") String beginDate, @Param("endDate") String endDate);
	/**
	 * 充值手续费+提现手续费
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月7日 下午6:22:33   
	 * @throws:
	 */
	BigDecimal getTranFeeMoney(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

}
