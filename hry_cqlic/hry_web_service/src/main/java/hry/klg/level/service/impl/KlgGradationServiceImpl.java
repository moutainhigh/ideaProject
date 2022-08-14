/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 14:49:58 
 */
package hry.klg.level.service.impl;

import hry.klg.level.model.KlgGradation;
import hry.klg.level.service.KlgGradationService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgGradationService </p>
 * @author:         lzy
 * @Date :          2019-05-17 14:49:58  
 */
@Service("klgGradationService")
public class KlgGradationServiceImpl extends BaseServiceImpl<KlgGradation, Long> implements KlgGradationService{
	
	@Resource(name="klgGradationDao")
	@Override
	public void setDao(BaseDao<KlgGradation, Long> dao) {
		super.dao = dao;
	}
	

}
