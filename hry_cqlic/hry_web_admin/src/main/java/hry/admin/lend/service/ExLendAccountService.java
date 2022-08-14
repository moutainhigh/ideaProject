/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:05 
 */
package hry.admin.lend.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.lend.model.ExLendAccount;
import hry.reward.model.page.FrontPage;
import hry.util.QueryFilter;


/**
 * <p> ExLendAccountService </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:48:05 
 */
public interface ExLendAccountService  extends BaseService<ExLendAccount, Long>{


    FrontPage findPageByFilter(QueryFilter filter);
}
