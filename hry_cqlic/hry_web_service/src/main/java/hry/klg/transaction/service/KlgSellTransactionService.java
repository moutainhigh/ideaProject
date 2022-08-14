/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 14:58:50 
 */
package hry.klg.transaction.service;

import java.math.BigDecimal;
import java.util.Map;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.klg.transaction.model.KlgSellTransaction;



/**
 * <p> KlgSellTransactionService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 14:58:50 
 */
public interface KlgSellTransactionService  extends BaseService<KlgSellTransaction, Long>{
	
	/**
     * 更新订单状态
     * @param map
     */
    void updateStatusByIds(Map<String,Object> map);
    /**
     * sql分页查询
     * @param filter
     * @return
     */
    FrontPage findPageBySql(Map<String,String > hashMap);
    
    /**
     * 获取用户所有卖出成功订单利润总和
     * @param map
     * @return
     */
    BigDecimal getSellProfitSum(Long customerId);

}
