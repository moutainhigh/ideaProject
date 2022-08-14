/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-04-10 18:11:14 
 */
package hry.web.log.service.impl;

import hry.web.log.model.LoginSafe;
import hry.web.log.service.LoginSafeService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> LoginSafeService </p>
 * @author:         denghf
 * @Date :          2018-04-10 18:11:14  
 */
@Service("loginSafeService")
public class LoginSafeServiceImpl extends BaseServiceImpl<LoginSafe, Long> implements LoginSafeService{
	
	@Resource(name="loginSafeDao")
	@Override
	public void setDao(BaseDao<LoginSafe, Long> dao) {
		super.dao = dao;
	}
	

}
