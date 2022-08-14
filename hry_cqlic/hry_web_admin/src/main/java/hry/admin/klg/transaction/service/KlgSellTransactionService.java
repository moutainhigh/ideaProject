/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 14:25:18 
 */
package hry.admin.klg.transaction.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.admin.klg.transaction.model.KlgSellTransaction;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;



/**
 * <p> KlgSellTransactionService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-16 14:25:18 
 */
public interface KlgSellTransactionService  extends BaseService<KlgSellTransaction, Long>{

	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);
	
	List<KlgSellTransactionVo> findListBySql(QueryFilter filter);
	
	/**
     * 根据订单状态查询卖出总和
     * @param map
     * @return
     */
    KlgSellTransactionVo getSmeMoneySumAndUsdtMoneyByStatus(Map<String,Object> map);
    
    /**
     * 更新订单状态为2排队成功
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
    List<KlgSellTransactionVo> findSellTransactionByIdINIds(List<Long> array);
    
    /**
     * 查询卖单id属于ids中的数据
     * @param ids
     * @return
     */
    List<KlgSellTransactionVo> findSellTransactionByIdINIdsStr(List<String> array);
    
	/**
	 * 查询卖单id属于ids中的数据
	 * @param ids
	 * @return
	 */
	BigDecimal getSellTransactionByIdINIds(String ids);
	
    /**
     * 更新糖果数量
     * @param id 订单id
     * @param candySmeMoney 增加糖果数量
     */
    void updateCandySmeMoneyById(Long id,BigDecimal candySmeMoney);
    
    /**
     * 查询超过预约时间的订单
     * @param map
     * @return
     */
    List<KlgSellTransactionVo> findBeyondList();
    
    /**
     * 按天分组求和分页查询
     * @param map
     * @return
     */
	PageResult findPageGroupBydaySql(QueryFilter filter);
}
