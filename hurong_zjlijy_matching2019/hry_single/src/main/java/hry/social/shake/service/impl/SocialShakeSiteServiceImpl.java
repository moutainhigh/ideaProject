/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 19:51:35
 */
package hry.social.shake.service.impl;

import com.alibaba.fastjson.JSON;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.model.social.shake.SocialShakeSite;
import hry.redis.common.utils.RedisService;
import hry.social.shake.dao.SocialShakeSiteDao;
import hry.social.shake.service.SocialShakeSiteService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p> SocialShakeSiteService </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 19:51:35
 */
@Service("socialShakeSiteService")
public class SocialShakeSiteServiceImpl extends BaseServiceImpl<SocialShakeSite,Long> implements SocialShakeSiteService {

    @Resource(name = "socialShakeSiteDao")
    @Override
    public void setDao(BaseDao<SocialShakeSite,Long> dao) {
        super.dao = dao;
    }

    public static String SHAKE_KEY = "SHAKE:SITE:";

    @Resource
    private RedisService redisService;
    @Resource
    private SocialShakeSiteDao socialShakeSiteDao;

    @Override
    public void shakeCache(String shakeSiteMsg) {
        System.out.println("shakeSiteMsg : " + shakeSiteMsg);
        SocialShakeSite socialShakeSite = JSON.parseObject(shakeSiteMsg, SocialShakeSite.class);
        Long shakeId = socialShakeSite.getShakeId();
        BigDecimal longitude = socialShakeSite.getLongitude();
        BigDecimal latitude = socialShakeSite.getLatitude();
        String sckey = SHAKE_KEY + shakeId;
        String jsonStr = JSON.toJSONString(socialShakeSite);
        redisService.save(sckey, jsonStr, 60);
        int hasData = socialShakeSiteDao.hasData(shakeId);
        if (hasData == 0) {
            dao.insertSelective(socialShakeSite);
        } else {
            socialShakeSiteDao.updateSite(shakeId, longitude, latitude);
        }
    }
}
