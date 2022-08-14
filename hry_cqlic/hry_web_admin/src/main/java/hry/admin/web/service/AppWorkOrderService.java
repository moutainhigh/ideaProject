/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:48:18 
 */
package hry.admin.web.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.AppWorkOrder;
import hry.util.QueryFilter;


/**
 * <p> appWorkOrderService </p>
 * @author:         sunyujie
 * @Date :          2018-07-02 09:48:18 
 */
public interface AppWorkOrderService extends BaseService<AppWorkOrder, Long>{
    public JsonResult sendMessageToCustomer(AppWorkOrder appWorkOrder);
    public PageResult queryByPage(QueryFilter filter, AppWorkOrder appWorkOrder);
}
