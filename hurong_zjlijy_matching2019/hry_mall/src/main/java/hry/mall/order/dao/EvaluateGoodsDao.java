/**
 * Copyright:    
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-27 11:56:50 
 */
package hry.mall.order.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.order.model.EvaluateGoods;
import hry.mall.order.model.vo.ExDigitalmoneyAccount;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

import java.util.List;
import java.util.Map;

/**
 * <p> EvaluateGoodsDao </p>
 * @author:         luyue
 * @Date :          2018-11-27 11:56:50  
 */
public interface EvaluateGoodsDao extends BaseDao<EvaluateGoods, Long> {

    public List<EvaluateGoods> findEvaluateList(Map<String, Object> params);
    //统计数量
    public int countEvaluateGoods(Map<String, Object> params);

    ExDigitalmoneyAccount getExDigitalmoneyAccount(Map<String, Object> params );
}
