package hry.customer.agents.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.customer.agents.dao.AppAgentsCustromerDao;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.vo.AgentsForMoney;
import hry.customer.agents.model.vo.CustomerInfoForAgents;
import hry.customer.agents.model.vo.CustromerInfo;
import hry.customer.agents.service.AppAgentsCustromerService;
import hry.customer.agents.service.CustomerAsAgentsService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.user.model.AppCustomer;
import hry.manage.remote.model.commendCode;
import hry.web.app.model.AppArticle;

@Service("appAgentsCustromerService")
public class AppAgentsCustromerServiceImpl extends BaseServiceImpl<AppAgentsCustromer,Long> implements AppAgentsCustromerService {

	@Resource(name="appAgentsCustromerDao")
	public void setDao(BaseDao<AppAgentsCustromer, Long> dao) {
		super.dao = dao;
	}
	
	@Resource(name="customerAsAgentsService")
	public CustomerAsAgentsService customerAsAgentsService;


	/**
	 * 
	 * 批量修改申请代理的用户(ids 表示 列表的 id   states表示状态  --- 2表示通过  ： 3表示删除)
	 * 
	 */
	@Override
	public JsonResult pasetUser(Long[] ids , Integer states) {
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		if(ids.length>0 ){
			if(states  == 2){
				for(Long id: ids){
					AppAgentsCustromer appAgentsCustromer = super.get(id);
					Integer i = appAgentsCustromer.getStates();
					if(i ==1 ){
						appAgentsCustromer.setStates(states);
						super.update(appAgentsCustromer);
					}
				}
				return jsonResult;
			}if(states == 3){
				for(Long id: ids){
					AppAgentsCustromer appAgentsCustromer = super.get(id);
					Integer j = appAgentsCustromer.getStates();
					if(j != 3){
						appAgentsCustromer.setStates(states);
						super.update(appAgentsCustromer);
					}
				}
				return jsonResult;
			}
		}
		jsonResult.setSuccess(false);
		return jsonResult;
	}

	/**
	 * 根据用户的id分页查询第几级用户信息     
	 */
	@Override
	public PageResult findCustromerByNum(QueryFilter filter, Long custromerId , Integer num) {
		
		// 创建PageResult对象
				PageResult pageResult = new PageResult();
				Page<AppArticle> page = null;
				if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
					// pageSize = -1 时
					// pageHelper传pageSize参数传0查询全部
					page = PageHelper.startPage(filter.getPage(), 0);
				} else {
					page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
				}

		
		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		
		if(num!=null&&custromerId!=null){
			List<AppPersonInfo> list = appAgentsCustromerDao.findAgentsCustromerByNum(custromerId, num);
		}
		
		pageResult.setRows(page.getResult());
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		
		return pageResult;
	}

	
	/**
	 * 通过用户的名字 查询第几级父  参数i 表示第几级        
	 * 
	 */
	@Override
	public AppAgentsCustromer findAgentsByCustromer(String custromerName,
			Integer i) {
		
		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		                  
		AppAgentsCustromer appAgent = appAgentsCustromerDao.findAgentsByCustromerName(custromerName, i);
		
		return appAgent;
	}
	
	
	/**
	 * 
	 * 根据用户的id 查询第几级所有用户
	 * 
	 */
	@Override
	public List<AppPersonInfo> findAllCustromer(Long id,Integer num){

		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		List<AppPersonInfo> list = appAgentsCustromerDao.findAgentsCustromerByNum(id, num);
		return list;
		
	}
	
	
	// 通过用户id 查询他所有的下级用户
	@Override
	public CustomerInfoForAgents findCustomerById(Long id){
		
		CustomerInfoForAgents customerInfoForAgents = new CustomerInfoForAgents();
		
		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		
		List<AppPersonInfo> list1 = appAgentsCustromerDao.findAgentsCustromerByNum(id,1);
		List<AppPersonInfo> list2 = appAgentsCustromerDao.findAgentsCustromerByNum(id,2);
		List<AppPersonInfo> list3 = appAgentsCustromerDao.findAgentsCustromerByNum(id,3);
		
		customerInfoForAgents.setOneList(list1);
		customerInfoForAgents.setTwoList(list2);
		customerInfoForAgents.setThreeList(list3);
		
		return customerInfoForAgents;
		
	}

	/**
	 * 
	 */
	@Override
	public PageResult findAgentsForMoney(QueryFilter filter,String agentName,String fixPriceCoinCode) {
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		
		if(null != agentName){
			agentName = agentName+"%";
		}
		
		List<AgentsForMoney> list = appAgentsCustromerDao.findAgentsForMoney(agentName,fixPriceCoinCode);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
		

	}
	
	/**
	 * 查询某个代理商的佣金详情 
	 * 
	 */
	@Override
	public AgentsForMoney findAgentsForMoneyToList(String agentName){
		
		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		
	//	List<AgentsForMoney> list = appAgentsCustromerDao.findAgentsForMoney(agentName,fixPriceCoinCode);
		
		/*if(list.size()>0){
			return list.get(0);
		}*/
		
		return null;
	}

	
	/**
	 * 
	 * 查询代理商的下级用户的详细信息
	 * 
	 * angetName 代理商的名字 
	 * 
	 * rank   1 表示1级代理商   2 表示2 级代理商  3 表示3级代理商
	 * 
	 * 
	 */
	@Override
	public List<CustromerInfo> findCustromerInfo(String agentName, Integer rank) {
		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		List<CustromerInfo> list = appAgentsCustromerDao.findCustromerInfo(agentName, rank);
		return list;
		
	}
	
	/**
	 * 审核认证
	 */
	@Override
	public void audit(AppAgentsCustromer appAgentsCustromer) {
		// TODO Auto-generated method stub
		
		AppAgentsCustromer appAgents = super.get(appAgentsCustromer.getId());
		appAgents.setModified(new Date());
		appAgents.setStates(2);
		super.update(appAgents);
		
	}

	@Override
	public List<AgentsForMoney> findAgentsForMoneyToListOne(String agentName) {
		
		AppAgentsCustromerDao appAgentsCustromerDao = (AppAgentsCustromerDao)dao;
		
		List<AgentsForMoney> list = appAgentsCustromerDao.findAgentsForMoney(agentName,null);
		
		/*if(list.size()>0){
			return list.get(0);
		}*/
		
		return list;

	}


	
	
	
	
	
	
	
	
	
	
	
}
