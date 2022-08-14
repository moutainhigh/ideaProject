/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-29 11:28:48 
 */
package hry.licqb.record.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.licqb.record.model.ApplyTeamAward;
import hry.licqb.record.service.ApplyTeamAwardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> ApplyTeamAwardService </p>
 * @author:         zhouming
 * @Date :          2019-08-29 11:28:48  
 */
@Service("applyTeamAwardService")
public class ApplyTeamAwardServiceImpl extends BaseServiceImpl<ApplyTeamAward, Long> implements ApplyTeamAwardService{
	
	@Resource(name="applyTeamAwardDao")
	@Override
	public void setDao(BaseDao<ApplyTeamAward, Long> dao) {
		super.dao = dao;
	}

}
