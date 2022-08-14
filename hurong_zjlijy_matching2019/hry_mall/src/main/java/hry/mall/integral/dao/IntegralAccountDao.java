/**
 * Copyright:    
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 10:53:05 
 */
package hry.mall.integral.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.mall.integral.model.IntegralAccount;

import java.util.List;

/**
 * <p> IntegralAccountDao </p>
 * @author:         kongdb
 * @Date :          2019-01-07 10:53:05  
 */
public interface IntegralAccountDao extends BaseDao<IntegralAccount, Long> {
    /**
     * 获取未生成积分账户的用户集合
     * */
    public List<Long> findNoIntegralAccountUser();

    /**
     *  获取未获取注册积分的用户集合
     * */
    public List<Long> findNoIntegralDetailUser();
}
