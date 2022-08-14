/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-12-07 14:06:38 
 */
package hry.customer.businessman.service;

import hry.core.mvc.service.base.BaseService;
import hry.customer.businessman.model.C2cCoin;

import java.math.BigDecimal;


/**
 * <p> C2cCoinService </p>
 * @author:         liushilei
 * @Date :          2017-12-07 14:06:38  
 */
public interface C2cCoinService  extends BaseService<C2cCoin, Long>{
    BigDecimal getCoinPrice(String coin, String type);

    BigDecimal getSellPrice(String coin);

    BigDecimal getbuyPrice(String coin);

    C2cCoin getByCoin(String coin);
    public enum PriceType{
        BUY,Sell
    }
}
