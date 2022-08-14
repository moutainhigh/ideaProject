/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 15:27:21 
 */
package hry.cm.grade.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm.grade.model.CmGradeMiner;
import hry.cm.grade.model.CmGradeMinercamps;



/**
 * <p> CmGradeMinercampsService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 15:27:21 
 */
public interface CmGradeMinercampsService  extends BaseService<CmGradeMinercamps, Long>{

	/**
	 * 根据等级 查询等级信息
	 * @param grade
	 * @return
	 */
	public CmGradeMinercamps getCmGradeMinercamps(int grade);

}
