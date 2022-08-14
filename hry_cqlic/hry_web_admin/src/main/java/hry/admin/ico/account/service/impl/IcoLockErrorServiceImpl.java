/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-05 10:03:27 
 */
package hry.admin.ico.account.service.impl;

import hry.admin.ico.account.model.IcoLockError;
import hry.admin.ico.account.service.IcoLockErrorService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoLockErrorService </p>
 * @author:         lzy
 * @Date :          2019-03-05 10:03:27  
 */
@Service("icoLockErrorService")
public class IcoLockErrorServiceImpl extends BaseServiceImpl<IcoLockError, Long> implements IcoLockErrorService{
	
	@Resource(name="icoLockErrorDao")
	@Override
	public void setDao(BaseDao<IcoLockError, Long> dao) {
		super.dao = dao;
	}
	

}
