/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:40:12 
 */
package hry.licqb.ecology.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.ecology.model.EcologEnter;

import java.util.Map;


/**
 * <p> EcologEnterDao </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:40:12  
 */
public interface EcologEnterDao extends  BaseDao<EcologEnter, Long> {
    public int countEnter(Map<String, Object> map);
}
