/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:37:09 
 */
package hry.licqb.exchange.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.exchange.model.ExchangeTotal;



/**
 * <p> ExchangeTotalService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:37:09 
 */
public interface ExchangeTotalService  extends BaseService<ExchangeTotal, Long>{

    public void payAndExtractExchangeTotal();
}
