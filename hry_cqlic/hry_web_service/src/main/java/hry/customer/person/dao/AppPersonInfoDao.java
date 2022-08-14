/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0 
 * @Date:        2016-11-22 18:25:52 
 */
package hry.customer.person.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.person.model.AppPersonInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * <p> AppPersonInfoDao </p>
 * @author:         zhangcb
 * @Date :          2016-11-22 18:25:52  
 */
public interface AppPersonInfoDao extends  BaseDao<AppPersonInfo, Long> {
	/**
	 * 列表查询
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param map
	 * @return: void 
	 * @Date :          2016年11月24日 下午1:32:42   
	 * @throws:
	 */
	List<AppPersonInfo> findPageBySql(Map<String, Object> map);
	
	/**
	 * 审核列表查询
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param map
	 * @return: void 
	 * @Date :          2016年11月24日 下午1:32:42   
	 * @throws:
	 */
	List<AppPersonInfo> findrefuselist(Map<String, Object> map);

	/**
	 * 查询单个对象
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppPersonInfo> 
	 * @Date :          2016年11月24日 下午6:39:40   
	 * @throws:
	 */
	List<AppPersonInfo> getById(Map<String, Object> map);
	
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppPersonInfo> 
	 * @Date :          2017年3月8日 下午2:52:52   
	 * @throws:
	 */
	List<AppPersonInfo> getByCustomerId(Map<String, Object> map);

	/**
	 * 注销用户列表查询
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppPersonInfo> 
	 * @Date :          2016年11月28日 下午2:17:24   
	 * @throws:
	 */
	List<AppPersonInfo> findcanclelist(Map<String, Object> map);

	
	/**
	 * 代理商申请列表
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppPersonInfo> 
	 * @Date :          2017年3月13日 下午5:25:03   
	 * @throws:
	 */
	List<AppPersonInfo> findAgentApplyList(Map<String, Object> map);

	/**
	 * 根据代理等级和区域编码获取代理商对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param agentLevel
	 * @param:    @param provinceId
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2017年3月22日 下午2:30:01   
	 * @throws:
	 */
	AppPersonInfo findAgentByTypeAndAreacode(@Param("jkAgentType") String jkAgentType, @Param("areaCode") String areaCode);

	/**
	 * 获取推荐人的personinfo对象
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerid
	 * @param:    @return
	 * @return: AppPersonInfo 
	 * @Date :          2017年3月30日 下午4:06:52   
	 * @throws:
	 */
	AppPersonInfo getAgentPerson(@Param("customerid") Long customerid);

	/**
	 * 金科新加用户资金报表
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppPersonInfo> 
	 * @Date :          2017年4月12日 上午11:25:14   
	 * @throws:
	 */
	List<AppPersonInfo> findPageBySql1(Map<String, Object> map);

	/**
	 * 获取一个用户的总充值
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月12日 下午1:57:34   
	 * @throws:
	 */
	BigDecimal getTotalRechargeMoney(@Param("customerId") Long customerId);
	/**
	 * 获取一个用户的总提现
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年4月12日 下午1:57:34   
	 * @throws:
	 */
	BigDecimal getTotalWithdrawalsMoney(@Param("customerId") Long customerId);
	

	/**
	 * 列id,cardId
	 * @param customerid
	 * @return
	 */
	AppPersonInfo getAppPersonInfoByCardId(@Param("cardId") String cardId);
	
	/**
	 * 列id,trueName,customerType
	 * @param customerid
	 * @return
	 */
	AppPersonInfo getAppPersonInfoByCustomerId(@Param("customerId") Long customerId);
	/**
	 * 通过邮箱 手机号查询用户信息
	 * @param list
	 * @return
	 */
	List<AppPersonInfo> getAppPersonInfoByEmailPhone(Map<String, Object> paramMap);
	
}
