/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 15:01:37 
 */
package hry.mall.platform.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.Invoice;
import hry.mall.platform.service.InvoiceService;
import org.springframework.stereotype.Service;

/**
 * <p> InvoiceService </p>
 * @author:         luyue
 * @Date :          2018-11-16 15:01:37  
 */
@Service("invoiceService")
public class InvoiceServiceImpl extends BaseServiceImpl<Invoice, Long> implements InvoiceService {
	
	@Resource(name="invoiceDao")
	@Override
	public void setDao(BaseDao<Invoice, Long> dao) {
		super.dao = dao;
	}
	

}
