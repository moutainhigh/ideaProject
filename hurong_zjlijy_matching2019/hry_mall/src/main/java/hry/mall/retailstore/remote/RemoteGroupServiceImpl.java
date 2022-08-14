package hry.mall.retailstore.remote;

import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.common.util.DateUtils;
import hry.core.shiro.PasswordHelper;
import hry.mall.goods.model.Goods;
import hry.mall.goods.model.GoodsSpec;
import hry.mall.goods.service.GoodsService;
import hry.mall.goods.service.GoodsSpecService;
import hry.mall.order.remote.RemoteOrderService;
import hry.mall.platform.model.Address;
import hry.mall.platform.model.MallConfig;
import hry.mall.platform.model.Payment;
import hry.mall.platform.model.ThirdpayRecord;
import hry.mall.platform.model.Transport;
import hry.mall.platform.service.*;
import hry.mall.retailstore.model.Activity;
import hry.mall.retailstore.model.ActivityGoods;
import hry.mall.retailstore.model.Group;
import hry.mall.retailstore.model.GroupDetail;
import hry.mall.retailstore.service.ActivityGoodsService;
import hry.mall.retailstore.service.ActivityService;
import hry.mall.retailstore.service.GroupDetailService;
import hry.mall.retailstore.service.GroupService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.UserRedisUtils;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteGroupServiceImpl implements RemoteGroupService {
   @Resource
   public GroupService groupService;
   @Resource
   public ActivityService activityService;
   @Resource
   public ActivityGoodsService activityGoodsService;
   @Resource
   public AddressService addressService;
   @Resource
   public MallConfigService mallConfigService;
   @Resource
   public GoodsService goodsService;
   @Resource
   public RemoteOrderService remoteOrderService;
   @Resource
   public TransportService transportService;
   @Resource
   public PaymentService paymentService;
   @Resource
   public GroupDetailService groupDetailService;
   @Resource
   public GoodsSpecService goodsSpecService;
   @Resource
   private MessageProducer messageProducer;
   @Resource
   private ThirdpayRecordService thirdpayRecordService;
   
	@Override
	public ApiJsonResult findGroup(Map<String, String> map) {
		// TODO Auto-generated method stub
		List<Group>list=groupService.findGroup(map);
		return new  ApiJsonResult().setSuccess(true).setObj(list);
	}
	
	@Override
	public ApiJsonResult toJoinGroup(Map<String, String> map) {
		// TODO Auto-generated method stub
		ApiJsonResult  jsonResult=new ApiJsonResult();
		Map<String, Object> objMap=new HashMap<String,Object>();
		String memberId=map.get("memberId");
		//1、验证该团信息
		String groupId=map.get("groupId");
		if(null!=groupId && !"".equals(groupId)){
		Group group=groupService.get(Long.valueOf(groupId));
		jsonResult=this.validateGroup(group);
			if(!jsonResult.getSuccess()){
				  return jsonResult;
			}
		//判断是否已经参团
		QueryFilter filter1=new QueryFilter(GroupDetail.class);
		filter1.addFilter("memberId=", memberId);
		filter1.addFilter("groupId=", group.getId());
		List<GroupDetail> glist=groupDetailService.find(filter1);
		if(null!=glist && glist.size()>0 ){
			jsonResult.setSuccess(false).setMsg("您已参加该团，换个团试试吧");
			return jsonResult;
		 }
		}
		String activityGoodsId=map.get("activityGoodsId");
		//2、验证该团购活动以及数量、金额信息
		ActivityGoods activityGoods=activityGoodsService.get(Long.valueOf(activityGoodsId));
		String moneys=map.get("moneys");
		String counts=map.get("counts");
		jsonResult=this.validateActivityGoods(activityGoods, moneys, counts);
			if(!jsonResult.getSuccess()){
				  return jsonResult;
			}
	 	 //3、查询默认收货地址
		Address address=null;
		List<Address>  list=addressService.findByCustomerId(Long.valueOf(memberId),Short.valueOf("1"));
		if(null!=list &&list.size()>0){
			address=list.get(0);
		}else{
			List<Address>  list1=addressService.findByCustomerId(Long.valueOf(memberId),Short.valueOf("0"));
			if(null!=list1 &&list1.size()>0){
				address=list1.get(0);
			}
		}
		//4、计算总金额
		BigDecimal sumMoney=new BigDecimal(moneys).multiply(new BigDecimal(counts));
		objMap.put("sumMoney", sumMoney);
	  //5、根据收货地址、商品、下单数量计算运费
	  if(null!=address && !"".equals(address)){
		  Map<String, String> cmap=new HashMap<String, String>();
		  cmap.put("activityGoodsId",activityGoods.getId().toString());
		  cmap.put("counts",counts);
		  cmap.put("provinceCode",address.getProvinceId());
		  cmap.put("moneys",moneys);
		  cmap.put("sumMoney",sumMoney.toString());
		  BigDecimal fee=this.calculateTransfee(cmap);
		  objMap.put("address", address);
		  objMap.put("fee", fee);
		}else{
			objMap.put("address", null);
			objMap.put("fee", new BigDecimal("0"));
		}
	  //6、查询人民币的支付通道
			QueryFilter filter=new QueryFilter(Payment.class);
			filter.addFilter("paymentState=", Integer.valueOf("1"));
			List<Payment> plist=paymentService.find(filter);
			if(null!=plist && plist.size()>0){
				objMap.put("paymentMay", plist) ; 
			}else{
				objMap.put("paymentMay", null) ; 
			}
			jsonResult.setSuccess(true).setObj(objMap).setMsg("caozuochenggong");
		
		return jsonResult;
	}

	@Override
	public BigDecimal calculateTransfee(Map<String, String> map) {
		// TODO Auto-generated method stub
		String activityGoodsId=map.get("activityGoodsId");
		BigDecimal fee=new BigDecimal("0");
		ActivityGoods  activityGoods=activityGoodsService.get(Long.valueOf(activityGoodsId));
		//1、如果平台包邮，这直接返回0
		if(2==activityGoods.getTransportType()){
		  return  fee;	
		}else{
			//商品数量
			String counts=map.get("counts");
			//收货地址所在省
		    String provinceCode=map.get("provinceCode");
		   //商品金额
		    String moneys=map.get("moneys");
			//2、获得系统的配置的包邮金额
			BigDecimal baoMoney=new BigDecimal("0");//平台包邮金额
			List<MallConfig> list=mallConfigService.findAll();
			if(null!=list && list.size()>0){
				MallConfig config=	list.get(0);
				baoMoney= new BigDecimal(config.getPackageMail()); 
			}
			//3、计算商品总金额和包邮金额比较，如果大于则运费为0
			BigDecimal sumMoney=new BigDecimal(map.get("sumMoney"));
			if(sumMoney.compareTo(baoMoney)>0){
				return fee;
			}else{
			//4、判断是按商品配置的运费模板还是活动商品单选的运费模板
				Transport transport=null;
				//跟随商品
				if(1==activityGoods.getTransportType()){
					Goods goods=goodsService.get(activityGoods.getGoodsId());
					transport=transportService.get(goods.getTransportId());
				}
				//活动单选
				if(3==activityGoods.getTransportType()){
				  transport=transportService.get(activityGoods.getTransportId());
				}
				fee=remoteOrderService.calculateFeeByConfig(provinceCode,Integer.valueOf(counts),transport.getId());
			}
		    return fee;
		}
	}
	public ApiJsonResult validateGroup(Group group){
		ApiJsonResult  jsonResult=new ApiJsonResult();
		if(null==group || "".equals(group)){
			jsonResult.setSuccess(false).setMsg("该团不存在，请重新选择");
			return jsonResult;
		}
		if(group.getStatus()!=0){
			jsonResult.setSuccess(false).setMsg("该团已结束，请重新选择");
			return jsonResult;
		}
		//2、判断该团是否已到时
		Date endTime= DateUtils.addDateHour(group.getCreated(),group.getLimitHour());
		if(endTime.compareTo(new Date())<0){
			jsonResult.setSuccess(false).setMsg("该团已结束，请重新选择");
			return jsonResult;
		}
		return jsonResult.setSuccess(true);
	}
	
	public ApiJsonResult validateActivityGoods(ActivityGoods activityGoods,String moneys,String counts){
		//1、查询该团购是否已结束
		ApiJsonResult  jsonResult=new ApiJsonResult();
		if(null==activityGoods || "".equals(activityGoods)){
			jsonResult.setSuccess(false).setMsg("该活动商品不存在，请重新选择");
			return jsonResult;
		}
		Activity activity=activityService.get(activityGoods.getActivityId());
		if(null==activity || "".equals(activity)){
			jsonResult.setSuccess(false).setMsg("该活动不存在，请重新选择");
			return jsonResult;
		}
		if(activity.getEndTime().compareTo(new Date())<0){
			jsonResult.setSuccess(false).setMsg("该团购已结束，请重新选择");
			return jsonResult;
		}
		//2、判断库存是否充足
		if(activityGoods.getActivityStorage().compareTo(Integer.valueOf(counts))<0){
			jsonResult.setSuccess(false).setMsg("该团购商品库存不够，请重新选择");
			return jsonResult;
		}
		//3、判断该商品是否库存充足
		GoodsSpec goodspec=goodsSpecService.get(activityGoods.getGoodsSpecId());
		if(null!=goodspec &&!"".equals(goodspec)){
			if(goodspec.getSpecGoodsStorage()<Integer.valueOf(counts)){
				jsonResult.setSuccess(false).setMsg("该团购商品库存不够，请重新选择");
				return jsonResult;
			}
		}
		//4、判断是否超过限购数量
		if(activityGoods.getLimitCount()<Integer.valueOf(counts)){
			jsonResult.setSuccess(false).setMsg("购买数量超过限购数量，请重新选择");
			return jsonResult;
		}
		//5、判断价格是否一致
		if(activityGoods.getActivityPrice().compareTo(new BigDecimal(moneys))!=0){
			jsonResult.setSuccess(false).setMsg("该团购商品价格变动，请重新选择");
			return jsonResult;
		}
		return jsonResult.setSuccess(true);
	}

	@Override
	public ApiJsonResult submitGroup(Map<String, String> map) {
		// TODO Auto-generated method stub
			ApiJsonResult  jsonResult=new ApiJsonResult();
			String memberId=map.get("memberId");
		   //1、验证收货地址是否存在，无则返回
			String addressId=map.get("addressId");
			Address address=addressService.get(Long.valueOf(addressId));
			if(null==address || "".equals(address)){
				jsonResult.setSuccess(false).setMsg("wugaidizhi");
				return jsonResult;
			}
			//2、验证团购商品信息
			String activityGoodsId=map.get("activityGoodsId");
			ActivityGoods  activityGoods=activityGoodsService.get(Long.valueOf(activityGoodsId));
			String moneys=map.get("moneys");
			String counts=map.get("counts");
			jsonResult=this.validateActivityGoods(activityGoods, moneys, counts);
			if(!jsonResult.getSuccess()){
				  return jsonResult;
			}
			
		   //3、如果是拼团、则验证团信息，如无则为开团，初始化团信息
			  String groupId=map.get("groupId");
			  Group  group=null;
			  String isFunder="0";
			  if(null!=groupId && !"".equals(groupId)){
				    group=groupService.get(Long.valueOf(groupId));
					jsonResult=this.validateGroup(group);
						if(!jsonResult.getSuccess()){
							  return jsonResult;
						}  
			  }else{
				  //新开团，则保存新团信息
				  group=groupService.saveGroup(activityGoods, memberId);
				  isFunder="1";
			  }
			  map.put("isFunder", isFunder);
		//4、保存参团信息
			 GroupDetail detail=groupDetailService.saveGroupDetail(map, group, address);
			
		     return jsonResult.setSuccess(true).setObj(detail).setMsg("caozuochenggong");
	}

	@Override
	public ApiJsonResult payCoinConfirmGroup(Map<String, String> map) {
		// TODO Auto-generated method stub
		ApiJsonResult result=new ApiJsonResult();
		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		User user=remoteManageService.findUserById(Long.valueOf(map.get("memberId")));
		//1、验证支付密码
		 if(null!=user){
			 //如果支付密码密码为空，则提示用户去维护支付密码
			 if(null==user.getAccountPassWord() || "".equals(user.getAccountPassWord())){
				  result.setSuccess(false).setMsg("zhifumimakong"); //支付密码为空,请先去维护支付密码
	              return result; 
			  }
			    PasswordHelper passwordHelper = new PasswordHelper();
	            String encryString = passwordHelper.encryString(map.get("accountPassWord"), user.getSalt());
	            if(!encryString.equals(user.getAccountPassWord())){
	            	 result.setSuccess(false).setMsg("zhifumimacuowu"); //支付密码错误
	            	 return result;
	            }
	     //2、查询参团详情
	        GroupDetail detail=groupDetailService.get(Long.valueOf(map.get("groupDetailId")));
	       if(null==detail){
	    	  result.setSuccess(false).setMsg("参团信息不存在"); //参团信息不存在
			  return result;
	       }
	       if(1==detail.getStatus()){
	    	   result.setSuccess(false).setMsg("dingdanyizhifu"); //该订单已支付，无需重复支付
			   return result;
	       }
	       if(1==detail.getCoinPayStatus() ){
	    	   result.setSuccess(false).setMsg("shubiyizhifuchengong"); //该订单数币已支付成功，无需重复支付
			   return result;
	       }
	       
	     //3、验证该团信息
	       Group  group=groupService.get(Long.valueOf(detail.getGroupId()));
	       result=this.validateGroup(group);
	       if(!result.getSuccess()){
	    	   return result;  
	       }
	       //4、验证该团购商品信息
		   	ActivityGoods  activityGoods=activityGoodsService.get(group.getActivityGoodsId());
			result=this.validateActivityGoods(activityGoods, detail.getActivityPrice().toString(), detail.getCount().toString());
			if(!result.getSuccess()){
			    return result;
			}
	       Boolean flag=true;
	    //4、如果是虚拟币或者虚拟币、人民币混合支付，要验证账户金额是否充足
	       if(("2".equals(detail.getPayType())|| "3".equals(detail.getPayType())) &&  detail.getCoinCount().compareTo(new BigDecimal("0"))>0 ){
	    //5、重新计算下数字币，因为汇率在实时变化
    		 BigDecimal coinMoney=detail.getAmount().subtract(detail.getRmbMoney()).add(detail.getShippingFee());
    		 BigDecimal coinRate=goodsService.getCoinRate(detail.getCoinCode());//新的实时汇率
    		 detail.setCoinRate(coinRate.setScale(10, BigDecimal.ROUND_HALF_UP));
    		 detail.setCoinCount(coinMoney.divide(coinRate, 10, BigDecimal.ROUND_HALF_UP));//虚拟币数量 
    		 detail.setCoinFeeCount(detail.getCoinCount().multiply(detail.getCoinFeeRate()).divide(new BigDecimal("100")).setScale(10, BigDecimal.ROUND_HALF_UP));
	    //6、验证账户币余额是否充足
	    	   Map<String, String> vmap=new HashMap<String,String>();
		       vmap.put("memberId", detail.getMemberId().toString());
		       vmap.put("coinCode", detail.getCoinCode());
		       vmap.put("coinCount", (detail.getCoinCount().add(detail.getCoinFeeCount())).toString());
		       JsonResult result1=remoteOrderService.validateExaccount(vmap);
		       if(!result1.getSuccess()){
		    	   result.setSuccess(result1.getSuccess()).setMsg(result1.getMsg());
		    	   return result;
		       }else{
		    	//7、如果余额充足，则进行扣币操作   
		    	   if(this.withhold(detail)){
		    		   detail.setCoinPayStatus(Short.valueOf("1"));
		    	   }else{
		    		   flag=false;
		    	   }
		       }
	       }
	       //8、支付成功，更改订单状态
	       if(flag){
	    	    //如果是数币支付，则更改订单状态
	    	   if("2".equals(detail.getPayType()) && 1==detail.getCoinPayStatus()){
	    		   detail.setStatus(Integer.valueOf("1"));
	    		   //9、已参团人数加1
	    		   group.setAlreadyCount(group.getAlreadyCount()+1);
		    	    detail.setPaymentTime(new Date());
		    	    groupDetailService.update(detail);
		    	    //10、如果已参团人数达到拼团人数，则下订单
		    	    if(group.getAlreadyCount()==group.getCount() && group.getCount()>0){
		    	    	groupService.groupSuccess(group);
		    	    	  group.setStatus(1);
		    	    }
		    	  
		    	    groupService.update(group);
	    	   }
	    	   result.setSuccess(true).setMsg("zhifuchenggong").setObj(detail);  //支付成功
	       }
		 }else{
			 result.setSuccess(false).setMsg("user_no_exist"); //用户 不存在
			 return result;
		 }
		return result;
	}
	
	//转币操作
	public Boolean withhold( GroupDetail detail){
		ExDigitalmoneyAccountRedis dmAccount= UserRedisUtils.getAccountRedis(detail.getMemberId().toString(), detail.getCoinCode());
	     // 热账户减少
        Accountadd accountadd = new Accountadd();
        accountadd.setAccountId(dmAccount.getId());
        accountadd.setMoney((detail.getCoinCount().add(detail.getCoinFeeCount())).multiply(new BigDecimal(-1)));
        accountadd.setMonteyType(1);//1是操作热账户，其他是操作冻结账户
        accountadd.setAcccountType(1);
        accountadd.setRemarks(50);
        accountadd.setTransactionNum("TG"+detail.getId().toString());
        List<Accountadd> list = new ArrayList<Accountadd>();
        list.add(accountadd);
        messageProducer.toAccount(JSON.toJSONString(list));
		return true;
	}

	@Override
	public ApiJsonResult payRmbConfirmGroup(Map<String, String> map) {
		// TODO Auto-generated method stub
		ApiJsonResult result=new ApiJsonResult();
		//1、验证支付密码
		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		User user=remoteManageService.findUserById(Long.valueOf(map.get("memberId")));
		 if(null!=user){
		 //2、查询参团详情
			GroupDetail detail=groupDetailService.get(Long.valueOf(map.get("groupDetailId")));
		   if(null==detail){
			  result.setSuccess(false).setMsg("参团信息不存在"); //参团信息不存在
			  return result;
		   }
		   if(1==detail.getStatus()){
			   result.setSuccess(false).setMsg("dingdanyizhifu"); //该订单已支付，无需重复支付
			   return result;
		   }
		   if(1==detail.getCoinPayStatus() ){
			   result.setSuccess(false).setMsg("shubiyizhifuchengong"); //该订单数币已支付成功，无需重复支付
			   return result;
		   }

		 //3、验证该团信息
		   Group  group=groupService.get(Long.valueOf(detail.getGroupId()));
		   result=this.validateGroup(group);
		   if(!result.getSuccess()){
			   return result;
		   }
		   //4、验证该团购商品信息
			ActivityGoods  activityGoods=activityGoodsService.get(group.getActivityGoodsId());
			result=this.validateActivityGoods(activityGoods, detail.getActivityPrice().toString(), detail.getCount().toString());
			if(!result.getSuccess()){
				return result;
			}
		   Boolean flag=true;
		   //4、人民币支付，获得支付通道id,计算手续费
			String paymentId=map.get("paymentId");
			if(null!=paymentId && !"".equals(paymentId)){
				detail.setPaymentId(paymentId);
				Payment payment=paymentService.get(Long.valueOf(paymentId));
				if(null!=payment &&null!=payment.getPayPoundage() && !"".equals(payment.getPayPoundage())){
					BigDecimal rate=new BigDecimal(payment.getPayPoundage());
					detail.setRmbFeeRate(rate);
					detail.setRmbFeeMoney(detail.getTotalPrice().multiply(rate).divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
				}
			}

		 //5、如果有人民币支付，则记录请求第三方的记录
		   if(("1".equals(detail.getPayType())|| "3".equals(detail.getPayType())) &&  detail.getRmbMoney().compareTo(new BigDecimal("0"))>0){
			   ThirdpayRecord record=thirdpayRecordService.saveRecord(detail);
			   if(!"PAY_SUCCESS".equals(record.getCode())){
				   flag=false;
			   }else{
				   detail.setRmbPayStatus(Short.valueOf("1"));
			   }
		   }

		   //8、支付成功，更改订单状态
		   if(flag){
				//如果是数币支付，则更改订单状态
			   if("1".equals(detail.getPayType()) && 1==detail.getRmbPayStatus()){
				   detail.setStatus(Integer.valueOf("1"));
				   //9、已参团人数加1
				   group.setAlreadyCount(group.getAlreadyCount()+1);
					detail.setPaymentTime(new Date());
					groupDetailService.update(detail);
					//10、如果已参团人数达到拼团人数，则下订单
					if(group.getAlreadyCount()==group.getCount() && group.getCount()>0){
						groupService.groupSuccess(group);
						  group.setStatus(1);
					}

					groupService.update(group);
			   }
			   result.setSuccess(true).setMsg("zhifuchenggong").setObj(detail);  //支付成功
		   }


		 }else{
			 result.setSuccess(false).setMsg("user_no_exist"); //用户 不存在
			 return result;
		 }
		return result;
	}

	@Override
	public ApiJsonResult groupDetail(Map<String, String> map) {
		// TODO Auto-generated method stub
		String groupId=map.get("groupId");
		QueryFilter filter=new QueryFilter(GroupDetail.class);
		filter.addFilter("groupId=", groupId);
		filter.addFilter("status=", 1);
		List<GroupDetail> list=groupDetailService.find(filter);
		if(null!=list && list.size()>0){
			return new ApiJsonResult().setSuccess(true).setObj(list);
		}
		return new ApiJsonResult().setSuccess(false).setMsg("该团无效，换个试试");
	}

}
