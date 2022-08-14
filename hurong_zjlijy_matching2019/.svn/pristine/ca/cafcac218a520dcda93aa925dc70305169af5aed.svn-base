/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-18 17:01:07
 */
package hry.social.shake.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.shake.SocialShakeShop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialShakeShopDao </p>
 *
 * @author: lixin
 * @Date :          2019-04-18 17:01:07
 */
public interface SocialShakeShopDao extends BaseDao<SocialShakeShop,Long> {

    List<SocialShakeShop> findPageList(Map<String,String> map);

    SocialShakeShop getShopInfo(@Param("shopId") Long shopId);

    int updateRecord(SocialShakeShop sss);
}
