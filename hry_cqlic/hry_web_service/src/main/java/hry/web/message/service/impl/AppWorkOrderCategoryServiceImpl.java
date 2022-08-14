/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:35:25 
 */
package hry.web.message.service.impl;

import hry.web.message.dao.AppWorkOrderCategoryDao;
import hry.web.message.dao.AppWorkOrderDao;
import hry.web.message.model.AppWorkOrder;
import hry.web.message.model.AppWorkOrderCategory;
import hry.web.message.service.AppWorkOrderCategoryService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppWorkOrderCategoryService </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:35:25  
 */
@Service("appWorkOrderCategoryService")
public class AppWorkOrderCategoryServiceImpl extends BaseServiceImpl<AppWorkOrderCategory, Long> implements AppWorkOrderCategoryService{
	
	@Resource(name="appWorkOrderCategoryDao")
	@Override
	public void setDao(BaseDao<AppWorkOrderCategory, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<hry.manage.remote.model.AppWorkOrderCategory> findWorkOrderCategoryList(Map<String, String> params) {
		List<hry.manage.remote.model.AppWorkOrderCategory> list = ((AppWorkOrderCategoryDao) dao).queryByList(params);
		return list;
	}
	

}
