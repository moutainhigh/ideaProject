/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:39:55 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.MailConfig;
import hry.admin.web.service.MailConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> MailConfigService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 15:39:55  
 */
@Service("mailConfigService")
public class MailConfigServiceImpl extends BaseServiceImpl<MailConfig, Long> implements MailConfigService{
	
	@Resource(name="mailConfigDao")
	@Override
	public void setDao(BaseDao<MailConfig, Long> dao) {
		super.dao = dao;
	}
	

}
