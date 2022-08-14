/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-11-01 18:38:24 
 */
package hry.admin.lock.service;

import hry.admin.lock.model.ExDmLockReleaseTime;
import hry.core.mvc.service.base.BaseService;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmLockReleaseTimeService </p>
 * @author:         liuchenghui
 * @Date :          2018-11-01 18:38:24 
 */
public interface ExDmLockReleaseTimeService  extends BaseService<ExDmLockReleaseTime, Long>{

    List<ExDmLockReleaseTime> getReleaseTime (Map<String, Object> map);

    // 自动-更新释放记录
    void updReleaseTimeForAuto (Long recordId);

    // 手动-更新释放记录
    void updReleaseTimeForManual (Long recordId);
}
