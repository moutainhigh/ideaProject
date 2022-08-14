/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 15:01:17 
 */
package hry.app.otc.releaseAdvertisement.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.app.otc.releaseAdvertisement.service.AppAppealService;
import hry.otc.manage.remote.model.releaseAdvertisement.AppAppeal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppAppealService </p>
 * @author:         denghf
 * @Date :          2018-06-29 15:01:17  
 */
@Service("appAppealService")
public class AppAppealServiceImpl extends BaseServiceImpl<AppAppeal, Long> implements AppAppealService{
	
	@Resource(name="appAppealDao")
	@Override
	public void setDao(BaseDao<AppAppeal, Long> dao) {
		super.dao = dao;
	}
	

}
