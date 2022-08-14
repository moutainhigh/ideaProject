/**
 * Copyright:   
 * @author:      yjl
 * @version:     V1.0 
 * @Date:        2018-12-10 18:05:54 
 */
package hry.social.friend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.friend.SocialCircleRecycleBin;
import hry.util.QueryFilter;


/**
 * <p> SocialCircleRecycleBinService </p>
 * @author:         yjl
 * @Date :          2018-12-10 18:05:54 
 */
public interface SocialCircleRecycleBinService  extends BaseService<SocialCircleRecycleBin, Long> {


    PageResult findPageBySql(QueryFilter filter);

    void open(Long ids);

    SocialCircleRecycleBin findOne(Long id);
}
