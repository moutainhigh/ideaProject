/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 15:27:21 
 */
package hry.cm.grade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.cm.grade.model.CmGradeMinercamps;
import hry.cm.grade.service.CmGradeMinercampsService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;

/**
 * <p> CmGradeMinercampsService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 15:27:21  
 */
@Service("cmGradeMinercampsService")
public class CmGradeMinercampsServiceImpl extends BaseServiceImpl<CmGradeMinercamps, Long> implements CmGradeMinercampsService{
	
	@Resource(name="cmGradeMinercampsDao")
	@Override
	public void setDao(BaseDao<CmGradeMinercamps, Long> dao) {
		super.dao = dao;
	}

	@Override
	public CmGradeMinercamps getCmGradeMinercamps(int grade) {
		// TODO Auto-generated method stub
		//查询用户矿场等级信息
		QueryFilter filterGrade = new QueryFilter(CmGradeMinercamps.class);
		filterGrade.addFilter("grade=", grade);
		CmGradeMinercamps cmGradeMinercamps = super.get(filterGrade);
		return cmGradeMinercamps;
	}
	

}
