/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-09 17:55:31 
 */
package hry.mall.retailstore.service.impl;

import hry.common.util.DateUtils;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.goods.service.GoodsService;
import hry.mall.platform.model.Address;
import hry.mall.platform.model.Payment;
import hry.mall.platform.service.PaymentService;
import hry.mall.retailstore.model.ActivityGoods;
import hry.mall.retailstore.model.Group;
import hry.mall.retailstore.model.GroupDetail;
import hry.mall.retailstore.service.ActivityGoodsService;
import hry.mall.retailstore.service.GroupDetailService;
import hry.manage.remote.RemoteMallService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.manage.remote.model.mall.ExProductVo;
import hry.util.SpringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p> GroupDetailService </p>
 * @author:         luyue
 * @Date :          2019-05-09 17:55:31  
 */
@Service("groupDetailService")
public class GroupDetailServiceImpl extends BaseServiceImpl<GroupDetail, Long> implements GroupDetailService {
	@Resource
	public PaymentService paymentService;
	@Resource
	public GoodsService goodsService;
	@Resource
	public ActivityGoodsService activityGoodsService;
	
	@Resource(name="groupDetailDao")
	@Override
	public void setDao(BaseDao<GroupDetail, Long> dao) {
		super.dao = dao;
	}

	@Override
	public GroupDetail saveGroupDetail(Map<String, String> map, Group group, Address address) {
		// TODO Auto-generated method stub
		GroupDetail detail=new GroupDetail();
	    detail.setGroupId(group.getId());
		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		User user=remoteManageService.findUserById(Long.valueOf(map.get("memberId")));
	    detail.setMemberId(Long.valueOf(map.get("memberId")));
	    detail.setMemberName(user.getNickNameOtc());   
	    detail.setImage(user.getUserFace());
	    detail.setIsFunder(Integer.valueOf(map.get("isFunder")));
   		BigDecimal ling=new BigDecimal("0");
   		BigDecimal sumMoney=new BigDecimal(map.get("moneys")).multiply(new BigDecimal(map.get("counts")));
	    detail.setDiscount(ling);
	    detail.setAmount(sumMoney);
	    detail.setTotalPrice(sumMoney);
	    detail.setStatus(0);
	    detail.setGoodsPrice(group.getGoodsPrice());
	    detail.setActivityPrice(group.getActivityPrice());
	    detail.setCount(Integer.valueOf(map.get("counts")));
	    detail.setShippingFee(new BigDecimal(map.get("fee")));
   		String payType=map.get("payType");
   		detail.setPayType(payType);//支付方式,人民币:1,数字币：2,混合支付：3
   		BigDecimal rmbMoney=new BigDecimal(map.get("rmbMoney"));//人民币支付金额
   		detail.setRmbMoney(rmbMoney);
   		detail.setRmbFeeMoney(ling);
   		detail.setRmbFeeRate(ling);
   		//如果是人民币支付方式，则保存支付通道的id,手续费率
   		if("1".equals(payType)){
   			detail.setRmbMoney(sumMoney.add(new BigDecimal(map.get("fee"))));
   			//支付通道id
   			String paymentId=map.get("paymentId");
   			if(null!=paymentId && !"".equals(paymentId)){
   				detail.setPaymentId(paymentId);
   				Payment payment=paymentService.get(Long.valueOf(paymentId));
   				detail.setCoinCount(ling);//虚拟币数量
   				detail.setCoinFeeRate(ling);
   				detail.setCoinFeeCount(ling);
   				if(null!=payment &&null!=payment.getPayPoundage() && !"".equals(payment.getPayPoundage())){
   					BigDecimal rate=new BigDecimal(payment.getPayPoundage());
   					detail.setRmbFeeRate(rate);
   					detail.setRmbFeeMoney(sumMoney.multiply(rate).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
   				}
   			}
   		}
   		String coinCode=map.get("coinCode");
   		//币种不为空，则使用虚拟币进行支付了
   		if(null!=coinCode && !"".equals(coinCode)){
	   		 BigDecimal coinMoney=sumMoney.subtract(rmbMoney).add(new BigDecimal(map.get("fee")));
	   		 BigDecimal coinRate=goodsService.getCoinRate(coinCode);
	   		 detail.setCoinRate(coinRate.setScale(10, BigDecimal.ROUND_HALF_UP));
	   		 detail.setCoinCode(coinCode);
	   		 detail.setCoinCount(coinMoney.divide(coinRate, 10, BigDecimal.ROUND_HALF_UP));//虚拟币数量
	   		 RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");  
	   		 ExProductVo vo=remoteMallService.findByCoinCode(coinCode);
	   		 if(null!=vo && null!=vo.getBuyGoodsRate()){
	   			detail.setCoinFeeRate(vo.getBuyGoodsRate());
	   			detail.setCoinFeeCount(detail.getCoinCount().multiply(vo.getBuyGoodsRate()).divide(new BigDecimal("100")).setScale(10, BigDecimal.ROUND_HALF_UP));
	   		 }
   	
   		}
   		//获得系统的配置的订单失效时长
		Integer validTime=new Integer("0");
		ActivityGoods activityGoods=activityGoodsService.get(group.getActivityGoodsId());
		if(null!=activityGoods && !"".equals(activityGoods)){
			validTime=activityGoods.getLimitPayMinute();
			
		}
   		Date payEndTime= DateUtils.addDateMinut(new Date(), validTime);
   		detail.setPayEndTime(payEndTime);
   		//保存收货地址信息
   		detail.setProvinceId(address.getProvinceId());
   		detail.setCityId(address.getCityId());
   		detail.setCountyId(address.getCountyId());
   		detail.setStreet(address.getStreet());
   		detail.setDetailAddress(address.getDetailAddress());
   		detail.setCellphone(address.getCellphone());
   		detail.setReceiveName(address.getReceiveName());
   		detail.setZipCode(address.getZipCode());
   		this.save(detail);
		return detail;
	}
}
