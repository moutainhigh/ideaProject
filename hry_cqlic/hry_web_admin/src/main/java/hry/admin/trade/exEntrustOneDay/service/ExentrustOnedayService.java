/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-06-14 17:35:14 
 */
package hry.admin.trade.exEntrustOneDay.service;

import hry.admin.exchange.model.ExEntrust;
import hry.admin.trade.exEntrustOneDay.model.ExentrustOneday;
import hry.core.mvc.service.base.BaseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> ExentrustOnedayService </p>
 * @author:         shangxl
 * @Date :          2017-06-14 17:35:14  
 */
public interface ExentrustOnedayService extends BaseService<ExentrustOneday, Long> {
	/***
	 * 根据EntrustNumber删除数据
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param entrustNumber
	 * @return: void 
	 * @Date :          2017年6月15日 下午1:32:58   
	 * @throws:
	 */
	public void delByEntrustNumber(Long id);
	/**
	 * 获取最高委托价
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

	
}
