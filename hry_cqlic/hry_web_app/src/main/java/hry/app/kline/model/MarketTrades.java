/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年5月13日 下午5:27:31
 */
package hry.app.kline.model;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月13日 下午5:27:31 
 */
public class MarketTrades {
	public List<MarketTradesSub> trades;

	/**
	 * <p> TODO</p>
	 * @return:     List<ExOrder>
	 */
	public List<MarketTradesSub> getTrades() {
		return trades;
	}

	/** 
	 * <p> TODO</p>
	 * @return: List<ExOrder>
	 */
	public void setTrades(List<MarketTradesSub> trades) {
		this.trades = trades;
	}

	

}
