/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0 
 * @Date:        2016-11-22 18:25:52 
 */
package hry.app.customer.person.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.otc.manage.remote.model.customer.person.AppPersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> AppPersonInfoDao </p>
 * @author:         zhangcb
 * @Date :          2016-11-22 18:25:52  
 */
public interface AppPersonInfoDao extends  BaseDao<AppPersonInfo, Long> {
	
	public List<AppPersonInfo> getByCustomerId (@Param(value = "customerId") Long customerId);
}
