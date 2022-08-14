/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-27 14:52:56 
 */
package hry.otc.releaseAdvertisement.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.releaseAdvertisement.model.TrustShield;
import hry.otc.releaseAdvertisement.service.TrustShieldService;

/**
 * <p> TrustShieldService </p>
 * @author:         denghf
 * @Date :          2018-06-27 14:52:56  
 */
@Service("trustShieldService")
public class TrustShieldServiceImpl extends BaseServiceImpl<TrustShield, Long> implements TrustShieldService{
	
	@Resource(name="trustShieldDao")
	@Override
	public void setDao(BaseDao<TrustShield, Long> dao) {
		super.dao = dao;
	}
	

}
