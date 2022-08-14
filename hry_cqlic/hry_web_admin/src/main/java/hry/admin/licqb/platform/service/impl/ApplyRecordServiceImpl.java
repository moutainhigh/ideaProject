/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:46:02 
 */
package hry.admin.licqb.platform.service.impl;

import hry.admin.licqb.platform.model.ApplyRecord;
import hry.admin.licqb.platform.service.ApplyRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ApplyRecordService </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:46:02  
 */
@Service("applyRecordService")
public class ApplyRecordServiceImpl extends BaseServiceImpl<ApplyRecord, Long> implements ApplyRecordService{
	
	@Resource(name="applyRecordDao")
	@Override
	public void setDao(BaseDao<ApplyRecord, Long> dao) {
		super.dao = dao;
	}
	

}
