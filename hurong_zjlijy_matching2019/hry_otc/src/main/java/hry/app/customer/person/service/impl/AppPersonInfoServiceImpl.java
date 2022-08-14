/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0 
 * @Date:        2016-11-22 18:25:52 
 */
package hry.app.customer.person.service.impl;

import hry.app.customer.person.service.AppPersonInfoService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.customer.person.AppPersonInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppPersonInfoService </p>
 * @author:         zhangcb
 * @Date :          2016-11-22 18:25:52  
 */
@Service("appPersonInfoService")
public class AppPersonInfoServiceImpl extends BaseServiceImpl<AppPersonInfo, Long> implements AppPersonInfoService {
	
	@Resource(name="appPersonInfoDao")
	@Override
	public void setDao(BaseDao<AppPersonInfo, Long> dao) {
		super.dao = dao;
	}

	
}