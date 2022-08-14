/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-04 14:36:50 
 */
package hry.web.mail.service.impl;

import hry.web.mail.model.MailConfig;
import hry.web.mail.service.MailConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> MailConfigService </p>
 * @author:         sunyujie
 * @Date :          2018-06-04 14:36:50  
 */
@Service("mailConfigService")
public class MailConfigServiceImpl extends BaseServiceImpl<MailConfig, Long> implements MailConfigService{
	
	@Resource(name="mailConfigDao")
	@Override
	public void setDao(BaseDao<MailConfig, Long> dao) {
		super.dao = dao;
	}
	

}
