/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 14:27:41 
 */
package hry.licqb.record.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.record.model.UsdtTotal;
import hry.licqb.record.model.UserAccount;

import java.util.List;


/**
 * <p> UsdtTotalService </p>
 * @author:         zhouming
 * @Date :          2020-06-11 14:27:41 
 */
public interface UsdtTotalService  extends BaseService<UsdtTotal, Long>{

    public void payAndExtractTotal();

    public List<UserAccount> coldBusinessPingZhang();
}
