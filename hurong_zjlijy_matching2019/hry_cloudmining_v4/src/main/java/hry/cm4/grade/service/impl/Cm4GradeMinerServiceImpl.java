/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:02:31 
 */
package hry.cm4.grade.service.impl;

import hry.cm4.grade.model.Cm4GradeMiner;
import hry.cm4.grade.service.Cm4GradeMinerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> Cm4GradeMinerService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:02:31  
 */
@Service("cm4GradeMinerService")
public class Cm4GradeMinerServiceImpl extends BaseServiceImpl<Cm4GradeMiner, Long> implements Cm4GradeMinerService{
	
	@Resource(name="cm4GradeMinerDao")
	@Override
	public void setDao(BaseDao<Cm4GradeMiner, Long> dao) {
		super.dao = dao;
	}
	@Override
	public Cm4GradeMiner getCmGradeMiner(int grade) {
		// TODO Auto-generated method stub
		//查询用户矿工等级信息
		QueryFilter filterGrade = new QueryFilter(Cm4GradeMiner.class);
		filterGrade.addFilter("grade=", grade);
		Cm4GradeMiner cmGradeMiner = super.get(filterGrade);
		return cmGradeMiner;
	}

}
