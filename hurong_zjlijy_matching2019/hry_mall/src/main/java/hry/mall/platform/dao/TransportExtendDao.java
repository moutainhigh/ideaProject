/**
 * Copyright:    
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-04 16:17:57 
 */
package hry.mall.platform.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.platform.model.TransportExtend;

import java.util.List;

/**
 * <p> TransportExtendDao </p>
 * @author:         kongdebiao
 * @Date :          2018-12-04 16:17:57  
 */
public interface TransportExtendDao extends BaseDao<TransportExtend, Long> {

    List<TransportExtend> findPageBySql();
}
