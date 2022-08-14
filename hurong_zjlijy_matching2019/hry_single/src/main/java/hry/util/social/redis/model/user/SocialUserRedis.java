/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-10-25 18:07:42
 */
package hry.util.social.redis.model.user;


import hry.front.redis.model.UserRedis;

import java.util.List;

/**
 * <p> SocialCalculationAwardGatherRecord </p>
 *
 * @author: lixin
 * @Date :          2018-10-25 18:07:47
 */
public class SocialUserRedis extends UserRedis {

    private List<Long> pickIds;

    public List<Long> getPickIds() {
        return pickIds;
    }

    public void setPickIds(List<Long> pickIds) {
        this.pickIds = pickIds;
    }
}
