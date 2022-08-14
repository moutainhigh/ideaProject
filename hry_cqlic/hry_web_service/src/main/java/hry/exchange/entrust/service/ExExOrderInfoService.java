/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.exchange.entrust.service;

import hry.core.mvc.service.base.BaseService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExExOrderInfoService extends BaseService<ExOrderInfo, Long> {
	/**
	 * 
	 * <p> 成交之后要生成流水</p>
	 * @author:         Gao Mimi
	 * @param:    @param exEntrust
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年8月25日 上午9:56:53   
	 * @throws:
	 */
	public String[] deductMoney(ExOrderInfo exOrderInfo, ExEntrust buyexEntrust, ExEntrust sellentrust, ExOrderInfo exOrderInfosell, ExOrder exOrder);
}
