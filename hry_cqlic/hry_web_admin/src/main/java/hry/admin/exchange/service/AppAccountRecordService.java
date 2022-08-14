/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-20 09:55:15 
 */
package hry.admin.exchange.service;

import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.web.model.AppOurAccount;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.AppAccountRecord;



/**
 * <p> AppAccountRecordService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-20 09:55:15 
 */
public interface AppAccountRecordService  extends BaseService<AppAccountRecord, Long>{
    public void addRecordForBit(AppOurAccount ourAccount, String exDigNum, ExDmTransaction exDmTransaction, String auditor, String remark);

}
