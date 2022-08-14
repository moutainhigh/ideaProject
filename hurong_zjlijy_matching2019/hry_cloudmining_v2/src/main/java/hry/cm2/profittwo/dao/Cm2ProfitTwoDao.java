/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 09:46:51 
 */
package hry.cm2.profittwo.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.profittwo.model.Cm2ProfitTwo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> Cm2ProfitTwoDao </p>
 * @author:         yaozh
 * @Date :          2019-10-15 09:46:51  
 */
public interface Cm2ProfitTwoDao extends  BaseDao<Cm2ProfitTwo, Long> {

    /**
     * 统计每个用户所有未发放收益的总数
     * @return
     */
    public List<Cm2ProfitTwo> findProfitSum();

    /**
     * 更新未发放收益为已发放
     * @param customerId
     */
    void updateStatus(@Param("customerId")Long customerId);

    /**
     * 用户今天收益的总数
     * @param customerId
     * @return
     */
    public BigDecimal getTodayProfitSum(@Param("customerId")Long customerId);

    /**
     * 用户总收益的总数
     * @param customerId
     * @return
     */
    public BigDecimal getTotalProfitSum(@Param("customerId")Long customerId);


}
