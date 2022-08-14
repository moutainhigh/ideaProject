/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 17:58:41 
 */
package hry.admin.ico.rulesconfig.service.impl;

import hry.admin.ico.rulesconfig.model.IcoUpgradeConfig;
import hry.admin.ico.rulesconfig.service.IcoUpgradeConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoUpgradeConfigService </p>
 * @author:         lzy
 * @Date :          2019-01-12 17:58:41  
 */
@Service("icoUpgradeConfigService")
public class IcoUpgradeConfigServiceImpl extends BaseServiceImpl<IcoUpgradeConfig, Long> implements IcoUpgradeConfigService{
	
	@Resource(name="icoUpgradeConfigDao")
	@Override
	public void setDao(BaseDao<IcoUpgradeConfig, Long> dao) {
		super.dao = dao;
	}
	

}
