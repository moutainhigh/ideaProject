/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:43:26 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppLoginLog;
import hry.admin.web.service.AppLoginLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppLoginLogService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:43:26  
 */
@Service("appLoginLogService")
public class AppLoginLogServiceImpl extends BaseServiceImpl<AppLoginLog, Long> implements AppLoginLogService{
	
	@Resource(name="appLoginLogDao")
	@Override
	public void setDao(BaseDao<AppLoginLog, Long> dao) {
		super.dao = dao;
	}
	

}
