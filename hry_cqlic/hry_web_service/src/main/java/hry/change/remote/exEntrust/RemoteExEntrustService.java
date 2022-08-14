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
import hry.exchange.product.model.ProductCommon;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExEntrustPlan;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.redis.model.EntrustTrade;

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
public interface RemoteExEntrustService {
	/**
	 * 
	 * <p>
	 * 委托申请
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月19日 下午4:42:17
	 * @throws:
	 */
	public String[] saveExEntrust(ExEntrust exEntrust);

	/**
	 * 
	 * <p>
	 * 撤销委托
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param entrustNums
	 * @return: void
	 * @Date : 2016年4月19日 下午4:42:22
	 * @throws:
	 */
	public String[] cancelExEntrust(EntrustTrade entrustTrade);
	/**
	 * 
	 * <p>
	 * 获得委托
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param entrustNums
	 * @return: void
	 * @Date : 2016年4月19日 下午4:42:22
	 * @throws:
	 */
	public ExEntrust getExEntrust(String entrustNums, String coinCode);
	/**
	 * 
	 * <p>
	 * 获得委托
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param entrustNums
	 * @return: void
	 * @Date : 2016年4月19日 下午4:42:22
	 * @throws:
	 */
	public List<ExEntrust> getExEntrustlist(String entrustNums);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param filter
	 * @param:    @return
	 * @return: List<ExEntrust> 
	 * @Date :          2016年5月16日 下午4:46:00   
	 * @throws:
	 */
	public PageResult listExEntrust(RemoteQueryFilter filter);
	
	public List<ExEntrust>  listExEntrust(QueryFilter filter);
	/**
	 * 
	 * <p>
	 * 查询委托列表
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param customerId
	 * @param: @param status 0未成交　1部分成交　2已完成　 3部分成交已撤销 4已撤销
	 * @param: @return
	 * @return: List<ExEntrust>
	 * @Date : 2016年4月19日 下午4:42:25
	 * @throws:
	 */
	public List<ExEntrust> findByCustomerId(Long customerId, Integer status);

	/**
	 * 
	 * <p>
	 * 查询成交订单表
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param customerId
	 * @param: @param type 1买2卖
	 * @param: @return
	 * @return: List<ExOrderInfo>
	 * @Date : 2016年4月19日 下午4:42:30
	 * @throws:
	 */
	public PageResult orderFindByCustomerId(RemoteQueryFilter remoteQueryFilter);

	/**
	 * 
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param type 1买2卖
	 * @param: @param depth 深度 默认为0
	 * @param: @param n 挡（5挡10挡20挡50挡）
	 * @param: @return
	 * @return: JsonResult
	 * @Date : 2016年4月19日 下午3:57:14
	 * @throws:
	 */

	public String getExEntrustChange(String coinCode, Integer type, String depth, Integer n, String customerType);


	/**
	 * 
	 * <p> 获得最新10条成交记录</p>
	 * @author:         Gao Mimi
	 * @param:    @param coinCode
	 * @param:    @param count
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年4月27日 下午4:48:55   
	 * @throws:
	 */
	 public String getNewList(String coinCode, Integer count);
	 

	 
	 
	 /**
	  * 条件查询ExOrderList
	  * <p> TODO</p>
	  * @author:         Liu Shilei
	  * @param:    @param remoteQueryFilter
	  * @param:    @return
	  * @return: String 
	  * @Date :          2016年5月19日 下午2:13:52   
	  * @throws:
	  */
	 public List<ExOrder> findExOrderList(RemoteQueryFilter remoteQueryFilter);
	 /**
	  * 
	  * <p> 首页加初始话数据</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: String 
	  * @Date :          2016年5月20日 上午11:08:00   
	  * @throws:
	  */

