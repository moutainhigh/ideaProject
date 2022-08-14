/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.trade.entrust.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.manage.remote.model.Order;
import hry.manage.remote.model.base.FrontPage;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExOrderInfoService extends BaseService<ExOrderInfo, Long> {
	
	/**
	 * 
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param id
	 * @param: @return
	 * @return: String
	 * @Date : 2016年4月21日 下午5:25:33
	 * @throws:
	 */

	 /**
	  * 
	  * <p> 成单后，针对坐市账户的特殊处理</p>
	  * @author:         Gao Mimi
	  * @param:    
	  * @return: void 
	  * @Date :          2016年6月30日 下午7:41:56   
	  * @throws:
	  */
	 public void AfterMatchSpecialDeal(ExEntrust exEntrust);

	    /**
	     * 
	     * <p> 如果买家和卖家都已经匹配完了就可以从mongo里面删除</p>
	     * @author:         Gao Mimi
	     * @param:    @param buyexEntrust
	     * @param:    @param sellappAccount
	     * @return: void 
	     * @Date :          2016年4月22日 下午6:04:04   
	     * @throws:
	     */
//	 public void mongoRemoveEntrust(ExEntrust buyexEntrust,ExEntrust sellentrust);
	 
	 public String[] endTransaction(ExOrderInfo exOrderInfo, ExOrder exOrder, ExEntrust buyexEntrust, ExEntrust sellentrust);
	 
   /**
     * 保存分期最后一个节点的数据
     * @param exOrderInfo
     */
	 public void savePeriodLastKLineList(ExOrderInfo exOrderInfo);
	 /**
	  * 获取一个用户的总买币额
	  * <p> TODO</p>
	  * @author:         Zhang Lei
	  * @param:    @param buyCustomId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2017年3月9日 上午10:24:50   
	  * @throws:
	  */
	 public BigDecimal getTotalBuyMoney(Long buyCustomId);
	 /**
	  * 获取今天的最后一笔成交价
	  * <p> TODO</p>
	  * @author:         Zhang Lei
	  * @param:    @param buyCustomId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2017年3月9日 上午10:24:50   
	  * @throws:
	  */
	 public ExOrderInfo exAveragePrice(String coinCode, String fixPriceCoinCode);
	 /**
	  * 获取今天的最后一笔成交价
	  * <p> TODO</p>
	  * @author:         Zhang Lei
	  * @param:    @param buyCustomId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2017年3月9日 上午10:24:50   
	  * @throws:
	  */
	 public ExOrderInfo getAveragePriceYesterday(String coinCode);
	 
	 /**
	  * 删除挂单临时表中的买单、卖单
	  * <p> TODO</p>
	  * @author:         Shangxl
	  * @param:    @param buyEntrust
	  * @param:    @param sellEntrust
	  * @param:    @return
	  * @return: boolean 
	  * @Date :          2017年6月15日 上午11:23:57   
	  * @throws:
	  */
	 public boolean removeExentrustOneDay(ExEntrust buyEntrust, ExEntrust sellEntrust);
		/**
		 * 
		 * @param type
		 * @param buyExEntrust
		 * @param sellentrust
		 * @param tradeCount
		 * @param tradePrice
		 * @return
		 */
		public ExOrderInfo createExOrderInfo(Integer type, ExEntrust buyExEntrust, ExEntrust sellentrust, BigDecimal tradeCount, BigDecimal tradePrice);
		
	 
	/**
	 * 前台分页查询方法
	 * @param params
	 */
	public FrontPage findFrontPage(Map<String, String> params);
	
	public ExOrder createExOrder(ExOrderInfo exOrderInfo);
	/**
	 * 定时计算我放手续费和佣金
	 */
	public void  timingCulAtferMoney();
	public FrontPage selectFee(Map<String, String> params);
	public FrontPage frontselectFee(Map<String, String> params);
	
	public List<BigDecimal> yesterdayPrice();


	public  List<ExOrderInfo> selectTransaction(String username);

	public int selectTransactionCount(String username);
	
	/**
	 * SQL分页
	 * @param filter
	 * @return
	 */
	public PageResult findPageBySql(QueryFilter filter);
	
	/**
	 * 新sql分页
	 * @param filter
	 * @return
	 */
	public PageResult findPageBySqlList(QueryFilter filter);
}
