/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-12-21 18:25:14 
 */
package hry.otc.releaseAdvertisement.service.impl;

import hry.otc.releaseAdvertisement.model.OtcCompletionRate;
import hry.otc.releaseAdvertisement.service.OtcCompletionRateService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> OtcCompletionRateService </p>
 * @author:         denghf
 * @Date :          2018-12-21 18:25:14  
 */
@Service("otcCompletionRateService")
public class OtcCompletionRateServiceImpl extends BaseServiceImpl<OtcCompletionRate, Long> implements OtcCompletionRateService{
	
	@Resource(name="otcCompletionRateDao")
	@Override
	public void setDao(BaseDao<OtcCompletionRate, Long> dao) {
		super.dao = dao;
	}
	

}
