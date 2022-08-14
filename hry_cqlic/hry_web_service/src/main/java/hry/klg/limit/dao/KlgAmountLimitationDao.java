/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-19 17:06:43 
 */
package hry.klg.limit.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.limit.model.KlgAmountLimitation;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> KlgAmountLimitationDao </p>
 * @author:         lzy
 * @Date :          2019-04-19 17:06:43  
 */
public interface KlgAmountLimitationDao extends  BaseDao<KlgAmountLimitation, Long> {

    /**
     * 减少限额额度
     * @param type
     * @param money
     * @return
     */
    int reduceLimitQuota(@Param("type")String type,@Param("money") BigDecimal money);
}
