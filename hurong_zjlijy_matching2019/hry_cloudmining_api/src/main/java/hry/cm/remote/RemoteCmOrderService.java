package hry.cm.remote;

import java.util.Map;

import hry.bean.FrontPage;
import hry.bean.JsonResult;

public interface RemoteCmOrderService {
	
	/**
	 * 购买矿机-下单
	 * @param params
	 * @return
	 */
	public JsonResult getorder(Map<String, String> params);
	
	/**
	 * 查询用户订单列表
	 * @param params
	 * @return
	 */
	public FrontPage findOrderList(Map<String, String> params);

}
