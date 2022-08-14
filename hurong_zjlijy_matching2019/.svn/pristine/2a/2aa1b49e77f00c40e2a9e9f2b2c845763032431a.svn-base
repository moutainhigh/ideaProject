/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:44:40
 */
package hry.social.miningreward.service.impl;


import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.model.social.miningreward.SocialOrchardConf;
import hry.redis.common.utils.RedisService;
import hry.social.miningreward.service.SocialOrchardConfService;
import org.springframework.stereotype.Service;


/**
 * <p> SocialOrchardConfService </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 11:44:40
 */
@Service("socialOrchardConfService")
public class SocialOrchardConfServiceImpl extends BaseServiceImpl<SocialOrchardConf,Long> implements SocialOrchardConfService {

    @Resource(name = "socialOrchardConfDao")
    @Override
    public void setDao(BaseDao<SocialOrchardConf,Long> dao) {
        super.dao = dao;
    }

    @Resource
    private RedisService redisService;

//    @Override
//    public void initCache() {
//        Map<String,String> map = new HashMap<>();
//        List<SocialOrchardConf> socs = dao.selectAll();
//        for (SocialOrchardConf soc : socs) {
//            String confKey = soc.getConfKey();
//            String confVal = soc.getConfVal();
//            map.put(confKey, confVal);
//        }
//        redisService.saveMap(SocialUtil.ORCHARD_CONF, map);
//    }
}
