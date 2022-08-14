/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.trade.entrust.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.user.model.AppCustomer;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.redis.model.EntrustTrade;

/**
 * 
 * <p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:34:18
 */
public interface ExEntrustDao extends BaseDao<ExEntrust, Long> {
	
	public ExEntrust getExEntrustByentrustNum(String entrustNum);
    public void  insertExEntrust(List<EntrustTrade> list);
    public void  updateExEntrust(List<ExEntrust> list);
    public  List<ExEntrust>   getExEntrustListByNums(List<EntrustTrade> list);
    List<ExEntrust> getExEdBycustomerId(Map<String,Object> map);
    public  List<ExEntrust>   getExEntrustListByNumstoMysql(List<EntrustTrade> list);
    public  List<ExEntrust>   getEntrustingByCustomerId(Map<String,Object> map);
    public  List<EntrustTrade>   getEntrustTradeingByCustomerId(Map<String,Object> map);
    List<ExEntrust> getExEdBycustomerId2(Map<String, Object> map);
	List<ExEntrust> getExIngBycustomerId2(Map<String, Object> map);
}
