/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:53:18 
 */
package hry.klg.prizedraw.service;

import java.util.List;
import java.util.Map;

import hry.core.mvc.service.base.BaseService;
import hry.klg.prizedraw.model.KlgPrizedrawCustomer;



/**
 * <p> KlgPrizedrawCustomerService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:53:18 
 */
public interface KlgPrizedrawCustomerService  extends BaseService<KlgPrizedrawCustomer, Long>{

	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlgPrizedrawCustomer> findPageBySql(Map<String,String> map);
    
    /**
     * 获取用户抽奖次数限制
     * @param map
     * @return
     */
    int getCustomerDrawCount(Map<String,String> map);
    
    /**
     * 查询用户本期抽奖次数
     * @param map
     * @return
     */
    int getCustomerIssueDrawCount(Map<String,String> map);
    
    /**
     * 查询用户本周排单次数
     * @param map
     * @return
     */
    int getCustomerBuyOrderCount(Map<String,String> map);

}
