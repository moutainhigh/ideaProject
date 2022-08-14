/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:34:03 
 */
package hry.licqb.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.exchange.model.ExchangeConfig;

import java.util.List;
import java.util.Map;


/**
 * <p> ExchangeConfigDao </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:34:03  
 */
public interface ExchangeConfigDao extends  BaseDao<ExchangeConfig, Long> {
    List<ExchangeConfig> findExchangeConfig(Map<String, String> map);
}
