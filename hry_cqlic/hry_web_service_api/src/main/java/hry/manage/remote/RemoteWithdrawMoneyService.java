package hry.manage.remote;

import java.util.List;
import java.util.Map;

import hry.manage.remote.model.ExProductManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;

/**
 * 提币模块远程调用接口类
 * @author lenovo
 *
 */
public interface RemoteWithdrawMoneyService {

	/**
	 * 获取币种类型数据
	 * @return
	 */
	List<ExProductManage> getSelectProduct();
	
	/**
	 * 列表查询
	 * @param params
	 * @return
	 */
	FrontPage findFrontPage(Map<String, String> params);

	/**
	 * 审核通过
	 * @param params
	 * @return
	 */
	RemoteResult checkPass(Map<String, String> params);

	/**
	 * 审核驳回
	 * @param params
	 * @return
	 */
	RemoteResult checkReject(Map<String, String> params);

}
