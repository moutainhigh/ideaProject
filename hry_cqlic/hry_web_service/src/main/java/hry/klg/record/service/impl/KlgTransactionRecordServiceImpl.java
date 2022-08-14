/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-28 16:11:36 
 */
package hry.klg.record.service.impl;

import hry.klg.record.model.KlgTransactionRecord;
import hry.klg.record.service.KlgTransactionRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgTransactionRecordService </p>
 * @author:         lzy
 * @Date :          2019-04-28 16:11:36  
 */
@Service("klgTransactionRecordService")
public class KlgTransactionRecordServiceImpl extends BaseServiceImpl<KlgTransactionRecord, Long> implements KlgTransactionRecordService{
	
	@Resource(name="klgTransactionRecordDao")
	@Override
	public void setDao(BaseDao<KlgTransactionRecord, Long> dao) {
		super.dao = dao;
	}

}
