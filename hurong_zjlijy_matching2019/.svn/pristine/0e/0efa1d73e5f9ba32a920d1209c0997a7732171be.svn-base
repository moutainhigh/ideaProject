/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-11 14:32:20
 */
package hry.social.module.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.module.SocialAppModuleConfig;
import hry.social.module.dao.SocialAppModuleConfigDao;
import hry.social.module.service.SocialAppModuleConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> SocialAppModuleConfigService </p>
 * @author: lixin
 * @Date :          2019-04-11 14:32:20  
 */
@Service("socialAppModuleConfigService")
public class SocialAppModuleConfigServiceImpl extends BaseServiceImpl<SocialAppModuleConfig,Long> implements SocialAppModuleConfigService {

    @Resource(name = "socialAppModuleConfigDao")
    @Override
    public void setDao(BaseDao<SocialAppModuleConfig,Long> dao) {
        super.dao = dao;
    }


    @Override
    public void updateStatus(SocialAppModuleConfig samc) {
        ((SocialAppModuleConfigDao) dao).updateStatus(samc.getAreaName(), samc.getModuleName(), samc.getStatus());
    }

    @Override
    public int hasData(String moduleName, String areaName) {
        return ((SocialAppModuleConfigDao) dao).hasData(moduleName, areaName);
    }

    @Override
    public List<SocialAppModuleConfig> getModuleConfig(String countryArea) {
        return ((SocialAppModuleConfigDao) dao).getModuleConfig(countryArea);
    }
}
