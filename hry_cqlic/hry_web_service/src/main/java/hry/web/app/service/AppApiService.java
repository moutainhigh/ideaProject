/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月10日  18:41:55
 */
package hry.web.app.service;



import java.util.List;
import java.util.Map;

import hry.core.mvc.model.AppConfig;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.web.app.model.AppApi;
import hry.web.app.model.AppApiParam;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月22日 下午4:23:37
 */
public interface AppApiService extends BaseService<AppApi, Long> {
	

	PageResult findPageBySql(QueryFilter filter);
	
}


