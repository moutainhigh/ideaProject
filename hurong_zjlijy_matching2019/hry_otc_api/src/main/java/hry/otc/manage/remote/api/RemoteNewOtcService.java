package hry.otc.manage.remote.api;

import hry.bean.FrontPage;
import hry.util.dto.OtcAccountRedis;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RemoteNewOtcService {

    /**
     * 初始化账号列表
     *
     * @param customerId
     * @return
     */
    public Boolean initAccount(Long customerId);

    public List<OtcAccountRedis> getAccoutList(Long customerId);


    /**
     * 建立账户
     *
     * @param customerId
     * @param coinCode
     * @return
     */
    public String[] beforaccount(Long customerId, String coinCode);

    /***
     * 从otc账户往主账户转
     * @param customerId
     * @param mainAccountId
     *
     * @param coinCode
     * @param count
     */
    public String[] accountOtcTomain(Long customerId, Long mainAccountId, String coinCode, BigDecimal count);

    /***
     * 从主账号往otc账户转
     * @param customerId
     * @param mainAccountId
     *
     * @param coinCode
     * @param count
     */
    public String[] accountMainToOtc(Long customerId, Long mainAccountId, String coinCode, BigDecimal count);


    BigDecimal getHotMoneyByCoin(String coinCode, Long customerId);


    /**
     * 查询账户记录
     *
     * @param params
     * @return
     */
    FrontPage getAccountRecord(Map<String,String> params);


    OtcAccountRedis getAccoutByIdAndCoinCode(Long customerId, String coinCode);


    void publish(Long userId, Long accountId, String coinCode, BigDecimal money, Integer monteyType, Integer acccountType, String transactionNum, Integer remarks);
}
