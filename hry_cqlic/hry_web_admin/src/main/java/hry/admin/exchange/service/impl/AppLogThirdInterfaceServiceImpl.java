/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-20 10:19:20 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.AppLogThirdInterface;
import hry.admin.exchange.service.AppLogThirdInterfaceService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppLogThirdInterfaceService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-20 10:19:20  
 */
@Service("appLogThirdInterfaceService")
public class AppLogThirdInterfaceServiceImpl extends BaseServiceImpl<AppLogThirdInterface, Long> implements AppLogThirdInterfaceService{
	
	@Resource(name="appLogThirdInterfaceDao")
	@Override
	public void setDao(BaseDao<AppLogThirdInterface, Long> dao) {
		super.dao = dao;
	}
	

}
