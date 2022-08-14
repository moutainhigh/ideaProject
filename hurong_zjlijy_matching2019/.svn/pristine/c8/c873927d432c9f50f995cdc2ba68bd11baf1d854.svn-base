/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 16:51:43
 */
package hry.social.transfer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.transfer.SocialTransferRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialTransferRecordDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 16:51:43
 */
public interface SocialTransferRecordDao extends BaseDao<SocialTransferRecord,Long> {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    List<SocialTransferRecord> findPageList(Map<String,Object> map);

    /**
     * 查询币账户地址
     *
     * @param coinCode
     * @param customerId
     * @return
     */
    String getPublicKey(@Param("coinCode") String coinCode, @Param("customerId") Long customerId);

}
