/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:04 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppSmsSend;
import hry.admin.web.service.AppSmsSendService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppSmsSendService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:42:04  
 */
@Service("appSmsSendService")
public class AppSmsSendServiceImpl extends BaseServiceImpl<AppSmsSend, Long> implements AppSmsSendService{
	
	@Resource(name="appSmsSendDao")
	@Override
	public void setDao(BaseDao<AppSmsSend, Long> dao) {
		super.dao = dao;
	}
	

}
