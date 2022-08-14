/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:40:12 
 */
package hry.licqb.ecology.service;

import hry.core.mvc.service.base.BaseService;
import hry.licqb.ecology.model.EcologEnter;

import java.util.Map;


/**
 * <p> EcologEnterService </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:40:12 
 */
public interface EcologEnterService  extends BaseService<EcologEnter, Long>{

    public int countEnter(Map<String, Object> map);
}
