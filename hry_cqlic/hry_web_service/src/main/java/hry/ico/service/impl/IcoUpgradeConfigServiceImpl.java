/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-12 19:48:59 
 */
package hry.ico.service.impl;

import hry.ico.model.IcoUpgradeConfig;
import hry.ico.service.IcoUpgradeConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoUpgradeConfigService </p>
 * @author:         denghf
 * @Date :          2019-01-12 19:48:59  
 */
@Service("icoUpgradeConfigService")
public class IcoUpgradeConfigServiceImpl extends BaseServiceImpl<IcoUpgradeConfig, Long> implements IcoUpgradeConfigService{
	
	@Resource(name="icoUpgradeConfigDao")
	@Override
	public void setDao(BaseDao<IcoUpgradeConfig, Long> dao) {
		super.dao = dao;
	}
	

}
