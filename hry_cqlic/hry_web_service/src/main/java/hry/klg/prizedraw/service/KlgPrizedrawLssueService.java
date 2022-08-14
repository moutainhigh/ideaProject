/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:53:49 
 */
package hry.klg.prizedraw.service;

import hry.core.mvc.service.base.BaseService;
import hry.klg.prizedraw.model.KlgPrizedrawLssue;



/**
 * <p> KlgPrizedrawLssueService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:53:49 
 */
public interface KlgPrizedrawLssueService  extends BaseService<KlgPrizedrawLssue, Long>{

	/**
     * 查询本期信息
     * @return
     */
	KlgPrizedrawLssue getIssue();

}
