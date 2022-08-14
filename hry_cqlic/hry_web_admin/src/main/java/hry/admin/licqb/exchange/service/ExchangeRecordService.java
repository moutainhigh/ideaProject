/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:36:32 
 */
package hry.admin.licqb.exchange.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.licqb.exchange.model.ExchangeRecord;
import hry.util.QueryFilter;


/**
 * <p> ExchangeRecordService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:36:32 
 */
public interface ExchangeRecordService  extends BaseService<ExchangeRecord, Long>{

    PageResult findPageBySql(QueryFilter filter);
}
