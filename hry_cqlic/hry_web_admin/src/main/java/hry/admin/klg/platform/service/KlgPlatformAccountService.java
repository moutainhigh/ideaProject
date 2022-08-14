/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:35:25 
 */
package hry.admin.klg.platform.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.klg.platform.model.KlgPlatformAccount;

import java.util.Map;


/**
 * <p> KlgPlatformAccountService </p>
 * @author:         lzy
 * @Date :          2019-04-15 16:35:25 
 */
public interface KlgPlatformAccountService  extends BaseService<KlgPlatformAccount, Long>{

    public  int updatePlatformAccount(String money,String account);

    public JsonResult recharge(String money,String account);

    public  JsonResult transfer(String money,String account,String toAccount);
}
