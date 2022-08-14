/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-29 15:54:03 
 */
package hry.admin.klg.assetsrecord.service;

import hry.admin.klg.assetsrecord.model.KlgAssetsRecord;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;



/**
 * <p> KlgAssetsRecordService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-29 15:54:03 
 */
public interface KlgAssetsRecordService  extends BaseService<KlgAssetsRecord, Long>{
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);


}
