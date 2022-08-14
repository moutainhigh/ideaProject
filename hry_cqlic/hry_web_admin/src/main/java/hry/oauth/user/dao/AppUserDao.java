/**
 * Copyright:    互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-05-27 16:05:55 
 */
package hry.oauth.user.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.oauth.user.model.AppUser;
import hry.oauth.user.model.vo.AppUserVO;


/**
 * <p> AppUserDao </p>
 * @author:         liushilei
 * @Date :          2017-05-27 16:05:55  
 */
public interface AppUserDao extends  BaseDao<AppUser, Long> {
	
	/**
	 * sql 分页
	 * @param map
	 * @return
	 */
	List<AppUserVO> findPageBySql(Map<String, Object> map);

}
