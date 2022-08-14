/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午12:19:16
 */
package hry.customer.remote;

import com.alibaba.dubbo.rpc.RpcContext;
import hry.bean.JsonResult;
import hry.core.shiro.PasswordHelper;
import hry.customer.agents.model.CustomerAsAgents;
import hry.customer.agents.service.CustomerAsAgentsService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.UUIDUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月28日 下午12:19:16 
 */
public class RemoteAppCustomerServiceImpl implements  RemoteAppCustomerService {
	
	
	@Resource
	private AppCustomerService appCustomerService;
	
	@Resource
	private AppPersonInfoService appPersonInfoService;
	
	@Resource(name="customerAsAgentsService")
	private CustomerAsAgentsService customerAsAgentsService;
	

	@Override
	public AppCustomer login(String userName,String saasId) {
		QueryFilter queryFilter = new QueryFilter(AppCustomer.class);
		queryFilter.setSaasId(saasId);
		queryFilter.addFilter("userName=", userName);
		return appCustomerService.get(queryFilter.setNosaas());
	}

	@Override
	public JsonResult regist(AppCustomer appCustomer) {
		
		String country = RpcContext.getContext().getAttachment("country");
		
		JsonResult jsonResult = new JsonResult();
		
		QueryFilter  queryFilter = new QueryFilter(AppCustomer.class);
		queryFilter.addFilter("userName=", appCustomer.getUserName());
		
		//查询此用户有没有被注册
		AppCustomer _appCustomer = appCustomerService.get(queryFilter);
		if(_appCustomer!=null){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("该用户已注册");
		}else{
			//设置uid
			appCustomer.setUserCode(UUIDUtil.getUUID());
	    	PasswordHelper passwordHelper = new PasswordHelper();
	    	//密码加密与加盐
			String[] strings = passwordHelper.encryptPassword(appCustomer.getPassWord());
			appCustomer.setSalt(strings[0]);
			appCustomer.setPassWord(strings[1]);
			appCustomerService.save(appCustomer);
	    	
	    	//初始化数据AppPersonInfo
	    	AppPersonInfo appPersonInfo = new AppPersonInfo();
	    	appPersonInfo.setCountry(country);
	    	appPersonInfo.setCustomerSource(0);
	    	appPersonInfo.setMobilePhone(appCustomer.getUserName());
	    	appPersonInfo.setCustomerId(appCustomer.getId());
	    	if(null==appPersonInfo.getCustomerType()){
	    		appPersonInfo.setCustomerType(1);
	    	}
	    	appPersonInfoService.save(appPersonInfo);
	    	
	    	// 初始化用户与代理的关系
	    	if(!"".equals(appCustomer.getReferralCode())){
	    		customerAsAgentsService.saveObj(appCustomer);
	    	}
	    	appCustomer.setAppPersonInfo(appPersonInfo);
	    	
	    	jsonResult.setObj(appCustomer);
	    	jsonResult.setSuccess(true);
		}
	
    	
    	return jsonResult;
	}
	
	
	@Override
	public JsonResult regist(AppCustomer appCustomer,
			CustomerAsAgents customerAsAgents) {
		
		String saasId = RpcContext.getContext().getAttachment("saasId");
		appCustomer.setSaasId(saasId);
		
		JsonResult jsonResult = new JsonResult();
		
		QueryFilter  queryFilter = new QueryFilter(AppCustomer.class);
		queryFilter.setSaasId(saasId);
		queryFilter.addFilter("userName=", appCustomer.getUserName());
		
		//查询此用户有没有被注册
		AppCustomer _appCustomer = appCustomerService.get(queryFilter);
		if(_appCustomer!=null){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("该用户已注册");
		}else{
			
			//设置uid
			appCustomer.setUserCode(UUIDUtil.getUUID());
	    	PasswordHelper passwordHelper = new PasswordHelper();
	    	//密码加密与加盐
			String[] strings = passwordHelper.encryptPassword(appCustomer.getPassWord());
			appCustomer.setSalt(strings[0]);
			appCustomer.setPassWord(strings[1]);
	    	appCustomerService.save(appCustomer);
	    	
	    	//初始化数据AppPersonInfo
	    	AppPersonInfo appPersonInfo = new AppPersonInfo();
	    	appPersonInfo.setSaasId(saasId);
	    	appPersonInfo.setCustomerSource(0);
	    	appPersonInfo.setMobilePhone(appCustomer.getUserName());
	    	appPersonInfo.setCustomerId(appCustomer.getId());
	    	if(null==appPersonInfo.getCustomerType()){
	    		appPersonInfo.setCustomerType(1);
	    	}
	    	appPersonInfoService.save(appPersonInfo);
	    	
	    	//初始代理中间表
	    	CustomerAsAgents customerAsAgents2 = new CustomerAsAgents();
	    	
	    	jsonResult.setObj(appCustomer);
	    	jsonResult.setSuccess(true);
		}
	
    	
    	return jsonResult;
		
		
	
	}
	
	
	
	
	
	

	@Override
	public void update(AppCustomer appCustomer) {
		appCustomerService.update(appCustomer);
	}

	@Override
	public AppCustomer getById(Long id) {
		return appCustomerService.get(id);
	}

	@Override
	public AppCustomer getByQueryFilter(RemoteQueryFilter remoteQueryFilter) {
		return appCustomerService.get(remoteQueryFilter.getQueryFilter());
	}

	@Override
	public List<AppCustomer> find(RemoteQueryFilter filter) {
		return appCustomerService.find(filter.getQueryFilter());
	}

	@Override
	public List<AppCustomer> getRealNameCustomer() {
		
		List<AppCustomer> l= appCustomerService.getRealNameCustomer();
		return l;
	}


	@Override
	public List<AppCustomer> getFundChangeCustomers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appCustomerService.getFundChangeCustomers(map);
	}

	@Override
	public int getHasAuthNum() {
		return appCustomerService.getHasAuthNum();
	}

	/**
	 * 金科新加
	 * 判断是这个推荐码的手机号是否存在
	 * 
	 * 其实就是判断是这个手机号是否存在
	 */
	@Override
	public AppCustomer getAppCustomerByCode(String referralCode) {
		QueryFilter filter = new QueryFilter(AppCustomer.class);
		filter.addFilter("userName=", referralCode);
		AppCustomer customer = appCustomerService.get(filter);
		if(null != customer){
			return customer;                    
		}                  
		return null;
	}




}
