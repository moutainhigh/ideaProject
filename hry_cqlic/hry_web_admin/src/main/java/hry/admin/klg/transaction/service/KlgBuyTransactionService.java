/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 11:40:59 
 */
package hry.admin.klg.transaction.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;



/**
 * <p> KlgBuyTransactionService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-16 11:40:59 
 */
public interface KlgBuyTransactionService  extends BaseService<KlgBuyTransaction, Long>{

	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);
	
	/**
	 * 查询买单id属于ids中的数据
	 * @param ids
	 * @return
	 */
	BigDecimal getBuyTransactionByIdINIds(String ids);
	
	/**
     * 查询买单id属于ids中的数据
     * @param ids
     * @param trusteeshipStatus 是否抢单，传null查询所有
     * @return
     */
    List<KlgBuyTransactionVo> findBuyTransactionByIdINIds(String ids,Integer trusteeshipStatus);
	
	/**
     * 根据订单状态查询usdt与sme总和
     * @param map
     * @return
     */
	KlgBuyTransactionVo getUsdtMoneySumByStatus(Map<String,Object> map);
	
	/**
     * 更新订单状态为2待支付
     * @param map
     */
    void updateStatus(Map<String,Object> map);
    
    List<KlgBuyTransactionVo> findListBySql(QueryFilter filter);
    
	/**
     * 查询买单id属于ids中的数据
     * @param ids
     * @return
     */
    List<KlgBuyTransactionVo> findBuyTransactionByIdINIds(List<Long> array);
    
    /**
     * 更新支付超时的订单为已作废
     * @param timeHour 超时时间(小时)
     */
    void updateStatusByDate(Integer timeHour);
    
    /**
     * 查询废弃订单保证金总数
     * @param map
     * @return
     */
    BigDecimal getDiscardFirstMoneySum(Integer timeHour);
    
    /**
     * 查询废弃订单并且订单状态再待支付状态的订单列表
     * @param map
     * @return
     */
    List<KlgBuyTransactionVo> findDiscardList(Integer timeHour);
    
    /**
     * 查询订单状态为排队状态且时间大于起息时间的订单列表
     * @param map
     * @return
     */
    List<KlgBuyTransactionVo> findDiscardListByDay(Integer marginDays);
    
    /**
     * 更新订单利息
     * @param interestMoney 增加利息金额
     * @param id 订单ID
     */
    void updateInterestMoney(BigDecimal interestMoney,Long id);
    
    /**
     * 按天分组求和分页查询
     * @param map
     * @return
     */
	PageResult findPageGroupBydaySql(QueryFilter filter);
	
	/**
	 * 解冻确认
	 * @return
	 */
	JsonResult jiedongSubmit(String ids);
	
	/**
	 * 预测未来排单购买平台币数量
	 * @return
	 */
	List<KlgBuyTransactionVo> getForecastSum();
	
	/**
     * 预测未来排单购买平台币数量
     * @param map
     * @return 
     */
    BigDecimal getForecastSum(Map<String,Object> map);

}
