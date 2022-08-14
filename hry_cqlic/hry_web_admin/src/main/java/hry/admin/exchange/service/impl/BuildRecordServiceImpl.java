/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-09-17 12:01:26 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.BuildRecord;
import hry.admin.exchange.service.BuildRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> BuildRecordService </p>
 * @author:         denghf
 * @Date :          2018-09-17 12:01:26  
 */
@Service("buildRecordService")
public class BuildRecordServiceImpl extends BaseServiceImpl<BuildRecord, Long> implements BuildRecordService{
	
	@Resource(name="buildRecordDao")
	@Override
	public void setDao(BaseDao<BuildRecord, Long> dao) {
		super.dao = dao;
	}
	

}
