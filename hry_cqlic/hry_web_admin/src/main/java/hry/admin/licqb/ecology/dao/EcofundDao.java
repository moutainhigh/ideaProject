/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-04 11:06:01 
 */
package hry.admin.licqb.ecology.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.licqb.ecology.model.Ecofund;

import java.util.List;
import java.util.Map;


/**
 * <p> EcofundDao </p>
 * @author:         zhouming
 * @Date :          2020-06-04 11:06:01  
 */
public interface EcofundDao extends  BaseDao<Ecofund, Long> {
    public List<Ecofund> findEcofundList(Map<String, Object> map);
}
