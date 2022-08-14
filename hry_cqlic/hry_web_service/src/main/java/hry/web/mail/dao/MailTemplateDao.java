/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-05 14:54:52 
 */
package hry.web.mail.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.web.mail.model.MailTemplate;

import java.util.List;


/**
 * <p> MailTemplateDao </p>
 * @author:         sunyujie
 * @Date :          2018-06-05 14:54:52  
 */
public interface MailTemplateDao extends  BaseDao<MailTemplate, Long> {
    public void updateTemplateStauts(MailTemplate mailTemplate);

    public List<MailTemplate> queryByPage(MailTemplate mailTemplate);
}
