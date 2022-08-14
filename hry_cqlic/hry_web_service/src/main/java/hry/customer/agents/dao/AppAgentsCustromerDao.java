/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月6日 下午5:58:48
 */
package hry.customer.agents.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.vo.AgentsForMoney;
import hry.customer.agents.model.vo.CustromerInfo;
import hry.customer.agents.model.vo.CustromerToAgentsNum;
import hry.customer.person.model.AppPersonInfo;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月6日 下午5:58:48 
 * 
 */
public interface AppAgentsCustromerDao extends BaseDao<AppAgentsCustromer,Long>{
	
	
	/**
	 * 通过用户id和你要查询第几级推荐人   查出其所对应的几级推荐人的所有信息数 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月18日 上午9:31:07
	 */
	public List<AppPersonInfo> findAgentsCustromerByNum(@Param(value = "userId") Long userId, @Param(value = "num") Integer num);

	
	/**
	 * 
	 * 通过某个用户的id他下面所对应的一级推荐人的个数 二级用户的推荐个数 三级用户的个数
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月18日 上午9:25:39
	 */
	public CustromerToAgentsNum selectCountByCustromerId(@Param(value = "custromerId") Long userId);

	/**
	 * 通过查询用户的名字查询代理商信息 
	 * 第二个参数是  查询几级代理商
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月18日 下午4:55:38
	 */
	public AppAgentsCustromer findAgentsByCustromerName(@Param(value = "custromerName") String custromerName, @Param(value = "num") Integer num);
	
	
	/**
	 * 
	 * 查询代理商的佣金详情    
	 * 
	 * 参数 agentName 如果该参数为 null  查询所有代理商
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月3日 下午8:07:41
	 */
//	public List<AgentsForMoney> findAgentsForMoney(@Param(value = "agentName")String agentName);


	/**
	 * 
	 * 返回代理商的子用户的详细 信息
	 * 
	 * rank：1 表示查一级用户的list  2 表示查询二级用户的list  3 表示查询三级用户的list  
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午11:13:12
	 */
	public List<CustromerInfo> findCustromerInfo(@Param(value = "agentName") String agentName, @Param(value = "rank") Integer rank);


	public List<AgentsForMoney> findAgentsForMoney(@Param(value = "customerName") String customerName, @Param(value = "fixPriceCoinCode") String fixPriceCoinCode);
	
	
	
}
