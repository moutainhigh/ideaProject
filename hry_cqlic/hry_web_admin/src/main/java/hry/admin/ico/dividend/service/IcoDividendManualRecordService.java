/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-22 11:33:10 
 */
package hry.admin.ico.dividend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.dividend.model.IcoDividendManualRecord;
import hry.util.QueryFilter;


/**
 * <p> IcoDividendManualRecordService </p>
 * @author:         lzy
 * @Date :          2019-03-22 11:33:10 
 */
public interface IcoDividendManualRecordService  extends BaseService<IcoDividendManualRecord, Long>{

    public PageResult findPageBySql(QueryFilter filter);
}
