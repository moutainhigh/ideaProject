/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:32:39 
 */
package hry.admin.klg.prizedraw.service;

import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.prizedraw.model.KlPrizedrawIssue;
import hry.bean.PageResult;



/**
 * <p> KlPrizedrawIssueService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:32:39 
 */
public interface KlPrizedrawIssueService  extends BaseService<KlPrizedrawIssue, Long>{
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);


}
