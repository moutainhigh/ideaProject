/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-13 18:38:15 
 */
package hry.admin.web.service;

import hry.admin.exchange.model.ExDmTransaction;
import hry.core.mvc.service.base.BaseService;
import hry.admin.web.model.AppOurAccount;

import java.math.BigDecimal;


/**
 * <p> AppOurAccountService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-13 18:38:15 
 */
public interface AppOurAccountService  extends BaseService<AppOurAccount, Long>{

    public AppOurAccount getAppOurAccount(String website, String currencyType, Integer type);

    public Boolean changeBitForOurAccount(BigDecimal bitCount, AppOurAccount ourAccount);

    public void changeCountToOurAccoun(AppOurAccount ourAccount, ExDmTransaction dmTransaction, String digAccountNum, String remark, String auditor);
}
