package hry.web.remote;

import hry.exchange.account.model.ExApiApply;
import hry.exchange.account.service.ExApiApplyService;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.manage.remote.RemoteApiService;
import hry.manage.remote.model.ApiExApiApply;
import hry.manage.remote.model.Entrust;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.service.ExEntrustService;
import hry.util.QueryFilter;
import hry.util.serialize.ObjectUtil;

import javax.annotation.Resource;


public class RemoteApiServiceImpl implements RemoteApiService{
	@Resource
	private ExApiApplyService exApiApplyService;
	
	
	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource
	private ExEntrustService exEntrustService;
	@Override
	public ApiExApiApply getExApiApply(String accesskey ,String ip) {
		QueryFilter qf = new QueryFilter(ExApiApply.class);
		qf.addFilter("customerId=", accesskey);		
		qf.addFilter("ipAddress=",ip);
		ExApiApply exApiApply = exApiApplyService.get(qf);
		ApiExApiApply bean2bean = ObjectUtil.bean2bean(exApiApply, ApiExApiApply.class);
		return bean2bean;
	}

	@Override
	public ApiExApiApply getExApiApply(String accesskey) {
		QueryFilter qf = new QueryFilter(ExApiApply.class);
		qf.addFilter("customerId=", accesskey);		
		ExApiApply exApiApply = exApiApplyService.get(qf);
		ApiExApiApply bean2bean = ObjectUtil.bean2bean(exApiApply, ApiExApiApply.class);
		return bean2bean;
	}

	

	@Override
	public Entrust selectExEntrust(String entrustNum) {
		QueryFilter qf = new QueryFilter(ExEntrust.class);
		qf.addFilter("entrustNum=", entrustNum);
		ExEntrust exEntrust = exEntrustService.get(qf);
		Entrust entrust = ObjectUtil.bean2bean(exEntrust, Entrust.class);
		return entrust;
	}

	
	



}
