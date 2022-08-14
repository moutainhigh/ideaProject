/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-24 16:36:09 
 */
package hry.exchange.subscription.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.subscription.model.ExBuybackRecord;
import hry.exchange.subscription.model.ExSubscriptionRecord;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;



/**
 * <p> ExBuybackRecordService </p>
 * @author:         zenghao
 * @Date :          2016-11-24 16:36:09  
 */
public interface ExBuybackRecordService  extends BaseService<ExBuybackRecord, Long>{

	/**
	 * 生成回购记录
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param subRecord
	 * @param:    @param backNum
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年11月24日 下午5:37:12   
	 * @throws:
	 */
	public JsonResult saveBuybackRecord(ExSubscriptionRecord subRecord, BigDecimal backNum);
	/**
	 * 通过回购
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param id
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年11月25日 下午1:54:25   
	 * @throws:
	 */
	public JsonResult passedRecord(Long id);
	/**
	 * 驳回回购
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param id
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年11月25日 下午1:54:34   
	 * @throws:
	 */
	public JsonResult rejectRecord(HttpServletRequest request);

}
