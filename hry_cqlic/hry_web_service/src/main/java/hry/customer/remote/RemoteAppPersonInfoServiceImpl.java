/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午12:19:16
 */
package hry.customer.remote;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import com.alibaba.dubbo.rpc.RpcContext;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.email.EmailUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月28日 下午12:19:16 
 */
public class RemoteAppPersonInfoServiceImpl implements  RemoteAppPersonInfoService {
	
	@Resource
	private AppPersonInfoService appPersonInfoService;
	
	@Resource
	private AppCustomerService appCustomerService;
			
	@Override
	public AppPersonInfo getByCustomerId(Long id) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter = new QueryFilter(AppPersonInfo.class);
		filter.addFilter("customerId=", id);
		return appPersonInfoService.get(filter);
	}
	

	@Override
	public void save(AppPersonInfo appPersonInfo) {
		appPersonInfoService.save(appPersonInfo);
	}

	@Override
	public void update(AppPersonInfo appPersonInfo) {
		appPersonInfoService.update(appPersonInfo);
	}

	@Override
	public JsonResult addUserEmail(Long custromerId, String email) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(false);
		QueryFilter filter = new QueryFilter(AppPersonInfo.class);
		filter.addFilter("customerId=", custromerId);
		//   filter.setSaasId("402880e4514780120151479ac3a50005");
		List<AppPersonInfo> list = appPersonInfoService.find(filter);
		if(list.size()>0){
			AppPersonInfo appPersonInfo = list.get(0);
		
//		String email2 = appPersonInfo.getEmail();
//		if("".equals(email2)){
			String url = PropertiesUtils.APP.getProperty("app.emailurl_cn");
			String emailCode = UUID.randomUUID().toString();
			appPersonInfo.setEmail(email);
			appPersonInfo.setEmailCode(emailCode);
			String website = ContextUtil.getRemoteWebsite();
			
			if("en".equals(website)){
				url = PropertiesUtils.APP.getProperty("app.emailurl_en");
			}
			String companyName = PropertiesUtils.APP.getProperty("app.email.companyName");
			if(null == companyName || "" == companyName){
				companyName = "点击激活";
			}
			
			String path = url+"/email/callback/activationEmail?emailCode="+emailCode; 
			Boolean b = EmailUtil.sendMail(email, "绑定邮箱", "尊敬的用户您已经在<a href="+path+">"+companyName+"</a>成功的绑定了您的邮箱<br>如果不能激活请手动复制以下路径输入在浏览器中访问：<br>"+path+"        ");
			if(b){
				jsonResult.setMsg("邮箱绑定成功  请在2个工作日之内激活此邮箱");
				jsonResult.setSuccess(true);
				appPersonInfoService.update(appPersonInfo);
				return jsonResult;
			}else{
				jsonResult.setMsg("邮箱绑定失败");
				jsonResult.setSuccess(false);
				return jsonResult;
			}
		
		}
		return jsonResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 通过email判断用户的是否存在  如果存在 就返回true  如果不存在就返回false           
	 * 
	 */
	@Override
	public Boolean findCustromerByEmail(String email){
		
		QueryFilter filter = new QueryFilter(AppPersonInfo.class);
		
		filter.addFilter("email=",email);
		
		AppPersonInfo personInfo = appPersonInfoService.get(filter);
		
		if(null != personInfo){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	/**
	 * 
	 * 用的id 和 激活码 来激活用户的邮箱
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月30日 下午12:48:09
	 */
	@Override
	public Boolean activationEmail(String emailCode){
		
		QueryFilter filter = new QueryFilter(AppPersonInfo.class);
		filter.addFilter("emailCode=", emailCode);
		AppPersonInfo personInfo = appPersonInfoService.get(filter);
		
		if(null != personInfo){
			Long id = personInfo.getCustomerId();
			AppCustomer customer = appCustomerService.get(id);
			if(null != customer){
				customer.setHasEmail(1);
				appCustomerService.update(customer);
				return true;
			}
		}		
		return false;
	}


	@Override
	public AppPersonInfo get(RemoteQueryFilter remoteQueryFilter) {
		return appPersonInfoService.get(remoteQueryFilter.getQueryFilter());
	}



	@Override
	public List<AppPersonInfo> find(
			RemoteQueryFilter remoteQueryFilter) {
		// TODO Auto-generated method stub
		 return appPersonInfoService.find(remoteQueryFilter.getQueryFilter());
	}


	@Override
	public AppPersonInfo get(Long id) {
		return appPersonInfoService.get(id);
	}

	/**
	 * 判断申请的该地区的代理是否已存在
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param agentLevel
	 * @param:    @param provinceId
	 * @param:    @param cityId
	 * @param:    @param countyId
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2017年3月22日 下午2:19:54   
	 * @throws:
	 */
	@Override
	public boolean isAgentExist(String agentLevel, String provinceId,String cityId, String countyId) {
		return appPersonInfoService.isAgentExist(agentLevel,provinceId,cityId,countyId);
	}


	/**
	 * 查询用户积分列表
	 */
	@Override
	public PageResult findJifenRecord(RemoteQueryFilter filter,Long id) {
		return null;
	}


	


}
