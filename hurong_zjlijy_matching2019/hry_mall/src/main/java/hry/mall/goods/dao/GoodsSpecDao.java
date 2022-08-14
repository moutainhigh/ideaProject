/**
 * Copyright:    
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:45:45 
 */
package hry.mall.goods.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.goods.model.GoodsSpec;
import java.util.List;
import java.util.Map;

/**
 * <p> GoodsSpecDao </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:45:45  
 */
public interface GoodsSpecDao extends BaseDao<GoodsSpec, Long> {
    List<GoodsSpec> getGoodsSpec(Map<String, Object> map);
}
