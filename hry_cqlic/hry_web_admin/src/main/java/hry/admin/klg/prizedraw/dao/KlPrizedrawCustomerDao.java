/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:31:26 
 */
package hry.admin.klg.prizedraw.dao;

import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.admin.klg.prizedraw.model.KlPrizedrawCustomer;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;


/**
 * <p> KlPrizedrawCustomerDao </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:31:26  
 */
public interface KlPrizedrawCustomerDao extends  BaseDao<KlPrizedrawCustomer, Long> {
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlPrizedrawCustomer> findPageBySql(Map<String,Object> map);
    
    /**
	 * 根据期号更新用户抽奖数据为已开奖
	 * @param issueNumber
	 */
	void updateStatusByIssueNumber(Map<String,Object> map);

}
