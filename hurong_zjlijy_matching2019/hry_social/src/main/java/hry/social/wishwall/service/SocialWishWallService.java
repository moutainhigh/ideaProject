/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-18 15:46:48
 */
package hry.social.wishwall.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.wishwall.SocialWishWall;

import java.util.Map;


/**
 * <p> SocialWishWallService </p>
 *
 * @author: lixin
 * @Date :          2018-12-18 15:46:48
 */
public interface SocialWishWallService extends BaseService<SocialWishWall,Long> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    FrontPage findPageList(Map<String,String> params);

}
