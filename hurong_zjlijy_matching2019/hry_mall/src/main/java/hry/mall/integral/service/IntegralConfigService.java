/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 14:42:27 
 */
package hry.mall.integral.service;

import hry.bean.ApiJsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralConfig;

import java.util.Map;

/**
 * <p> IntegralConfigService </p>
 * @author:         kongdb
 * @Date :          2019-01-07 14:42:27 
 */
public interface IntegralConfigService  extends BaseService<IntegralConfig, Long> {

    public String findIntegralCode();

    public CustomerIntegral findCustomerIntegral(Map<String, String> map);

    /**
     *  根据用户id 和级数 查询该用户的下级级数为series的推荐人数
     * @param customerId  用户id
     * @param series	下级级数
     * @return
     */
    ApiJsonResult getUserRecommendedNumber(Long customerId, Integer series);
}
