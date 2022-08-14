/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 14:55:28 
 */
package hry.admin.web.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.AppArticle;



/**
 * <p> AppArticleService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 14:55:28 
 */
public interface AppArticleService  extends BaseService<AppArticle, Long>{


    String removeAll (String ids);
}
