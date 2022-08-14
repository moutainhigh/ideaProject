/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:53:48 
 */
package hry.klg.prizedraw.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.prizedraw.model.KlgPrizedrawLssue;


/**
 * <p> KlgPrizedrawLssueDao </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:53:48  
 */
public interface KlgPrizedrawLssueDao extends  BaseDao<KlgPrizedrawLssue, Long> {
	
	/**
     * 查询本期信息
     * @return
     */
	KlgPrizedrawLssue getIssue();

}
