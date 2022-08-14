/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:35:20 
 */
package hry.licqb.exchange.service.impl;

import hry.licqb.exchange.dao.ExchangeItemDao;
import hry.licqb.exchange.model.ExchangeItem;
import hry.licqb.exchange.service.ExchangeItemService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p> ExchangeItemService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:35:20  
 */
@Service("exchangeItemService")
public class ExchangeItemServiceImpl extends BaseServiceImpl<ExchangeItem, Long> implements ExchangeItemService{
	
	@Resource(name="exchangeItemDao")
	@Override
	public void setDao(BaseDao<ExchangeItem, Long> dao) {
		super.dao = dao;
	}

}
