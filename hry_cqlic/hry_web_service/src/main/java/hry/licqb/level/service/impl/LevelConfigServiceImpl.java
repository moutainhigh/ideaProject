/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-16 15:46:29 
 */
package hry.licqb.level.service.impl;

import hry.licqb.level.model.LevelConfig;
import hry.licqb.level.service.LevelConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> LevelConfigService </p>
 * @author:         zhouming
 * @Date :          2019-08-16 15:46:29  
 */
@Service("levelConfigService")
public class LevelConfigServiceImpl extends BaseServiceImpl<LevelConfig, Long> implements LevelConfigService{
	
	@Resource(name="levelConfigDao")
	@Override
	public void setDao(BaseDao<LevelConfig, Long> dao) {
		super.dao = dao;
	}
	

}
