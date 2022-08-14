/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      niuy
 * @version:     V1.0 
 * @Date:        2018-04-25 16:59:26 
 */
package hry.web.AppMessageTemplate.service.impl;

import hry.web.AppMessageTemplate.dao.AppMessageTemplateDao;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.AppMessageTemplate.service.AppMessageTemplateService;
import hry.web.app.dao.AppConfigDao;
import hry.web.app.model.AppMessageCateTemp;
import hry.web.app.model.AppMessageCategory;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppMessageTemplateService </p>
 * @author:         niuy
 * @Date :          2018-04-25 16:59:26  
 */
@Service("appMessageTemplateService")
public class AppMessageTemplateServiceImpl extends BaseServiceImpl<AppMessageTemplate, Long> implements AppMessageTemplateService{
	
	@Resource(name="appMessageTemplateDao")
	@Override
	public void setDao(BaseDao<AppMessageTemplate, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<AppMessageCateTemp> findKey() {
		return ((AppMessageTemplateDao)dao).findKey();
	}

	@Override
	public JsonResult insertBatch(List<AppMessageTemplate> list) {
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
		/*
		try {
			((AppMessageTemplateDao)dao).insertBatch(list);
			jsonResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg("添加失败");
		}*/
		jsonResult.setSuccess(true);
		return jsonResult;
	}
	

}
