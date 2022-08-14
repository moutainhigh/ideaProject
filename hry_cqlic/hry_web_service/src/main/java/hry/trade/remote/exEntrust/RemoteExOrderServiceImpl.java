/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.remote.exEntrust;

import hry.change.remote.exEntrust.RemoteExOrderService;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;

import java.util.List;

import javax.annotation.Resource;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午2:04:29
 */

public class RemoteExOrderServiceImpl implements RemoteExOrderService {

	@Resource
	private ExOrderInfoService exOrderInfoService;
	@Resource
	private ExOrderService exOrderService;
	@Override
	public ExOrderInfo findByOrderNum(String orderNum) {
		
		QueryFilter filter = new QueryFilter(ExOrderInfo.class);
		filter.addFilter("orderNum=", orderNum);
		ExOrderInfo exOrderInfo = exOrderInfoService.get(filter);
		return exOrderInfo;
	}

	@Override
	public List<ExOrderInfo> find(RemoteQueryFilter remoteQueryFilter) {
		// TODO Auto-generated method stub
		return exOrderInfoService.find(remoteQueryFilter.getQueryFilter());
	}

	@Override
	public void savePeriodLastKLineList(ExOrderInfo exOrderInfo) {
		 exOrderInfoService.savePeriodLastKLineList(exOrderInfo);
	}

	@Override
	public PageResult findPage(RemoteQueryFilter filter) {
		
		return exOrderInfoService.findPageResult(filter.getQueryFilter());
	}

	@Override
	public List<ExOrder> exAveragePrice(String coinCode) {
		// TODO Auto-generated method stub
		return exOrderService.exAveragePrice(coinCode);
	}

	
}
