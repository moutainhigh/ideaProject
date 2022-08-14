/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-17 10:48:13 
 */
package hry.admin.ico.upgraderecord.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.upgraderecord.model.IcoUpgradeRecord;
import hry.util.QueryFilter;


/**
 * <p> IcoUpgradeRecordService </p>
 * @author:         houz
 * @Date :          2019-01-17 10:48:13 
 */
public interface IcoUpgradeRecordService  extends BaseService<IcoUpgradeRecord, Long>{

    public PageResult findPageBySql(QueryFilter filter);

}
