/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-12 11:24:44 
 */
package hry.social.vip.service;

import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.manage.remote.model.vip.SocialVipInfo;

import java.util.List;


/**
 * <p> SocialVipInfoService </p>
 * @author:         javalx
 * @Date :          2019-06-12 11:24:44 
 */
public interface SocialVipInfoService  extends BaseService<SocialVipInfo, Long>{

    /**
     * 查询可购买的会员信息
     *
     * @param socialCustomerVip
     * @return
     */
    List<SocialVipInfo> getAvailableVip(SocialCustomerVip socialCustomerVip);
}
