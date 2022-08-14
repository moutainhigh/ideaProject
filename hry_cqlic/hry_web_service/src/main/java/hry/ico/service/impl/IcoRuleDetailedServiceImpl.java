/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 18:38:38 
 */
package hry.ico.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.ico.model.IcoRuleDetailed;
import hry.ico.service.IcoRuleDetailedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> IcoRuleDetailedService </p>
 * @author:         lzy
 * @Date :          2019-01-12 18:38:38  
 */
@Service("icoRuleDetailedService")
public class IcoRuleDetailedServiceImpl extends BaseServiceImpl<IcoRuleDetailed, Long> implements IcoRuleDetailedService {
	
	@Resource(name="icoRuleDetailedDao")
	@Override
	public void setDao(BaseDao<IcoRuleDetailed, Long> dao) {
		super.dao = dao;
	}
	

}
