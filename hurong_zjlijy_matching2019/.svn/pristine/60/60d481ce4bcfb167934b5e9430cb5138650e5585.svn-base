/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:43:35
 */
package hry.social.force.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.social.force.dao.SocialForceCoinAdditionDao;
import hry.social.force.service.SocialForceCoinAdditionService;
import hry.social.manage.remote.model.force.SocialForceCoinAddition;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p> SocialForceCoinAdditionService </p>
 * @author: javalx
 * @Date :          2019-06-11 14:43:35  
 */
@Service("socialForceCoinAdditionService")
public class SocialForceCoinAdditionServiceImpl extends BaseServiceImpl<SocialForceCoinAddition,Long> implements SocialForceCoinAdditionService {

    @Resource(name = "socialForceCoinAdditionDao")
    @Override
    public void setDao(BaseDao<SocialForceCoinAddition,Long> dao) {
        super.dao = dao;
    }


    /**
     * 查询加成值
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal findAddition(Long customerId) {
        return ((SocialForceCoinAdditionDao) dao).findAddition(customerId);
    }

    @Override
    public SocialForceCoinAddition findVip(Long customerId) {
        return ((SocialForceCoinAdditionDao) dao).findVip(customerId);
    }
}
