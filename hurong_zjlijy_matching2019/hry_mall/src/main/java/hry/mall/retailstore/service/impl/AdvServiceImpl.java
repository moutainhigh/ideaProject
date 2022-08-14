/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-31 16:05:22 
 */
package hry.mall.retailstore.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.Adv;
import hry.mall.retailstore.service.AdvService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p> AdvService </p>
 * @author:         zhouming
 * @Date :          2019-05-31 16:05:22  
 */
@Service("advService")
public class AdvServiceImpl extends BaseServiceImpl<Adv, Long> implements AdvService {
	
	@Resource(name="advDao")
	@Override
	public void setDao(BaseDao<Adv, Long> dao) {
		super.dao = dao;
	}
	

}
