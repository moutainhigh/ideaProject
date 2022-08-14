package hry.web.remote;

import java.util.Map;

import javax.annotation.Resource;

import hry.manage.remote.RemoteTokenCollectService;
import hry.manage.remote.model.ExDmTransfColdDetailManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.web.ExDmTransfColdDetail.service.ExDmTransfColdDetailService;

public class RemoteTokenCollectServiceImpl implements RemoteTokenCollectService {

	@Resource
	public ExDmTransfColdDetailService transfColdDetailService;
	
	@Override
	public FrontPage listTokenAssets (Map<String, String> params) {
		return transfColdDetailService.listTokenAssets(params);
	} 
	
	@Override
	public RemoteResult rechargeTxFee2TokenAddress(Map<String, String> params) {
		return transfColdDetailService.rechargeTxFee2TokenAddress(params);
	}
	
	@Override
	public RemoteResult tokenCollect(Map<String, String> params) {
		return transfColdDetailService.tokenCollect(params);
	}
	
	@Override
	public RemoteResult toColdAccount(Map<String, String> params) {
		return transfColdDetailService.toColdAccount(params);
	}

	@Override
	public FrontPage transfColdRecordList(Map<String, String> params) {
		return transfColdDetailService.transfColdRecordList(params);
	}

	@Override
	public ExDmTransfColdDetailManage transfColdDetail(Long id) {
		return transfColdDetailService.transfColdDetail(id);
	}
	
	@Override
	public FrontPage listWalletBalance(Map<String, String> params) {
		return transfColdDetailService.listWalletBalance(params);
	}
}
