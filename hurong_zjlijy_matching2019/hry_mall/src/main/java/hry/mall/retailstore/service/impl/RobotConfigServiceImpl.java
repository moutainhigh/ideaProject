/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-16 15:20:22 
 */
package hry.mall.retailstore.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.RobotConfig;
import hry.mall.retailstore.service.RobotConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> RobotConfigService </p>
 * @author:         zhouming
 * @Date :          2019-05-16 15:20:22  
 */
@Service("robotConfigService")
public class RobotConfigServiceImpl extends BaseServiceImpl<RobotConfig, Long> implements RobotConfigService {
	
	@Resource(name="robotConfigDao")
	@Override
	public void setDao(BaseDao<RobotConfig, Long> dao) {
		super.dao = dao;
	}
	

}
