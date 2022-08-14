/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:26:42 
 */
package hry.admin.exchange.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExOrderInfo;
import hry.util.QueryFilter;

import java.math.BigDecimal;


/**
 * <p> ExOrderInfoService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:26:42 
 */
public interface ExOrderInfoService  extends BaseService<ExOrderInfo, Long>{

    public PageResult findPageBySql(QueryFilter filter);

    public PageResult findPageBySqlList(QueryFilter filter);

    public BigDecimal getYesterdayTreads(String flag);

}
