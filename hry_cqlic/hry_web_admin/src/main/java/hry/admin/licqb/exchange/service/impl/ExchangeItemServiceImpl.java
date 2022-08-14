/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:35:32 
 */
package hry.admin.licqb.exchange.service.impl;

import hry.admin.licqb.exchange.model.ExchangeItem;
import hry.admin.licqb.exchange.service.ExchangeItemService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExchangeItemService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:35:32  
 */
@Service("exchangeItemService")
public class ExchangeItemServiceImpl extends BaseServiceImpl<ExchangeItem, Long> implements ExchangeItemService{
	
	@Resource(name="exchangeItemDao")
	@Override
	public void setDao(BaseDao<ExchangeItem, Long> dao) {
		super.dao = dao;
	}
	

}
