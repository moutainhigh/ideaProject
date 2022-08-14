/**

 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.exchange.entrust.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.rpc.RpcContext;

import hry.account.fund.model.AppAccount;
import hry.account.remote.RemoteAppAccountService;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.sys.ContextUtil;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.product.model.ProductCommon;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExExEntrustService extends BaseService<ExEntrust, Long> {
	public void autoAddExEntrust();
}
