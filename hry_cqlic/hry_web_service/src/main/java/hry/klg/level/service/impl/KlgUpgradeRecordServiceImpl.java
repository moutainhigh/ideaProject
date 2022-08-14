/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 13:37:41 
 */
package hry.klg.level.service.impl;

import hry.klg.level.model.KlgUpgradeRecord;
import hry.klg.level.service.KlgUpgradeRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgUpgradeRecordService </p>
 * @author:         lzy
 * @Date :          2019-05-17 13:37:41  
 */
@Service("klgUpgradeRecordService")
public class KlgUpgradeRecordServiceImpl extends BaseServiceImpl<KlgUpgradeRecord, Long> implements KlgUpgradeRecordService{
	
	@Resource(name="klgUpgradeRecordDao")
	@Override
	public void setDao(BaseDao<KlgUpgradeRecord, Long> dao) {
		super.dao = dao;
	}
	

}
