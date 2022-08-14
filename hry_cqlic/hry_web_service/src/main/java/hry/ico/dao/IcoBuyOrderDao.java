/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-15 15:40:55 
 */
package hry.ico.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoBuyOrder;
import hry.ico.model.util.RecommenderOrder;
import hry.ico.remote.model.RemoteRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoBuyOrderDao </p>
 * @author:         lzy
 * @Date :          2019-01-15 15:40:55  
 */
public interface IcoBuyOrderDao extends  BaseDao<IcoBuyOrder, Long> {

    List<IcoBuyOrder> findPageBySql(Map<String, String> map);

    /**
     * ITX 查询推荐列表
     * @param customerId
     * @return
     */
    List<RemoteRecommend> finRecommendBySql(@Param("customerId") Long customerId);

    /**
     * 查询上级是否有过交易
     * @return
     */
    RecommenderOrder finSuperiorCountByCustomerId(@Param("customerId") Long customerId);

    /**
     * 查找所有用户购买总数
     * @return
     */
    List<IcoBuyOrder> findBuyTotal();

}
