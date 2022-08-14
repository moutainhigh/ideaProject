/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:43 
 */
package hry.admin.klg.level.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.klg.level.model.KlgLevelConfig;



/**
 * <p> KlgLevelConfigService </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:30:43 
 */
public interface KlgLevelConfigService  extends BaseService<KlgLevelConfig, Long>{


    /**
     * 获取用户当前等级的LevelConfig配置信息
     * @param customerId
     * @return
     */
    KlgLevelConfig getLevelConfigByCustomerId(Long customerId);
	
}
