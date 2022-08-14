/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:33:04 
 */
package hry.admin.klg.prizedraw.dao;

import java.util.List;
import java.util.Map;

import hry.admin.klg.prizedraw.model.KlPrizedrawSelection;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> KlPrizedrawSelectionDao </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:33:04  
 */
public interface KlPrizedrawSelectionDao extends  BaseDao<KlPrizedrawSelection, Long> {
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlPrizedrawSelection> findPageBySql(Map<String,Object> map);

}
