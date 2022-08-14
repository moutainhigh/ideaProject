/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-19 17:06:43 
 */
package hry.klg.limit.service;

import hry.core.mvc.service.base.BaseService;
import hry.klg.limit.model.AmountLimitType;
import hry.klg.limit.model.KlgAmountLimitation;

import java.math.BigDecimal;


/**
 * <p> KlgAmountLimitationService </p>
 * @author:         lzy
 * @Date :          2019-04-19 17:06:43 
 */
public interface KlgAmountLimitationService  extends BaseService<KlgAmountLimitation, Long>{

    /**
     * 验证额度是否足够
     * @param type
     * @param money
     * @return
     */
    public  boolean isCheckNum(AmountLimitType type, BigDecimal money);
    /**
     * 减少额度
     * @return
     */
    public boolean  reduceLimitQuota(AmountLimitType type, BigDecimal money);
    /**
     * 定时每天12点额度清0
     */
    public void resetToZero();
}
