/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 12:01:59 
 */
package hry.admin.licqb.platform.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.licqb.platform.model.UsdtTotal;

import java.util.List;
import java.util.Map;


/**
 * <p> UsdtTotalDao </p>
 * @author:         zhouming
 * @Date :          2020-06-11 12:01:59  
 */
public interface UsdtTotalDao extends  BaseDao<UsdtTotal, Long> {

    public List<UsdtTotal> findUsdtTotalList(Map<String, Object> map);
}
