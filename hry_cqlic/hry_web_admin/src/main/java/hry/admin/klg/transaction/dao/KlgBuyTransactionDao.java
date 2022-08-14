/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 11:40:59 
 */
package hry.admin.klg.transaction.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> KlgBuyTransactionDao </p>
 * @author:         yaozhuo
 * @Date :          2019-04-16 11:40:59  
 */
public interface KlgBuyTransactionDao extends  BaseDao<KlgBuyTransaction, Long> {
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlgBuyTransactionVo> findPageBySql(Map<String,Object> map);
    
    /**
	 * 查询买单id属于ids中的数据
	 * @param ids
	 * @return
	 */
    BigDecimal getBuyTransactionByIdINIds(Map<String,Object> map);
    /**
     * 查询买单id属于ids中的数据
     * @param ids
     * @return
     */
    List<KlgBuyTransactionVo> findBuyTransactionByIdINIds(Map<String,Object> map);
    /**
     * 根据订单状态查询sme总和
     * @param map
     * @return
     */
    KlgBuyTransactionVo getUsdtMoneySumByStatus(Map<String,Object> map);
    
    /**
     * 更新订单状态为2待支付
     * @param map
     */
    void updateStatus(Map<String,Object> map);
    
    /**
     * 更新支付超时的订单为已作废
     * @param map
     */
    void updateStatusByDate(Map<String,Object> map);
    
    /**
     * 查询废弃订单保证金总数
     * @param map
     * @return
     */
    BigDecimal getDiscardFirstMoneySum(Map<String,Object> map);
    
    /**
     * 查询废弃订单并且订单状态再待支付状态的订单列表
     * @param map
     * @return
     */
    List<KlgBuyTransactionVo> findDiscardList(Map<String,Object> map);
    
    /**
     * 查询订单状态为排队状态且时间大于起息时间的订单列表
     * @param map
     * @return
     */
    List<KlgBuyTransactionVo> findDiscardListByDay(Map<String,Object> map);
    
    /**
     * 更新订单利息
     * @param map
     */
    void updateInterestMoney(Map<String,Object> map);
    
    /**
     * 按天分组求和
     * @return
     */
    List<KlgBuyTransactionVo> findBuyListGroupByDay();
    
    /**
     * 预测未来排单购买平台币数量
     * @param map
     * @return 
     */
    BigDecimal getForecastSum(Map<String,Object> map);
    
}
