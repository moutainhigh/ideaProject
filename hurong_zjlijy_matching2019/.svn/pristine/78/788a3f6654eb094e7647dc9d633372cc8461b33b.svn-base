/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-18 15:46:48
 */
package hry.social.manage.remote.api.wishwall;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.wishwall.SocialWishWall;
import hry.util.QueryFilter;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * <p> SocialWishWallService </p>
 *
 * @author: lixin
 * @Date :          2018-12-18 15:46:48
 */
public interface RemoteWishWallService {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    FrontPage findPageList(Map<String,String> params );

    /**
     * 新增许愿
     *
     * @param content    心愿
     * @param signature  签名
     * @param acpwd      资金密码
     * @param customerId 用户ID
     * @return
     */
    RemoteResult save(String content, String signature, String acpwd, Long customerId);

}
