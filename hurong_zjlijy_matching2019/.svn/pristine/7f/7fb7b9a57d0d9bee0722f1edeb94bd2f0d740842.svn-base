/**
 * Copyright:    
 * @author:      yjl
 * @version:     V1.0 
 * @Date:        2018-12-10 18:05:54 
 */
package hry.social.friend.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialCircleRecycleBin;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p> SocialCircleRecycleBinDao </p>
 * @author:         yjl
 * @Date :          2018-12-10 18:05:54  
 */
public interface SocialCircleRecycleBinDao extends BaseDao<SocialCircleRecycleBin, Long> {

    List<SocialCircleRecycleBin> findPageBySql(Map<String,Object> map);

    void open(@Param("ids") Long ids);

    SocialCircleRecycleBin findOne(@Param("id") Long id);
}
