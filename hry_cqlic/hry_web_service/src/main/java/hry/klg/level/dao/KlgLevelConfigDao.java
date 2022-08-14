/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-17 14:31:17 
 */
package hry.klg.level.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.level.model.KlgLevelConfig;
import org.nutz.mvc.annotation.Param;


/**
 * <p> KlgLevelConfigDao </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:31:17  
 */
public interface KlgLevelConfigDao extends  BaseDao<KlgLevelConfig, Long> {

    KlgLevelConfig getLevelConfigByCustomerId(@Param("customerId") Long customerId);
}
