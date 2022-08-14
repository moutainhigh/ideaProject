/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:43 
 */
package hry.admin.klg.level.dao;

import hry.core.mvc.dao.base.BaseDao;

import org.nutz.mvc.annotation.Param;

import hry.admin.klg.level.model.KlgLevelConfig;


/**
 * <p> KlgLevelConfigDao </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:30:43  
 */
public interface KlgLevelConfigDao extends  BaseDao<KlgLevelConfig, Long> {
	
	KlgLevelConfig getLevelConfigByCustomerId(@Param("customerId") Long customerId);

}
