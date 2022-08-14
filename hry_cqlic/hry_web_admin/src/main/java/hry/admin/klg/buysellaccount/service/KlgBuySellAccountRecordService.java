/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-22 17:02:55 
 */
package hry.admin.klg.buysellaccount.service;

import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.buysellaccount.model.KlgBuySellAccountRecord;
import hry.bean.PageResult;



/**
 * <p> KlgBuySellAccountRecordService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-22 17:02:55 
 */
public interface KlgBuySellAccountRecordService  extends BaseService<KlgBuySellAccountRecord, Long>{
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);


}
