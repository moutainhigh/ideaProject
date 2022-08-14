/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:48:18 
 */
package hry.admin.web.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.web.model.AppWorkOrder;

import java.util.List;


/**
 * <p> appWorkOrderDao </p>
 * @author:         sunyujie
 * @Date :          2018-07-02 09:48:18  
 */
public interface AppWorkOrderDao extends  BaseDao<AppWorkOrder, Long> {
    public List<AppWorkOrder> queryByPage(AppWorkOrder appWorkOrder);
}
