/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:55:39 
 */
package hry.web.message.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.web.message.model.AppWorkOrder;
import org.apache.ibatis.annotations.Param;


/**
 * <p> AppWorkOrderDao </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:55:39  
 */
public interface AppWorkOrderDao extends  BaseDao<AppWorkOrder, Long> {

	public List<AppWorkOrder> queryByPage(AppWorkOrder appWorkOrder);

	public AppWorkOrder get(@Param("id") Long id);

	public int queryCount(@Param("categoryId") Long categoryId);

	public List<AppWorkOrder> queryByList(Map<String, String> params);

}
