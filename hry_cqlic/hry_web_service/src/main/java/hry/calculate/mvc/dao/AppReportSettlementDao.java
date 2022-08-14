/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月3日 下午2:51:01
 */
package hry.calculate.mvc.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import hry.calculate.settlement.model.AppReportSettlement;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.model.BaseModel;

/**
 * @author Wu shuiming
 * @date 2016年9月3日 下午2:51:01
 */
public interface AppReportSettlementDao extends BaseDao<AppReportSettlement, Long> {
    
	
	public List<AppReportSettlement> getEndDate(); 
    
    /**
     * 查询平台账户用户的结算报表
     * 
     * @param stutus
     * @return
     */
    public List<AppReportSettlement> findPageByStates(@Param(value = "stutus") Integer stutus, @Param(value = "userName") String userName);
    
    
    public List<AppReportSettlement> getEndDate(Long customerId,
                                                String currencyType, String website);


}
