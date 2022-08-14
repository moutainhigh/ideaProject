/**
 * Copyright:   
 * @author:      huanggh
 * @version:     V1.0 
 * @Date:        2019-03-28 11:28:10 
 */
package hry.mall.integral.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.IntegralTransferOrder;

import java.math.BigDecimal;


/**
 * <p> IntegralTransferOrderService </p>
 * @author:         huanggh
 * @Date :          2019-03-28 11:28:10 
 */
public interface IntegralTransferOrderService extends BaseService<IntegralTransferOrder, Long>{


    JsonResult isTransfer(Long memberId, BigDecimal amount);
}
