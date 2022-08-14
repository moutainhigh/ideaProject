package hry.mall.integral.remote;

import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.mall.integral.remote.model.CustomerIntegralVo;
import java.math.BigDecimal;
import java.util.Map;

public interface RemoteIntegralConfigService {
	//查询积分代码
	public String findIntegralCode();

	//保存用户积分信息
	//params : 用户积分表
	public void saveCustomerIntegral(Map<String, Object> params);

	public BigDecimal getCustomerIntegral(long memberId);
	
	public CustomerIntegralVo findCustomerIntegral(Map<String, String> map);

	/**
	 * 每日签到---用户签到记录
	 * */
	public JsonResult findSignInList (Map<String, String> map);

	/**
	 * 每日签到---用户签到
	 * */
	public JsonResult userHandleSign (Map<String, String> map);


	/**
	 *  根据用户id 和级数 查询该用户的下级级数为series的推荐人数
	 * @param customerId  用户id
	 * @param series	下级级数
	 * @return
	 */
	ApiJsonResult getUserRecommendedNumber(Long customerId, Integer series);


	public void saveCustomerIntegral2(Map<String, Object> params);




}
