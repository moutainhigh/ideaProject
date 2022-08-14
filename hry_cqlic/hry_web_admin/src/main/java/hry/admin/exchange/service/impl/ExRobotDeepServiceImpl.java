/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-12 20:31:39 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExRobotDeep;
import hry.admin.exchange.service.ExRobotDeepService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> exRobotDeepService </p>
 * @author:         tianpengyu
 * @Date :          2018-09-12 20:31:39  
 */
@Service("exRobotDeepService")
public class ExRobotDeepServiceImpl extends BaseServiceImpl<ExRobotDeep, Long> implements ExRobotDeepService {
	
	@Resource(name="exRobotDeepDao")
	@Override
	public void setDao(BaseDao<ExRobotDeep, Long> dao) {
		super.dao = dao;
	}
	

}
