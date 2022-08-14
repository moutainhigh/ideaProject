/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-13 11:25:18 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExRobotLog;
import hry.admin.exchange.service.ExRobotLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExRobotLogService </p>
 * @author:         tianpengyu
 * @Date :          2018-09-13 11:25:18  
 */
@Service("exRobotLogService")
public class ExRobotLogServiceImpl extends BaseServiceImpl<ExRobotLog, Long> implements ExRobotLogService{
	
	@Resource(name="exRobotLogDao")
	@Override
	public void setDao(BaseDao<ExRobotLog, Long> dao) {
		super.dao = dao;
	}
	

}
