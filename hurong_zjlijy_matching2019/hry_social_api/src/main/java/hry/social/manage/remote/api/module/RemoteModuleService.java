package hry.social.manage.remote.api.module;

import hry.social.manage.remote.model.module.SocialAppModuleConfig;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author javal
 * @title: RemoteModuleService
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/6/2414:16
 */
public interface RemoteModuleService {

    /**
     * 根据国家地区查询权限列表
     *
     * @param countryArea
     * @return
     */
    List<SocialAppModuleConfig> getModuleConfig(String countryArea);
}
