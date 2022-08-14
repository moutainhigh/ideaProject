/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:18:14
 */
package hry.account.remote;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.rpc.RpcContext;

import hry.account.fund.model.AppBankCard;
import hry.account.fund.service.AppBankCardService;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.sys.ContextUtil;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月1日 上午11:18:14 
 */
public class RemoteAppBankCardServiceImpl implements  RemoteAppBankCardService {
	
	
	@Resource
	private AppBankCardService appBankCardService;

	@Override
	public void save(AppBankCard appBankCard) {
		appBankCardService.save(appBankCard);
	}

	@Override
	public List<AppBankCard> findByCustomerId(Long id) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter queryFilter = new QueryFilter(AppBankCard.class);
		queryFilter.setSaasId(saasId);
		queryFilter.addFilter("customerId=", id);
		queryFilter.addFilter("currencyType=", ContextUtil.getRemoteCurrencyType());
		queryFilter.addFilter("website=", ContextUtil.getRemoteWebsite());
		List<AppBankCard> find = appBankCardService.find(queryFilter);
		return find;
	}

	@Override
	public boolean delete(Long id) {
		try {
			appBankCardService.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public AppBankCard get(Long id) {
		return appBankCardService.get(id);
	}

	@Override
	public AppBankCard get(RemoteQueryFilter remoteQueryFilter) {
		return appBankCardService.get(remoteQueryFilter.getQueryFilter());
	}

	@Override
	public void update(AppBankCard appBankCard) {
		appBankCardService.update(appBankCard);
	}
}
