/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-05 10:01:31 
 */
package hry.ico.service.impl;

import hry.ico.model.IcoLockError;
import hry.ico.service.IcoLockErrorService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoLockErrorService </p>
 * @author:         lzy
 * @Date :          2019-03-05 10:01:31  
 */
@Service("icoLockErrorService")
public class IcoLockErrorServiceImpl extends BaseServiceImpl<IcoLockError, Long> implements IcoLockErrorService{
	
	@Resource(name="icoLockErrorDao")
	@Override
	public void setDao(BaseDao<IcoLockError, Long> dao) {
		super.dao = dao;
	}
	

}
