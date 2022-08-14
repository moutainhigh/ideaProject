/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:42:53
 */
package hry.social.force.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.force.SocialTerminableForce;

import java.util.List;


/**
 * <p> SocialTerminableForceDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:42:53
 */
public interface SocialTerminableForceDao extends BaseDao<SocialTerminableForce,Long> {

    /**
     * 有效的时效性算力记录
     *
     * @param customerId
     * @return
     */
    List<SocialTerminableForce> findTerminablen(Long customerId);

    /**
     * 失效的时效性算力记录
     *
     * @param customerId
     * @return
     */
    List<SocialTerminableForce> findDisTerminable(Long customerId);
}
