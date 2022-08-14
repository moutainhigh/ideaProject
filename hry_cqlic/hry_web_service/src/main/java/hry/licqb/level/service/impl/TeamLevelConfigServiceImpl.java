/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-20 20:09:52 
 */
package hry.licqb.level.service.impl;

import hry.licqb.level.model.TeamLevelConfig;
import hry.licqb.level.service.TeamLevelConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> TeamLevelConfigService </p>
 * @author:         zhouming
 * @Date :          2019-08-20 20:09:52  
 */
@Service("teamLevelConfigService")
public class TeamLevelConfigServiceImpl extends BaseServiceImpl<TeamLevelConfig, Long> implements TeamLevelConfigService{
	
	@Resource(name="teamLevelConfigDao")
	@Override
	public void setDao(BaseDao<TeamLevelConfig, Long> dao) {
		super.dao = dao;
	}
	

}
