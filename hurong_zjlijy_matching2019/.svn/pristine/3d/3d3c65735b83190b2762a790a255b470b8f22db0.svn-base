/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午4:21:38
 */
package hry.app.customer.user.service.impl;


import hry.app.customer.user.dao.AppCustomerDao;
import hry.app.customer.user.service.AppCustomerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.customer.user.AppCustomer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月24日 下午4:21:38 
 */
@Service("appCustomerService")
public class AppCustomerServiceImpl extends BaseServiceImpl<AppCustomer, Long> implements AppCustomerService {
	
	@Resource(name = "appCustomerDao")
	@Override
	public void setDao(BaseDao<AppCustomer, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<AppCustomer> getFundChangeCustomers(Map<String, Object> map) {
		return ((AppCustomerDao)dao).getFundChangeCustomers(map);
	}


}
