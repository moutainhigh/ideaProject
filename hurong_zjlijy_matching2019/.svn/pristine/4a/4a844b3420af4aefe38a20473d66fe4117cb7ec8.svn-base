/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 17:03:59 
 */
package hry.cm2.grade.service.impl;

import hry.cm2.grade.model.Cm2GradeMiner;
import hry.cm2.grade.service.Cm2GradeMinerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

/**
 * <p> Cm2GradeMinerService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 17:03:59  
 */
@Service("cm2GradeMinerService")
public class Cm2GradeMinerServiceImpl extends BaseServiceImpl<Cm2GradeMiner, Long> implements Cm2GradeMinerService{
	
	@Resource(name="cm2GradeMinerDao")
	@Override
	public void setDao(BaseDao<Cm2GradeMiner, Long> dao) {
		super.dao = dao;
	}

	@Override
	public Cm2GradeMiner getCmGradeMiner(int grade) {
		// TODO Auto-generated method stub
		//查询用户矿工等级信息
		QueryFilter filterGrade = new QueryFilter(Cm2GradeMiner.class);
		filterGrade.addFilter("grade=", grade);
		Cm2GradeMiner cmGradeMiner = super.get(filterGrade);
		return cmGradeMiner;
	}

}
