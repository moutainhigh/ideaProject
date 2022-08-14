/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:35:24 
 */
package hry.web.message.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.web.message.model.AppWorkOrderCategory;


/**
 * <p> AppWorkOrderCategoryDao </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:35:24  
 */
public interface AppWorkOrderCategoryDao extends  BaseDao<AppWorkOrderCategory, Long> {

	List<hry.manage.remote.model.AppWorkOrderCategory> queryByList(Map<String, String> params);

}
