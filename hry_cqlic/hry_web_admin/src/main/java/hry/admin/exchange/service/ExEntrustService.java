/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:12:40 
 */
package hry.admin.exchange.service;

import hry.admin.exchange.model.ExEntrust;
import hry.admin.exchange.model.ExOrderInfo;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.trade.redis.model.EntrustTrade;
import hry.util.QueryFilter;

import java.util.List;


/**
 * <p> ExEntrustService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:12:40 
 */
public interface ExEntrustService  extends BaseService<ExEntrust, Long>{

    public String getHeader(String coinCode, String fixPriceCoinCode);

    public PageResult findPageBySql(QueryFilter filter);

    public String[] cancelExEntrust(EntrustTrade entrustTrade, String remark);

    public List<ExOrderInfo> getMatchDetail(String entrustNum, String coinCode, String type);

    PageResult findLendPageBySql(QueryFilter filter);
}
