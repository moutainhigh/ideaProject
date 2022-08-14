/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-18 17:01:54
 */
package hry.social.shake.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.shake.SocialShakeSite;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p> SocialShakeSiteDao </p>
 *
 * @author: lixin
 * @Date :          2019-04-18 17:01:54
 */
public interface SocialShakeSiteDao extends BaseDao<SocialShakeSite,Long> {

    /**
     * 根据用户ID更新位置经纬度
     *
     * @param shakeId
     * @param longitude
     * @param latitude
     */
    void updateSite(@Param("shakeId") Long shakeId, @Param("longitude") BigDecimal longitude, @Param("latitude") BigDecimal latitude);

    int hasData(@Param("shakeId") Long shakeId);

}
