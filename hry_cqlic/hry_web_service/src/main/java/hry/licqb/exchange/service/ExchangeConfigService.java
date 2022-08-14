/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:34:03 
 */
package hry.licqb.exchange.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.exchange.model.ExchangeConfig;
import hry.licqb.exchange.model.ExchangeItem;

import java.util.List;
import java.util.Map;


/**
 * <p> ExchangeConfigService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:34:03 
 */
public interface ExchangeConfigService  extends BaseService<ExchangeConfig, Long>{

    List<ExchangeConfig> findExchangeConfig(Map<String, String> map);
}
