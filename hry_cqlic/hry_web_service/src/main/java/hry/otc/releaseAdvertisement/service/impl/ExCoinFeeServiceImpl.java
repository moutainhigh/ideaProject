/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 16:39:22 
 */
package hry.otc.releaseAdvertisement.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.releaseAdvertisement.model.ExCoinFee;
import hry.otc.releaseAdvertisement.service.ExCoinFeeService;

/**
 * <p> ExCoinFeeService </p>
 * @author:         denghf
 * @Date :          2018-06-29 16:39:22  
 */
@Service("exCoinFeeService")
public class ExCoinFeeServiceImpl extends BaseServiceImpl<ExCoinFee, Long> implements ExCoinFeeService{
	
	@Resource(name="exCoinFeeDao")
	@Override
	public void setDao(BaseDao<ExCoinFee, Long> dao) {
		super.dao = dao;
	}
	

}
