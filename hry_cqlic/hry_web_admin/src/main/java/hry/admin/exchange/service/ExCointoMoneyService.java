/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 10:07:04 
 */
package hry.admin.exchange.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.ExCointoMoney;



/**
 * <p> ExCointoMoneyService </p>
 * @author:         tianpengyu
 * @Date :          2018-08-22 10:07:04 
 */
public interface ExCointoMoneyService  extends BaseService<ExCointoMoney, Long>{

    public void initRedis();
}
