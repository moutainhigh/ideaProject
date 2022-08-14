/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:40:03 
 */
package hry.mall.retailstore.service.impl;

import hry.bean.JsonResult;
import hry.common.util.DateUtils;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.dao.CouponDetailDao;
import hry.mall.retailstore.model.Coupon;
import hry.mall.retailstore.model.CouponDetail;
import hry.mall.retailstore.model.CouponGoods;
import hry.mall.retailstore.model.vo.CouponVo;
import hry.mall.retailstore.service.CouponDetailService;
import hry.mall.retailstore.service.CouponGoodsService;
import hry.mall.retailstore.service.CouponService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.util.QueryFilter;
import hry.util.SpringUtil;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * <p> CouponDetailService </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:40:03  
 */
@Service("couponDetailService")
public class CouponDetailServiceImpl extends BaseServiceImpl<CouponDetail, Long> implements CouponDetailService {
	@Resource
	public CouponService couponService;
	@Resource
	public CouponGoodsService couponGoodsService;
	
	@Resource(name="couponDetailDao")
	@Override
	public void setDao(BaseDao<CouponDetail, Long> dao) {
		super.dao = dao;
	}

	@Override
	public CouponDetail saveDetail(Map<String, String> map) {
		// TODO Auto-generated method stub
		String couponId=map.get("couponId");//优惠券id
		String memberId=map.get("memberId");//用户id
		String receiveType=map.get("receiveType");
		Coupon coupon=couponService.get(Long.valueOf(couponId));
		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		User user=remoteManageService.findUserById(Long.valueOf(map.get("memberId")));
		//1、保存领取明细
		CouponDetail detail= new CouponDetail();
		detail.setCouponId(coupon.getId());
		detail.setMemberId(Long.valueOf(memberId));
		detail.setMemberName(user.getTruename());
		detail.setMemberName("");
		detail.setReceiveType(Integer.valueOf(receiveType));
		detail.setReceiveTime(new Date());
		detail.setType(coupon.getType());
		detail.setFaceValue(coupon.getFaceValue());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		//按照期限
		if(1==coupon.getValidType()){
			detail.setStartDate(coupon.getStartDate());
			String sendDate=sdf.format(coupon.getEndDate());
			String ss=sendDate+" 23:59:59";
			try {
				detail.setEndDate(format.parse(ss));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       }else{
    	   String sendDate= DateUtils.getDateAddDays(coupon.getDays());
    	   detail.setStartDate(date);
    	   try {
			detail.setEndDate(format.parse(sendDate));
		   } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
       }  
		this.save(detail);
		//2、修改优惠券信息
		coupon.setReceiveCount(coupon.getReceiveCount()+1);
		couponService.update(coupon);
		return null;
	}

	@Override
	public JsonResult isCanUse(Map<String, String> map) {
		// TODO Auto-generated method stub
		JsonResult jsonResoult=new JsonResult();
		String couponDetailId=map.get("couponDetailId");
		//1、验证该优惠券是否存在
		CouponDetail detail=this.get(Long.valueOf(couponDetailId));
		if(null==detail || "".equals(detail)){
			jsonResoult.setSuccess(false).setMsg("yhqbucunzai");//所选优惠券不存在，请重新选择
			return jsonResoult;
		}
		//2、判断优惠券是否过期
		Date date=new Date();
		if(date.compareTo(detail.getEndDate())>0 ){
			jsonResoult.setSuccess(false).setMsg("yhqyishixiao");//该优惠券已失效，请重新选择
			return jsonResoult;
		}
		if( date.compareTo(detail.getStartDate())<0){
			jsonResoult.setSuccess(false).setMsg("yhqweishengxiao");//该优惠券未生效，请重新选择
			return jsonResoult;
		}
		//3、计算商品总金额，看是否满足限用金额
		String moneys=map.get("moneys");
		BigDecimal sumMoney=new BigDecimal("0");
		//商品数量拼串
		String counts=map.get("counts");
		//商品id拼串
		String goodsIds=map.get("goodsIds");
		String [] countArr =counts.split(",");
		String [] moneyArr =moneys.split(",");
		String [] goodsArr =goodsIds.split(",");
		for(int i=0;i<moneyArr.length;i++){
			sumMoney=sumMoney.add(new BigDecimal(moneyArr[i]).multiply(new BigDecimal(countArr[i])));
		}
		Coupon coupon=couponService.get(detail.getCouponId());
		if(sumMoney.compareTo(coupon.getUseMoney())<0){
			jsonResoult.setSuccess(false).setMsg("yhqweidaojine");//订单金额未达到优惠券的使用金额,请重新选择
			return jsonResoult;
		}
		//4、是否满足使用条件
		//如果不是全场通用，看订单商品是否是是购物券使用商品
		Boolean flag=false;
		if(1!=coupon.getUseType()){
			for(int i=0;i<goodsArr.length;i++){
				QueryFilter filter=new QueryFilter(CouponGoods.class);
				filter.addFilter("couponId=", coupon.getId());
				filter.addFilter("goodsId=", goodsArr[i]);
				List<CouponGoods> list=couponGoodsService.find(filter);
				if(null!=list && list.size()>0){
					flag=true;
					break;
				}
			}
		}
		else{
			flag=true;
		}
		if(!flag){
			jsonResoult.setSuccess(false).setMsg("yhqbuzhichishangpin");//该优惠券只限部分商品使用,不支持 您所选商品,请重新选择
			return jsonResoult;
		}
		return jsonResoult.setSuccess(true);
	}


	@Override
	public List<CouponVo> findMyCouponList(Map<String, String> map) {
		return ((CouponDetailDao)dao).findMyCouponList(map);
	}
}
