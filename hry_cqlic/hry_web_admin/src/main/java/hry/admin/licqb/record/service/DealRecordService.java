/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:22:23 
 */
package hry.admin.licqb.record.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.licqb.record.model.DealRecord;
import hry.util.QueryFilter;

import java.util.Map;


/**
 * <p> DealRecordService </p>
 * @author:         zhouming
 * @Date :          2019-08-14 15:22:23 
 */
public interface DealRecordService  extends BaseService<DealRecord, Long>{

    public PageResult findDealRecordList(QueryFilter filter);

    public PageResult getUserTotal(QueryFilter filter);

    public PageResult findAllUserInfo(QueryFilter filter);

    public PageResult findUserTeamInfo(QueryFilter filter);

    public Long getDealDmMoney(Integer optType);
}
