/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 15:26:55 
 */
package hry.cm.grade.service.impl;

import hry.cm.grade.model.CmGradeMiner;
import hry.cm.grade.service.CmGradeMinerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CmGradeMinerService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 15:26:55  
 */
@Service("cmGradeMinerService")
public class CmGradeMinerServiceImpl extends BaseServiceImpl<CmGradeMiner, Long> implements CmGradeMinerService{
	
	@Resource(name="cmGradeMinerDao")
	@Override
	public void setDao(BaseDao<CmGradeMiner, Long> dao) {
		super.dao = dao;
	}

	@Override
	public CmGradeMiner getCmGradeMiner(int grade) {
		// TODO Auto-generated method stub
		//查询用户矿工等级信息
		QueryFilter filterGrade = new QueryFilter(CmGradeMiner.class);
		filterGrade.addFilter("grade=", grade);
		CmGradeMiner cmGradeMiner = super.get(filterGrade);
		return cmGradeMiner;
	}
	

}
