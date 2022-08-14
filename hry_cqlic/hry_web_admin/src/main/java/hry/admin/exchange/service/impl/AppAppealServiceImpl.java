/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:41:12 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.AppAppeal;
import hry.admin.exchange.service.AppAppealService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppAppealService </p>
 * @author:         denghf
 * @Date :          2018-10-29 13:41:12  
 */
@Service("appAppealService")
public class AppAppealServiceImpl extends BaseServiceImpl<AppAppeal, Long> implements AppAppealService{
	
	@Resource(name="appAppealDao")
	@Override
	public void setDao(BaseDao<AppAppeal, Long> dao) {
		super.dao = dao;
	}
	

}
