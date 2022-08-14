package hry.mall.retailstore.remote;

import hry.bean.ApiJsonResult;

import java.math.BigDecimal;
import java.util.Map;

public interface RemoteGroupService {
	public ApiJsonResult  findGroup(Map<String, String> map);
	
	public ApiJsonResult  toJoinGroup(Map<String, String> map);
	
	public  BigDecimal  calculateTransfee(Map<String, String> map);
	
	/**
	 * 提交团购信息
	 * @param map
	 * @return
	 */
	public ApiJsonResult  submitGroup(Map<String, String> map);
	/**
	 * 团购数币支付确认
	 * @param map
	 * @return
	 */
	public ApiJsonResult payCoinConfirmGroup(Map<String, String> map);
	
	/**
	 * 团购人民币支付确认
	 * @param map
	 * @return
	 */
	public ApiJsonResult payRmbConfirmGroup(Map<String, String> map);
	
	public ApiJsonResult groupDetail(Map<String, String> map);
	
}
