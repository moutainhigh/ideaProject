/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 16:51:43
 */
package hry.social.transfer.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.transfer.SocialTransferRecord;
import hry.util.QueryFilter;

import java.util.Map;


/**
 * <p> SocialTransferRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 16:51:43
 */
public interface SocialTransferRecordService extends BaseService<SocialTransferRecord,Long> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    FrontPage findPageList(Map<String,String> params);

    /**
     * 查询币地址
     * @param coinCode
     * @param tocustomerId
     * @return
     */
    String getPublicKey(String coinCode, Long tocustomerId);
}
