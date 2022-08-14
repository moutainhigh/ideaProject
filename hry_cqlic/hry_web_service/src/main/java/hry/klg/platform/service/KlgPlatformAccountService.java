/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-24 14:07:27 
 */
package hry.klg.platform.service;

import hry.core.mvc.service.base.BaseService;
import hry.klg.platform.model.KlgPlatformAccount;



/**
 * <p> KlgPlatformAccountService </p>
 * @author:         lzy
 * @Date :          2019-04-24 14:07:27 
 */
public interface KlgPlatformAccountService  extends BaseService<KlgPlatformAccount, Long>{
    int updatePlatformAccount( String money,String account);
    /**
     * 根据类型获取平台账户余额
     * @param account
     * @return
     */
    public  String getPlatformAccountByAccountType(String account);
}
