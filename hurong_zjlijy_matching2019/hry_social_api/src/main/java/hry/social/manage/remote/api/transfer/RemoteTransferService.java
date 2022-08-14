/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 16:51:43
 */
package hry.social.manage.remote.api.transfer;

import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p> SocialTransferRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 16:51:43
 */
public interface RemoteTransferService {

    /**
     * 转账
     *
     * @param fromUser
     * @param toUser
     * @param coinCode
     * @param transferNum
     * @param accountPwd
     * @param remark
     * @return
     */
    RemoteResult transferCoin(User fromUser, User toUser, String coinCode, BigDecimal transferNum, String accountPwd, String remark);

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    FrontPage findTransferPage(Map<String,String> params);
}
