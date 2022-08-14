/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 16:55:42 
 */
package hry.admin.klg.limit.service.impl;

import hry.admin.klg.limit.model.KlgAmountLimitation;
import hry.admin.klg.limit.service.KlgAmountLimitationService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgAmountLimitationService </p>
 * @author:         lzy
 * @Date :          2019-04-16 16:55:42  
 */
@Service("klgAmountLimitationService")
public class KlgAmountLimitationServiceImpl extends BaseServiceImpl<KlgAmountLimitation, Long> implements KlgAmountLimitationService{
	
	@Resource(name="klgAmountLimitationDao")
	@Override
	public void setDao(BaseDao<KlgAmountLimitation, Long> dao) {
		super.dao = dao;
	}
	

}
