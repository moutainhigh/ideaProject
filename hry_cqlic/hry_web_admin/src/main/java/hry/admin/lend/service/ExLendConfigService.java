/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:47:26 
 */
package hry.admin.lend.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.lend.model.ExLendConfig;
import hry.reward.model.page.FrontPage;
import hry.util.QueryFilter;

import java.util.List;
import java.util.Map;


/**
 * <p> ExLendConfigService </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:47:26 
 */
public interface ExLendConfigService  extends BaseService<ExLendConfig, Long>{


    List<Map<String, String>> getCoinCodes();

    FrontPage findList(QueryFilter filter);
}
