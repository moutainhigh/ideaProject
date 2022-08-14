/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:50:05
 */
package hry.account.fund.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.account.fund.model.AppAccount;
import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.account.model.ExDigitalmoneyAccount;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:50:05 
 */
public interface AppAccountDao extends BaseDao<AppAccount,Long>{
	public int updateByVersion(@Param("cold") BigDecimal cold,@Param("hot")BigDecimal hot, @Param("lendMoney") BigDecimal lendMoney, @Param("newversion") Integer newversion,@Param("customerId") Long customerId, @Param("currencyType") String currencyType, @Param("version") Integer version);

	/**
	 * <p>后台分页查询</p>
	 * @author:         Liu Shilei
	 * @param:    @param map
	 * @return: void 
	 * @Date :          2016年8月13日 下午4:59:14   
	 * @throws:
	 */
	public List<AppAccount> findPageBySql(Map<String, Object> map);
	
	
	/**
	 * 查询用户的资金账户  
	 * 
	 * @param userName
	 * @param website
	 * @return
	 */
	public List<AppAccount> findListAccount(@Param(value = "userName") String userName, @Param(value = "website") String website);
	
	
	public List<ExDigitalmoneyAccount> findDigitalmoneyAccount(@Param(value = "userName") String userName, @Param(value = "website") String website);

	/**
	 * 金科添加：查找所有的代理商账户
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppAccount> 
	 * @Date :          2017年3月11日 上午10:31:29   
	 * @throws:
	 */
	public List<AppAccount> findAgentPageBySql(Map<String, Object> map);
	
	/**
	 * 新sql分页查询
	 * @param map
	 * @return
	 */
	public List<AppAccount> findPageBySqlList(Map<String, Object> map);
	
	/**
	 * 根据条件查询用户信息
	 * @param map
	 * @return
	 */
	List<String> findCustomerByCondition(Map<String, Object> map);
	
	/**
	 * 统计分页总条数
	 * @param map
	 * @return
	 */
	Long findPageBySqlCount(Map<String, Object> map);

}
