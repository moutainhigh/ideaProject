/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-09-11 14:46:04 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.KlineData;
import hry.admin.exchange.service.KlineDataService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlineDataService </p>
 * @author:         liushilei
 * @Date :          2018-09-11 14:46:04  
 */
@Service("klineDataService")
public class KlineDataServiceImpl extends BaseServiceImpl<KlineData, Long> implements KlineDataService{
	
	@Resource(name="klineDataDao")
	@Override
	public void setDao(BaseDao<KlineData, Long> dao) {
		super.dao = dao;
	}
	

}
