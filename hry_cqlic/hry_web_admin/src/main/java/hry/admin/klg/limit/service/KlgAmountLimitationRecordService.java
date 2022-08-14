/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 14:48:45 
 */
package hry.admin.klg.limit.service;

import hry.admin.klg.limit.model.KlgAmountLimitation;
import hry.bean.BaseManageUser;
import hry.core.mvc.service.base.BaseService;
import hry.admin.klg.limit.model.KlgAmountLimitationRecord;



/**
 * <p> KlgAmountLimitationRecordService </p>
 * @author:         lzy
 * @Date :          2019-04-18 14:48:45 
 */
public interface KlgAmountLimitationRecordService  extends BaseService<KlgAmountLimitationRecord, Long>{

    /**
     * 添加操作日志
     * @param amountLimitation
     */
    public void saveLimitationRecord(BaseManageUser user,KlgAmountLimitation amountLimitation);
}
