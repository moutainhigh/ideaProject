/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-21 16:02:14 
 */
package hry.ico.service.impl;

import hry.ico.model.IcoLockAppendRecord;
import hry.ico.service.IcoLockAppendRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoLockAppendRecordService </p>
 * @author:         lzy
 * @Date :          2019-03-21 16:02:14  
 */
@Service("icoLockAppendRecordService")
public class IcoLockAppendRecordServiceImpl extends BaseServiceImpl<IcoLockAppendRecord, Long> implements IcoLockAppendRecordService{
	
	@Resource(name="icoLockAppendRecordDao")
	@Override
	public void setDao(BaseDao<IcoLockAppendRecord, Long> dao) {
		super.dao = dao;
	}
	

}
