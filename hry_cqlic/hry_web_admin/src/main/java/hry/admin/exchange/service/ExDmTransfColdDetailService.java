/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-27 18:01:50 
 */
package hry.admin.exchange.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExDmTransfColdDetail;

import java.util.Map;


/**
 * <p> ExDmTransfColdDetailService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-27 18:01:50 
 */
public interface ExDmTransfColdDetailService  extends BaseService<ExDmTransfColdDetail, Long>{

    public JsonResult toColdAccount(Map<String, String> params);
}
