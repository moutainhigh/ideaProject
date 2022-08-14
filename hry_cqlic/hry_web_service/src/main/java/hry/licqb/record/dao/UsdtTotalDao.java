/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 14:27:41 
 */
package hry.licqb.record.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.licqb.record.model.UsdtTotal;
import hry.licqb.record.model.UserAccount;

import java.util.List;


/**
 * <p> UsdtTotalDao </p>
 * @author:         zhouming
 * @Date :          2020-06-11 14:27:41  
 */
public interface UsdtTotalDao extends  BaseDao<UsdtTotal, Long> {

    public UsdtTotal getYesterdayTotal();

    public List<UserAccount> coldBusinessPingZhang();
}
