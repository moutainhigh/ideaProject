/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-26 10:52:07 
 */
package hry.ico.service;

import hry.core.mvc.service.base.BaseService;
import hry.ico.model.IcoAwardRecord;



/**
 * <p> IcoAwardRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-26 10:52:07 
 */
public interface IcoAwardRecordService  extends BaseService<IcoAwardRecord, Long>{
         //推荐奖励（首推+推荐5%）
   String recommendedLockSum(Long customerId);
}
