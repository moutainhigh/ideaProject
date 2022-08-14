/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
package hry.admin.c2c.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.c2c.model.C2cCoin;



/**
 * <p> C2cCoinService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 12:06:01 
 */
public interface C2cCoinService  extends BaseService<C2cCoin, Long>{

    /**
     * 初始化C2C交易币种的缓存
     */
    void flushRedis();

}
