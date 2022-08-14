package hry.mall.retailstore.remote;

import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;

import java.util.Map;

public interface RemoteCouponService {
	
	public ApiJsonResult receiveCoupon(Map<String, String> map);
   
	public ApiJsonResult validCoupon(Map<String, String> map);


	// 商品优惠券领取集合
	public ApiJsonResult goodsCouponList(Map<String, String> map);

	public ApiJsonResult couponList(Map<String, String> map);
	
	public JsonResult isCanUse(Map<String, String> map);

	// 优惠券集合
	public ApiJsonResult findCouponList(Map<String, String> map);

	// 发放优惠券
	public ApiJsonResult giveOutCouponList(String memberId, String couponType);

	// 优惠券集合
	public ApiJsonResult findMyCouponList(Map<String, String> map);
}
