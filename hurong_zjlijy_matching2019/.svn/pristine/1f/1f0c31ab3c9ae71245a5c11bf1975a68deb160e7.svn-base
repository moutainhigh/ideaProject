/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-01 12:05:17 
 */
package hry.mall.platform.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.Payment;
import hry.mall.platform.service.PaymentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p> PaymentService </p>
 * @author:         kongdebiao
 * @Date :          2018-12-01 12:05:17  
 */
@Service("paymentService")
public class PaymentServiceImpl extends BaseServiceImpl<Payment, Long> implements PaymentService {
	
	@Resource(name="paymentDao")
	@Override
	public void setDao(BaseDao<Payment, Long> dao) {
		super.dao = dao;
	}
	

}
