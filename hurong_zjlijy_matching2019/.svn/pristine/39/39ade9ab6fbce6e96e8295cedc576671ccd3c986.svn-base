/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-04 16:17:57 
 */
package hry.mall.platform.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.platform.model.TransportExtend;
import hry.util.QueryFilter;


/**
 * <p> TransportExtendService </p>
 * @author:         kongdebiao
 * @Date :          2018-12-04 16:17:57 
 */
public interface TransportExtendService  extends BaseService<TransportExtend, Long> {


    PageResult findPageBySql(QueryFilter filter);
    void  saveExtend(String title, String tranStr, String type, String isDefault);
    void  editExtend(String title, String tranStr, String type, String transportId, String isDefault);
}
