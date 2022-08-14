/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-05 14:54:53 
 */
package hry.web.mail.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.web.mail.model.MailTemplate;



/**
 * <p> MailTemplateService </p>
 * @author:         sunyujie
 * @Date :          2018-06-05 14:54:53  
 */
public interface MailTemplateService  extends BaseService<MailTemplate, Long>{

   public void updateTemplateStauts(MailTemplate mailTemplate);

    public PageResult queryByPage(QueryFilter filter, MailTemplate mailTemplate);
}
