/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 14:22:05 
 */
package hry.app.otc.releaseAdvertisement.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.app.otc.releaseAdvertisement.service.AppOrderSpeakService;
import hry.otc.manage.remote.model.releaseAdvertisement.AppOrderSpeak;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppOrderSpeakService </p>
 * @author:         denghf
 * @Date :          2018-06-29 14:22:05  
 */
@Service("appOrderSpeakService")
public class AppOrderSpeakServiceImpl extends BaseServiceImpl<AppOrderSpeak, Long> implements AppOrderSpeakService{
	
	@Resource(name="appOrderSpeakDao")
	@Override
	public void setDao(BaseDao<AppOrderSpeak, Long> dao) {
		super.dao = dao;
	}
}
