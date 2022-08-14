/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-06 15:31:10 
 */
package hry.admin.klg.log.service.impl;

import hry.admin.klg.log.model.KlgExceptionLog;
import hry.admin.klg.log.service.KlgExceptionLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgExceptionLogService </p>
 * @author:         yaozhuo
 * @Date :          2019-05-06 15:31:10  
 */
@Service("klgExceptionLogService")
public class KlgExceptionLogServiceImpl extends BaseServiceImpl<KlgExceptionLog, Long> implements KlgExceptionLogService{
	
	@Resource(name="klgExceptionLogDao")
	@Override
	public void setDao(BaseDao<KlgExceptionLog, Long> dao) {
		super.dao = dao;
	}
	

}
