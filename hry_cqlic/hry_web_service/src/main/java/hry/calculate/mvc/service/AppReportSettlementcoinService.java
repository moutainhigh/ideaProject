/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月23日 下午9:13:09
 */
package hry.calculate.mvc.service;

import java.util.Date;
import java.util.List;

import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.product.model.ExProduct;

/**
 * @author Wu shuiming
 * @date 2016年8月23日 下午9:13:09
 */
public interface AppReportSettlementcoinService extends BaseService<AppReportSettlementcoin, Long>{

	/**
	 * 查询下拉框中的产品
	 * 
	 * @return
	 */
	public List<ExProduct> getSelectProduct();

	
}
