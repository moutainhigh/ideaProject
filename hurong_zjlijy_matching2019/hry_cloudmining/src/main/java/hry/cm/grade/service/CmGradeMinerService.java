/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 15:26:55 
 */
package hry.cm.grade.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm.grade.model.CmGradeMiner;



/**
 * <p> CmGradeMinerService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 15:26:55 
 */
public interface CmGradeMinerService  extends BaseService<CmGradeMiner, Long>{
	
	/**
	 * 根据等级 查询等级信息
	 * @param grade
	 * @return
	 */
	public CmGradeMiner getCmGradeMiner(int grade);


}
