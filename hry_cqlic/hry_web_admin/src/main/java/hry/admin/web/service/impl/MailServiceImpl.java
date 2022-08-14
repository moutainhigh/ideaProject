/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:41:28 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.Mail;
import hry.admin.web.service.MailService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> MailService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:41:28  
 */
@Service("mailService")
public class MailServiceImpl extends BaseServiceImpl<Mail, Long> implements MailService{
	
	@Resource(name="mailDao")
	@Override
	public void setDao(BaseDao<Mail, Long> dao) {
		super.dao = dao;
	}
	

}
