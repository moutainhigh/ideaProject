/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 19:00:43 
 */
package hry.admin.klg.limit.service;

import hry.bean.BaseManageUser;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.klg.limit.model.KlgDesignatedRewardecord;
import hry.util.QueryFilter;


/**
 * <p> KlgDesignatedRewardecordService </p>
 * @author:         lzy
 * @Date :          2019-04-18 19:00:43 
 */
public interface KlgDesignatedRewardecordService  extends BaseService<KlgDesignatedRewardecord, Long>{

    public PageResult findPageBySql(QueryFilter filter);

    /**
     * 添加操作日志
     */
    public void saveLimitationRecord(BaseManageUser user, KlgDesignatedRewardecord designatedRewardecord);

}
