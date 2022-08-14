/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-04 19:08:23 
 */
package hry.mall.platform.service;
import hry.core.mvc.service.base.BaseService;
import hry.mall.platform.model.Express;
/**
 * <p> ExpressService </p>
 * @author:         luyue
 * @Date :          2018-12-04 19:08:23 
 */
public interface ExpressService  extends BaseService<Express, Long> {
    public String createNum();
 
}
