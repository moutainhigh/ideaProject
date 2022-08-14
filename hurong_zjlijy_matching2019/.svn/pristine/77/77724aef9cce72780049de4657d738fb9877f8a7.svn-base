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

/**
 * <p> SocialShakeSiteDao </p>
 *
 * @author: lixin
 * @Date :          2019-04-18 17:01:54
 */
public interface SocialShakeSiteDao extends BaseDao<SocialShakeSite,Long> {

    SocialShakeSite randomShake(@Param("shakeId") Long shakeId);

    SocialShakeSite randomShakeFans(@Param("shakeId") Long shakeId, @Param("shopCode") String shopCode);
}
