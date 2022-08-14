/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:34:03 
 */
package hry.licqb.exchange.service.impl;

import hry.licqb.exchange.dao.ExchangeConfigDao;
import hry.licqb.exchange.dao.ExchangeItemDao;
import hry.licqb.exchange.model.ExchangeConfig;
import hry.licqb.exchange.service.ExchangeConfigService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p> ExchangeConfigService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:34:03  
 */
@Service("exchangeConfigService")
public class ExchangeConfigServiceImpl extends BaseServiceImpl<ExchangeConfig, Long> implements ExchangeConfigService{
	
	@Resource(name="exchangeConfigDao")
	@Override
	public void setDao(BaseDao<ExchangeConfig, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<ExchangeConfig> findExchangeConfig(Map<String, String> map) {
		return ((ExchangeConfigDao)dao).findExchangeConfig(map);
	}
}
