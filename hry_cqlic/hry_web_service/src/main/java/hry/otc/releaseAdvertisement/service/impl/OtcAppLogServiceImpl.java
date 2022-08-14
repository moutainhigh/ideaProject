/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-08-10 15:10:34 
 */
package hry.otc.releaseAdvertisement.service.impl;

import hry.otc.releaseAdvertisement.model.OtcAppLog;
import hry.otc.releaseAdvertisement.service.OtcAppLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> OtcAppLogService </p>
 * @author:         denghf
 * @Date :          2018-08-10 15:10:34  
 */
@Service("otcAppLogService")
public class OtcAppLogServiceImpl extends BaseServiceImpl<OtcAppLog, Long> implements OtcAppLogService{
	
	@Resource(name="otcAppLogDao")
	@Override
	public void setDao(BaseDao<OtcAppLog, Long> dao) {
		super.dao = dao;
	}
	

}
