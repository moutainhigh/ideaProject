/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:33:52 
 */
package hry.admin.c2c.service.impl;

import hry.admin.c2c.model.AppBusinessman;
import hry.admin.c2c.service.AppBusinessmanService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> appBusinessmanService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:33:52  
 */
@Service("appBusinessmanService")
public class AppBusinessmanServiceImpl extends BaseServiceImpl<AppBusinessman, Long> implements AppBusinessmanService {
	
	@Resource(name="appBusinessmanDao")
	@Override
	public void setDao(BaseDao<AppBusinessman, Long> dao) {
		super.dao = dao;
	}
	

}
