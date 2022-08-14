/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 14:58:50 
 */
package hry.klg.transaction.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.transaction.model.KlgSellTransaction;


/**
 * <p> KlgSellTransactionDao </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 14:58:50  
 */
public interface KlgSellTransactionDao extends  BaseDao<KlgSellTransaction, Long> {
	
	/**
     * 更新订单状态
     * @param map
     */
    void updateStatusByIds(Map<String,Object> map);

    List<KlgSellTransaction> findPageBySql(Map<String, String> hashMap);
    
    /**
     * 获取用户所有卖出成功订单利润总和
     * @param map
     * @return
     */
    BigDecimal getSellProfitSum(Map<String,Object> map);
}
