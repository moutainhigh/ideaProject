/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月8日 上午11:01:32
 */
package hry.customer.remote;

import java.math.BigDecimal;
import java.util.List;

import hry.bean.JsonResult;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.CommissionDeploy;
import hry.customer.agents.model.vo.AgentsForMoney;
import hry.customer.agents.model.vo.CustomerInfoForAgents;
import hry.customer.agents.model.vo.CustromerInfo;
import hry.customer.agents.model.vo.CustromerToAgentsNum;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月8日 上午11:01:32 
 */
public interface RemoteAppAgentsService {

	
	/**
	 * 保存一个推荐商
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月2日 上午11:11:27
	 */
	public JsonResult saveAgents(AppAgentsCustromer appAgentsCustromer);
	
	
	/**
	 * 修改一个推荐商
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月2日 上午11:11:27
	 */
	public JsonResult updateAgents(AppAgentsCustromer appAgentsCustromer);
	
	
	/**
	 * 通过用户的推荐吗 返回一个推荐商的信息 如果没有则返回null
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月2日 上午11:09:36
	 */
	public AppAgentsCustromer getAppAgentsCustromerByCode(String referralCode);

	public AppAgentsCustromer getAppAgentsCustromerByUserId(Long userId);
	
	/**
	 * 
	 * 通过用户的id 返回用户的下级用户的数量
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月2日 上午11:10:33
	 */
	public CustromerToAgentsNum fidnAgentNum(Long custromerId);

	/**
	 * 
	 * 通过用户id 返回用户的所有下级
	 * CustomerInfoForAgents 的三个属性表示其所对应的三级下属用户  
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月1日 下午2:25:12
	 */
	public CustomerInfoForAgents findCusomerByAgentsId(Long id);
	
	/**
	 * 
	 * 通过用户id返回代理商信息
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月1日 下午4:34:00
	 */
	public JsonResult findDetailByCustromerId(Long id);

	/**
	 * 通过用户的名字查询用户的佣金数量
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月2日 上午10:57:26
	 */
	public BigDecimal findMoneyByCustomerName(String customerName);


	/**
	 * 通过佣金类型 以及几级父 返回一个佣金参数对象 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月15日 下午4:33:23
	 */
	public CommissionDeploy findCommissionDeploy(Integer costId);

	/**
	 * 查询 代理商佣金的详细 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午10:23:15
	 */
	public AgentsForMoney findAgentsForMoney(String agentName);

	/**
	 * agentName 表示 代理商 的名字 
	 * 
	 * rank 表示 代理商的级别   1 2 3  分别表示3 级代理商 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月4日 下午1:06:12
	 */
	public List<CustromerInfo> findCustromerInfo(String agentName, Integer rank);

}
