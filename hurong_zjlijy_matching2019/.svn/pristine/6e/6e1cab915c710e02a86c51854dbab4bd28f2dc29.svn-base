/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:43:17 
 */
package hry.cm4.team.service.impl;

import hry.cm4.team.dao.Cm4TeamlevelDao;
import hry.cm4.team.model.Cm4Teamlevel;
import hry.cm4.team.service.Cm4TeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Cm4TeamlevelService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:43:17  
 */
@Service("cm4TeamlevelService")
public class Cm4TeamlevelServiceImpl extends BaseServiceImpl<Cm4Teamlevel, Long> implements Cm4TeamlevelService{
	
	@Resource(name="cm4TeamlevelDao")
	@Override
	public void setDao(BaseDao<Cm4Teamlevel, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Cm4Teamlevel> findTeamByCustomerId(Long customerId, Integer level) {
		// TODO Auto-generated method stub
		return ((Cm4TeamlevelDao)dao).findTeamByCustomerId(customerId, level);
	}

	@Override
	public List<Cm4Teamlevel> findUpTeamByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		QueryFilter filter = new QueryFilter(Cm4Teamlevel.class);
		filter.addFilter("customerId=", customerId);
		filter.setOrderby("level asc");
		return super.find(filter);
	}

}
