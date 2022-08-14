/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:45:27 
 */
package hry.financail.customer.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.customer.model.AppPersonInfo;
import hry.financail.customer.service.AppPersonInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppPersonInfoService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:45:27  
 */
@Service("appPersonInfoService")
public class AppPersonInfoServiceImpl extends BaseServiceImpl<AppPersonInfo, Long> implements AppPersonInfoService {
	
	@Resource(name="appPersonInfoDao")
	@Override
	public void setDao(BaseDao<AppPersonInfo, Long> dao) {
		super.dao = dao;
	}


}
