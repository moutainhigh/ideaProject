/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-07-08 17:52:28
 */
package hry.social.advertise.service.impl;

import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.advertise.service.SocialAdvertiseService;
import hry.social.manage.remote.model.advertise.SocialAdvertise;
import org.springframework.stereotype.Service;

/**
 * <p> SocialAdvertiseService </p>
 *
 * @author: javalx
 * @Date :          2019-07-08 17:52:28
 */
@Service("socialAdvertiseService")
public class SocialAdvertiseServiceImpl extends BaseServiceImpl<SocialAdvertise,Long> implements SocialAdvertiseService {

    @Resource(name = "socialAdvertiseDao")
    @Override
    public void setDao(BaseDao<SocialAdvertise,Long> dao) {
        super.dao = dao;
    }


}
