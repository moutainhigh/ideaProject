/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.trade.entrust.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExOrderInfoDao extends BaseDao<ExOrderInfo, Long> {
    public  void insertExorderInfos(List<ExOrderInfo> list);
    public List<ExOrderInfo> findNewList(Map<String,Object> map);
	public List<ExOrderInfo> findNewListDesc(Map<String,Object> map);
	
	

}
