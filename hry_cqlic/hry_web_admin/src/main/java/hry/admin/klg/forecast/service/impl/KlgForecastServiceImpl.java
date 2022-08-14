/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-03 16:36:19 
 */
package hry.admin.klg.forecast.service.impl;

import hry.admin.klg.forecast.model.KlgForecast;
import hry.admin.klg.forecast.service.KlgForecastService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgForecastService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-03 16:36:19  
 */
@Service("klgForecastService")
public class KlgForecastServiceImpl extends BaseServiceImpl<KlgForecast, Long> implements KlgForecastService{
	
	@Resource(name="klgForecastDao")
	@Override
	public void setDao(BaseDao<KlgForecast, Long> dao) {
		super.dao = dao;
	}
	

}
