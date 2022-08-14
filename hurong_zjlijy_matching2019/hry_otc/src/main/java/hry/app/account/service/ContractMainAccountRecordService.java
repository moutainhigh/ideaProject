/**
 * Copyright:   
 * @author:      gaomimi
 * @version:     V1.0 
 * @Date:        2019-01-21 18:07:33 
 */
package hry.app.account.service;

import hry.core.mvc.service.base.BaseService;
import hry.otc.manage.remote.model.account.ContractMainAccountRecord;

import java.math.BigDecimal;


/**
 * <p> ContractMainAccountRecordService </p>
 * @author:         gaomimi
 * @Date :          2019-01-21 18:07:33 
 */
public interface ContractMainAccountRecordService extends BaseService<ContractMainAccountRecord, Long>{
    /**
     * otc账户转给主账户
     * @param customerId
     * @param mainAccountId
     * @param contractAccountId
     * @param coinCode
     * @param count
     */
    public  void otcTomain (Long customerId, Long mainAccountId, Long contractAccountId, String coinCode, BigDecimal count);

    /** 用户主账户转给otc账户
     *
     * @param customerId
     * @param mainAccountId
     * @param contractAccountId
     * @param coinCode
     * @param count
     */
    public  void mainToOtc (Long customerId, Long mainAccountId, Long contractAccountId, String coinCode, BigDecimal count);

    }
