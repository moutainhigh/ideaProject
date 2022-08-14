/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 17:45:48 
 */
package hry.cm.team.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hry.cm.team.model.CmTeamlevel;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> CmTeamlevelDao </p>
 * @author:         yaozh
 * @Date :          2019-08-01 17:45:48  
 */
public interface CmTeamlevelDao extends  BaseDao<CmTeamlevel, Long> {
	
	/**
	 * 查询用户团队信息
	 * @param customerId
	 * @return
	 */
	public List<CmTeamlevel> findTeamByCustomerId(@Param("customerId") Long customerId,@Param("level") Integer level);

}
