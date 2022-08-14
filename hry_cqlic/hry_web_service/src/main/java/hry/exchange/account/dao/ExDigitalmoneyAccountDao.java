/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午5:55:59
 */
package hry.exchange.account.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.vo.DigitalmoneyAccountAndProduct;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午5:55:59
 */
public interface ExDigitalmoneyAccountDao extends
		BaseDao<ExDigitalmoneyAccount, Long> {
	public int updateByVersion(@Param("cold") BigDecimal cold, @Param("hot") BigDecimal hot,@Param("lend") BigDecimal lend, @Param("newversion") Integer newversion,@Param("customerId") Long customerId,@Param("coinCode") String coinCode, @Param("currencyType") String currencyType, @Param("website") String website, @Param("version") Integer version);
		
	/**
	 * 
	 * 通过站内星息 查询用户的虚拟币账户信息 包括产品
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月1日 下午6:54:13
	 */
	public List<DigitalmoneyAccountAndProduct> findNewProductByCustomer(@Param(value = "website") String website, @Param(value = "customerName") String customerName, @Param(value = "isMarket") Integer isMarket);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param map
	 * @return: void 
	 * @Date :          2016年8月13日 下午6:07:29   
	 * @throws:
	 */
	public List<ExDigitalmoneyAccount> findPageBySql(Map<String, Object> map);	
	
	/**
	 * 新sql查询功能
	 * @param map
	 * @return
	 */
	public List<ExDigitalmoneyAccount> findPageBySqlList(Map<String, Object> map);
	
	/**
	 * 根据条件查询客户信息
	 * @param map
	 * @return
	 */
	List<String> findCustomerByCondition(Map<String, Object> map);
	
	/**
	 * 查询分页总数
	 * @param map
	 * @return
	 */
	Long findPageBySqlCount(Map<String, Object> map);

}
