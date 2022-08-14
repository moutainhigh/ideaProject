/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-16 21:24:40 
 */
package hry.admin.ico.level.service.impl;

import hry.admin.ico.level.model.IcoCustomerLevel;
import hry.admin.ico.level.service.IcoCustomerLevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoCustomerLevelService </p>
 * @author:         houz
 * @Date :          2019-01-16 21:24:40  
 */
@Service("icoCustomerLevelService")
public class IcoCustomerLevelServiceImpl extends BaseServiceImpl<IcoCustomerLevel, Long> implements IcoCustomerLevelService{
	
	@Resource(name="icoCustomerLevelDao")
	@Override
	public void setDao(BaseDao<IcoCustomerLevel, Long> dao) {
		super.dao = dao;
	}
	

}
