/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-19 11:31:39 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppServerMonitor;
import hry.admin.web.service.AppServerMonitorService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppServerMonitorService </p>
 * @author:         sunyujie
 * @Date :          2018-07-19 11:31:39  
 */
@Service("appServerMonitorService")
public class AppServerMonitorServiceImpl extends BaseServiceImpl<AppServerMonitor, Long> implements AppServerMonitorService{
	
	@Resource(name="appServerMonitorDao")
	@Override
	public void setDao(BaseDao<AppServerMonitor, Long> dao) {
		super.dao = dao;
	}
	

}
