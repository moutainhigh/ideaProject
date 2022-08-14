/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:21:31 
 */
package hry.cm2.dividend.service.impl;

import hry.cm2.dividend.model.Cm2DividendConfig;
import hry.cm2.dividend.service.Cm2DividendConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> Cm2DividendConfigService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:21:31  
 */
@Service("cm2DividendConfigService")
public class Cm2DividendConfigServiceImpl extends BaseServiceImpl<Cm2DividendConfig, Long> implements Cm2DividendConfigService{
	
	@Resource(name="cm2DividendConfigDao")
	@Override
	public void setDao(BaseDao<Cm2DividendConfig, Long> dao) {
		super.dao = dao;
	}
	

}
