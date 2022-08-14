/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-14 20:35:10 
 */
package hry.mall.retailstore.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.Robot;
import hry.mall.retailstore.service.RobotService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p> RobotService </p>
 * @author:         luyue
 * @Date :          2019-05-14 20:35:10  
 */
@Service("robotService")
public class RobotServiceImpl extends BaseServiceImpl<Robot, Long> implements RobotService {
	
	@Resource(name="robotDao")
	@Override
	public void setDao(BaseDao<Robot, Long> dao) {
		super.dao = dao;
	}
	

}
