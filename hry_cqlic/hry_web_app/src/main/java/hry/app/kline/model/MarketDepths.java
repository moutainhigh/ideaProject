/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年5月13日 下午5:27:31
 */
package hry.app.kline.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月13日 下午5:27:31 
 */
public class MarketDepths {
	public Map<String,List<BigDecimal[]>> depths;

	/**
	 * <p> TODO</p>
	 * @return:     Map<String,List<BigDecimal[]>>
	 */
	public Map<String, List<BigDecimal[]>> getDepths() {
		return depths;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Map<String,List<BigDecimal[]>>
	 */
	public void setDepths(Map<String, List<BigDecimal[]>> depths) {
		this.depths = depths;
	}

}
