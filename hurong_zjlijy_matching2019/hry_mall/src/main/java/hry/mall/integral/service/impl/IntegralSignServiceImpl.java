/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-01-14 19:15:07 
 */
package hry.mall.integral.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.integral.model.IntegralSign;
import hry.mall.integral.service.IntegralSignService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p> IntegralSignService </p>
 * @author:         zhouming
 * @Date :          2019-01-14 19:15:07  
 */
@Service("integralSignService")
public class IntegralSignServiceImpl extends BaseServiceImpl<IntegralSign, Long> implements IntegralSignService {
	
	@Resource(name="integralSignDao")
	@Override
	public void setDao(BaseDao<IntegralSign, Long> dao) {
		super.dao = dao;
	}
	

}
