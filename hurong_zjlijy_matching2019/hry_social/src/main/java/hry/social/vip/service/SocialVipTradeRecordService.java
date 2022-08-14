/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:32:00
 */
package hry.social.vip.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.vip.SocialVipTradeRecord;
import hry.util.QueryFilter;


/**
 * <p> SocialVipTradeRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:32:00
 */
public interface SocialVipTradeRecordService extends BaseService<SocialVipTradeRecord,Long> {

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    PageResult findPageList(QueryFilter filter);
}
