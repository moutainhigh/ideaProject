/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.trade.entrust.service;

import java.math.BigDecimal;
import java.util.Map;

import hry.core.mvc.service.base.BaseService;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.redis.model.EntrustTrade;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExOrderInfoService extends BaseService<ExOrderInfo, Long> {



	public ExOrderInfo createExOrderInfo(Integer type,EntrustTrade buyExEntrust,EntrustTrade sellentrust,BigDecimal tradeCount,BigDecimal tradePrice);
	

    public ExOrder createExOrder(ExOrderInfo exOrderInfo);
    
    public void  reidsToMysql();
    
    public void reidsToredisLog();
    
    public void reidsToMysqlmq();
    public void reidsToredisLogmq();
	
}
