/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-15 15:40:55 
 */
package hry.ico.service;

import hry.bean.FrontPage;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoBuyOrder;
import hry.ico.model.util.RecommenderOrder;
import hry.util.QueryFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p> IcoBuyOrderService </p>
 * @author:         lzy
 * @Date :          2019-01-15 15:40:55 
 */
public interface IcoBuyOrderService  extends BaseService<IcoBuyOrder, Long>{
    /**
     * sql分页查询
     * @param filter
     * @return
     */
    FrontPage findPageBySql(Map<String,String > hashMap);

    /**
     * 查询推荐列表
     * @param hashMap
     * @return
     */
    FrontPage finRecommendBySql(Map<String,String > hashMap);

    /**
     * 查询上级是否有过交易
     * @param customerId
     * @return
     */
    RecommenderOrder finSuperiorCountByCustomerId(Long customerId);
    /**
     * 查找所有用户购买总数
     * @return
     */
    List<IcoBuyOrder> findBuyTotal();
}
