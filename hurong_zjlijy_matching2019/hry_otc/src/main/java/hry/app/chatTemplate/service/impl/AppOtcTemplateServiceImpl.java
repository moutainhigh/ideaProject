/**
 * Copyright:   
 * @author:      songbing
 * @version:     V1.0 
 * @Date:        2019-07-29 14:42:17 
 */
package hry.app.chatTemplate.service.impl;

import hry.app.chatTemplate.service.AppOtcTemplateService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.message.AppOtcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppOtcTemplateService </p>
 * @author:         songbing
 * @Date :          2019-07-29 14:42:17  
 */
@Service("appOtcTemplateService")
public class AppOtcTemplateServiceImpl extends BaseServiceImpl<AppOtcTemplate, Long> implements AppOtcTemplateService {
	
	@Resource(name="appOtcTemplateDao")
	@Override
	public void setDao(BaseDao<AppOtcTemplate, Long> dao) {
		super.dao = dao;
	}
	

}
