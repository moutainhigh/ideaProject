/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-19 11:32:34 
 */
package hry.klg.buysellaccount.service.impl;

import hry.klg.buysellaccount.model.KlgBuySellAccountrecordRecord;
import hry.klg.buysellaccount.service.KlgBuySellAccountrecordRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgBuySellAccountrecordRecordService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-19 11:32:34  
 */
@Service("klgBuySellAccountrecordRecordService")
public class KlgBuySellAccountrecordRecordServiceImpl extends BaseServiceImpl<KlgBuySellAccountrecordRecord, Long> implements KlgBuySellAccountrecordRecordService{
	
	@Resource(name="klgBuySellAccountrecordRecordDao")
	@Override
	public void setDao(BaseDao<KlgBuySellAccountrecordRecord, Long> dao) {
		super.dao = dao;
	}
	

}
