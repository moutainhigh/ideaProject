/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:42:58 
 */
package hry.admin.customer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.customer.model.AppBankCard;

import java.util.List;
import java.util.Map;


/**
 * <p> AppBankCardDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:42:58  
 */
public interface AppBankCardDao extends  BaseDao<AppBankCard, Long> {
    /**
     * 新sql查询方法
     * @param map
     * @return
     */
    List<AppBankCard> findPageBySql(Map<String, Object> map);

}
