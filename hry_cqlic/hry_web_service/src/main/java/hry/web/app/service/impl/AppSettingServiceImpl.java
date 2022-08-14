/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年3月30日 上午11:44:02
 */
package hry.web.app.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.web.app.model.AppSetting;
import hry.web.app.service.AppSettingService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年3月30日 上午11:44:02 
 */
@Service("appSettingService")
public class AppSettingServiceImpl  extends BaseServiceImpl<AppSetting, Long> implements AppSettingService{

	
	@Resource(name="appSettingDao")
	@Override
	public void setDao(BaseDao<AppSetting, Long> dao) {
		super.dao=dao;
		
	}

	


}
