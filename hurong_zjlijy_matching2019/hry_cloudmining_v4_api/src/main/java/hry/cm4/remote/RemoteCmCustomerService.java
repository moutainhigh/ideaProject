package hry.cm4.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;

import java.util.Map;

public interface RemoteCmCustomerService {
	
	/**
	 * 获取用户等级信息
	 * @param params
	 * @return
	 */
	public JsonResult getCustomerGrade(Map<String, String> params);
	
	/**
	 * 查询团队列表
	 * @return
	 */
	public FrontPage findCustomerTeamlist(Map<String, String> params);
	
	/**
	 * 获取用户收益统计信息
	 * @param params
	 * @return
	 */
	public JsonResult getProfitStatistics(Map<String, String> params);
	/**
	 * 查询平台币Code
	 * @param params
	 * @return
	 */
	public JsonResult getPlatCoin();
	/**
	 * 我的矿机页面
	 * @param params
	 * @return
	 */
	public JsonResult getMyMiner(Map<String, String> params);
	/**
	 * 我的矿场页面
	 * @param params
	 * @return
	 */
	public JsonResult getMyMinercamps(Map<String, String> params);

}
