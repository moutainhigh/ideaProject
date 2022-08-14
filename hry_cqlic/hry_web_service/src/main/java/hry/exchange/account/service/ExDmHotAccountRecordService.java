/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:20:35
 */
package hry.exchange.account.service;

import java.util.List;

import hry.core.mvc.service.base.BaseService;
import hry.exchange.account.model.ExDmHotAccountRecord;

/**
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:22:07
 */
public interface ExDmHotAccountRecordService extends
		BaseService<ExDmHotAccountRecord, Long> {

	public ExDmHotAccountRecord findByAccountId(Long id);
	
	public List<ExDmHotAccountRecord> findHotAccountRecordBytransactionNum(String transactionNum);
	

}
