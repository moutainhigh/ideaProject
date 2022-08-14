/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:04 
 */
package hry.sms.send.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.sms.send.model.AppSmsSend;
import hry.sms.send.service.AppSmsSendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppSmsSendService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:42:04  
 */
@Service("appSmsSendService")
public class AppSmsSendServiceImpl extends BaseServiceImpl<AppSmsSend, Long> implements AppSmsSendService {
	
	@Resource(name="appSmsSendDao")
	@Override
	public void setDao(BaseDao<AppSmsSend, Long> dao) {
		super.dao = dao;
	}
	

}
