/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 09:57:59 
 */
package hry.admin.exchange.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExLawcoin;



/**
 * <p> ExLawcoinService </p>
 * @author:         tianpengyu
 * @Date :          2018-08-22 09:57:59 
 */
public interface ExLawcoinService  extends BaseService<ExLawcoin, Long>{

    void initRedis();
}
