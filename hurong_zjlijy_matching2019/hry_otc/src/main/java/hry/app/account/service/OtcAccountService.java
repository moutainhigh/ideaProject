/**
 * Copyright:   
 * @author:      xubb
 * @version:     V1.0 
 * @Date:        2019-07-04 10:00:54 
 */
package hry.app.account.service;

import hry.core.mvc.service.base.BaseService;
import hry.otc.manage.remote.model.account.OtcAccount;
import hry.util.dto.Accountadd;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> OtcAccountService </p>
 * @author:         xubb
 * @Date :          2019-07-04 10:00:54 
 */
public interface OtcAccountService extends BaseService<OtcAccount, Long>{

    public Boolean accountaddQueue (String accoutadds);
    public  Boolean dealaccount (redis.clients.jedis.Transaction transaction, String accoutadds);

    public void changeCustomerHotAccount (List<Accountadd> aaddlists, Long customerId, String coinCode, Long contractAccountId, BigDecimal count, String transtionNum, Integer remark);
    public void changeCustomerColdAccount (List<Accountadd> aaddlists, Long customerId, String coinCode, Long contractAccountId, BigDecimal count, String transtionNum, Integer remark);
    public void saveAccount (List<Accountadd> accountaddlist);


}
