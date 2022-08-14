/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月8日 上午11:07:27
 */
package hry.customer.remote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.Page;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.customer.agents.dao.AppAgentsCustromerDao;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.CommissionDeploy;
import hry.customer.agents.model.vo.AgentsForMoney;
import hry.customer.agents.model.vo.CustomerInfoForAgents;
import hry.customer.agents.model.vo.CustromerInfo;
import hry.customer.agents.model.vo.CustromerToAgentsNum;
import hry.customer.agents.service.AppAgentsCustromerService;
import hry.customer.agents.service.CommissionDeployService;
import hry.customer.agents.service.CommissionDetailService;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月8日 上午11:07:27 
 */
public class RemoteAppAgentsServiceImpl implements RemoteAppAgentsService {

	@Resource(name="appAgentsCustromerService")
	public AppAgentsCustromerService appAgentsCustromerService;
	
	@Resource(name="appAgentsCustromerDao")
	public AppAgentsCustromerDao appAgentsCustromerDao;
	
	@Resource(name="commissionDetailService")
	public CommissionDetailService commissionDetailService;
	
	@Resource(name="commissionDeployService")
	public CommissionDeployService commissionDeployService;
	
	
	
	@Override
	public JsonResult saveAgents(AppAgentsCustromer appAgentsCustromer) {
		JsonResult jsonResult = new JsonResult();
		
		String name = appAgentsCustromer.getCustomerName();
		QueryFilter filter = new QueryFilter(AppAgentsCustromer.class);
		filter.addFilter("customerName=", name);
		filter.addFilter("states!=", 3);
		AppAgentsCustromer custromer = appAgentsCustromerService.get(filter);
		if(null==custromer){
			// appAgentsCustromer.setStates(2);
			appAgentsCustromerService.save(appAgentsCustromer);
			jsonResult.setMsg("保存成功");
			jsonResult.setSuccess(true);
			return jsonResult;
			
		}else{
			jsonResult.setSuccess(true);
			jsonResult.setMsg("用户实名有重复");
			
		}
		
		return jsonResult;
	}

	
	/**
	 * 通过推荐码 返回一个推荐用户的详细信息
	 */
	@Override
	public AppAgentsCustromer getAppAgentsCustromerByCode(String referralCode) {
		
		QueryFilter filter = new QueryFilter(AppAgentsCustromer.class);
		filter.addFilter("recommendCode=", referralCode);
		filter.addFilter("states=", 2);
		AppAgentsCustromer appAgentsCustromer = appAgentsCustromerService.get(filter);
		if(null != appAgentsCustromer){
			return appAgentsCustromer;                    
		}                  
		return null;
	}
	
	/**
	 * 通过用户id返回一个推荐用户的详细信息
	 */
	@Override
	public AppAgentsCustromer getAppAgentsCustromerByUserId(Long userId) {
		
		QueryFilter filter = new QueryFilter(AppAgentsCustromer.class);
		filter.addFilter("customerId=", userId);
		filter.addFilter("states !=", 3);
		AppAgentsCustromer appAgentsCustromer = appAgentsCustromerService.get(filter);
		if(null != appAgentsCustromer){
			return appAgentsCustromer;                    
		}                  
		return null;
	}

	/**
	 * 根据用户的id查询用户的一级用户的个数 二级用户的个数 三级用户的个数
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月1日 下午12:03:01
	 */
	@Override
	public CustromerToAgentsNum fidnAgentNum(Long custromerId){
		CustromerToAgentsNum agentsNum = appAgentsCustromerDao.selectCountByCustromerId(custromerId);
		if(null == agentsNum){
			CustromerToAgentsNum custromerToAgentsNum = new CustromerToAgentsNum(0,0,0);
			return custromerToAgentsNum;
		}
		return agentsNum;
	}
	
	/**
	 * 通过用户id查询他所有的下级用户
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月1日 下午2:24:15
	 */
	@Override
	public CustomerInfoForAgents findCusomerByAgentsId(Long id){
		CustomerInfoForAgents customerInfoForAgents = appAgentsCustromerService.findCustomerById(id);
		return customerInfoForAgents;
	}


	@Override
	public JsonResult findDetailByCustromerId(Long id) {
		
		JsonResult jsonResult = new JsonResult();
		
		QueryFilter filter = new QueryFilter(AppAgentsCustromer.class);
		filter.addFilter("customerId=", id);
		filter.addFilter("states=", 2);
		AppAgentsCustromer agentsCustromer = appAgentsCustromerService.get(filter);
		if(null != agentsCustromer){
			jsonResult.setSuccess(true);
			jsonResult.setObj(agentsCustromer);
			return jsonResult;
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setObj(null);
			return jsonResult;
		}
	
	}
	
	/**
	 * 
	 * 通过用户的名字查询用户的的佣金数量
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月2日 上午10:50:58
	 */
	@Override
	public BigDecimal findMoneyByCustomerName(String customerName){
		
		BigDecimal commission = commissionDetailService.findMoneyByCustromerName(customerName);
		
		return commission;
		
	}
	
	
	/**
	 * 
	 * 通过查询什么类型的佣金参数  以及几级父  返回一个佣金配置信息  
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月15日 下午4:28:34
	 */
	@Override
	public CommissionDeploy findCommissionDeploy(Integer costId){
		
		CommissionDeploy deploy = commissionDeployService.findCommissionDeploy(costId);
		
		return deploy;
	}
	
	@Override
	public AgentsForMoney findAgentsForMoney(String agentName){
		AgentsForMoney agentsForMoney = appAgentsCustromerService.findAgentsForMoneyToList(agentName);
		return agentsForMoney;
	}


	/**
	 * 查询 代理商的几级代理商的详细信息
	 * 
	 * rank   1表示 1级代理商      2 表示 2级代理商     3 表示3级代理商  
	 * 
	 */
	@Override
	public List<CustromerInfo> findCustromerInfo(String agentName, Integer rank) {
		List<CustromerInfo> list = appAgentsCustromerService.findCustromerInfo(agentName, rank);
		return list;
	}


	@Override
	public JsonResult updateAgents(AppAgentsCustromer appAgentsCustromer) {
	JsonResult jsonResult = new JsonResult();
		
		String name = appAgentsCustromer.getCustomerName();
		QueryFilter filter = new QueryFilter(AppAgentsCustromer.class);
		filter.addFilter("customerName=", name);
		AppAgentsCustromer custromer = appAgentsCustromerService.get(filter);
		if(null!=custromer){
			custromer.setStates(2);
			appAgentsCustromerService.update(custromer);
			//appAgentsCustromerService.update(appAgentsCustromer);
			//appAgentsCustromerService.save(appAgentsCustromer);
			jsonResult.setMsg("修改成功");
			jsonResult.setSuccess(true);
			return jsonResult;
			
		}else{
			jsonResult.setSuccess(true);
			jsonResult.setMsg("用户实名有重复");
			
		}
		
		return jsonResult;
	}

}
