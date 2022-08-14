/**
 * Copyright:    
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-04-11 14:32:20 
 */
package hry.social.module.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.module.SocialAppModuleConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialAppModuleConfigDao </p>
 * @author:         lixin
 * @Date :          2019-04-11 14:32:20  
 */
public interface SocialAppModuleConfigDao extends BaseDao<SocialAppModuleConfig, Long> {

    void updateStatus(@Param("areaName") String areaName, @Param("moduleName") String moduleName, @Param("status") String status);

    int hasData(@Param("moduleName") String moduleName, @Param("areaName") String areaName);

    List<SocialAppModuleConfig> getModuleConfig(@Param("countryArea") String countryArea);

}
