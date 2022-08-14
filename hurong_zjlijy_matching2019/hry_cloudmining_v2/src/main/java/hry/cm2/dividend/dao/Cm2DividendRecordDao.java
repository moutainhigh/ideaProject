/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:27:21 
 */
package hry.cm2.dividend.dao;

import hry.cm2.customer.model.Cm2Customer;
import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.dividend.model.Cm2DividendRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> Cm2DividendRecordDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:27:21  
 */
public interface Cm2DividendRecordDao extends  BaseDao<Cm2DividendRecord, Long> {

    /**
     * 获取开启分红的用户
     * */
    public List<Cm2Customer> findCmCustomer();

    /**
     * 根据customerId和coinCode获取各业务的手续费
     * feeType 手续费类型 1.OTC 2.提币 3.币币交易 4.c2c交易
     * */
    public List<Map<String,Object>> findUserFee(@Param("customerId")Long customerId, @Param("coinCode")String coinCode);

}
