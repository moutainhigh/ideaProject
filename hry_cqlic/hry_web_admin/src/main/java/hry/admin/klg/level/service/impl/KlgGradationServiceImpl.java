/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:30:24 
 */
package hry.admin.klg.level.service.impl;

import hry.admin.klg.level.model.KlgGradation;
import hry.admin.klg.level.service.KlgGradationService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgGradationService </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:30:24  
 */
@Service("klgGradationService")
public class KlgGradationServiceImpl extends BaseServiceImpl<KlgGradation, Long> implements KlgGradationService{
	
	@Resource(name="klgGradationDao")
	@Override
	public void setDao(BaseDao<KlgGradation, Long> dao) {
		super.dao = dao;
	}
	

}
