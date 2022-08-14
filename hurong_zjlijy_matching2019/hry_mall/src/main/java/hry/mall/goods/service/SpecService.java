/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:46:10 
 */
package hry.mall.goods.service;

import hry.core.mvc.service.base.BaseService;
import hry.mall.goods.model.Spec;
import java.util.List;
import java.util.Map;


/**
 * <p> SpecService </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:46:10 
 */
public interface SpecService  extends BaseService<Spec, Long> {

    public List<Spec> findSpecList(Map<String, Object> paramMap);

    Spec findSpecById(Long specId);
}
