/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 16:50:30 
 */
package hry.admin.ico.account.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.account.model.IcoTransactionRecord;
import hry.util.QueryFilter;


/**
 * <p> IcoTransactionRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-14 16:50:30 
 */
public interface IcoTransactionRecordService  extends BaseService<IcoTransactionRecord, Long>{
    /**
     * sql分页查询
     * @param filter
     * @return
     */
    public PageResult findPageBySql(QueryFilter filter);

}
