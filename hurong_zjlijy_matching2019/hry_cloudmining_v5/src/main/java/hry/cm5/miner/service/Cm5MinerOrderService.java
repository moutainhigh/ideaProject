/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:05:03 
 */
package hry.cm5.miner.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.cm5.miner.model.Cm5MinerOrder;



/**
 * <p> Cm5MinerOrderService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:05:03 
 */
public interface Cm5MinerOrderService  extends BaseService<Cm5MinerOrder, Long>{

    /**
     * 保存订单
     * @param customerId 用户Id
     * @param minerId 矿机Id
     * @param cm5MinerOrder 已有矿机
     * */
    public JsonResult saveMinerOrder(Long customerId, Long minerId, Cm5MinerOrder cm5MinerOrder);
}
