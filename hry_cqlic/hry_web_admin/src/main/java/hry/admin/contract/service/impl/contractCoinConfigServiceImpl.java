/**
 * Copyright:   
 * @author:      hec
 * @version:     V1.0 
 * @Date:        2018-12-27 15:00:02 
 */
package hry.admin.contract.service.impl;

import hry.admin.contract.model.contractCoinConfig;
import hry.admin.contract.service.contractCoinConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> contractCoinConfigService </p>
 * @author:         hec
 * @Date :          2018-12-27 15:00:02  
 */
@Service("contractCoinConfigService")
public class contractCoinConfigServiceImpl extends BaseServiceImpl<contractCoinConfig, Long> implements contractCoinConfigService{
	
	@Resource(name="contractCoinConfigDao")
	@Override
	public void setDao(BaseDao<contractCoinConfig, Long> dao) {
		super.dao = dao;
	}
	

}
