/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月20日 下午5:05:21
 */
package hry.web.app.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.web.app.model.AppApi;



/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月20日 下午5:05:21 
 */
public interface AppApiDao extends BaseDao<AppApi, Long>{

	
	List<AppApi> findPageBySql(Map<String, Object> map);
}
