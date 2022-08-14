/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:43:18 
 */
package hry.admin.licqb.platform.service.impl;

import hry.admin.licqb.platform.model.TeamLevelConfig;
import hry.admin.licqb.platform.service.TeamLevelConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> TeamLevelConfigService </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:43:18  
 */
@Service("teamLevelConfigService")
public class TeamLevelConfigServiceImpl extends BaseServiceImpl<TeamLevelConfig, Long> implements TeamLevelConfigService{
	
	@Resource(name="teamLevelConfigDao")
	@Override
	public void setDao(BaseDao<TeamLevelConfig, Long> dao) {
		super.dao = dao;
	}
	

}
