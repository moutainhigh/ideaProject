/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 16:07:54 
 */
package hry.customer.commend.service;

import hry.core.mvc.service.base.BaseService;
import hry.customer.commend.model.AppCommendDeploy;



/**
 * <p> AppCommendDeployService </p>
 * @author:         menwei
 * @Date :          2017-11-28 16:07:54  
 */
public interface AppCommendDeployService  extends BaseService<AppCommendDeploy, Long>{
   
   public AppCommendDeploy getAppCommendDeploy();
}
