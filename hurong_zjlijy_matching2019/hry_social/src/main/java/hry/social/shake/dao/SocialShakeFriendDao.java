/**
 * Copyright:    
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-04-18 16:37:49 
 */
package hry.social.shake.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.shake.SocialShakeFriend;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialShakeFriendDao </p>
 * @author:         lixin
 * @Date :          2019-04-18 16:37:49  
 */
public interface SocialShakeFriendDao extends BaseDao<SocialShakeFriend, Long> {

    List<SocialShakeFriend> findPageList(Map<String,String> map);

    int updateRecord(SocialShakeFriend ssf);
}
