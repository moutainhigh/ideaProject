/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月9日 下午7:27:20
 */
package hry.oauth.user.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.oauth.user.model.AppUserOrganization;
import hry.oauth.user.service.AppUserOrganizationService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年12月9日 下午7:27:20 
 */
@Service("appUserOrganizationService")
public class AppUserOrganizationServiceImpl extends BaseServiceImpl<AppUserOrganization, Long>  implements AppUserOrganizationService  {
	
	@Resource(name = "appUserOrganizationDao")
	@Override
	public void setDao(BaseDao<AppUserOrganization, Long> dao) {
		super.dao = dao;
	}

}

	