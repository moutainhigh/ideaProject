/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 14:25:18 
 */
package hry.admin.klg.transaction.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.admin.klg.transaction.model.KlgSellTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> KlgSellTransactionDao </p>
 * @author:         yaozhuo
 * @Date :          2019-04-16 14:25:18  
 */
public interface KlgSellTransactionDao extends  BaseDao<KlgSellTransaction, Long> {
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlgSellTransactionVo> findPageBySql(Map<String,Object> map);
    
    /**
     * 根据订单状态查询卖出总和
     * @param map
     * @return
     */
    KlgSellTransactionVo getSmeMoneySumAndUsdtMoneyByStatus(Map<String,Object> map);
    
    /**
     * 更新订单状态为2待收款
     * @param map
     */
    void updateStatus(Map<String,Object> map);
    
    /**
     * 更新订单状态
     * @param map
     */
    void updateStatusByIds(Map<String,Object> map);
    
    /**
     * 查询卖单id属于ids中的数据
     * @param ids
     * @return
     */
    List<KlgSellTransactionVo> findSellTransactionByIdINIds(Map<String,Object> map);
    
    /**
	 * 查询卖单id属于ids中的数据
	 * @param ids
	 * @return
	 */
    BigDecimal getSellTransactionByIdINIds(Map<String,Object> map);
    
    /**
     * 更新糖果数量
     * @param map
     */
    void updateCandySmeMoneyById(Map<String,Object> map);
    
    /**
     * 查询超过预约时间的订单
     * @param map
     * @return
     */
    List<KlgSellTransactionVo> findBeyondList();
    
    /**
     * 按天分组求和
     * @return
     */
    List<KlgSellTransactionVo> findSellListGroupByDay();
}
