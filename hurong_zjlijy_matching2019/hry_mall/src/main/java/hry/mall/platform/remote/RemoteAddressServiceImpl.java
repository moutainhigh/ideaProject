package hry.mall.platform.remote;

import hry.bean.JsonResult;
import hry.common.enums.RedisKeyEnum;
import hry.mall.order.model.Feedback;
import hry.mall.order.service.FeedbackService;
import hry.mall.platform.model.Address;
import hry.mall.platform.model.BlendPay;
import hry.mall.platform.model.Payment;
import hry.mall.platform.remote.model.AddressVo;
import hry.mall.platform.service.AddressService;
import hry.mall.platform.service.BlendPayService;
import hry.mall.platform.service.PaymentService;
import hry.manage.remote.RemoteMallService;
import hry.manage.remote.model.AppDic;
import hry.manage.remote.model.mall.AppAreaDicVo;
import hry.manage.remote.model.mall.ExProductVo;
import hry.util.BeanToBean;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoteAddressServiceImpl implements RemoteAddressService {
   private static Logger logger = Logger.getLogger(RemoteAddressServiceImpl.class);
   @Resource
   private AddressService addressService;
   @Resource
   private FeedbackService feedbackService;
   @Resource
   private BlendPayService blendPayService;
   @Resource
   private PaymentService paymentService;
   
	@Override
	public List<AddressVo> findByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		List<AddressVo> list=new ArrayList<AddressVo>();
		List<Address>  alist=addressService.findByCustomerId(customerId,null);
		if(null!=alist && alist.size()>0){
			for(Address a:alist){
				AddressVo vo=new AddressVo();
				vo=(AddressVo) BeanToBean.convertBean2Bean(a, vo);
				list.add(vo);
			}
		}
		return list;
	}
	@Override
	public JsonResult addAddress(AddressVo addressVo) {
		// TODO Auto-generated method stub
		Address  address=new Address();
	             address=(Address)BeanToBean.convertBean2Bean(addressVo, address);
	     		//1、查询是否是默认收货地址，如果是，则要将其他设为默认的变为非默认
	     		if(null!=address.getIsDefault() && 1==address.getIsDefault()){
	     			QueryFilter filter = new QueryFilter(Address.class);
	     			filter.addFilter("isDefault=", 1);
	     			List<Address> list=addressService.find(filter);
	     			if(null!=list && list.size()>0){
	     				for(Address a:list){
	     					a.setIsDefault(Short.valueOf("0"));
	     					addressService.update(a);
	     				}
	     			}
	     		}
		         addressService.save(address);
		         return new JsonResult().setSuccess(true).setMsg("tianjiachenggong") ;
	}

	@Override
	public JsonResult modifyAddress(AddressVo addressVo) {
		// TODO Auto-generated method stub
		Address  address=new Address();
        address=(Address)BeanToBean.convertBean2Bean(addressVo, address);
		//1、查询是否是默认收货地址，如果是，则要将其他设为默认的变为非默认
		if(null!=address.getIsDefault() && 1==address.getIsDefault()){
			QueryFilter filter = new QueryFilter(Address.class);
			filter.addFilter("isDefault=", 1);
			List<Address> list=addressService.find(filter);
			if(null!=list && list.size()>0){
				for(Address a:list){
					a.setIsDefault(Short.valueOf("0"));
					addressService.update(a);
				}
			}
		}
        addressService.update(address);
        return new JsonResult().setSuccess(true).setMsg("xiugaichenggong") ;
	}

	@Override
	public JsonResult deleteAddress(Long id) {
		// TODO Auto-generated method stub
		addressService.delete(id);
		 return new JsonResult().setSuccess(true).setMsg("shanchuchenggong") ;
	}

	@Override
	public JsonResult getAddress(Long id) {
		// TODO Auto-generated method stub
		JsonResult result=new JsonResult();
		Address address=addressService.get(id);
		if(null!=address && !"".equals(address)){
		    RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
		  //1、获得省名称
	        AppAreaDicVo  vo1=remoteMallService.findAreaRedis(RedisKeyEnum.KeyEnum.REIDS_DATA_AREADIC_PROVINCE.getIndex(), address.getProvinceId());
	        address.setProvinceName(vo1.getRegionName());
	      //2、获得市名称
	        AppAreaDicVo  vo2=remoteMallService.findAreaRedis(RedisKeyEnum.KeyEnum.REIDS_DATA_AREADIC_CITY.getIndex(), address.getCityId());
	        address.setCityName(vo2.getRegionName());
	      //3、获得区名称
	        AppAreaDicVo  vo3=remoteMallService.findAreaRedis(RedisKeyEnum.KeyEnum.REIDS_DATA_AREADIC_COUNTY.getIndex(), address.getCountyId());
	        address.setCountyName(vo3.getRegionName());
			result.setSuccess(true).setObj(address);	
       }else{
			result.setSuccess(false).setMsg("wugaidizhi");
		}
		return result;
	}

	@Override
	public JsonResult getFeedback() {
		// 获取反馈类型
	    RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
	    List<AppDic> list=remoteMallService.findListByPkey("feedback");
	    return new JsonResult().setSuccess(true).setObj(list);
	}

	@Override
	public JsonResult saveFeedback(Map<String, String> map) {
		Feedback feedback = new Feedback();
		feedback.setContent(map.get("content"));
		feedback.setMemberId(Long.parseLong(map.get("memberId")));
		feedback.setImage(map.get("image"));
		feedback.setMobile(map.get("mobile"));
		RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
        AppDic appDic=remoteMallService.findAppDicById(Long.parseLong(map.get("feedbackType")));
		feedback.setFeedbackType(appDic.getName());
		feedbackService.save(feedback);
		return new JsonResult().setSuccess(true).setMsg("tijiaochenggong");
	}
	@Override
	public JsonResult mallCoinList() {
		// TODO Auto-generated method stub
		 RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
		 List<ExProductVo> mlist = remoteMallService.findByIssueState("1");
	//	 return new JsonResult().setSuccess(true).setObj(net.sf.json.JSONArray.fromObject(mlist));
		 return new JsonResult().setSuccess(true).setObj(mlist);
	}

	@Override
	public JsonResult blendCoinList() {
		// TODO Auto-generated method stub
		//1、查询混合支付的设置
		  List<BlendPay> blist=blendPayService.findAll();
		  List<ExProductVo> mlist=new ArrayList<ExProductVo>();
		  if(null!=blist && blist.size()>0){
			  RemoteMallService remoteMallService = SpringUtil.getBean("remoteMallService");
			  BlendPay pay=blist.get(0);
			  if(null!=pay.getSupportCurrency() && !"".equals(pay.getSupportCurrency())){
				  String [] arry=pay.getSupportCurrency().split(",");
				  for(int i=0;i<arry.length;i++){
					  ExProductVo vo=remoteMallService.findByCoinCode(arry[i]);
					  if(null!=vo){
						  mlist.add(vo); 
					  }
						
				  }
			  }
		  }else{
			
		  }
		 return new JsonResult().setSuccess(true).setObj(mlist);
	}

	@Override
	public JsonResult rmbList() {
		// TODO Auto-generated method stub
		  //查询人民币的支付通道
		QueryFilter filter=new QueryFilter(Payment.class);
		filter.addFilter("paymentState=", Integer.valueOf("1"));
		List<Payment> plist=paymentService.find(filter);
		return new JsonResult().setSuccess(true).setObj(plist);
	}

}
