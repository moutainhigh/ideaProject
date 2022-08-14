/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:07:50 
 */
package hry.financail.financing.service;

import hry.core.mvc.service.base.BaseService;
import hry.financail.financing.model.AppFinancialProducts;
import hry.financail.financing.model.AppFinancialUser;
import hry.remote.model.FinancialUser;
import hry.remote.model.RemoteResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> AppFinancialUserService </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:07:50 
 */
public interface AppFinancialUserService  extends BaseService<AppFinancialUser, String>{

    /**
     * 创建订单
     * @param paramMap
     * @return
     */
    RemoteResult createFinancialOrder(AppFinancialProducts afp, String customerId, BigDecimal buyNumber,BigDecimal redPacketsMoney);

    List<FinancialUser> findFinancialUserList(Map<String,String> paramMap);

    /**
     * 获得已投资人数
     * @param pMap
     * @return
     */
    Integer findInvestmentedPersonCount(Map<String,Object> pMap);
}
