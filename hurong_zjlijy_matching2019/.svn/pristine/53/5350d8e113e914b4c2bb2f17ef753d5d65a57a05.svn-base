/**
 * Copyright:    
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2018-12-18 15:46:48 
 */
package hry.social.wishwall.dao;
import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.wishwall.SocialWishWall;
import java.util.List;
import java.util.Map;


/**
 * <p> SocialWishWallDao </p>
 * @author:         lixin
 * @Date :          2018-12-18 15:46:48  
 */
public interface SocialWishWallDao extends BaseDao<SocialWishWall, Long> {

    /**
     * 根据id集合查询数据
     *
     * @param map
     * @return
     */
    List<SocialWishWall> findBuyIds(Map<String,Object> map);

    List<SocialWishWall> findPageList(Map<String,Object> map);

    SocialWishWall footing(Map<String,Object> map);


    List<SocialWishWall> chainList(int type);

}
