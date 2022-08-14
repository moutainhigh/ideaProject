/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-17 10:48:13 
 */
package hry.ico.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.ico.model.IcoUpgradeRecord;
import hry.ico.service.IcoUpgradeRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> IcoUpgradeRecordService </p>
 * @author:         houz
 * @Date :          2019-01-17 10:48:13  
 */
@Service("icoUpgradeRecordService")
public class IcoUpgradeRecordServiceImpl extends BaseServiceImpl<IcoUpgradeRecord, Long> implements IcoUpgradeRecordService {
	
	@Resource(name="icoUpgradeRecordDao")
	@Override
	public void setDao(BaseDao<IcoUpgradeRecord, Long> dao) {
		super.dao = dao;
	}
	

}
