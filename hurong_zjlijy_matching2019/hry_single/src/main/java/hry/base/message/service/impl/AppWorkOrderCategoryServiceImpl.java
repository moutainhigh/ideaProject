/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:35:25 
 */
package hry.base.message.service.impl;

import javax.annotation.Resource;

import hry.base.message.service.AppWorkOrderCategoryService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.model.base.message.AppWorkOrderCategory;
import org.springframework.stereotype.Service;

/**
 * <p> AppWorkOrderCategoryService </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:35:25  
 */
@Service("appWorkOrderCategoryService")
public class AppWorkOrderCategoryServiceImpl extends BaseServiceImpl<AppWorkOrderCategory, Long> implements AppWorkOrderCategoryService {
	
	@Resource(name="appWorkOrderCategoryDao")
	@Override
	public void setDao(BaseDao<AppWorkOrderCategory, Long> dao) {
		super.dao = dao;
	}

}
