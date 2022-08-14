/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 19:48:29 
 */
package hry.admin.klg.limit.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.klg.limit.model.KlgCustomerLevel;
import hry.util.QueryFilter;

import java.math.BigDecimal;
import java.util.HashMap;


/**
 * <p> KlgCustomerLevelService </p>
 * @author:         lzy
 * @Date :          2019-04-16 19:48:29 
 */
public interface KlgCustomerLevelService  extends BaseService<KlgCustomerLevel, Long>{

    /**
     * 发送奖励
     * @param hashMap
     * @return
     */
    public JsonResult addReward(HashMap<String,String> hashMap);

    public PageResult findPageBySql(QueryFilter queryFilter);
    
    /**
     *增加奖励额度
     */
    public void buyRewardQuotaAdd(Long customerId,BigDecimal buyNum);
}
