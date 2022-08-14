/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-07-08 16:15:05 
 */
package hry.klg.log.service.impl;

import hry.klg.log.model.KlgExceptionLog;
import hry.klg.log.service.KlgExceptionLogService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgExceptionLogService </p>
 * @author:         yaozhuo
 * @Date :          2019-07-08 16:15:05  
 */
@Service("klgExceptionLogService")
public class KlgExceptionLogServiceImpl extends BaseServiceImpl<KlgExceptionLog, Long> implements KlgExceptionLogService{
	
	@Resource(name="klgExceptionLogDao")
	@Override
	public void setDao(BaseDao<KlgExceptionLog, Long> dao) {
		super.dao = dao;
	}
	

}
