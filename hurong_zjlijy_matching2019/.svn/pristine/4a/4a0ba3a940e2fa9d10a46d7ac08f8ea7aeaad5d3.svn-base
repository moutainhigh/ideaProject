/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-07 10:53:05 
 */
package hry.mall.integral.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.dao.IntegralAccountDao;
import hry.mall.integral.model.IntegralAccount;
import hry.mall.integral.service.IntegralAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> IntegralAccountService </p>
 * @author:         kongdb
 * @Date :          2019-01-07 10:53:05  
 */
@Service("integralAccountService")
public class IntegralAccountServiceImpl extends BaseServiceImpl<IntegralAccount, Long> implements IntegralAccountService {
	
	@Resource(name="integralAccountDao")
	@Override
	public void setDao(BaseDao<IntegralAccount, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Long> findNoIntegralAccountUser() {
		return ((IntegralAccountDao)dao).findNoIntegralAccountUser();
	}

	@Override
	public List<Long> findNoIntegralDetailUser() {
		return ((IntegralAccountDao)dao).findNoIntegralDetailUser();
	}
}
