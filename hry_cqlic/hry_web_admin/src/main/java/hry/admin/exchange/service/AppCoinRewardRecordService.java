/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-02 11:44:17 
 */
package hry.admin.exchange.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.AppCoinRewardRecord;
import hry.util.QueryFilter;


/**
 * <p> AppCoinRewardRecordService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-02 11:44:17 
 */
public interface AppCoinRewardRecordService  extends BaseService<AppCoinRewardRecord, Long>{

    public PageResult findPageBySql(QueryFilter filter);
}
