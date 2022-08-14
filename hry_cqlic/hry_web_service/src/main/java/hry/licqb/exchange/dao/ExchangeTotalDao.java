/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:37:09 
 */
package hry.licqb.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.exchange.model.ExchangeTotal;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> ExchangeTotalDao </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:37:09  
 */
public interface ExchangeTotalDao extends  BaseDao<ExchangeTotal, Long> {
    ExchangeTotal getExchangeNum(@Param("gainCoinCode") String gainCoinCode);
    ExchangeTotal getExtractNum(@Param("gainCoinCode") String gainCoinCode);
}
