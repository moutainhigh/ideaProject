/**
 * Copyright:    
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-08 17:32:03 
 */
package hry.mall.integral.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.integral.model.IntegralDetail;

import java.math.BigDecimal;
import java.util.Map;


/**
 * <p> IntegralDetailDao </p>
 * @author:         kongdb
 * @Date :          2019-01-08 17:32:03  
 */
public interface IntegralDetailDao extends BaseDao<IntegralDetail, Long> {

    public Map<String,Object> findbyKey(Map<String, Object> map);

    //获取昨天的购物、分销、任务产出
    public Map<String,Object> findByRewardType(Map<String, Object> map);

    // 查询用户单月已使用电子券
    public BigDecimal getThisMonthRoll(String memberId);

}
