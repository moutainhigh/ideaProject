/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 17:16:18 
 */
package hry.admin.ico.award.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.award.model.IcoAwardRecord;
import hry.util.QueryFilter;


/**
 * <p> IcoAwardRecordService </p>
 * @author:         houz
 * @Date :          2019-01-14 17:16:18 
 */
public interface IcoAwardRecordService  extends BaseService<IcoAwardRecord, Long>{

    public PageResult findPageBySql(QueryFilter filter);


}
