/**
 * Copyright:   
 * @author:      hec
 * @version:     V1.0 
 * @Date:        2018-12-27 16:37:41 
 */
package hry.admin.contract.service.impl;

import hry.admin.contract.model.ContractScheme;
import hry.admin.contract.service.ContractSchemeService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ContractSchemeService </p>
 * @author:         hec
 * @Date :          2018-12-27 16:37:41  
 */
@Service("contractSchemeService")
public class ContractSchemeServiceImpl extends BaseServiceImpl<ContractScheme, Long> implements ContractSchemeService{
	
	@Resource(name="contractSchemeDao")
	@Override
	public void setDao(BaseDao<ContractScheme, Long> dao) {
		super.dao = dao;
	}
	

}
