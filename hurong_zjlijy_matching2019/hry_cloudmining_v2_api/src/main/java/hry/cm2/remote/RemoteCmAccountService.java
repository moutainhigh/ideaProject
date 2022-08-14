package hry.cm2.remote;

import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.cm2.dto.CmAccountRedis;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RemoteCmAccountService {
	
    /***
     * 从CM账户往主账户转
     * @param customerId
     * @param mainAccountId
     *
     * @param coinCode
     * @param count
     */
    public String[] accountSubTomain(Long customerId, Long mainAccountId, String coinCode, BigDecimal count);

    /***
     * 从主账号往CM账户转
     * @param customerId
     * @param mainAccountId
     * @param coinCode
     * @param count
     */
    public String[] accountMainToSub(Long customerId, Long mainAccountId, String coinCode, BigDecimal count);


    /**
     * 初始化账号列表
     *
     * @param customerId
     * @return
     */
    public Boolean initAccount(Long customerId);


    /**
     *建立账户
     * @param customerId
     * @param coinCode
     * @return
     */
    public String[] beforaccount(Long customerId, String coinCode);
    
    /**
	 * 我的资产
	 * @param params
	 * @return
	 */
	public JsonResult myAccount(Map<String, String> params);
	
	/**
	 * 我的资产-账户流水
	 * @return
	 */
	public FrontPage findMyAccountRecordList(Map<String, String> params);
	
	/**
	 * 我的资产-主账户平台币资产
	 * @param params
	 * @return
	 */
	public JsonResult mainAccount(Map<String, String> params);
	
    public List<CmAccountRedis> getAccoutList(Long customerId);
    
    BigDecimal getHotMoneyByCoin(String coinCode, Long customerId);
    
    /**
     * 查询账户记录
     *
     * @param params
     * @return
     */
    FrontPage getAccountRecord(Map<String, String> params);
    
  //根据coinCode获取币种汇率
    ApiJsonResult getCoinRate(String coinCode);

}
