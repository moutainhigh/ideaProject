/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 17:05:34 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExCoinExchangeRate;
import hry.admin.exchange.service.ExCoinExchangeRateService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExCoinExchangeRateService </p>
 * @author:         denghf
 * @Date :          2018-10-26 17:05:34  
 */
@Service("exCoinExchangeRateService")
public class ExCoinExchangeRateServiceImpl extends BaseServiceImpl<ExCoinExchangeRate, Long> implements ExCoinExchangeRateService{
	
	@Resource(name="exCoinExchangeRateDao")
	@Override
	public void setDao(BaseDao<ExCoinExchangeRate, Long> dao) {
		super.dao = dao;
	}
	

}
