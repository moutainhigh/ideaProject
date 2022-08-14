/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-21 15:28:20 
 */
package hry.mall.platform.service;

import hry.core.mvc.service.base.BaseService;
import hry.mall.order.model.Order;
import hry.mall.platform.model.ThirdpayRecord;
import hry.mall.retailstore.model.GroupDetail;

/**
 * <p> ThirdpayRecordService </p>
 * @author:         luyue
 * @Date :          2018-12-21 15:28:20 
 */
public interface ThirdpayRecordService  extends BaseService<ThirdpayRecord, Long> {

    public ThirdpayRecord  saveRecord(Order order);
    
    /**
     * 根据参团信息保存第三方记录
     * @param detail
     * @return
     */
	public ThirdpayRecord saveRecord(GroupDetail detail);
}
