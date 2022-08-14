/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 13:54:29 
 */
package hry.cm2.account.dao;

import hry.cm2.dto.CmAccountRedis;
import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.account.model.Cm2Account;

import java.util.List;


/**
 * <p> Cm2AccountDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 13:54:29  
 */
public interface Cm2AccountDao extends  BaseDao<Cm2Account, Long> {

    void  updateAccount(List<CmAccountRedis> list);

}
