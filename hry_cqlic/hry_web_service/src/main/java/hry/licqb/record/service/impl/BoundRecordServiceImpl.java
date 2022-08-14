/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-04-02 10:46:06 
 */
package hry.licqb.record.service.impl;

import hry.licqb.record.model.BoundRecord;
import hry.licqb.record.service.BoundRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> BoundRecordService </p>
 * @author:         zhouming
 * @Date :          2020-04-02 10:46:06  
 */
@Service("boundRecordService")
public class BoundRecordServiceImpl extends BaseServiceImpl<BoundRecord, Long> implements BoundRecordService{
	
	@Resource(name="boundRecordDao")
	@Override
	public void setDao(BaseDao<BoundRecord, Long> dao) {
		super.dao = dao;
	}
	

}
