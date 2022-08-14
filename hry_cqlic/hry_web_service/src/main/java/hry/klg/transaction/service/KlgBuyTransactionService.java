/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 15:00:12 
 */
package hry.klg.transaction.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.klg.transaction.model.KlgBuyTransaction;

import java.math.BigDecimal;
import java.util.Map;


/**
 * <p> KlgBuyTransactionService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 15:00:12 
 */
public interface KlgBuyTransactionService  extends BaseService<KlgBuyTransaction, Long>{

    /**
     * 根据用户id 获取用户预约单数
     * @param customerId
     * @return
     */
    int getBuyTransactionCountByCustomerId(Long customerId);

    /**
     * sql分页查询
     * @param filter
     * @return
     */
    FrontPage findPageBySql(Map<String,String > hashMap);
    
    /**
     * 获取用户所有买单成功订单利息总和
     * @param map
     * @return
     */
    BigDecimal getBuyInterestSum(Long customerId);


    /**
     * 验证购买期限【预约购买间隔6天】
     * @param customerId
     * @return
     */
    boolean checkBuyInterval(Long customerId);

    /**
     * 验证购买间隔是否 超过 day天
     * @param customerId
     * @param day
     * @return
     */
    boolean checkIntervalByday(Long customerId,Integer day);

    /**
     * 获取预约购买间隔天数
     * @param customerId
     * @return
     */
    Integer getBuyInterval(Long customerId);

    /**
     * 获取用户买单冻结保证金总和
     * @param map
     * @return
     */
    BigDecimal getBuyFirstMoneySum(Long customerId);
}
