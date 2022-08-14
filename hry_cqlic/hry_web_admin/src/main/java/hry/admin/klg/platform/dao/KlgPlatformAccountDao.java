/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:35:25 
 */
package hry.admin.klg.platform.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.klg.platform.model.KlgPlatformAccount;
import org.apache.ibatis.annotations.Param;


/**
 * <p> KlgPlatformAccountDao </p>
 * @author:         lzy
 * @Date :          2019-04-15 16:35:25  
 */
public interface KlgPlatformAccountDao extends  BaseDao<KlgPlatformAccount, Long> {

    int updatePlatformAccount(@Param("money") String money, @Param("account")String account);
}
