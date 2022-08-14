/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-04 10:12:46 
 */
package hry.cmson.account.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.cmson.account.model.CmSonAccountRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> CmSonAccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-11-04 10:12:46 
 */
public interface CmSonAccountRecordService  extends BaseService<CmSonAccountRecord, Long>{

    /**
     * 获取用户收益统计信息
     * @param customerId
     * @param coinCode
     * @return
     */
    public List<CmSonAccountRecord> getProfitStatistics(@Param("customerId") Long customerId, @Param("coinCode") String coinCode);

    FrontPage getAccountRecord (Map<String, String> params);

}
