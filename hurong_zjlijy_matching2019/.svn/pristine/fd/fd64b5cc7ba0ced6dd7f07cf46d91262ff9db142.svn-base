/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-04-11 14:32:20 
 */
package hry.social.module.service;


import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.module.SocialAppModuleConfig;

import java.util.List;

/**
 * <p> SocialAppModuleConfigService </p>
 * @author:         lixin
 * @Date :          2019-04-11 14:32:20 
 */
public interface SocialAppModuleConfigService  extends BaseService<SocialAppModuleConfig, Long> {

    void updateStatus(SocialAppModuleConfig samc);

    int hasData(String moduleName, String areaName);

    List<SocialAppModuleConfig> getModuleConfig(String countryArea);
}
