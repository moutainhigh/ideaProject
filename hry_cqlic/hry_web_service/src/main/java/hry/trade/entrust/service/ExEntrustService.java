/**

 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.trade.entrust.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.base.FrontPage;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.exEntrustOneDay.model.ExentrustOneday;
import hry.trade.redis.model.EntrustTrade;
import hry.util.QueryFilter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExEntrustService extends BaseService<ExEntrust, Long> {
	 //查找货币冠军
  	public List<ExEntrust> findFirstCoin();
  	public List<String> getFirstCoinNum();
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
	 * 前台查询分页记录
	 * @param params
	 * @return
	 */
	public FrontPage findPage(Map<String, String> params);
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
	public String[] saveExEntrustTrade(EntrustTrade exEntrust);
	

	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param type  1买2卖
	 * @param:    @param depth  深度 默认为0
	 * @param:    @param n  挡（5挡10挡20挡50挡）
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2016年4月19日 下午3:57:14   
	 * @throws:
	 */
    public String getExEntrustChange(String coinCode, Integer type, String depth, Integer n, String customerType);
    
    /**
     * 
     * <p> 委托深度0</p>
     * @author:         Gao Mimi
     * @param:    @param coinCode
     * @param:    @param depth
     * @param:    @param n
     * @param:    @param customerType
     * @param:    @return
     * @return: String 
     * @Date :          2016年7月28日 下午4:19:12   
     * @throws:
     */
	public String getExEntrustChangeMarket(String coinCode, String depth, Integer n, String customerType);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param coinCode
	 * @param:    @param customerType
	 * @param:    @param n  挡5，10，20
	 * @param:    @param depth  深度1,2,3,4,5   0.1,0.0.2,0.3,0.4,0.5
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年7月28日 下午4:21:36
	 * @throws:
	 */
	public String getExEntrustChangeDephMarket(String coinCode, String customerType, Integer n, BigDecimal depth);
    /**
     * 
     * <p> 如果买家是现价的话，取出能匹配的卖家优先顺序列表</p>
     * @author:         Gao Mimi
     * @param:    @param saasId
     * @param:    @param buyexEntrust
     * @param:    @return
     * @return: List<ExEntrust> 
     * @Date :          2016年4月25日 上午10:51:33   
     * @throws:
     */
    public List<ExentrustOneday> listMatchByBuyLimitedPrice(String saasId, ExEntrust buyexEntrust, String customerType); //买家限价（必须相等才匹配）
    /**
     * 
     * <p> 如果买家是市价的话，取出能匹配的卖家优先顺序列表</p>
     * @author:         Gao Mimi
     * @param:    @param saasId
     * @param:    @param buyexEntrust
     * @param:    @return
     * @return: List<ExEntrust> 
     * @Date :          2016年4月25日 上午10:52:59   
     * @throws:
     */
    public List<ExentrustOneday> listMatchByBuyMarketPrice(String saasId, ExEntrust buyexEntrust, String customerType); ////买家市价 	//只要未完成的卖家都可以
     /**
      * 
      * <p> 如果卖家是现价的话，取出能匹配的卖家优先顺序列表</p>
      * @author:         Gao Mimi
      * @param:    @param saasId
      * @param:    @param buyexEntrust
      * @param:    @return
      * @return: List<ExEntrust> 
      * @Date :          2016年4月25日 上午10:53:20   
      * @throws:
      */
    public List<ExentrustOneday> listMatchBySellLimitedPrice(String saasId, ExEntrust buyexEntrust, String customerType); ////卖家限价  //必须相等才匹配
      /**
       * 
       * <p> 如果买家是市价的话，取出能匹配的卖家优先顺序列表</p>
       * @author:         Gao Mimi
       * @param:    @param saasId
       * @param:    @param buyexEntrust
       * @param:    @return
       * @return: List<ExEntrust> 
       * @Date :          2016年4月25日 上午10:53:27   
       * @throws:
       */
    public List<ExentrustOneday> listMatchBySellMarketPrice(String saasId, ExEntrust buyexEntrust, String customerType); //////卖家市价 //只要未完成的卖家都可以

    /**
	 * 
	 * <p>
	 * 推送委托事实列表
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月22日 下午4:45:16
	 * @throws:
	 */
    public void pushEntrust(String coinCode, Integer type, String customerType);
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
	public ExEntrust getExEntrust(String entrustNums, String saasId, String coinCode);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Gao Mimi
	 * @param:    @param entrustNum
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: List<ExOrder> 
	 * @Date :          2016年5月24日 下午2:49:27   
	 * @throws:
	 */
	 public List<ExOrderInfo> getMatchDetail(String entrustNum, String coinCode, String type);
	 /**
	  * 
	  * <p> 初始化委托</p>
	  * @author:         Gao Mimi
	  * @param:    @param exEntrust
	  * @return: void 
	  * @Date :          2016年5月25日 下午4:03:31   
	  * @throws:
	  */
	 public void initExEntrust(ExEntrust exEntrust);
	 /**
	  * 
	  * <p> 冲突委托</p>
	  * @author:         Gao Mimi
	  * @param:    @param customerType
	  * @param:    @return
	  * @return: String[] 
	  * @Date :          2016年6月30日 下午7:13:35   
	  * @throws:
	  */
	 
	 public void conflictExEntrust(ExEntrust exEntrust);
	 /**
	  * 
	  * <p> 撤销委托</p>
	  * @author:         Gao Mimi
	  * @param:    @param entrustNums
	  * @param:    @param coinCode
	  * @param:    @return
	  * @return: String[] 
	  * @Date :          2016年6月30日 下午7:24:21   
	  * @throws:
	  */
	 public String[] cancelExEntrust(EntrustTrade entrustTrade, String remark);
	 /**
	  * <p> 取消所有的未成交的委托</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 /**
	  * <p> 取消客户的所有委托单</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelAllExEntrust(EntrustTrade entrustTrade);
	 public void cancelAllExEntrust();
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
	  * <p> 取消客户的所以委托单</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelCustAllExEntrust(EntrustTrade entrustTrade);
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
	  * <p> 取消客户条件的委托</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelConditionExEntrust(Map<String, String> p);
	 /**
	  * <p> 取消客户所有的未成交的委托</p>
	  * @author:         Gao Mimi
	  * @param:    @return
	  * @return: void
	  * @Date :          2016年7月1日 下午1:44:16   
	  * @throws:
	  */
	 public void cancelAllcustomerIdExEntrust(Long customerId, String currencyType, String website);
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
	  * <p> 删除之日期之前的所有委托单</p>
	  * @author:         Gao Mimi
	  * @param:    @param date
	  * @return: void 
	  * @Date :          2016年8月4日 下午12:04:37   
	  * @throws:
	  */
	 public void removeExEntrustByTime(Date date); 
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
		  * <p> 定时计算大盘参数</p>
		  * @author:         Gao Mimi
		  * @param:    
		  * @return: void 
		  * @Date :          2016年7月7日 下午3:07:24   
		  * @throws:
		  */
    public void  calculationMarketParameter();
    /**
     * 
     * <p> 定时判断mq是否档了，档了要做处理</p>
     * @author:         Gao Mimi
     * @param:    
     * @return: void 
     * @Date :          2016年8月25日 下午3:04:47   
     * @throws:
     */
    public void  dealEntrustMatch();
    /**
     * 
     * <p>指定配对清除</p>
     * @author:         Gao Mimi
     * @param:    @param exEntrust
     * @return: void 
     * @Date :          2016年9月18日 下午4:20:38   
     * @throws:
     */
    public void isCanelOnlyentrustNum(ExEntrust exEntrust);
    /**
     * 
     * <p> 委托放进mq</p>
     * @author:         Gao Mimi
     * @param:    @param exEntrust
     * @param:    @return
     * @return: String[] 
     * @Date :          2016年9月19日 下午3:59:50   
     * @throws:
     */
    public String[] mqsaveExEntrust(ExEntrust exEntrust);
    /**
     * 
     * <p> 撤销委托回掉处理的mq方法</p>
     * @author:         Gao Mimi
     * @param:    @param exEntrust
     * @param:    @return
     * @return: String[] 
     * @Date :          2016年9月19日 下午4:08:26   
     * @throws:
     */
    public String[] mqcancelExEntrust(ExEntrust exEntrust);
    /**
     * 
     * <p> mongo删除委托</p>
     * @author:         Gao Mimi
     * @param:    @param exEntrust
     * @param:    @return
     * @return:
     * @Date :          2016年9月19日 下午4:08:26   
     * @throws:
     */
