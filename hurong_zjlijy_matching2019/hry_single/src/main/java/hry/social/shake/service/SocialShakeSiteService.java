/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-04 19:51:35 
 */
package hry.social.shake.service;

import hry.core.mvc.service.base.BaseService;
import hry.model.social.shake.SocialShakeSite;

/**
 * <p> SocialShakeSiteService </p>
 * @author:         javalx
 * @Date :          2019-06-04 19:51:35 
 */
public interface SocialShakeSiteService extends BaseService<SocialShakeSite, Long> {

    /**
     * 先存入redis中,然后存储到数据库中
     *
     * @param shakeSiteMsg
     */
    void shakeCache(String shakeSiteMsg);
}
