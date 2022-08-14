/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-01-16 15:41:31 
 */
package hry.mall.integral.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.integral.model.CustomerIntegral;

import java.util.List;
import java.util.Map;

/**CustomerIntegral
 * <p> CustomerIntegralDao </p>
 * @author:         zhouming
 * @Date :          2019-01-16 15:41:31  
 */
public interface CustomerIntegralDao extends BaseDao<CustomerIntegral, Long> {
    public List<Map<String,Object>> findByTotal();

    //查询出没有默认会员的用户
    List<CustomerIntegral> findNoDefaultLevel();
}
