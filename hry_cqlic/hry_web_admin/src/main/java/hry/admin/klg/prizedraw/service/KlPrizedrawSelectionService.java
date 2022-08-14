/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:33:04 
 */
package hry.admin.klg.prizedraw.service;

import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.prizedraw.model.KlPrizedrawSelection;
import hry.bean.PageResult;



/**
 * <p> KlPrizedrawSelectionService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:33:04 
 */
public interface KlPrizedrawSelectionService  extends BaseService<KlPrizedrawSelection, Long>{

	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);

}
