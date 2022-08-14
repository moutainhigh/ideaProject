/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 14:51:32 
 */
package hry.admin.ico.account.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.account.model.IcoBuyOrder;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoBuyOrderDao </p>
 * @author:         lzy
 * @Date :          2019-01-14 14:51:32  
 */
public interface IcoBuyOrderDao extends  BaseDao<IcoBuyOrder, Long> {

    List<IcoBuyOrder> findPageBySql(Map<String, Object> map);
}
