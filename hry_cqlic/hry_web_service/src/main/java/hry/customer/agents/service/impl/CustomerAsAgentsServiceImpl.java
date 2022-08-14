package hry.customer.agents.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.CustomerAsAgents;
import hry.customer.agents.service.AppAgentsCustromerService;
import hry.customer.agents.service.CustomerAsAgentsService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;

@Service("customerAsAgentsService")
public class CustomerAsAgentsServiceImpl extends BaseServiceImpl<CustomerAsAgents,Long> implements CustomerAsAgentsService {

	@Resource(name="customerAsAgentsDao")
	public void setDao(BaseDao<CustomerAsAgents, Long> dao) {
		super.dao = dao;
	}

	@Resource(name="appAgentsCustromerService")
	public AppAgentsCustromerService appAgentsCustromerService;
	
	@Resource(name="appCustomerService")
	public AppCustomerService appCustomerService;
	
	@Resource(name="customerAsAgentsService")
	public CustomerAsAgentsService customerAsAgentsService;
	
	@Override
	public void saveObj(AppCustomer appCustomer) {
		
		QueryFilter filter = new QueryFilter(AppCustomer.class);
		filter.addFilter("commendCode=", appCustomer.getReferralCode());
	//	filter.addFilter("states=", 2);
		AppAgentsCustromer agentsCustromer = appAgentsCustromerService.get(filter);
		if(null != agentsCustromer){
		
			CustomerAsAgents customerAsAgents = new CustomerAsAgents();
			customerAsAgents.setCustomerId(appCustomer.getId());  // 设置用户id
			customerAsAgents.setAgentsCustromerId(agentsCustromer.getId()); // 设置推荐人id
			
			customerAsAgents.setOneParentId(agentsCustromer.getCustomerId());  // 设置1级代理用户id	
			customerAsAgents.setOneParentName(agentsCustromer.getCustomerName());  // 设置1级代理用户名字
			
			QueryFilter filter1 = new QueryFilter(CustomerAsAgents.class);
			filter1.addFilter("customerId=", agentsCustromer.getCustomerId());
			CustomerAsAgents customerAsAgents2 = customerAsAgentsService.get(filter1); // 查询中间表 如果存在 那说明只有有父
			
			if(null != customerAsAgents2 ){
				
				AppCustomer customer = appCustomerService.get(customerAsAgents2.getOneParentId());
				// 判断2级推荐人是否可用
				if(null != customer ){
					
					// 设置2级推荐人
					customerAsAgents.setTwoParentId(customer.getId());
					customerAsAgents.setTwoParentName(customer.getUserName());
					
					AppCustomer customer2 = appCustomerService.get(customerAsAgents2.getTwoParentId());
					
					// 判断三级推荐人是否可用
					if(null != customer2 && 1 != customer2.getIsDelete()){
						
						// 设置三级推荐人
						customerAsAgents.setThreeParentId(customer2.getId());
						customerAsAgents.setThreeParentName(customer2.getUserName());
				
					}
			
				}
			
			}
			
			// 当父推荐码不可用时 不会保存用户的推荐人信息
			customerAsAgentsService.save(customerAsAgents);	
		}
	}


}
