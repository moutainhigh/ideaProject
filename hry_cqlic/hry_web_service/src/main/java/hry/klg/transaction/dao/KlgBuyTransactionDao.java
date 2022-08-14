/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 15:00:12 
 */
package hry.klg.transaction.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.transaction.model.KlgBuyTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> KlgBuyTransactionDao </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 15:00:12  
 */
public interface KlgBuyTransactionDao extends  BaseDao<KlgBuyTransaction, Long> {

    List<KlgBuyTransaction> findPageBySql(Map<String, String> hashMap);
    
    /**
     * 获取用户所有买单成功订单利息总和
     * @param map
     * @return
     */
    BigDecimal getBuyInterestSum(Map<String, Object> map);
    
    /**
     * 获取用户买单冻结保证金总和
     * @param map
     * @return
     */
    BigDecimal getBuyFirstMoneySum(Map<String, Object> map);
}
