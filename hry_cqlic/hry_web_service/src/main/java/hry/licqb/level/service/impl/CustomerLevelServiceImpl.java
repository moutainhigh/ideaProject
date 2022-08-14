/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-16 14:33:37 
 */
package hry.licqb.level.service.impl;

import hry.licqb.level.model.CustomerLevel;
import hry.licqb.level.service.CustomerLevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CustomerLevelService </p>
 * @author:         zhouming
 * @Date :          2019-08-16 14:33:37  
 */
@Service("customerLevelService")
public class CustomerLevelServiceImpl extends BaseServiceImpl<CustomerLevel, Long> implements CustomerLevelService{
	
	@Resource(name="customerLevelDao")
	@Override
	public void setDao(BaseDao<CustomerLevel, Long> dao) {
		super.dao = dao;
	}
	

}
