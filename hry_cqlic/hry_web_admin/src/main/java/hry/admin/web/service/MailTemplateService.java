/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:40:16 
 */
package hry.admin.web.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.MailTemplate;
import hry.util.QueryFilter;


/**
 * <p> MailTemplateService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 15:40:16 
 */
public interface MailTemplateService  extends BaseService<MailTemplate, Long>{


    public void updateTemplateStauts(MailTemplate mailTemplate);

    public PageResult queryByPage(QueryFilter filter, MailTemplate mailTemplate);
}
