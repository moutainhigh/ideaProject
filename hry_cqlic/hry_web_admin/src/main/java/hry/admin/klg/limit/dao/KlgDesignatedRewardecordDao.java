/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 19:00:43 
 */
package hry.admin.klg.limit.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.klg.limit.model.KlgDesignatedRewardecord;

import java.util.List;
import java.util.Map;


/**
 * <p> KlgDesignatedRewardecordDao </p>
 * @author:         lzy
 * @Date :          2019-04-18 19:00:43  
 */
public interface KlgDesignatedRewardecordDao extends  BaseDao<KlgDesignatedRewardecord, Long> {

    List<KlgDesignatedRewardecord> findPageBySql(Map<String, Object> map);
}
