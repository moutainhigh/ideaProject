/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 18:29:25 
 */
package hry.social.mill.service;

import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.model.mill.SocialMillInfo;

import java.math.BigDecimal;


/**
 * <p> SocialMillInfoService </p>
 * @author:         javalx
 * @Date :          2019-06-11 18:29:25 
 */
public interface SocialMillInfoService  extends BaseService<SocialMillInfo, Long>{

    /**
     * 查看用户矿机购买信息
     *
     * @param customerId
     * @return
     */
    RemoteResult getMillInfo(Long customerId);

    /**
     * 查看用户购买数币矿机总奖励量
     *
     * @param customerId
     * @return
     */
    BigDecimal findMillCoin(Long customerId);

}
