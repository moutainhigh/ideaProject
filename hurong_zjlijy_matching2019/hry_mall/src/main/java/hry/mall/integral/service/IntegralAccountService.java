/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 10:53:05 
 */
package hry.mall.integral.service;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.IntegralAccount;

import java.util.List;

/**
 * <p> IntegralAccountService </p>
 * @author:         kongdb
 * @Date :          2019-01-07 10:53:05 
 */
public interface IntegralAccountService  extends BaseService<IntegralAccount, Long> {

    /**
     * 获取未生成积分账户的用户集合
     * */
    public List<Long> findNoIntegralAccountUser();

    /**
     *  获取账户的父级推荐人未获取注册积分的账户集合
     * */
    public List<Long> findNoIntegralDetailUser();


}
