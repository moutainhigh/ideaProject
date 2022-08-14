/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-10-23 11:12:53 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExTradingArea;
import hry.admin.exchange.service.ExTradingAreaService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExTradingAreaService </p>
 * @author:         sunyujie
 * @Date :          2018-10-23 11:12:53  
 */
@Service("exTradingAreaService")
public class ExTradingAreaServiceImpl extends BaseServiceImpl<ExTradingArea, Long> implements ExTradingAreaService{
	
	@Resource(name="exTradingAreaDao")
	@Override
	public void setDao(BaseDao<ExTradingArea, Long> dao) {
		super.dao = dao;
	}
	

}
