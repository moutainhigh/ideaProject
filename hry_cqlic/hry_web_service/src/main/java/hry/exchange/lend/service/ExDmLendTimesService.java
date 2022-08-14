/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gaomm
 * @version:     V1.0 
 * @Date:        2017-11-29 18:36:30 
 */
package hry.exchange.lend.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.exchange.lend.model.ExDmLendTimes;



/**
 * <p> ExDmLendTimesService </p>
 * @author:         Gaomm
 * @Date :          2017-11-29 18:36:30  
 */
public interface ExDmLendTimesService  extends BaseService<ExDmLendTimes, Long>{
      public String[] confirm(String id, String lengPing, String lengRiskRate);
      public String[]  invalid(String id, String description);
      public String[]  invalids(String ids);

}
