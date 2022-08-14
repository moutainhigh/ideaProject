/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-26 15:47:20 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppCategoryLangname;
import hry.admin.web.service.AppCategoryLangnameService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppCategoryLangnameService </p>
 * @author:         liuchenghui
 * @Date :          2018-06-26 15:47:20  
 */
@Service("appCategoryLangnameService")
public class AppCategoryLangnameServiceImpl extends BaseServiceImpl<AppCategoryLangname, Long> implements AppCategoryLangnameService{
	
	@Resource(name="appCategoryLangnameDao")
	@Override
	public void setDao(BaseDao<AppCategoryLangname, Long> dao) {
		super.dao = dao;
	}
	

}
