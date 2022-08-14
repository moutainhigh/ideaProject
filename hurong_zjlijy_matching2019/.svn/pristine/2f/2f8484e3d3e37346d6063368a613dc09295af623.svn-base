/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-04 19:06:42 
 */
package hry.mall.platform.service.impl;

import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.Sensitive;
import hry.mall.platform.service.SensitiveService;
import org.springframework.stereotype.Service;

/**
 * <p> SensitiveService </p>
 * @author:         luyue
 * @Date :          2018-12-04 19:06:42  
 */
@Service("sensitiveService")
public class SensitiveServiceImpl extends BaseServiceImpl<Sensitive, Long> implements SensitiveService {
	
	@Resource(name="sensitiveDao")
	@Override
	public void setDao(BaseDao<Sensitive, Long> dao) {
		super.dao = dao;
	}
	

}
