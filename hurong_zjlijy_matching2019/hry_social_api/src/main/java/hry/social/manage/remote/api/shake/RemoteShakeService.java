package hry.social.manage.remote.api.shake;

import hry.bean.FrontPage;
import hry.manage.remote.model.User;
import hry.social.manage.remote.model.base.RemoteResult;
import hry.social.manage.remote.model.shake.SocialShakeProduct;
import hry.social.manage.remote.model.shake.SocialShakeShop;
import hry.social.manage.remote.model.shake.SocialShopProduct;
import org.nutz.lang.Lang;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RemoteShakeService {

    /**
     * 抖人
     *
     * @param customerId   用户ID
     * @param nickName     昵称
     * @param headPortrait 头像
     * @param longitude    经度
     * @param latitude     纬度
     * @return
     */
    RemoteResult shakePerson(Long customerId, String nickName, String headPortrait, BigDecimal longitude, BigDecimal latitude);

    /**
     * 抖粉
     *
     * @param user      用户信息
     * @param longitude 经度
     * @param latitude  纬度
     * @return
     */
    RemoteResult shakeFans(User user, BigDecimal longitude, BigDecimal latitude);

    /**
     * 抖购
     *
     * @param customerId   用户ID
     * @param nickName     昵称
     * @param headPortrait 头像
     * @param longitude    经度
     * @param latitude     纬度
     * @return
     */
    RemoteResult shakeProduct(Long customerId, String nickName, String headPortrait, BigDecimal longitude, BigDecimal latitude, SocialShakeProduct socialShakeProduct);

    /**
     * 抖店
     *
     * @param customerId   用户ID
     * @param nickName     昵称
     * @param headPortrait 头像
     * @param longitude    经度
     * @param latitude     纬度
     * @return
     */
    RemoteResult shakeShop(Long customerId, String nickName, String headPortrait, BigDecimal longitude, BigDecimal latitude);

    /**
     * 抖人记录
     *
     * @param params 分页参数
     * @return
     */
    FrontPage shakePersonPage(Map<String,String> params);

    /**
     * 抖粉记录
     *
     * @param params 分页参数
     * @return
     */
    FrontPage shakeFansPage(Map<String,String> params);

    /**
     * 抖购记录
     *
     * @param params 分页参数
     * @return
     */
    FrontPage shakeProductPage(Map<String,String> params);

    /**
     * 抖店记录
     *
     * @param params 分页参数
     * @return
     */
    FrontPage shakeShopPage(Map<String,String> params);

    /**
     * 查询店铺详情
     *
     * @param shopId 店铺ID
     * @return
     */
    SocialShakeShop getShopInfo(Long shopId);

    /**
     * 热销推荐商品
     *
     * @return
     */
    List<SocialShopProduct> recommendShop();

    /**
     * 热销推荐商品
     *
     * @return
     */
    Long lastProduct(Long customerId);

    /**
     * 查询用户是否有线下店铺
     *
     * @param inviteUserId
     * @return
     */
    int hasOffShop(Long inviteUserId);
}
