/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:49:05 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExProductParameter;
import hry.admin.exchange.service.ExProductParameterService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExProductParameterService </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:49:05  
 */
@Service("exProductParameterService")
public class ExProductParameterServiceImpl extends BaseServiceImpl<ExProductParameter, Long> implements ExProductParameterService{
	
	@Resource(name="exProductParameterDao")
	@Override
	public void setDao(BaseDao<ExProductParameter, Long> dao) {
		super.dao = dao;
	}
	

}
