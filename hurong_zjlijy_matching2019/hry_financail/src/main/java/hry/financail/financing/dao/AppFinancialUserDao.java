/**
 * Copyright:    
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:07:50 
 */
package hry.financail.financing.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.financail.financing.model.AppFinancialUser;
import hry.remote.model.FinancialUser;

import java.util.List;
import java.util.Map;


/**
 * <p> AppFinancialUserDao </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:07:50  
 */
public interface AppFinancialUserDao extends  BaseDao<AppFinancialUser, String> {

    List<FinancialUser> findFinancialUserList(Map<String,String> paramMap);

    /**
     * 获得已投资人数
     * @param pMap
     * @return
     */
    Integer findInvestmentedPersonCount(Map<String,Object> pMap);

    public int findByUid(Long customerId);


    public int findAppFinancialUserRefactorCount();

    public List<AppFinancialUser> findAppFinancialUserRefactorList(Map<String,Object> pMap);
}
