/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:23:25 
 */
package hry.admin.web.service;

import hry.admin.web.model.AppMessageCateTemp;
import hry.admin.web.model.AppMessageTemplate;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;

import java.util.List;


/**
 * <p> AppMessageTemplateService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:23:25 
 */
public interface AppMessageTemplateService  extends BaseService<AppMessageTemplate, Long>{

    List<AppMessageCateTemp> findKey ();

    JsonResult insertBatch (List<AppMessageTemplate> list);
}
