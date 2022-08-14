/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:59:41 
 */
package hry.admin.exchange.service;

import hry.admin.customer.model.AppCustomer;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.model.LmcTransfer;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExDmTransaction;
import hry.util.QueryFilter;

import java.math.BigDecimal;
import java.util.Map;


/**
 * <p> ExDmTransactionService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:59:41 
 */
public interface ExDmTransactionService  extends BaseService<ExDmTransaction, Long>{

    public PageResult findPageBySql(QueryFilter filter);
    void lockManagementHandler (Map<String, Object> paraMap);
    public JsonResult checkPass(Map<String, String> params);
    public void sendFrontMessage(AppCustomer customer, Boolean flag);

    public void sendOurCustomer(ExDmTransaction exDmTransaction, ExDigitalmoneyAccount exDigitalmoneyAccount);

    public String[] lmcTransfer(LmcTransfer transfer);

    public String pasePutOrder(Long id);

    public JsonResult checkReject(Map<String, String> params);

    public BigDecimal getYesterdayPostOrGet(String optType);

    JsonResult manualLockHandler (Map<String, Object> paramMap);
}
