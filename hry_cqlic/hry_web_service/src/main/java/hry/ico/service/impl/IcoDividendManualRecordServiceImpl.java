/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-22 11:32:20 
 */
package hry.ico.service.impl;

import hry.ico.model.IcoDividendManualRecord;
import hry.ico.service.IcoDividendManualRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoDividendManualRecordService </p>
 * @author:         lzy
 * @Date :          2019-03-22 11:32:20  
 */
@Service("icoDividendManualRecordService")
public class IcoDividendManualRecordServiceImpl extends BaseServiceImpl<IcoDividendManualRecord, Long> implements IcoDividendManualRecordService{
	
	@Resource(name="icoDividendManualRecordDao")
	@Override
	public void setDao(BaseDao<IcoDividendManualRecord, Long> dao) {
		super.dao = dao;
	}
	

}
