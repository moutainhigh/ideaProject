/**
 * Copyright:    
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:18:05 
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsCommunity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialSnsCommunityDao </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:18:05  
 */
public interface SocialSnsCommunityDao extends BaseDao<SocialSnsCommunity, Long> {

    /**
     * 查询首页显示社区信息
     * @param language 语种key
     * @param csn 查询数
     * @return
     */
    List<SocialSnsCommunity> communityInfo(@Param("language") String language, @Param("csn") String csn);
}
