/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:56:33 
 */
package hry.admin.exchange.service;

import hry.admin.exchange.model.ExProduct;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.util.QueryFilter;


/**
 * <p> ExDigitalmoneyAccountService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:56:33 
 */
public interface ExDigitalmoneyAccountService  extends BaseService<ExDigitalmoneyAccount, Long>{

    public void saveRecordAfterAudit (ExDigitalmoneyAccount eda, int type, ExProduct exProduct);

    public PageResult findPageBySql(QueryFilter filter);
}
