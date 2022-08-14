/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-10-25 17:56:24 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppSmsTemplate;
import hry.admin.web.service.AppSmsTemplateService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
	

}
