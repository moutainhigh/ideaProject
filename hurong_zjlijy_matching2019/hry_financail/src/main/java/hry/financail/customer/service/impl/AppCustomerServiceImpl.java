package hry.financail.customer.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.customer.model.AppCustomer;
import hry.financail.customer.service.AppCustomerService;
import org.springframework.stereotype.Service;

@Service("appCustomerService")
public class AppCustomerServiceImpl extends BaseServiceImpl<AppCustomer, Long> implements AppCustomerService {


	@Override
	public void setDao(BaseDao<AppCustomer, Long> baseDao) {

	}
}
