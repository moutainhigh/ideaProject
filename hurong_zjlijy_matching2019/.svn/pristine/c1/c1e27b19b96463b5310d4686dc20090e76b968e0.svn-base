/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 17:45:48 
 */
package hry.cm.team.service;

import java.util.List;

import hry.cm.team.model.CmTeamlevel;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> CmTeamlevelService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 17:45:48 
 */
public interface CmTeamlevelService  extends BaseService<CmTeamlevel, Long>{
	
	/**
	 * 查询用户团队信息
	 * @param customerId
	 * @return
	 */
	public List<CmTeamlevel> findTeamByCustomerId(Long customerId,Integer level);
	
	/**
	 * 查询用户所有上级
	 * @param customerId
	 * @return
	 */
	public List<CmTeamlevel> findUpTeamByCustomerId(Long customerId);


}
