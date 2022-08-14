/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:40:16 
 */
package hry.admin.web.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.web.model.MailTemplate;

import java.util.List;


/**
 * <p> MailTemplateDao </p>
 * @author:         liushilei
 * @Date :          2018-06-20 15:40:16  
 */
public interface MailTemplateDao extends  BaseDao<MailTemplate, Long> {

    public void updateTemplateStauts(MailTemplate mailTemplate);

    public List<MailTemplate> queryByPage(MailTemplate mailTemplate);
}
