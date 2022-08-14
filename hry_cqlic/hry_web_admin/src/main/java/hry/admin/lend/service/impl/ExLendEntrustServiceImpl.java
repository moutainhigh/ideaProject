/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:34 
 */
package hry.admin.lend.service.impl;

import hry.admin.lend.model.ExLendEntrust;
import hry.admin.lend.service.ExLendEntrustService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExLendEntrustService </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:48:34  
 */
@Service("exLendEntrustService")
public class ExLendEntrustServiceImpl extends BaseServiceImpl<ExLendEntrust, Long> implements ExLendEntrustService{
	
	@Resource(name="exLendEntrustDao")
	@Override
	public void setDao(BaseDao<ExLendEntrust, Long> dao) {
		super.dao = dao;
	}
	

}
