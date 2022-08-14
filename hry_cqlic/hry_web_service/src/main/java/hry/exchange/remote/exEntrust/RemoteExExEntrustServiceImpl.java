/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.remote.exEntrust;

import hry.change.remote.exEntrust.RemoteExExEntrustService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.entrust.service.ExExEntrustService;
import hry.exchange.product.model.ProductCommon;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.service.ExEntrustPlanService;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;

import javax.annotation.Resource;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午2:04:29
 */

public class RemoteExExEntrustServiceImpl implements RemoteExExEntrustService {

	@Resource
	private ExEntrustService entrustService;
	@Resource
	private ExOrderInfoService exOrderInfoService;
	@Resource
	private ExOrderService exOrderService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private ExEntrustPlanService exEntrustPlanService;
	@Resource
	private ExExEntrustService exExEntrustService;

	@Override
	public ProductCommon getProductCommon(String coinCode) {
		// TODO Auto-generated method stub
		return new ProductCommon();
	}

	@Override
	public String[] canceldeductMoney(ExEntrust exEntrust) {
		// TODO Auto-generated method stub
		return null;
	}
}
