/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年5月30日 下午5:28:56
 */
package hry.web.message.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.web.app.model.AppMessageCategory;
/**
 * 
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午5:28:56
 */
public interface AppMessageCategoryService extends BaseService<AppMessageCategory, Long> {
	
	public JsonResult removeCategory(Long[] ids);
	
	public JsonResult switchOpen(Long[] ids);
}


