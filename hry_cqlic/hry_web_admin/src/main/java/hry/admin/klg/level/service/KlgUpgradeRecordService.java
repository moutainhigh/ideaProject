/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 13:37:26 
 */
package hry.admin.klg.level.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.klg.level.model.KlgUpgradeRecord;
import hry.util.QueryFilter;


/**
 * <p> KlgUpgradeRecordService </p>
 * @author:         lzy
 * @Date :          2019-05-17 13:37:26 
 */
public interface KlgUpgradeRecordService  extends BaseService<KlgUpgradeRecord, Long>{

    public PageResult findPageBySql(QueryFilter filter);

}
