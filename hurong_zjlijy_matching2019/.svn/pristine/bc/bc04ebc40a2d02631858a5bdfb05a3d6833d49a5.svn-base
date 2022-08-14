/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 15:10:14 
 */
package hry.mall.platform.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.OrderInvoice;
import hry.mall.platform.service.OrderInvoiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> OrderInvoiceService </p>
 * @author:         luyue
 * @Date :          2018-11-16 15:10:14  
 */
@Service("orderInvoiceService")
public class OrderInvoiceServiceImpl extends BaseServiceImpl<OrderInvoice, Long> implements OrderInvoiceService {
	
	@Resource(name="orderInvoiceDao")
	@Override
	public void setDao(BaseDao<OrderInvoice, Long> dao) {
		super.dao = dao;
	}
	

}
