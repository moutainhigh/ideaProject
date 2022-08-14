/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-26 10:52:07 
 */
package hry.ico.service.impl;

import hry.ico.dao.IcoAwardRecordDao;
import hry.ico.model.IcoAwardRecord;
import hry.ico.service.IcoAwardRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoAwardRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-26 10:52:07  
 */
@Service("icoAwardRecordService")
public class IcoAwardRecordServiceImpl extends BaseServiceImpl<IcoAwardRecord, Long> implements IcoAwardRecordService{
	
	@Resource(name="icoAwardRecordDao")
	@Override
	public void setDao(BaseDao<IcoAwardRecord, Long> dao) {
		super.dao = dao;
	}


	@Override
	public String recommendedLockSum(Long customerId) {
		return ((IcoAwardRecordDao)dao).recommendedLockSum(customerId);
	}
}