     public String getTotalData(String currencyType, String Website);
     /**
      * 
      * <p> TODO</p>
      * @author:         Gao Mimi
      * @param:    @param coinCode
      * @param:    @return
      * @return: String 
      * @Date :          2016年5月24日 下午1:29:20   
      * @throws:
      */
	 public String getTotalData(String coinCode);
	 /**
	  * 
	  * <p> TODO</p>
	  * @author:         Gao Mimi
	  * @param:    @param coinCode
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2016年5月24日 下午2:59:28   
	  * @throws:
	  */
	 public BigDecimal getCurrentExchangPrice(String coinCode);
	 /**
	  * 
	  * <p> TODO</p>
	  * @author:         Gao Mimi
	  * @param:    @param entrustNum
	  * @param:    @param coinCode
	  * @param:    @return
	  * @return: List<ExOrderInfo> 
	  * @Date :          2016年5月24日 下午2:59:22   
	  * @throws:
	  */
	 public List<ExOrderInfo> getMatchDetail(String entrustNum, String coinCode, String type);
	 /**
	  * 
	  * <p> </p>
	  * @author:         Gao Mimi
	  * @param:    @param coinCode
	  * @param:    @param depth
	  * @param:    @param n
	  * @param:    @return
	  * @return: String 
	  * @Date :          2016年5月27日 下午2:01:16   
	  * @throws:
	  */
	 public String getExEntrustChangeMarket(String coinCode, String depth, Integer n, String customerType);
	 /**
	  * <p>得到委托最低的价格和最高的价格</p>
	  * @author:         Gao Mimi
	  * @param:    @param customerType
	  * @param:    @return
	  * @return: Map<String,BigDecimal> 
	  * @Date :          2016年7月5日 上午11:24:55   
	  * @throws:
	  */
	 public Map<String,BigDecimal> getExEntrustmMostPrice(String coincode, int type, List<Integer> customerType);
	 /**
	  * 
	  * <p> type：1买 小于次价格的委卖，type:2卖大于此价格的委买</p>
	  * @author:         Gao Mimi
	  * @param:    @param coincode
	  * @param:    @param type
	  * @param:    @param customerType
	  * @param:    @param entrustPrice
	  * @param:    @return
	  * @return: List<ExEntrust> 
	  * @Date :          2016年7月5日 下午12:02:01   
	  * @throws:
	  */
	 public List<ExEntrust>  getExEntrustByPrice(String coincode, int type, String customerType, BigDecimal entrustPrice);
	/**
	 * 
	 * <p> 选择条件是否符合大盘</p>
	 * @author:         Gao Mimi
	 * @param:    @param p
	 * @param:    @return
	 * @return: Boolean 
	 * @Date :          2016年8月2日 下午5:39:31   
	 * @throws:
	 */
	 public Boolean isAccordwithMarket(ExEntrustPlan p);
	 /**
	  * <p> 取消客户所有的买火卖的委托</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelAlltypeExEntrust(Integer type, String currencyType, String website, String customerType, Long customerId);
	 /**
	  * <p> 取消客户条件的委托</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelConditionExEntrust(Map<String, String> p);
	 /**
	  * 
	  * <p> 添加预埋</p>
	  * @author:         Gao Mimi
	  * @param:    @param p
	  * @return: void 
	  * @Date :          2016年8月8日 下午2:13:06   
	  * @throws:
	  */
	 public void saveExEntrustPlan(ExEntrustPlan p);
	 /**
	  * 
	  * <p> 分页预埋列表</p>
	  * @author:         Gao Mimi
	  * @param:    @param remoteQueryFilter
	  * @param:    @return
	  * @return: PageResult 
	  * @Date :          2016年8月8日 上午11:10:23   
	  * @throws:
	  */
	 public PageResult getexEntrustPlanPage(RemoteQueryFilter remoteQueryFilter);
	 public List<ExEntrustPlan> getEntrustPlanlist(RemoteQueryFilter remoteQueryFilter);
	 public void removeExEntrustPlan(Long id); 
	 /**
	  * <p> 取消客户的所以委托单</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelCustAllExEntrust(Long custtomerId);
	 /**
	  * <p> 取消客户的币种的所有委托单</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelCustAllCoinCodeExEntrust(Long custtomerId, String currencyType, String website, String CoinCode);
	 /**
	  * <p> 取消所有的未成交的委托</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelAllExEntrust();
	 
	 public  List<ExEntrust> find(RemoteQueryFilter remoteQueryFilter);

		/**
		 * 
		 * <p> TODO</p>
		 * @author:         将要收取的手续费
		 * @param:    @param exEntrust
		 * @param:    @return
		 * @return: BigDecimal 
		 * @Date :          2016年12月7日 下午1:07:57   
		 * @throws:
		 */
		public BigDecimal   getTransactionFeeing(ExEntrust exEntrust);
}
