/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:32:00
 */
package hry.social.vip.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.vip.SocialVipTradeRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialVipTradeRecordDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:32:00
 */
public interface SocialVipTradeRecordDao extends BaseDao<SocialVipTradeRecord,Long> {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    List<SocialVipTradeRecord> findPageList(Map<String,Object> map);
}
