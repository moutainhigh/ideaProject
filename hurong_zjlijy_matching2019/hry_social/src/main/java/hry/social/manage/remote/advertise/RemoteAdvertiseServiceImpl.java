/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-07-08 17:52:28
 */
package hry.social.manage.remote.advertise;

import hry.social.advertise.service.SocialAdvertiseService;
import hry.social.manage.remote.api.advertise.RemoteAdvertiseService;
import hry.social.manage.remote.model.advertise.SocialAdvertise;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> SocialAdvertiseService </p>
 *
 * @author: javalx
 * @Date :          2019-07-08 17:52:28
 */
public class RemoteAdvertiseServiceImpl implements RemoteAdvertiseService {

    @Resource
    SocialAdvertiseService socialAdvertiseService;

    /**
     * 查询广告列表
     *
     * @param langKey
     * @return
     */
    @Override
    public List<SocialAdvertise> advList(String langKey) {
        QueryFilter qf = new QueryFilter(SocialAdvertise.class);
        qf.addFilter("langKey=", langKey);
        qf.addFilter("states=", 1);
        qf.setOrderby("sortNum ASC");
        List<SocialAdvertise> list = socialAdvertiseService.find(qf);
        return list;
    }
}
