/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:55:39 
 */
package hry.web.message.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.base.FrontPage;
import hry.util.QueryFilter;
import hry.web.message.model.AppWorkOrder;

import java.util.Map;



/**
 * <p> AppWorkOrderService </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:55:39  
 */
public interface AppWorkOrderService  extends BaseService<AppWorkOrder, Long>{

	public PageResult queryByPage(QueryFilter filter, AppWorkOrder appWorkOrder);

	public AppWorkOrder get(Long id);
	public int queryCount(Long categoryId);

	public FrontPage findWorkOrder(Map<String, String> params);

	public JsonResult removeByIds(String ids);

	public void sendMessageToCustomer(AppWorkOrder appWorkOrder);
}
