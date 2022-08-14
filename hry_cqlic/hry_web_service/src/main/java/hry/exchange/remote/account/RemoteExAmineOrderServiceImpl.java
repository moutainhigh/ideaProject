/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午11:53:57
 */
package hry.exchange.remote.account;

import hry.change.remote.account.RemoteExAmineOrderService;
import hry.change.remote.account.RemoteExApiApplyService;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.exchange.account.model.ExApiApply;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExAmineOrderService;
import hry.exchange.account.service.ExApiApplyService;
import hry.exchange.transaction.model.ExDmTransaction;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月12日 上午11:53:57 
 */
public class RemoteExAmineOrderServiceImpl implements RemoteExAmineOrderService{
	
	@Resource(name = "examineOrderService")
	public ExAmineOrderService exAmineOrderService;

	/**
	 * 充币币账户修改
	 */
	@Override
	public String chargeAccount(ExDmTransaction tsx) {
		return exAmineOrderService.rechargeCoin(tsx);
	}

	
}
