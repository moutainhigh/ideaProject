/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:23:25 
 */
package hry.admin.web.service.impl;

import hry.admin.web.dao.AppMessageTemplateDao;
import hry.admin.web.model.AppMessageCateTemp;
import hry.admin.web.model.AppMessageTemplate;
import hry.admin.web.service.AppMessageTemplateService;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> AppMessageTemplateService </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:23:25  
 */
@Service("appMessageTemplateService")
public class AppMessageTemplateServiceImpl extends BaseServiceImpl<AppMessageTemplate, Long> implements AppMessageTemplateService{
	
	@Resource(name="appMessageTemplateDao")
	@Override
	public void setDao(BaseDao<AppMessageTemplate, Long> dao) {
		super.dao = dao;
	}


	@Override
	public List<AppMessageCateTemp> findKey () {
		return ((AppMessageTemplateDao)dao).findKey();
	}

	@Override
	public JsonResult insertBatch (List<AppMessageTemplate> list) {
		JsonResult jsonResult = new JsonResult();

		AppMessageTemplate appMessageTemplate = new AppMessageTemplate();
		for (AppMessageTemplate ls : list) {
			Long templateId = ls.getTemplateId();
			String title = ls.getTitle();
			String content = ls.getContent();
			AppMessageTemplate ble = ((AppMessageTemplateDao)dao).selectId(templateId);
			if(ble!=null) {
				((AppMessageTemplateDao)dao).updateId(templateId,title,content);
			}else {
				((AppMessageTemplateDao)dao).insertTemplate(templateId,title,content);
			}
		}
		jsonResult.setSuccess(true);
		return jsonResult;
	}
}
