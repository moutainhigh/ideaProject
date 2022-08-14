/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-08-22 13:39:20 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.LogCommonAop;
import hry.admin.web.service.LogCommonAopService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> LogCommonAopService </p>
 * @author:         liushilei
 * @Date :          2018-08-22 13:39:20  
 */
@Service("logCommonAopService")
public class LogCommonAopServiceImpl extends BaseServiceImpl<LogCommonAop, Long> implements LogCommonAopService{
	
	@Resource(name="logCommonAopDao")
	@Override
	public void setDao(BaseDao<LogCommonAop, Long> dao) {
		super.dao = dao;
	}
	

}
