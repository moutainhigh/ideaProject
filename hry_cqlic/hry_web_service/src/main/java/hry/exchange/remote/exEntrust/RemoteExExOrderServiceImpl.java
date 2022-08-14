/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.remote.exEntrust;

import hry.account.fund.model.AppAccount;
import hry.account.remote.RemoteAppAccountService;
import hry.change.remote.exEntrust.RemoteExExOrderService;
import hry.change.remote.exEntrust.RemoteExOrderService;
import hry.core.constant.CodeConstant;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.message.MessageConstant;
import hry.util.message.MessageUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.entrust.service.ExExOrderInfoService;
import hry.exchange.entrust.service.ExExOrderService;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import tk.mybatis.mapper.util.StringUtil;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午2:04:29
 */

public class RemoteExExOrderServiceImpl implements RemoteExExOrderService {

	@Resource
	private ExOrderInfoService exOrderInfoService;

	@Resource
	private ExExOrderInfoService exExOrderInfoService;
	@Resource
	private ExOrderService exOrderService;
	@Resource
	private ExExOrderService exExOrderService;
	@Override
	public List<ExOrderInfo> findByOrderNum(String orderNum) {
		
		QueryFilter filter = new QueryFilter(ExOrderInfo.class);
		filter.addFilter("orderNum=", orderNum);
		List<ExOrderInfo> list = exOrderInfoService.find(filter);
		return list;
	}


	@Override
	public String[] deductMoney(ExOrderInfo exOrderInfo,ExEntrust buyexEntrust,ExEntrust sellentrust,ExOrderInfo exOrderInfosell,ExOrder exOrder) {
		// TODO Auto-generated method stub
		return exExOrderInfoService.deductMoney(exOrderInfo, buyexEntrust, sellentrust,exOrderInfosell,exOrder);
	}


	/**
	 * 获得用户的订单量
	 * @param buyId
	 * @return
	 */
	@Override
	public Long ListCount(Long buyId) {
		// TODO Auto-generated method stub
		QueryFilter filter = new QueryFilter(ExOrderInfo.class);
		filter.addFilter("buyCustomId=", buyId);
		return exExOrderInfoService.count(filter);
	}

}
