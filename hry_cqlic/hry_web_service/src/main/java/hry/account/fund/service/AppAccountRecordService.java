/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月16日 上午11:20:00
 */
package hry.account.fund.service;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppAccountRecord;
import hry.account.fund.model.AppOurAccount;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.trade.entrust.model.ExOrderInfo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月16日 上午11:20:00 
 */
public interface AppAccountRecordService extends BaseService<AppAccountRecord, Long> {

	
	/**
	 * 我方账号扣除一笔佣金明细   
	 * 
	 * type : 2 提现
	 * 
	 * ( 注 ：  此方法只适合扣除佣金明细  不适合其他交易的明细 )
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午3:30:11
	 */
	public void addOurRecord(BigDecimal money, AppOurAccount appOurAccount,
                             AppAccount appAccount, int type, int states, String auditor);

	/**
	 * Map 里的值
	 * 			type :  1充值 2提现 3充值手续费 4提现手续费5表示卖方手续费6表示买方手续费
	 * 			source :  0 表示线下充值  1 表示线上充值 2表示 3表示交易手续费 4 表示充值手续费
	 * 			states :  0未审核 5已审核 10失败
	 *  
	 *  auditor : 操作人  
	 *  
	 *  remark : 描述 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月9日 上午9:26:03
	 */
	public void addOurRecord(BigDecimal money, AppOurAccount appOurAccount,
                             AppAccount appAccount, ExOrderInfo orderInfo,
                             Map<String, Integer> map, String auditor, String remark);

	
	/**
	 * 我方账户增加 或减少 币的 时候 保存流水 
	 * exDigNum   我方账户 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午6:05:41
	 */
	public void addRecordForBit(AppOurAccount ourAccount, String exDigNum,
                                ExDmTransaction exDmTransaction, String auditor, String remark);
	

}
