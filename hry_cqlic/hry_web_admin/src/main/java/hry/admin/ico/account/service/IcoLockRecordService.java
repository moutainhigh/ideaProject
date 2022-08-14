/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 19:47:55 
 */
package hry.admin.ico.account.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.account.model.IcoLockRecord;
import hry.util.QueryFilter;


/**
 * <p> IcoLockRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-14 19:47:55 
 */
public interface IcoLockRecordService  extends BaseService<IcoLockRecord, Long>{
    /**
     * sql分页查询
     * @param filter
     * @return
     */
    public PageResult findPageBySql(QueryFilter filter);

}
