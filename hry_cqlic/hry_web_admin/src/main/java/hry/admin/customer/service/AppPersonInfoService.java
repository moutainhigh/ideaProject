/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:45:27 
 */
package hry.admin.customer.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.customer.model.AppPersonInfo;

import java.util.List;



/**
 * <p> AppPersonInfoService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:45:27 
 */
public interface AppPersonInfoService  extends BaseService<AppPersonInfo, Long>{


    List<AppPersonInfo> getAppPersonInfoByEmailPhone (List<String> userNameList);

    public AppPersonInfo getByCustomerId(Long id);
}
