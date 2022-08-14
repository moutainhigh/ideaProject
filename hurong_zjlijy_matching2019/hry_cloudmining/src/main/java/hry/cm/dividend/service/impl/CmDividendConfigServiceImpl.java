/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-01 13:39:10 
 */
package hry.cm.dividend.service.impl;

import hry.cm.dividend.model.CmDividendConfig;
import hry.cm.dividend.service.CmDividendConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CmDividendConfigService </p>
 * @author:         zhouming
 * @Date :          2019-08-01 13:39:10  
 */
@Service("cmDividendConfigService")
public class CmDividendConfigServiceImpl extends BaseServiceImpl<CmDividendConfig, Long> implements CmDividendConfigService{
	
	@Resource(name="cmDividendConfigDao")
	@Override
	public void setDao(BaseDao<CmDividendConfig, Long> dao) {
		super.dao = dao;
	}
	

}
