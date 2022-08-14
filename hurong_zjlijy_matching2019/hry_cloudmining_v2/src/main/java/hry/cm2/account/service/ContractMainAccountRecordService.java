/**
 * Copyright:   
 * @author:      gaomimi
 * @version:     V1.0 
 * @Date:        2019-01-21 18:07:33 
 */
package hry.cm2.account.service;

import hry.cm2.account.model.ContractMainAccountRecord;
import hry.core.mvc.service.base.BaseService;

import java.math.BigDecimal;


/**
 * <p> ContractMainAccountRecordService </p>
 * @author:         gaomimi
 * @Date :          2019-01-21 18:07:33 
 */
public interface ContractMainAccountRecordService extends BaseService<ContractMainAccountRecord, Long>{
    /**
     * TC账户转给主账户
     * @param customerId
     * @param mainAccountId
     * @param contractAccountId
     * @param coinCode
     * @param count
     */
    public  void contractTomain(Long customerId, Long mainAccountId, Long contractAccountId, String coinCode, BigDecimal count);

    /** 用户主账户转给TC账户
     *
     * @param customerId
     * @param mainAccountId
     * @param contractAccountId
     * @param coinCode
     * @param count
     */
    public  void mainTocontract(Long customerId, Long mainAccountId, Long contractAccountId, String coinCode, BigDecimal count);

    }
