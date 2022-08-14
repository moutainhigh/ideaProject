/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-25 13:58:51 
 */
package hry.admin.mining.service;

import hry.core.mvc.service.base.BaseService;
import hry.admin.mining.model.MingTimer;



/**
 * <p> MingTimerService </p>
 * @author:         sunyujie
 * @Date :          2018-09-25 13:58:51 
 */
public interface MingTimerService  extends BaseService<MingTimer, Long>{


    void initRedis();
}
