/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      tianpy
 * @version:     V1.0 
 * @Date:        2018-01-08 21:16:43 
 */
package hry.web.mail.service.impl;

import hry.web.mail.model.Mail;
import hry.web.mail.service.MailService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> MailService </p>
 * @author:         tianpy
 * @Date :          2018-01-08 21:16:43  
 */
@Service("mailService")
public class MailServiceImpl extends BaseServiceImpl<Mail, Long> implements MailService{
	
	@Resource(name="mailDao")
	@Override
	public void setDao(BaseDao<Mail, Long> dao) {
		super.dao = dao;
	}
	

}
