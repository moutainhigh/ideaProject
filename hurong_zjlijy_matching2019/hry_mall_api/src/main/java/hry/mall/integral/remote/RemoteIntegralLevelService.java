package hry.mall.integral.remote;

import hry.bean.JsonResult;

import java.math.BigDecimal;
import java.util.Map;

public interface RemoteIntegralLevelService {
	public JsonResult levelList();
	/**
	 * 我的团队
	 * @param memberId
	 * @return
	 */
	public JsonResult findByCustomerId(long memberId);

	/**
	 * 购买会员
	 * @param memberId  用户ID
	 * @param account    购买金额
	 * @param accountPwd 支付密码
	 * @return
	 */
	JsonResult buyLevel(Long memberId, BigDecimal account, String accountPwd);

	/**
	 * 查看用户等级
	 * @param memberId
	 * @return
	 */
	JsonResult seeUserLevel(long memberId);

	/**
	 * 通过金额获取对应的会员等级
	 * @param account 购买金额
	 * @return
	 */
	JsonResult getLevelByAccount(BigDecimal account);


	JsonResult addLevelRecord(Map<String, Object> map);

}
