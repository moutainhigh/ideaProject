/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-06-26 21:03:57 
 */
package hry.mall.integral.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.ShopMemberPerformance;

import java.util.List;


/**
 * <p> ShopMemberPerformanceService </p>
 * @author:         houzhen
 * @Date :          2019-06-26 21:03:57 
 */
public interface ShopMemberPerformanceService extends BaseService<ShopMemberPerformance, Long>{



    /**
     * 维护用户业绩
     * @param customerIntegral
     * @return
     */
    JsonResult updateMemberPerformance(CustomerIntegral customerIntegral);

    /**
     * 获取未生成业绩账户的用户集合
     * */
    public List<Long> findNoMemberPerformanceUser();
}
