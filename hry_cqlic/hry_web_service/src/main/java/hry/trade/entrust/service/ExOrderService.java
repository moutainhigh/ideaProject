/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午1:45:20
 */
package hry.trade.entrust.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hry.core.mvc.service.base.BaseService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExOrderService extends BaseService<ExOrder, Long> {
	
	
	/**
	 * 
	 * <p> 查询最后一条成交的价格</p>
	 * @author:         Gao Mimi
	 * @param:    
	 * @return: void 
	 * @Date :          2016年4月26日 下午6:48:58   
	 * @throws:
	 */
	public void setCurrentExchangPrice(String coinCode);
	/**
	 * 
	 * <p>查询开盘价格 </p>
	 * @author:         Gao Mimi
	 * @param:    @param coinCode
	 * @return: void 
	 * @Date :          2016年5月3日 上午11:26:54   
	 * @throws:
	 */
	public void setOpenedExchangPrice(String coinCode, BigDecimal issuePrice);
	/**
	 * 
	 * <p>查询倒数第二条成交价格</p>
	 * @author:         Gao Mimi
	 * @param:    @param coinCode
	 * @return: void 
	 * @Date :          2016年5月3日 上午11:27:03   
	 * @throws:
	 */
	public void setLastExchangPrice(String coinCode);
	/**
	 * 
	 * <p> 成交一条记录，推送最新的列表</p>
	 * @author:         Gao Mimi
	 * @param:    @param count
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年4月27日 下午1:19:31   
	 * @throws:
	 */
    public void pushNewList(String coinCode, Integer count);
    public String getNewList(String coinCode, Integer count);
    public String getNewListMarket(String coinCode, Integer count, String timeorder);
    public String findNewListMarketnewAdd(String coinCode, String minDate, String maxDate);
    public void pushTotal(String coinCode);
    public String getTotal(String coinCode);
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
	
	public void setExchangeDataCache(ExOrderInfo exOrderInfo, ExOrder exOrder);
	
}
