/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:53 
 */
package hry.admin.licqb.ecology.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.licqb.ecology.model.EcologEnter;
import hry.util.QueryFilter;

import java.util.Map;


/**
 * <p> EcologEnterService </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:37:53 
 */
public interface EcologEnterService  extends BaseService<EcologEnter, Long>{

    public PageResult findPageEcologEnterList(QueryFilter filter);

    public EcologEnter getEcologEnter(Long id);

    public JsonResult updateEcologEnter(EcologEnter ecologEnter);

    public int countEnter(Map<String, Object> map);
}
