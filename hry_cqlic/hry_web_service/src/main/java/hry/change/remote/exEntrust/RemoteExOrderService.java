/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.change.remote.exEntrust;

import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gao MIMI
 * @Date : 2016年3月24日 下午1:47:26
 */
public interface RemoteExOrderService {
			
	public ExOrderInfo findByOrderNum(String orderNum);	
	public List<ExOrderInfo> find(RemoteQueryFilter remoteQueryFilter);	
	
	/**
	 * 保存分期最后一个节点的数据,存入redis缓存中
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param exOrderInfo
	 * @return: void 
	 * @Date :          2016年10月26日 上午11:19:46   
	 * @throws:
	 */
	public void savePeriodLastKLineList(ExOrderInfo exOrderInfo);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年12月9日 下午3:53:36   
	 * @throws:
	 */
	public PageResult findPage(RemoteQueryFilter filter); 
	  /**
		 * 查询均价
		 * <p> TODO</p>
		 * @author:         Zeng Hao
		 * @param:    @param coinCode
		 * @param:    @return
		 * @return: List 
		 * @Date :          2016年12月1日 下午4:36:02   
		 * @throws:
		 */
		public List<ExOrder> exAveragePrice(String coinCode);
}					
