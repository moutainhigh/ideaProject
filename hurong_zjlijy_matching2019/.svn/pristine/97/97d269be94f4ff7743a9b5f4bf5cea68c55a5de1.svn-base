/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月10日  18:41:55
 */
package hry.app.web.app.service.impl;


import hry.app.web.app.dao.AppConfigDao;
import hry.app.web.app.service.AppConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.core.AppConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:   Gao Mimi        
 * @Date :    2015年10月10日  18:41:55     
 */
@Service("appConfigService")
public class AppConfigServiceImpl extends BaseServiceImpl<AppConfig, Long> implements AppConfigService{

	@Resource(name = "appConfigDao")
	@Override
	public void setDao(BaseDao<AppConfig, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<AppConfig> getConfig(Map<String, String> map) {
		List<AppConfig> list=((AppConfigDao)dao).getConfig(map);
		return list;
	}

}