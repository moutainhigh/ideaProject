/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:32:12 
 */
package hry.admin.licqb.exchange.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.licqb.exchange.model.ExchangeConfig;



/**
 * <p> ExchangeConfigService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:32:12 
 */
public interface ExchangeConfigService  extends BaseService<ExchangeConfig, Long>{

    JsonResult handleConfig(Long id, String type);
}
