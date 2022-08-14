/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:12:39 
 */
package hry.cm5.customer.service.impl;

import hry.cm5.customer.model.Cm5CustomerLevelRecord;
import hry.cm5.customer.service.Cm5CustomerLevelRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> Cm5CustomerLevelRecordService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:12:39  
 */
@Service("cm5CustomerLevelRecordService")
public class Cm5CustomerLevelRecordServiceImpl extends BaseServiceImpl<Cm5CustomerLevelRecord, Long> implements Cm5CustomerLevelRecordService{
	
	@Resource(name="cm5CustomerLevelRecordDao")
	@Override
	public void setDao(BaseDao<Cm5CustomerLevelRecord, Long> dao) {
		super.dao = dao;
	}
	

}
