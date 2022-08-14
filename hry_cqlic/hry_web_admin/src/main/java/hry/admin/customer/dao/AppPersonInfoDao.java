/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:45:27 
 */
package hry.admin.customer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.customer.model.AppPersonInfo;

import java.util.List;
import java.util.Map;


/**
 * <p> AppPersonInfoDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:45:27  
 */
public interface AppPersonInfoDao extends  BaseDao<AppPersonInfo, Long> {

    List<AppPersonInfo> getAppPersonInfoByEmailPhone (Map<String, Object> paramMap);
}
