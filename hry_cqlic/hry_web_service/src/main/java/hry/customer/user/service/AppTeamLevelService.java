/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-15 15:30:25 
 */
package hry.customer.user.service;

import hry.core.mvc.service.base.BaseService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.model.AppTeamLevel;



/**
 * <p> AppTeamLevelService </p>
 * @author:         zhouming
 * @Date :          2019-08-15 15:30:25 
 */
public interface AppTeamLevelService  extends BaseService<AppTeamLevel, Long>{

    void saveTeamLevel(AppCustomer customer);
}
