/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      niuy
 * @version:     V1.0 
 * @Date:        2018-04-25 16:59:26 
 */
package hry.web.AppMessageTemplate.service;

import java.util.List;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.app.model.AppMessageCateTemp;
import hry.web.app.model.AppMessageCategory;



/**
 * <p> AppMessageTemplateService </p>
 * @author:         niuy
 * @Date :          2018-04-25 16:59:26  
 */
public interface AppMessageTemplateService  extends BaseService<AppMessageTemplate, Long>{

	public List<AppMessageCateTemp> findKey();

	public JsonResult insertBatch(List<AppMessageTemplate> list);


}
