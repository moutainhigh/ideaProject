package hry.social.manage.remote.module;


import hry.social.manage.remote.api.module.RemoteModuleService;
import hry.social.manage.remote.model.module.SocialAppModuleConfig;
import hry.social.module.service.SocialAppModuleConfigService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author javal
 * @title: RemoteModuleService
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/6/2414:16
 */
public class RemoteModuleServiceImpl implements RemoteModuleService {

    @Resource
    SocialAppModuleConfigService socialAppModuleConfigService;

    /**
     * 根据国家地区查询权限列表
     *
     * @param countryArea
     * @return
     */
    @Override
    public List<SocialAppModuleConfig> getModuleConfig(String countryArea) {
        countryArea = StringUtils.isEmpty(countryArea) ? "zh_CN" : countryArea;
        return socialAppModuleConfigService.getModuleConfig(countryArea);
    }
}
