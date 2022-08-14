/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:37:15 
 */
package hry.admin.licqb.exchange.service.impl;

import hry.admin.licqb.exchange.model.ExchangeTotal;
import hry.admin.licqb.exchange.service.ExchangeTotalService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExchangeTotalService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:37:15  
 */
@Service("exchangeTotalService")
public class ExchangeTotalServiceImpl extends BaseServiceImpl<ExchangeTotal, Long> implements ExchangeTotalService{
	
	@Resource(name="exchangeTotalDao")
	@Override
	public void setDao(BaseDao<ExchangeTotal, Long> dao) {
		super.dao = dao;
	}
	

}
