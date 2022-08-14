/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-10-23 11:12:53 
 */
package hry.exchange.tradingArea.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.tradingArea.model.ExTradingArea;
import hry.exchange.tradingArea.service.ExTradingAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> ExTradingAreaService </p>
 * @author:         sunyujie
 * @Date :          2018-10-23 11:12:53  
 */
@Service("exTradingAreaService")
public class ExTradingAreaServiceImpl extends BaseServiceImpl<ExTradingArea, Long> implements ExTradingAreaService {
	
	@Resource(name="exTradingAreaDao")
	@Override
	public void setDao(BaseDao<ExTradingArea, Long> dao) {
		super.dao = dao;
	}
	

}
