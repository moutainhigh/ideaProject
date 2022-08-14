/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-04 14:36:50 
 */
package hry.web.mail.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.manage.remote.model.MailConfigAndTemplate;
import hry.web.mail.model.MailConfig;

import java.util.List;
import java.util.Map;


/**
 * <p> MailConfigDao </p>
 * @author:         sunyujie
 * @Date :          2018-06-04 14:36:50  
 */
public interface MailConfigDao extends  BaseDao<MailConfig, Long> {

  public  List<MailConfigAndTemplate> findMailConfigAndTemplateList(Map<String, String> params);
}
