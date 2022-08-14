/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月6日 下午6:02:18
 */
package hry.customer.agents.service;

import java.util.List;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.vo.AgentsForMoney;
import hry.customer.agents.model.vo.CustomerInfoForAgents;
import hry.customer.agents.model.vo.CustromerInfo;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.user.model.AppCustomer;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月6日 下午6:02:18 
 */
public interface AppAgentsCustromerService extends BaseService<AppAgentsCustromer, Long> {
       
	/**
	 * 
	 * 批量处理代理人信息
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月21日 下午7:28:09
	 */
	public JsonResult pasetUser(Long[] ids, Integer states);
	
	
	/**
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月18日 下午3:57:38
	 * 
	 */
	public PageResult findCustromerByNum(QueryFilter filter, Long custromerId, Integer num);
	
	
	
	/**
	 * 
	 * 通过用户名查询它所对应的父  或父父   或者。。。   
	 * 第二个参数是   判断查询的级数  
	 * @author:    Wu Shuiming   
	 * @version:   V1.0 
	 * @date:      2016年7月18日 下午4:06:08
	 */
	public AppAgentsCustromer findAgentsByCustromer(String custromerName, Integer i);


	/**
	 * 通过用户的id查询他的子用户信息   num表示查询第几级
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月21日 下午7:41:35
	 */
	public List<AppPersonInfo> findAllCustromer(Long id, Integer num);


	/**
	 * 通过用户的id查询用户的所有下级用户
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月1日 下午2:20:36
	 */
	public CustomerInfoForAgents findCustomerById(Long id);
	
	// 返回 所有 的代理商 的佣金详情
	public PageResult findAgentsForMoney(QueryFilter filter, String agentName, String fixPriceCoinCode);


	// 根据代理商 查询他的 所有佣金详情 
	public AgentsForMoney findAgentsForMoneyToList(String agentName);

	
	public List<CustromerInfo> findCustromerInfo(String agentName, Integer rank);

    //审核认证
	public void audit(AppAgentsCustromer appAgentsCustromer);


	public List<AgentsForMoney> findAgentsForMoneyToListOne(String agentName);


	

}                                                                                 
