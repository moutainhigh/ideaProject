/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:31:38 
 */
package hry.mall.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.order.model.Order;
import hry.mall.order.model.vo.OrderBalanceVo;
import hry.mall.order.model.vo.OrderRefundVo;
import hry.util.QueryFilter;


/**
 * <p> OrderService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:31:38 
 */
public interface OrderService  extends BaseService<Order, Long> {

    public PageResult findPageBySql(QueryFilter filter);
    /**
     * 生成订单编号
     * @return
     */
    public String createNumber();
    /**
     * 生成订单并返回
     * @param map
     * @return
     */
    public Order saveOrder(Map<String, String> map);
    
	public List<OrderRefundVo> findUnionBySql(Map<String, String> map);

    /**
     * 生成订单并返回------李金沅项目定制
     * @param map
     * @return
     */
    public Order saveOrder1(Map<String, String> map);

    /**
     * 根据币种查找支付数量
     * @param coinCode
     * @param content
     * @return
     */
    public BigDecimal findCount(String coinCode,String content);

    /**
     * 查询币种图片
     * @param coinCode
     * @return
     */
    public String findPicturePath(String coinCode);

    /**
     * 商户结算订单查询
     * @param map
     * @return
     */
    List<OrderBalanceVo> findBalanceBySql(Map<String, String> map);



}
