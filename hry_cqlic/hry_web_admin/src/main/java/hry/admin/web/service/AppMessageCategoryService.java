/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:20:33 
 */
package hry.admin.web.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.AppMessageCategory;



/**
 * <p> AppMessageCategoryService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:20:33 
 */
public interface AppMessageCategoryService  extends BaseService<AppMessageCategory, Long>{

    JsonResult switchOpen (Long[] ids, String type);

    JsonResult removeCategory (String ids);
}
