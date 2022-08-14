/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 17:45:48 
 */
package hry.cm.team.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.cm.team.dao.CmTeamlevelDao;
import hry.cm.team.model.CmTeamlevel;
import hry.cm.team.service.CmTeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;

/**
 * <p> CmTeamlevelService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 17:45:48  
 */
@Service("cmTeamlevelService")
public class CmTeamlevelServiceImpl extends BaseServiceImpl<CmTeamlevel, Long> implements CmTeamlevelService{
	
	@Resource(name="cmTeamlevelDao")
	@Override
	public void setDao(BaseDao<CmTeamlevel, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<CmTeamlevel> findTeamByCustomerId(Long customerId,Integer level) {
		// TODO Auto-generated method stub
		return ((CmTeamlevelDao)dao).findTeamByCustomerId(customerId, level);
	}

	@Override
	public List<CmTeamlevel> findUpTeamByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		QueryFilter filter = new QueryFilter(CmTeamlevel.class);
		filter.addFilter("customerId=", customerId);
		filter.setOrderby("level asc");
		return super.find(filter);
	}
	

}
