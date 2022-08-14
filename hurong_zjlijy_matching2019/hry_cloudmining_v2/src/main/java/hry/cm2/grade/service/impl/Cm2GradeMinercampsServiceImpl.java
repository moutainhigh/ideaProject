/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 17:08:11 
 */
package hry.cm2.grade.service.impl;

import hry.cm2.grade.model.Cm2GradeMinercamps;
import hry.cm2.grade.service.Cm2GradeMinercampsService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

/**
 * <p> Cm2GradeMinercampsService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 17:08:11  
 */
@Service("cm2GradeMinercampsService")
public class Cm2GradeMinercampsServiceImpl extends BaseServiceImpl<Cm2GradeMinercamps, Long> implements Cm2GradeMinercampsService{
	
	@Resource(name="cm2GradeMinercampsDao")
	@Override
	public void setDao(BaseDao<Cm2GradeMinercamps, Long> dao) {
		super.dao = dao;
	}

	@Override
	public Cm2GradeMinercamps getCmGradeMinercamps(int grade) {
		// TODO Auto-generated method stub
		//查询用户矿场等级信息
		QueryFilter filterGrade = new QueryFilter(Cm2GradeMinercamps.class);
		filterGrade.addFilter("grade=", grade);
		Cm2GradeMinercamps cmGradeMinercamps = super.get(filterGrade);
		return cmGradeMinercamps;
	}

}
