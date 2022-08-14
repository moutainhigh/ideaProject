/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:21:54 
 */
package hry.admin.web.service;

import hry.admin.customer.model.AppCustomer;
import hry.admin.web.model.AppMessageCategory;
import hry.admin.web.model.AppMessageTemplate;
import hry.admin.web.model.AppWorkOrder;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.AppMessage;
import hry.util.MsgTypeEnum;
import hry.util.QueryFilter;



/**
 * <p> AppMessageService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:21:54 
 */
public interface AppMessageService  extends BaseService<AppMessage, Long>{

    JsonResult saveMessage (AppMessage appMessage, String userNames);

    JsonResult sendMessage (Long[] messageIds);

    PageResult selectMessageVoByPage (QueryFilter filter);

    void saveWorkOrderMessage(AppCustomer appCustomer, AppMessageCategory appMessageCategory, AppMessageTemplate appMessageTemplate, String workOrderNo);

    JsonResult saveWorkOrderMessage(AppCustomer customer, AppWorkOrder appWorkOrder);

    public void saveMessage(AppCustomer customer, AppMessageCategory appMessageCategory, AppMessageTemplate appMessageTemplate,
                            Boolean flag);
    public JsonResult sysSendMsg(AppCustomer customer, MsgTypeEnum msgTypeEnum);
}
