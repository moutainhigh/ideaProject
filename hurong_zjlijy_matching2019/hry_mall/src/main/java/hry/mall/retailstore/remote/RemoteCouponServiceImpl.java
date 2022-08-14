package hry.mall.retailstore.remote;

import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.mall.order.model.Order;
import hry.mall.order.service.OrderService;
import hry.mall.retailstore.model.Coupon;
import hry.mall.retailstore.model.CouponDetail;
import hry.mall.retailstore.model.vo.CouponVo;
import hry.mall.retailstore.service.CouponDetailService;
import hry.mall.retailstore.service.CouponService;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.util.*;

public class RemoteCouponServiceImpl implements RemoteCouponService {
	
	@Resource
	public CouponService couponService;
	@Resource
	public CouponDetailService couponDetailService;
	@Resource
	public OrderService orderService;

	/**
	 * 主动领取优惠券
	 */
	@Override
	public ApiJsonResult receiveCoupon(Map<String, String> map) {
		// TODO Auto-generated method stub
		ApiJsonResult  jsonResult= this.validCoupon(map);
		if(!jsonResult.getSuccess()){
			return jsonResult;
		}
		map.put("receiveType", "1");
		//3、保存领取明细及账户
		CouponDetail detail= couponDetailService.saveDetail(map);
		return jsonResult.setSuccess(true).setMsg("caozuochengong");
	}
	@Override
	public ApiJsonResult validCoupon(Map<String, String> map) {
		// TODO Auto-generated method stub
		ApiJsonResult  jsonResult=new ApiJsonResult();
		String couponId=map.get("couponId");//优惠券id
		String memberId=map.get("memberId");//用户id
		Coupon coupon=couponService.get(Long.valueOf(couponId));
		if(null== coupon ||  "".equals(coupon)){
			jsonResult.setSuccess(false).setMsg("该优惠券不存在");
			return jsonResult;
		}
		//1、验证该优惠券是否有剩余
		if((coupon.getReceiveCount()+1)>=coupon.getTotalCount()){
			jsonResult.setSuccess(false).setMsg("该优惠券已领完，试试别的吧");
			return jsonResult;
		}
	   //2、判断是否超过限领张数
		QueryFilter filter=new QueryFilter(CouponDetail.class);
		filter.addFilter("memberId=", memberId);
		filter.addFilter("couponId=", couponId);
		filter.addFilter("status=", 0);
		List<CouponDetail> list=couponDetailService.find(filter);
		int count=coupon.getLimitCount();
		int rcount=0;
		if(null!=list && list.size()>0){
			rcount=list.size();
		}
		if(rcount>=count){
			jsonResult.setSuccess(false).setMsg("您今天领取张数已达上线，明天再来吧");
			return jsonResult;
			
		}
		return jsonResult.setSuccess(true);
	}

	@Override
	public ApiJsonResult couponList(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<CouponDetail> list=new ArrayList<CouponDetail>();
		String memberId=map.get("memberId");//用户id
		QueryFilter filter=new QueryFilter(CouponDetail.class);
		filter.addFilter("memberId=", memberId);
		filter.addFilter("status=", 0);
		filter.addFilter("startDate<=", new Date());
		filter.addFilter("endDate>=", new Date());
	    list=couponDetailService.find(filter);
	    for(CouponDetail detail:list){
	    	Coupon  coupon=couponService.get(detail.getCouponId());
	    	detail.setUseMoney(coupon.getUseMoney());//限用金额
	    	if(1==coupon.getUseType()){
	    		detail.setUseName("全场通用");
	    	}else{
	    		detail.setUseName("部分商品");
	    	}
	    	
	    }
		return new ApiJsonResult().setSuccess(true).setObj(list);
	}
	@Override
	public JsonResult isCanUse(Map<String, String> map) {
		// TODO Auto-generated method stub
		return couponDetailService.isCanUse(map);
	}
	

	@Override
	public ApiJsonResult goodsCouponList(Map<String, String> map) {
		ApiJsonResult  jsonResult = new ApiJsonResult();
		String memberId = map.get("memberId");
		String goodsId =  map.get("goodsId");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("goodsId", goodsId);
		List<Coupon> couponList = couponService.couponGoodsList(paramMap);
		if (memberId != null && !"".equals(memberId)) {
			// 判断用户是否领取过优惠券
			checkCouponIsGet(couponList,memberId);
		}
		return jsonResult.setSuccess(true).setObj(couponList);
	}

	@Override
	public ApiJsonResult findCouponList(Map<String, String> map) {
		ApiJsonResult  jsonResult = new ApiJsonResult();
		String memberId = map.get("memberId");
		String couponType =  map.get("couponType");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("couponType", couponType);
		List<Coupon> couponList = couponService.findCouponList(paramMap);
		if (memberId != null && !"".equals(memberId)) {
			// 根据用户是否下过单判断用户是否为新用户
			QueryFilter filter = new QueryFilter(Order.class);
			filter.addFilter("memberId=", memberId);
			List<Order> orderList = orderService.find(filter);
			if (orderList != null && orderList.size() > 0) {
				// 因用户下过单则 默认该用户领取过新人专享优惠券
				for (Coupon coupon : couponList) {
					coupon.setIsGet(1);// 已领取
				}
			} else {
				// 判断用户是否领取过优惠券
				checkCouponIsGet(couponList,memberId);
			}
		}
		return jsonResult.setSuccess(true).setObj(couponList);
	}

	/**
	 * 判断用户是否领取过优惠券
	 * */
	private void checkCouponIsGet(List<Coupon> couponList, String memberId) {
		for (Coupon coupon : couponList) {
			QueryFilter filter = new QueryFilter(CouponDetail.class);
			filter.addFilter("memberId=",memberId);
			filter.addFilter("couponId=",coupon.getId());
			filter.addFilter("status=",0); // 未使用
			filter.addFilter("startDate <=",new Date()); // 有效期范围内
			filter.addFilter("endDate >= ",new Date()); // 有效期范围内
			List<CouponDetail> couponDetailList = couponDetailService.find(filter);
			if (couponDetailList != null && couponDetailList.size() > 0) {
				coupon.setIsGet(1); // 已领取
			} else {
				coupon.setIsGet(0); // 未领取
			}
		}
	}

	/**
	 * 优惠券发放
	 * @param memberId 用户id
	 * @param couponType 优惠券类型 1、手动领取 2、注册赠券 3、拉新赠券 4、邀请开店 5、新人专享'
	 * **/
	@Override
	public ApiJsonResult giveOutCouponList(String memberId,String couponType) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("couponType", couponType);
		List<Coupon> couponList = couponService.findCouponList(paramMap);
		for (Coupon coupon : couponList) {
			// 判断当前优惠券是否可领
			Map<String, String> map = new HashMap<>();
			map.put("memberId", memberId);
			map.put("couponId", coupon.getId().toString());
			ApiJsonResult jsonResult = validCoupon(map);
			if (jsonResult.getSuccess()) {
				map.put("receiveType", "2"); //1、手动领取 2、主动派放
				// 保存领取明细及账户
				CouponDetail detail = couponDetailService.saveDetail(map);
			}
		}
		return new ApiJsonResult().setSuccess(true);
	}

	@Override
	public ApiJsonResult findMyCouponList(Map<String, String> map) {
		List<CouponVo> CouponVoList = couponDetailService.findMyCouponList(map);
		return new ApiJsonResult().setSuccess(true).setObj(CouponVoList);
	}
}