/*    public void deleteMongoExEntrust(ExEntrust exEntrust);*/
    /***
     * 
     * <p> 委单到mongo</p>
     * @author:         Gao Mimi
     * @param:    @param exEntrust
     * @return: void 
     * @Date :          2016年11月25日 上午9:58:34   
     * @throws:
     */
    public void saveExtrustToMongo(ExEntrust exEntrust);
    

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
	
/*	*//**
	 * 通过委托对象删除redis中的缓存
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    
	 * @return: void 
	 * @Date :          2017年6月16日 下午5:56:58   
	 * @throws:
	 *//*
	public void delExEntrust(ExEntrust oneday);*/
/*	
	*//**
	 * 添加委托单缓存
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param oneday
	 * @return: void 
	 * @Date :          2017年7月5日 下午5:35:12   
	 * @throws:
	 *//*
	public void addExEntrust(ExEntrust oneday);*/
	/**
	 *get header
	 * <p> TODO</p>
	 * @author:           Gaomm
	 * @param:    @param oneday
	 * @return: void 
	 * @Date :          2017年7月5日 下午5:35:12   
	 * @throws:
	 */
	public String getHeader(String coinCode, String fixPriceCoinCode);

	/**
	 *get header
	 * <p> TODO</p>
	 * @author:           Gaomm
	 * @param:    @param oneday
	 * @return: void 
	 * @Date :          2017年7月5日 下午5:35:12   
	 * @throws:
	 */
	public String getHeader(String website, String currencyType, String coinCode, String fixPriceCoinCode);
	/**
	 *get header
	 * <p> TODO</p>
	 * @author:       Gaomm
	 * @param:    @param oneday
	 * @return: void 
	 * @Date :          2017年7月5日 下午5:35:12   
	 * @throws:
	 */
	public String getHeader(ExEntrust exEntrust);
	/**
	 *get header
	 * <p> TODO</p>
	 * @author:       Gaomm
	 * @param:    @param oneday
	 * @return: void 
	 * @Date :          2017年7月5日 下午5:35:12   
	 * @throws:
	 */
	public String getHeader(ExOrderInfo exOrderInfo);
	/**
	 * 委托单缓存到redis
	 * @param exEntrust
	 *//*
	public void saveExtrustToRedis(ExEntrust exEntrust); */


	
	/**
	 * 前台查询分页记录
	 * @param params
	 * @return
	 */
	public FrontPage findFrontPage(Map<String, String> params);
	
	public String[] saveExEntrustCheck(EntrustTrade exEntrust);
	public String[] deductMoney(ExEntrust exEntrust); 
	/**
	 * buy,sell,key里面已经 没有这个单撤销不了就，用这个撤销
	 * @param exEntrust
	 * @param remark
	 * @returnm
	 */
	public String[] cancelExEntrustnokey(ExEntrust exEntrust, String remark);
	/**
	 * sql 分页
	 * @param filter
	 * @return
	 */
	public PageResult findPageBySql(QueryFilter filter);
	
	
	/**
	 * 新sql分页功能
	 * @param filter
	 * @return
	 */
	public PageResult findPageBySqlList(QueryFilter filter);
}
