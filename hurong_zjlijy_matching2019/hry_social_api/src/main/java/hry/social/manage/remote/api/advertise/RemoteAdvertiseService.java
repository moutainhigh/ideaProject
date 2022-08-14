package hry.social.manage.remote.api.advertise;

import hry.social.manage.remote.model.advertise.SocialAdvertise;

import java.util.List;

/**
 * @author javal
 * @title: RemoteAdvertiseService
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/7/914:31
 */
public interface RemoteAdvertiseService {

    /**
     * 查询广告列表
     *
     * @param langKey
     * @return
     */
    List<SocialAdvertise> advList(String langKey);
}
