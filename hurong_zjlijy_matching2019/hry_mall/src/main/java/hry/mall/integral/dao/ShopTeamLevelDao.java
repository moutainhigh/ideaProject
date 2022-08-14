/**
 * Copyright:    
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-05-31 09:52:20 
 */
package hry.mall.integral.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.integral.model.ShopTeamLevel;

import java.util.List;
import java.util.Map;


/**
 * <p> ShopTeamLevelDao </p>
 * @author:         houzhen
 * @Date :          2019-05-31 09:52:20  
 */
public interface ShopTeamLevelDao extends BaseDao<ShopTeamLevel, Long> {

    List<Map<String,Object>> findPageBySql(Map<String, Object> map);
    Integer countTeanPersonNumByParam(Map<String, Object> map);
}
