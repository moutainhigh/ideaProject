/**

 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.trade.entrust.service;

import hry.core.mvc.service.base.BaseService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.redis.model.EntrustTrade;

import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExEntrustService extends BaseService<ExEntrust, Long> {
	/**
	 *get header
	 * <p> TODO</p>
	 * @author:           Gaomm
	 * @param:    @param oneday
	 * @return: void 
	 * @Date :          2017年7月5日 下午5:35:12   
	 * @throws:
	 */
	public String getHeader(String coinCode,String fixPriceCoinCode);


	public String getHeader(ExOrderInfo exEntrust);

	/**
	 *get header
	 * <p> TODO</p>
	 * @author:       Gaomm
	 * @param:    @param oneday
	 * @return: void 
	 * @Date :          2017年7月5日 下午5:35:12   
	 * @throws:
	 */
	public String getHeader(EntrustTrade exEntrust);

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
	 public ExEntrust getExEntrustByentrustNum(String entrustNum);
	 
     public void autoAddExEntrust();
     public void cancelAutoAddExEntrust();
     public String addExEntrust(Integer fixPriceType,Integer type,Long customerId,BigDecimal price,String autoUsername,String coinCode,BigDecimal entrustCount,String currencyType,String website);
    
     public String[] getExEntrustChangeMarket(String coinCode, String fixPriceCoinCode,Integer n,Integer n100);
     public void tradeInit();
     
     
     public String getExEntrustChangeDephMarket(String coinCode, String fixPriceCoinCode, Integer n, BigDecimal depth);
     
  
}
