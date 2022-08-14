/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 19:24:34 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExCoinFee;
import hry.admin.exchange.service.ExCoinFeeService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExCoinFeeService </p>
 * @author:         denghf
 * @Date :          2018-10-29 19:24:34  
 */
@Service("exCoinFeeService")
public class ExCoinFeeServiceImpl extends BaseServiceImpl<ExCoinFee, Long> implements ExCoinFeeService{
	
	@Resource(name="exCoinFeeDao")
	@Override
	public void setDao(BaseDao<ExCoinFee, Long> dao) {
		super.dao = dao;
	}
	

}
