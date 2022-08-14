/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:06:06 
 */
package hry.financail.financing.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.financail.financing.model.AppFinancialGiftRecord;
import hry.remote.model.RemoteResult;

import java.util.Map;


/**
 * <p> AppFinancialGiftRecordService </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:06:06 
 */
public interface AppFinancialGiftRecordService  extends BaseService<AppFinancialGiftRecord, String>{

    /**
     * 查询用户红包
     * @param map
     * @return
     */
    FrontPage findUserRedAccount(Map<String,String> map);

    /**
     * 用户一键领取红包
     * @param customerId
     * @param coinCode
     * @return
     */
    RemoteResult receiveAllRedAccount(String customerId,String coinCode);

    /**
     * 用户一键领取红包
     * @param customerId
     * @param coinCode
     * @return
     */
    RemoteResult receiveAllGiftRecord(String customerId, String coinCode);
}
