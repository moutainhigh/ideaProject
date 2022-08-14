/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-11-06 16:45:27 
 */
package hry.admin.lend.service.impl;

import hry.admin.lend.model.ExLendAccountRecord;
import hry.admin.lend.service.ExLendAccountRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExLendAccountRecordService </p>
 * @author:         HeC
 * @Date :          2018-11-06 16:45:27  
 */
@Service("exLendAccountRecordService")
public class ExLendAccountRecordServiceImpl extends BaseServiceImpl<ExLendAccountRecord, Long> implements ExLendAccountRecordService{
	
	@Resource(name="exLendAccountRecordDao")
	@Override
	public void setDao(BaseDao<ExLendAccountRecord, Long> dao) {
		super.dao = dao;
	}
	

}
