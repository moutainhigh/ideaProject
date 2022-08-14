/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:13:03 
 */
package hry.cm2.team.service.impl;

import hry.cm2.team.dao.Cm2TeamlevelDao;
import hry.cm2.team.model.Cm2Teamlevel;
import hry.cm2.team.service.Cm2TeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Cm2TeamlevelService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:13:03  
 */
@Service("cm2TeamlevelService")
public class Cm2TeamlevelServiceImpl extends BaseServiceImpl<Cm2Teamlevel, Long> implements Cm2TeamlevelService{
	
	@Resource(name="cm2TeamlevelDao")
	@Override
	public void setDao(BaseDao<Cm2Teamlevel, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Cm2Teamlevel> findTeamByCustomerId(Long customerId, Integer level) {
		// TODO Auto-generated method stub
		return ((Cm2TeamlevelDao)dao).findTeamByCustomerId(customerId, level);
	}

	@Override
	public List<Cm2Teamlevel> findUpTeamByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		QueryFilter filter = new QueryFilter(Cm2Teamlevel.class);
		filter.addFilter("customerId=", customerId);
		filter.setOrderby("level asc");
		return super.find(filter);
	}
	

}
