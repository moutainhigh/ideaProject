/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:38:34 
 */
package hry.mall.order.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.order.model.Refund;
import hry.mall.order.service.RefundService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> RefundService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:38:34  
 */
@Service("refundService")
public class RefundServiceImpl extends BaseServiceImpl<Refund, Long> implements RefundService {
	
	@Resource(name="refundDao")
	@Override
	public void setDao(BaseDao<Refund, Long> dao) {
		super.dao = dao;
	}
	

}
