package hry.mall.order.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import java.math.BigDecimal;
import java.util.Map;

public interface RemoteOrderService {

	/**
	 * 商城订单
	 * @param map
	 * @return
	 */
	public FrontPage findByMemberIdAndStatus(Map<String, String> map);
	/**
	 * 待付款详情
	 * @param map
	 * @return
	 */
	public JsonResult findNoPayOrder(Map<String, String> map);
	/**
	 * 取消订单
	 * @param map
	 * @return
	 */
	public JsonResult cancelOrder(Map<String, String> map);
	/**
	 * 待收货详情
	 * @param map
	 * @return
	 */
	public JsonResult waitGoods(Map<String, String> map);
	/**
	 * 删除订单
	 * @param map
	 * @return
	 */
	public JsonResult deleteOrder(Map<String, String> map);
	/**
	 * 确认收货
	 * @param map
	 * @return
	 */
	public JsonResult updateFinishOrder(Map<String, String> map);
	/**
	 * 申请售后
	 * @param map
	 * @return
	 */
	public FrontPage afterSales(Map<String, String> map);
	/**
	 * 申请售后
	 * @param map
	 * @return
	 */
	public FrontPage afterSalesRecord(Map<String, String> map);
	/**
	 * 申请退货提交
	 * @param map
	 * @return
	 */
	public JsonResult addReturn(Map<String, String> map);
	/**
	 * 退货查看记录
	 * @param map
	 * @return
	 */
	public JsonResult checkRecord(Map<String, String> map);
	/**
	 * 评价商品,待评价
	 * @param map
	 * @return
	 */
	public FrontPage evaluateGoods(Map<String, String> map);
	/**
	 * 评价商品，已评价
	 * @param map
	 * @return
	 */
	public FrontPage alreadyEvaluateGoods(Map<String, String> map);
	/**
	 * 发表评论
	 * @param map
	 * @return
	 */
	public JsonResult releaseEvaluate(Map<String, String> map);
	/**
	 * 订单取消原因
	 *
	 * @return
	 */
	JsonResult orderCancel();
	/**
	 * 订单退货原因
	 *
	 * @return
	 */
	JsonResult returnCause();
	/**
	 * 购物车点击去结算
	 * @param map
	 * @return
	 */
	public JsonResult toBalance(Map<String, String> map);
	/**
	 * 计算一单运费
	 * @param map
	 * @return
	 */
	public  BigDecimal  calculateTransfee(Map<String, String> map);
	/**
	 * 提交订单
	 * @param map
	 * @return
	 */
	public JsonResult submitOrder(Map<String, String> map);
	
	/**
	 * 获得订单的支付详情等信息
	 * @param map
	 * @return
	 */
	public JsonResult findOrder(Map<String, String> map);
	/**
	 * 支付确认方法
	 * @param map
	 * @return
	 */
	public JsonResult payConfirm(Map<String, String> map);
	/**
	 * 验证用户账户是否足够支付
	 * @param map
	 * @return
	 */
	public  JsonResult  validateExaccount(Map<String, String> map);
	
	/**
	 * 积分商品点击去抢购
	 * @param map
	 * @return
	 */
	public JsonResult toIntegralBalance(Map<String, String> map);
	/**
	 * 积分商品提交订单
	 * @param map
	 * @return
	 */
	public JsonResult submitIntegralOrder(Map<String, String> map);
	
	/**
	 * 支付确认方法
	 * @param map
	 * @return
	 */
	public JsonResult payIntegralConfirm(Map<String, String> map);
	
	/**
	 * 人民币支付确认方法
	 * @param map
	 * @return
	 */
	public JsonResult payRmbConfirm(Map<String, String> map);
	
	/**
	 * 数字币支付确认方法
	 * @param map
	 * @return
	 */
	public JsonResult payCoinConfirm(Map<String, String> map);

	/**
	 * 物流信息
	 * @param map
	 * @return
	 */
	public JsonResult trackingBtn(Map<String, String> map);
	
	public FrontPage findUnion(Map<String, String> map);
	
	 /**
	  * 计算运费
	  * @param provinceCode
	  * @param sumCount
	  * @param transportId
	  * @return
	  */
	 public  BigDecimal  calculateFeeByConfig(String provinceCode, Integer sumCount, Long transportId);


	/**
	 * 购物车点击去结算------李金沅定制项目单独开发------by luyue
	 * @param map
	 * @return
	 */
	public JsonResult toBalance1(Map<String, String> map);

	/**
	 * 数字币支付确认方法-----李金沅项目定制单独开发-----by luyue
	 * @param map
	 * @return
	 */
	public JsonResult payCoinConfirm1(Map<String, String> map);

	/**
	 * 获得订单的支付详情等信息------李金沅项目定制
	 * @param map
	 * @return
	 */
	public JsonResult findOrder1(Map<String, String> map);

	/**
	 * 查询商户结算订单列表
	 * @param map
	 * @return
	 */
	public FrontPage findBalance(Map<String, String> map);


	
}
