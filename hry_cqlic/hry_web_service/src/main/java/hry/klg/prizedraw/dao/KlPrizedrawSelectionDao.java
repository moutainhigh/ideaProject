/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:52:37 
 */
package hry.klg.prizedraw.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.prizedraw.model.KlPrizedrawSelection;


/**
 * <p> KlPrizedrawSelectionDao </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:52:37  
 */
public interface KlPrizedrawSelectionDao extends  BaseDao<KlPrizedrawSelection, Long> {
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlPrizedrawSelection> findPageBySql(Map<String,String> map);
    
}
