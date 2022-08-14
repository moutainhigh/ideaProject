/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:36:13 
 */
package hry.admin.klg.platform.service.impl;

import hry.admin.klg.platform.model.KlgPlatformAccountRecord;
import hry.admin.klg.platform.service.KlgPlatformAccountRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgPlatformAccountRecordService </p>
 * @author:         lzy
 * @Date :          2019-04-15 16:36:13  
 */
@Service("klgPlatformAccountRecordService")
public class KlgPlatformAccountRecordServiceImpl extends BaseServiceImpl<KlgPlatformAccountRecord, Long> implements KlgPlatformAccountRecordService{
	
	@Resource(name="klgPlatformAccountRecordDao")
	@Override
	public void setDao(BaseDao<KlgPlatformAccountRecord, Long> dao) {
		super.dao = dao;
	}
	

}
