/**
 * Copyright:    
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 14:42:27 
 */
package hry.mall.integral.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.integral.model.IntegralConfig;

/**
 * <p> IntegralConfigDao </p>
 * @author:         kongdb
 * @Date :          2019-01-07 14:42:27  
 */
public interface IntegralConfigDao extends BaseDao<IntegralConfig, Long> {

    /**
     * 查询用户某级下的下级会员人数
     * @param   param  格式为(series 层级，customerId用户id)：%series-customerId%
     * @return
     */
    Long getUserRecommendedNumber(String param);
}
