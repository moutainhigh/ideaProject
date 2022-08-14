/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2018-12-17 16:50:31 
 */
package hry.mall.platform.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.service.MallConfigService;
import org.springframework.stereotype.Service;

/**
 * <p> MallConfigService </p>
 * @author:         kongdb
 * @Date :          2018-12-17 16:50:31  
 */
@Service("mallConfigService")
public class MallConfigServiceImpl extends BaseServiceImpl<MallConfig, Long> implements MallConfigService {
	
	@Resource(name="mallConfigDao")
	@Override
	public void setDao(BaseDao<MallConfig, Long> dao) {
		super.dao = dao;
	}
	

}
