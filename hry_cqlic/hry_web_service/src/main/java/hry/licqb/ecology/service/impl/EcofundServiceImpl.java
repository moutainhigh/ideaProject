/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-04 11:08:19 
 */
package hry.licqb.ecology.service.impl;

import hry.licqb.ecology.model.Ecofund;
import hry.licqb.ecology.service.EcofundService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> EcofundService </p>
 * @author:         zhouming
 * @Date :          2020-06-04 11:08:19  
 */
@Service("ecofundService")
public class EcofundServiceImpl extends BaseServiceImpl<Ecofund, Long> implements EcofundService{
	
	@Resource(name="ecofundDao")
	@Override
	public void setDao(BaseDao<Ecofund, Long> dao) {
		super.dao = dao;
	}
	

}
