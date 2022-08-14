package hry.manage.remote;

import java.util.Map;

import hry.manage.remote.model.ExDmTransfColdDetailManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;

/**
 * 代币管理服务业务处理
 * @author liuchenghui
 *
 */
public interface RemoteTokenCollectService {

	FrontPage listTokenAssets(Map<String, String> params);

	RemoteResult rechargeTxFee2TokenAddress(Map<String, String> params);

	RemoteResult tokenCollect(Map<String, String> params);

	RemoteResult toColdAccount(Map<String, String> params);

	FrontPage transfColdRecordList(Map<String, String> params);

	ExDmTransfColdDetailManage transfColdDetail(Long id);

	FrontPage listWalletBalance(Map<String, String> params);

}
