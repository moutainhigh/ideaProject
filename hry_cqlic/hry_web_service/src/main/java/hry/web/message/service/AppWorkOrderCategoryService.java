/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:35:25 
 */
package hry.web.message.service;

import java.util.List;
import java.util.Map;

import hry.core.mvc.service.base.BaseService;
import hry.web.message.model.AppWorkOrderCategory;



/**
 * <p> AppWorkOrderCategoryService </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:35:25  
 */
public interface AppWorkOrderCategoryService  extends BaseService<AppWorkOrderCategory, Long>{

	List<hry.manage.remote.model.AppWorkOrderCategory> findWorkOrderCategoryList(Map<String, String> params);


}
