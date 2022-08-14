/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-20 15:43:03 
 */
package hry.cm4.account.service;

import hry.bean.FrontPage;
import hry.cm4.account.model.Cm4AccountRecord;
import hry.core.mvc.service.base.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> Cm4AccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-11-20 15:43:03 
 */
public interface Cm4AccountRecordService  extends BaseService<Cm4AccountRecord, Long>{

    /**
     * 获取用户收益统计信息
     * @param customerId
     * @param coinCode
     * @return
     */
    public List<Cm4AccountRecord> getProfitStatistics(@Param("customerId") Long customerId, @Param("coinCode") String coinCode);

    FrontPage getAccountRecord (Map<String, String> params);
}
