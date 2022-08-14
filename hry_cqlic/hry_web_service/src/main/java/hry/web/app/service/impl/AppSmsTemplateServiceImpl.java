/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-10-25 17:56:24 
 */
package hry.web.app.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.web.app.model.AppSmsTemplate;
import hry.web.app.service.AppSmsTemplateService;

/**
 * <p> AppSmsTemplateService </p>
 * @author:         zhouming
 * @Date :          2018-10-25 17:56:24  
 */
@Service("appSmsTemplateService")
public class AppSmsTemplateServiceImpl extends BaseServiceImpl<AppSmsTemplate, Long> implements AppSmsTemplateService {
	
	@Resource(name="appSmsTemplateDao")
	@Override
	public void setDao(BaseDao<AppSmsTemplate, Long> dao) {
		super.dao = dao;
	}
	
	@Override
	public AppSmsTemplate getSmsTemplate (String key, String lang) {
		QueryFilter temp_qf = new QueryFilter(AppSmsTemplate.class);
		temp_qf.addFilter("mkey=", key);
		temp_qf.addFilter("messageCategory=", lang);
		temp_qf.addFilter("isOpen=", 1);
		return super.get(temp_qf);
	}
	

}
