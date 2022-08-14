/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 17:58:04 
 */
package hry.admin.lend.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.lend.model.ExLendDetail;
import hry.reward.model.page.FrontPage;
import hry.util.QueryFilter;


/**
 * <p> ExLendDetailService </p>
 * @author:         HeC
 * @Date :          2018-10-18 17:58:04 
 */
public interface ExLendDetailService  extends BaseService<ExLendDetail, Long>{


    FrontPage findPageByFilter(QueryFilter filter);
}
