/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-01-19 10:07:55 
 */
package hry.customer.user.service.impl;

import hry.customer.user.model.AppCustomerCollection;
import hry.customer.user.service.AppCustomerCollectionService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppCustomerCollectionService </p>
 * @author:         liushilei
 * @Date :          2018-01-19 10:07:55  
 */
@Service("appCustomerCollectionService")
public class AppCustomerCollectionServiceImpl extends BaseServiceImpl<AppCustomerCollection, Long> implements AppCustomerCollectionService{
	
	@Resource(name="appCustomerCollectionDao")
	@Override
	public void setDao(BaseDao<AppCustomerCollection, Long> dao) {
		super.dao = dao;
	}
	

}
