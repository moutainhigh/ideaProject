/**
 * Copyright:    
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-04-18 17:02:48 
 */
package hry.social.shake.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.shake.SocialShopOffline;
import hry.social.manage.remote.model.shake.SocialShopProduct;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> SocialShopOfflineDao </p>
 * @author:         lixin
 * @Date :          2019-04-18 17:02:48  
 */
public interface SocialShopOfflineDao extends  BaseDao<SocialShopOffline, Long> {

    SocialShopOffline randomShop(@Param("longitude") BigDecimal longitude, @Param("latitude") BigDecimal latitude);

    List<String> shopPic(@Param("shopId") Long shopId);

    SocialShopProduct randomProduct();

    List<SocialShopProduct> recommendShop();

    int hasOffShop(@Param("customerId") Long inviteUserId);
}
