/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-28 17:24:59 
 */
package hry.mall.rebate.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.rebate.model.RebateDetail;



/**
 * <p> RebateDetailService </p>
 * @author:         luyue
 * @Date :          2019-11-28 17:24:59 
 */
public interface RebateDetailService  extends BaseService<RebateDetail, Long>{
    /**
     * 记录奖励、返佣、结算等记录
     * @param orderId
     * @return
     */
    public JsonResult addDetail(long  orderId);


}
