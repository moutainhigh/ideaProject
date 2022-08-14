/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-20 13:53:53 
 */
package hry.licqb.record.service.impl;

import hry.licqb.record.model.CustomerLevelRecord;
import hry.licqb.record.service.CustomerLevelRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> CustomerLevelRecordService </p>
 * @author:         zhouming
 * @Date :          2019-08-20 13:53:53  
 */
@Service("customerLevelRecordService")
public class CustomerLevelRecordServiceImpl extends BaseServiceImpl<CustomerLevelRecord, Long> implements CustomerLevelRecordService{
	
	@Resource(name="customerLevelRecordDao")
	@Override
	public void setDao(BaseDao<CustomerLevelRecord, Long> dao) {
		super.dao = dao;
	}
	

}
