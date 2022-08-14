/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 18:28:34
 */
package hry.social.mill.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.mill.SocialMillTradeRecord;
import hry.util.QueryFilter;

import java.math.BigDecimal;


/**
 * <p> SocialMillTradeRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 18:28:34
 */
public interface SocialMillTradeRecordService extends BaseService<SocialMillTradeRecord,Long> {

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    PageResult findPageList(QueryFilter filter);

    BigDecimal getCoinReward(String coinCode);

}
