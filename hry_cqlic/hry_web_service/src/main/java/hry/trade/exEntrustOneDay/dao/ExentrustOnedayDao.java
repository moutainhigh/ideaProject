/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-06-14 17:35:14 
 */
package hry.trade.exEntrustOneDay.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.trade.entrust.ExEntrustSimple;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.exEntrustOneDay.model.ExentrustOneday;


/**
 * <p> ExentrustOnedayDao </p>
 * @author:         shangxl
 * @Date :          2017-06-14 17:35:14  
 */
public interface ExentrustOnedayDao extends  BaseDao<ExentrustOneday, Long> {
	/**
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年6月15日 下午7:23:59   
	 * @throws:
	 */
	public BigDecimal getMaxOrMinEntrustPrice(Map<String, Object> map);
	
	/**
	 * 获取买单深度数据
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<ExEntrust> 
	 * @Date :          2017年6月16日 上午10:24:42   
	 * @throws:
	 */
	public List<ExEntrust> getExEntrustBuyDeph(Map<String, Object> map);
	/**
	 * 获取卖单深度数据
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<ExEntrust> 
	 * @Date :          2017年6月16日 上午10:25:00   
	 * @throws:
	 */
	public List<ExEntrust> getExEntrustSellDeph(Map<String, Object> map);
	/***
	 * 获取委托买0
	 * @param map
	 * @return
	 */
	public List<ExEntrustSimple> getExEntrustBuyChangeMarket(Map<String, Object> map);
	/**
	 * 获取委托卖0
	 * @param map
	 * @return
	 */
	public List<ExEntrustSimple> getExEntrustSellChangeMarket(Map<String, Object> map);
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
    public List<ExentrustOneday> listMatchByBuyLimitedPrice(Map<String, Object> map); //买家限价（必须相等才匹配）
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
    public List<ExentrustOneday> listMatchByBuyMarketPrice(Map<String, Object> map); ////买家市价 	//只要未完成的卖家都可以
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
    public List<ExentrustOneday> listMatchBySellLimitedPrice(Map<String, Object> map); ////卖家限价  //必须相等才匹配
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
    public List<ExentrustOneday> listMatchBySellMarketPrice(Map<String, Object> map); //////卖家市价 //只要未完成的卖家都可以

}
