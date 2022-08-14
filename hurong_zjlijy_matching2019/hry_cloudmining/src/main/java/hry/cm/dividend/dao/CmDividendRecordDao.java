/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-01 13:37:58 
 */
package hry.cm.dividend.dao;

import hry.cm.customer.model.CmCustomer;
import hry.core.mvc.dao.base.BaseDao;
import hry.cm.dividend.model.CmDividendRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> CmDividendRecordDao </p>
 * @author:         zhouming
 * @Date :          2019-08-01 13:37:58  
 */
public interface CmDividendRecordDao extends  BaseDao<CmDividendRecord, Long> {
        /**
         * 获取开启分红的用户
         * */
        public List<CmCustomer> findCmCustomer();

        /**
         * 根据customerId和coinCode获取各业务的手续费
         * feeType 手续费类型 1.OTC 2.提币 3.币币交易 4.c2c交易
         * */
        public List<Map<String,Object>> findUserFee(@Param("customerId")Long customerId, @Param("coinCode")String coinCode);
}
