/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:41:50 
 */
package hry.admin.licqb.platform.service.impl;

import hry.admin.licqb.platform.model.CustomerLevel;
import hry.admin.licqb.platform.service.CustomerLevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CustomerLevelService </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:41:50  
 */
@Service("customerLevelService")
public class CustomerLevelServiceImpl extends BaseServiceImpl<CustomerLevel, Long> implements CustomerLevelService{
	
	@Resource(name="customerLevelDao")
	@Override
	public void setDao(BaseDao<CustomerLevel, Long> dao) {
		super.dao = dao;
	}
	

}
