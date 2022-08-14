/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:37:17 
 */
package hry.admin.licqb.platform.service.impl;

import hry.admin.licqb.platform.model.LevelConfig;
import hry.admin.licqb.platform.service.LevelConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> LevelConfigService </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:37:17  
 */
@Service("levelConfigService")
public class LevelConfigServiceImpl extends BaseServiceImpl<LevelConfig, Long> implements LevelConfigService{
	
	@Resource(name="levelConfigDao")
	@Override
	public void setDao(BaseDao<LevelConfig, Long> dao) {
		super.dao = dao;
	}
	

}
