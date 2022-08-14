/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 13:57:48 
 */
package hry.cm2.account.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.cm2.account.model.Cm2AccountRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> Cm2AccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 13:57:48 
 */
public interface Cm2AccountRecordService  extends BaseService<Cm2AccountRecord, Long>{

    /**
     * 获取用户收益统计信息
     * @param customerId
     * @param coinCode
     * @return
     */
    public List<Cm2AccountRecord> getProfitStatistics(@Param("customerId") Long customerId, @Param("coinCode") String coinCode);

    FrontPage getAccountRecord (Map<String, String> params);


}
