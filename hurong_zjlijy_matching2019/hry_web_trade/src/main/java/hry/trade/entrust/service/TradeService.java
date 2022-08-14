/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.trade.entrust.service;

import hry.trade.redis.model.EntrustTrade;
import redis.clients.jedis.Jedis;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
public interface TradeService{
	/**
	 * 
	 * <p> 匹配队列</p>
	 * @author:         Gao Mimi
	 * @param:    @param exEntrust
	 * @return: void 
	 * @Date :          2016年4月15日 下午2:56:57   
	 * @throws:
	 * 
	 *

	 */
	public void matchExtrustToOrderQueue(String id);
	
	/**
	 * 
	 * <p> 资金队列</p>
	 * @author:         Gao Mimi
	 * @param:    @param exEntrust
	 * @return: void 
	 * @Date :          2016年4月15日 下午2:56:57   
	 * @throws:
	 * 
	 *

	 */
	public Boolean accountaddQueue(String accoutadd);
	public void appointMatchExtrust(EntrustTrade exEntrust,Jedis jedis);
}
