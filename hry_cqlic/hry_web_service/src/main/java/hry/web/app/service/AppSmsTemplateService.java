/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-10-25 17:56:24 
 */
package hry.web.app.service;

import hry.core.mvc.service.base.BaseService;
import hry.web.app.model.AppSmsTemplate;


/**
 * <p> AppSmsTemplateService </p>
 * @author:         zhouming
 * @Date :          2018-10-25 17:56:24 
 */
public interface AppSmsTemplateService extends BaseService<AppSmsTemplate, Long> {

	 AppSmsTemplate getSmsTemplate(String key, String lang);

}
