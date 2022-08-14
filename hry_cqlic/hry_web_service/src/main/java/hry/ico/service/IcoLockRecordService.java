/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-15 14:34:14 
 */
package hry.ico.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoLockRecord;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> IcoLockRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-15 14:34:14 
 */
public interface IcoLockRecordService  extends BaseService<IcoLockRecord, Long>{

    /**
     * 锁仓释放
     * @return
     */
    public boolean releaseLockRecord(IcoLockRecord icoLockRecord);
   // public boolean releaseLockRecord(Long lockRecordId,Integer state, BigDecimal releaseDeduct);
    /**
     * 追加天数
     * @param icoLockRecord
     * @return
     */
    public  boolean appendLockRecord(IcoLockRecord icoLockRecord,Integer addDay);
    /**
     * 获取待释放的订单（当天跟当天以前）
     * @return
     */
    List<IcoLockRecord> getWaitReleaseList();
}
