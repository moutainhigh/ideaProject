package hry.cm4.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.cm4.remote.model.CmCustomerMinerRemote;

import java.util.List;
import java.util.Map;

public interface RemoteCmCustomerMinerService {
	
	/**
	 * 查询用户矿机列表
	 * @return
	 */
	public FrontPage findCustomerMinerlist(Map<String, String> params);

	/**
	 * 查询用户矿机产出记录
	 * */
	public List<CmCustomerMinerRemote> findMinerOutputList(Map<String, String> params);

	/**
	 * 领取矿机产生收益
	 * */
	public JsonResult giveMinerOutput(Map<String, String> params);
}
