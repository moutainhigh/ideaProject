/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.admin.ico.dividend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.dividend.model.IcoDividendRecord;
import hry.util.QueryFilter;


/**
 * <p> IcoDividendRecordService </p>
 * @author:         houz
 * @Date :          2019-01-14 20:56:02 
 */
public interface IcoDividendRecordService  extends BaseService<IcoDividendRecord, Long>{

    public PageResult findPageBySql(QueryFilter filter);
    public PageResult expenditureAccount(QueryFilter filter);


}
