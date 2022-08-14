/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:48:57 
 */
package hry.admin.commend.service.impl;

import hry.admin.commend.model.AppCommendDeploy;
import hry.admin.commend.service.AppCommendDeployService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppCommendDeployService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:48:57  
 */
@Service("appCommendDeployService")
public class AppCommendDeployServiceImpl extends BaseServiceImpl<AppCommendDeploy, Long> implements AppCommendDeployService{
	
	@Resource(name="appCommendDeployDao")
	@Override
	public void setDao(BaseDao<AppCommendDeploy, Long> dao) {
		super.dao = dao;
	}
	

}
