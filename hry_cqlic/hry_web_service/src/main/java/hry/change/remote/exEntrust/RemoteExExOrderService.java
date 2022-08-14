/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.change.remote.exEntrust;

import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gao MIMI
 * @Date : 2016年3月24日 下午1:47:26
 */
public interface RemoteExExOrderService {
	public List<ExOrderInfo> findByOrderNum(String orderNum);			
	/**
	 * 
	 * <p> 委托生成完之后要生成流水</p>
	 * @author:         Gao Mimi
	 * @param:    @param exEntrust
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年8月25日 上午9:56:53   
	 * @throws:
	 */
	public String[] deductMoney(ExOrderInfo exOrderInfo, ExEntrust buyexEntrust, ExEntrust sellentrust, ExOrderInfo exOrderInfosell, ExOrder exOrder);

	
	public Long ListCount(Long buyId);
}					
