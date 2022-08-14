/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-05-10 19:18:43 
 */
package hry.account.fund.service.impl;

import hry.account.fund.model.AppUserCoin;
import hry.account.fund.service.AppUserCoinService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppUserCoinService </p>
 * @author:         denghf
 * @Date :          2018-05-10 19:18:43  
 */
@Service("appUserCoinService")
public class AppUserCoinServiceImpl extends BaseServiceImpl<AppUserCoin, Long> implements AppUserCoinService{
	
	@Resource(name="appUserCoinDao")
	@Override
	public void setDao(BaseDao<AppUserCoin, Long> dao) {
		super.dao = dao;
	}
	

}
