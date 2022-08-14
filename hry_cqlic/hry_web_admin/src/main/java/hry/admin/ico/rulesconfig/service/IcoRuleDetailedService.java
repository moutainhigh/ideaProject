/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 18:38:38 
 */
package hry.admin.ico.rulesconfig.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.rulesconfig.model.IcoRuleDetailed;



/**
 * <p> IcoRuleDetailedService </p>
 * @author:         lzy
 * @Date :          2019-01-12 18:38:38 
 */
public interface IcoRuleDetailedService  extends BaseService<IcoRuleDetailed, Long>{
    //定时同步数据库平台币数量
    public void timingPlatformCurrencySyn();
}
