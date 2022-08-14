/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:22 
 */
package hry.admin.commend.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.commend.model.AppCommendMoney;
import hry.util.QueryFilter;

import java.math.BigDecimal;


/**
 * <p> AppCommendMoneyService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:49:22 
 */
public interface AppCommendMoneyService  extends BaseService<AppCommendMoney, Long>{

    /**
     * sql分页
     * @param filter
     * @return
     */
    PageResult findPageBySql(QueryFilter filter);

    //派发佣金
    JsonResult postMoneyById(Long id, BigDecimal money);
}
