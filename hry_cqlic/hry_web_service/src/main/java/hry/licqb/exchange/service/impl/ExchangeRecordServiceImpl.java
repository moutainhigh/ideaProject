/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:36:34 
 */
package hry.licqb.exchange.service.impl;

import hry.licqb.exchange.model.ExchangeRecord;
import hry.licqb.exchange.service.ExchangeRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExchangeRecordService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:36:34  
 */
@Service("exchangeRecordService")
public class ExchangeRecordServiceImpl extends BaseServiceImpl<ExchangeRecord, Long> implements ExchangeRecordService{
	
	@Resource(name="exchangeRecordDao")
	@Override
	public void setDao(BaseDao<ExchangeRecord, Long> dao) {
		super.dao = dao;
	}
	

}
