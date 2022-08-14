/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Wu Shuiming
 * @version: V1.0
 * @Date: 2016年3月28日 下午7:05:06
 */
package hry.social.transaction.service.impl;

import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.transaction.model.ExDmCustomerPublicKey;
import hry.social.transaction.service.ExDmCustomerPublicKeyService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午7:05:06
 */
@Service("exDmCustomerPublicKeyService")
public class ExDmCustomerPublicKeyServiceImpl extends BaseServiceImpl<ExDmCustomerPublicKey,Long> implements ExDmCustomerPublicKeyService {

    @Resource(name = "exDmCustomerPublicKeyDao")
    @Override
    public void setDao(BaseDao<ExDmCustomerPublicKey,Long> dao) {
        super.dao = dao;
    }
}
