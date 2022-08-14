/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service;


import hry.trade.entrust.ExEntrustSimple;
import hry.trade.entrust.model.ExEntrust;

import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExEntrustMongoService {
	 List<ExEntrustSimple> geMongotbuyExEntrustChange(Map<String, Object> map);
	 List<ExEntrustSimple> geMongosellExEntrustChange(Map<String, Object> map);
	/* List<ExEntrustSimple> geMongoExEntrustBuyDeph(Map<String,Object> map);
	 List<ExEntrustSimple> ggeMongoExEntrustSellDeph(Map<String,Object> map);*/
}
