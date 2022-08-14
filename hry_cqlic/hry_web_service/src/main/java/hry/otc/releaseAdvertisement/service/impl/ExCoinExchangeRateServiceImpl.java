/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 15:56:13 
 */
package hry.otc.releaseAdvertisement.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.releaseAdvertisement.model.ExCoinExchangeRate;
import hry.otc.releaseAdvertisement.service.ExCoinExchangeRateService;

/**
 * <p> ExCoinExchangeRateService </p>
 * @author:         denghf
 * @Date :          2018-06-25 15:56:13  
 */
@Service("exCoinExchangeRateService")
public class ExCoinExchangeRateServiceImpl extends BaseServiceImpl<ExCoinExchangeRate, Long> implements ExCoinExchangeRateService{
	
	@Resource(name="exCoinExchangeRateDao")
	@Override
	public void setDao(BaseDao<ExCoinExchangeRate, Long> dao) {
		super.dao = dao;
	}
	

}
