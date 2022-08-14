/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:53 
 */
package hry.admin.licqb.ecology.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.licqb.ecology.model.EcologEnter;

import java.util.List;
import java.util.Map;


/**
 * <p> EcologEnterDao </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:37:53  
 */
public interface EcologEnterDao extends  BaseDao<EcologEnter, Long> {
    public List<EcologEnter> findEcologEnterList(Map<String, Object> map);
    public int countEnter(Map<String, Object> map);
}
