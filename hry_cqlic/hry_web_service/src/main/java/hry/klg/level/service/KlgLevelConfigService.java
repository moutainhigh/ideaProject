/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-17 14:31:17
 */
package hry.klg.level.service;

import hry.core.mvc.service.base.BaseService;
import hry.klg.level.model.KlgLevelConfig;


/**
 * <p> KlgLevelConfigService </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:31:17
 */
public interface KlgLevelConfigService  extends BaseService<KlgLevelConfig, Long>{

    /**
     * 根据等级Id获取配置
     * @param leveId
     * @return
     */
    KlgLevelConfig getLevelConfigByLevelId(Long leveId);

    /**
     * 获取用户当前等级的LevelConfig配置信息
     * @param customerId
     * @return
     */
    KlgLevelConfig getLevelConfigByCustomerId(Long customerId);


    /**
     * 根据等级排序的数量获取可获得等级
     * @param count
     * @param sort
     * @return
     */
    KlgLevelConfig getLevelConfigBySortAndCount(Integer count,Integer sort);

    //计算用户级差
    void alculationCustomerGradation();
}
