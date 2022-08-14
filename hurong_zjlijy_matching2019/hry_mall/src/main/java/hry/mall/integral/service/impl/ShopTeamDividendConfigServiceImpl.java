/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-05-31 09:53:19 
 */
package hry.mall.integral.service.impl;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.model.ShopTeamDividendConfig;
import hry.mall.integral.service.ShopTeamDividendConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> ShopTeamDividendConfigService </p>
 * @author:         houzhen
 * @Date :          2019-05-31 09:53:19  
 */
@Service("shopTeamDividendConfigService")
public class ShopTeamDividendConfigServiceImpl extends BaseServiceImpl<ShopTeamDividendConfig, Long> implements ShopTeamDividendConfigService {
	
	@Resource(name="shopTeamDividendConfigDao")
	@Override
	public void setDao(BaseDao<ShopTeamDividendConfig, Long> dao) {
		super.dao = dao;
	}
	

}
