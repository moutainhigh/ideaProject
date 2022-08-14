/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:46:50 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppWorkOrderCategory;
import hry.admin.web.service.AppWorkOrderCategoryService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> appWorkOrderCategoryService </p>
 * @author:         sunyujie
 * @Date :          2018-07-02 09:46:50  
 */
@Service("appWorkOrderCategoryService")
public class AppWorkOrderCategoryServiceImpl extends BaseServiceImpl<AppWorkOrderCategory, Long> implements AppWorkOrderCategoryService {
	
	@Resource(name="appWorkOrderCategoryDao")
	@Override
	public void setDao(BaseDao<AppWorkOrderCategory, Long> dao) {
		super.dao = dao;
	}
	

}
