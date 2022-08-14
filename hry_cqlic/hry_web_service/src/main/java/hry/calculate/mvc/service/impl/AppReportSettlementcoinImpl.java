/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月5日 上午10:42:10
 */
package hry.calculate.mvc.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hry.account.fund.model.AppTransaction;
import hry.account.remote.RemoteAppTransactionService;
import hry.calculate.mvc.service.AppReportSettlementService;
import hry.calculate.mvc.service.AppReportSettlementcoinService;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;
import hry.change.remote.exEntrust.RemoteExEntrustService;
import hry.change.remote.exEntrust.RemoteExOrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.date.DateUtil;
import hry.util.sys.ContextUtil;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.product.model.ExProduct;
import hry.exchange.remote.account.RemoteExProductService;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.DifCustomer;
import hry.trade.entrust.model.ExOrderInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.util.StringUtil;



/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月5日 上午10:42:10 
 */
@Service("appReportSettlementcoinService")
public class AppReportSettlementcoinImpl extends BaseServiceImpl<AppReportSettlementcoin, Long> implements
		AppReportSettlementcoinService  {

	@Resource(name="appReportSettlementcoinDao")
	@Override
	public void setDao(BaseDao<AppReportSettlementcoin, Long> dao) {
		super.dao = dao;
	}
	
	@Override
	public List<ExProduct> getSelectProduct(){
		RemoteExProductService remoteExProductService = (RemoteExProductService)ContextUtil.getBean("remoteExProductService");
		List<ExProduct> list = remoteExProductService.getSelectProduct();
		return list;
	}
	
	
	
	
}	











