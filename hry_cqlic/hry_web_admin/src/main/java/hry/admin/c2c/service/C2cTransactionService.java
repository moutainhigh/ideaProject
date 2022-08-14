/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:46 
 */
package hry.admin.c2c.service;

import hry.admin.c2c.model.C2cTransaction;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * <p> C2cTransactionService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:34:46 
 */
public interface C2cTransactionService  extends BaseService<C2cTransaction, Long>{
    /**
     * sql分页查询
     * @param filter
     * @return
     */
    public PageResult findPageBySql(QueryFilter filter);

    /**
     * 确认订单
     * @param valueOf
     * @return
     */
    JsonResult confirm(Long valueOf);

    /**
     * 关闭订单
     * @param valueOf
     * @return
     */
    JsonResult close(Long valueOf);

    String getC2cOrderDetail(String transactionNum);

    List<C2cTransaction> getC2cList(HttpServletRequest request);
}
