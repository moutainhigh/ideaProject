/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-04 11:06:01 
 */
package hry.admin.licqb.ecology.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.licqb.ecology.model.Ecofund;
import hry.util.QueryFilter;


/**
 * <p> EcofundService </p>
 * @author:         zhouming
 * @Date :          2020-06-04 11:06:01 
 */
public interface EcofundService  extends BaseService<Ecofund, Long>{

    public PageResult findPageEcofundList(QueryFilter filter);

    public Ecofund getEcofund(Long id);
}
