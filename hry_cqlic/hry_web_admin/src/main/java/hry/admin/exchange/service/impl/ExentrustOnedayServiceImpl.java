/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-06-14 17:35:14 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExEntrust;
import hry.admin.trade.exEntrustOneDay.dao.ExentrustOnedayDao;
import hry.admin.trade.exEntrustOneDay.model.ExentrustOneday;
import hry.admin.trade.exEntrustOneDay.service.ExentrustOnedayService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p> ExentrustOnedayService </p>
 * @author:         shangxl
 * @Date :          2017-06-14 17:35:14  
 */
@Service("exentrustOnedayService")
public class ExentrustOnedayServiceImpl extends BaseServiceImpl<ExentrustOneday, Long> implements ExentrustOnedayService {
	
	@Resource
    RedisService redisService;
	@Resource(name="exentrustOnedayDao")
	@Override
	public void setDao(BaseDao<ExentrustOneday, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void delByEntrustNumber(Long id) {
	/*	QueryFilter filter=new QueryFilter(ExentrustOneday.class);
		filter.addFilter("entrustNum=", entrustNumber);
		this.delete(filter);*/
		this.delete(id);
	}

	@Override
	public BigDecimal getMaxOrMinEntrustPrice(Map<String,Object> map) {
		ExentrustOnedayDao exentrustOnedayDao=(ExentrustOnedayDao)dao;
		
		return exentrustOnedayDao.getMaxOrMinEntrustPrice(map);
	}

	@Override
	public List<ExEntrust> getExEntrustBuyDeph(Map<String, Object> map) {
		ExentrustOnedayDao exentrustOnedayDao=(ExentrustOnedayDao)dao;
		return exentrustOnedayDao.getExEntrustBuyDeph(map);
	}

	@Override
	public List<ExEntrust> getExEntrustSellDeph(Map<String, Object> map) {
		ExentrustOnedayDao exentrustOnedayDao=(ExentrustOnedayDao)dao;
		return exentrustOnedayDao.getExEntrustSellDeph(map);
	}

}
