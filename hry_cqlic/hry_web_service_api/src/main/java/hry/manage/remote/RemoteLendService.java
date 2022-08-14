
package hry.manage.remote;

import hry.manage.remote.model.AppAccountManage;
import hry.manage.remote.model.AppOurAccountManage;
import hry.manage.remote.model.CoinAccount;
import hry.manage.remote.model.Lend;
import hry.manage.remote.model.LendCanCoinCode;
import hry.manage.remote.model.LendIntent;
import hry.manage.remote.model.LendTimes;
import hry.manage.remote.model.MyAccountTO;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.trade.redis.model.EntrustTrade;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RemoteLendService {

	/**
	 * 融基础币:净资产，已借资产，还能借多少
	 * @param customerId
	 * @return
	 */
	Map<String, String> getLengCoinCodeForRmbCenter(Long customerId);

	/**
	 * 客户是否进入了平账流程
	 * @param customerId
	 * @param userCode
	 * @param currencyType
	 * @param website
	 * @return
	 */
	Boolean isPinging(Long customerId, String userCode, String currencyType, String website);







































	// ==================================================分割线===========================================================

	/**
	 * 融资融币记录
	 * @param customerId
	 * @return
	 */
	public FrontPage findExDmLend(Map<String, String> params);
	/**
	 * 融资融币:总资产，净资产，已借资产，还能借多少
	 * @param customerId
	 * @return
	 */
	public Map<String,BigDecimal> getLengCenter(Long customerId, String coinCode);


	/**
	 *
	 * <p>
	 * 借钱
	 * </p>
	 *
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月19日 下午4:42:17
	 * @throws:
	 */
	public String[] saveExDmLend(Lend exDmLend);

	  /**
     *
     * <p> 资产/杠杆：比例</p>
     * @author:         Gao Mimi
     * @param:    @param customerId
     * @param:    @return
     * @return: BigDecimal
     * @Date :          2016年6月27日 下午5:38:18
     * @throws:
     */
    public BigDecimal netAsseToLend(Long customerId, String currencyType, String website);

    /**
     * 还款记录
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param filter
     * @param:    @return
     * @return: PageResult
     * @Date :          2016年6月27日 下午6:51:45
     * @throws:
     */
	public FrontPage fildIntentPage(Map<String, String> params);
	/**
	 *
	 * <p>还款的相关数据</p>
	 * @author:         Gao Mimi
	 * @param:    @param id
	 * @param:    @return
	 * @return: String
	 * @Date :          2016年6月28日 上午10:52:29
	 * @throws:
	 */
	public String repaymentInfo(Long lendId);
	/**
	 *
	 * <p> 还款</p>
	 * @author:         Gao Mimi
	 * @param:    @param id
	 * @param:    @param type
	 * @param:    @return
	 * @return: String
	 * @Date :          2016年6月28日 上午11:42:56
	 * @throws:
	 */
	public String[] repayment(Long id, String type, BigDecimal repaymentMoney);





   /**
    *
    * <p>查询单个用户的杠杆信息</p>
    * @author:         Zhang Xiaofang
    * @param:    @param customerId
    * @param:    @param userCode
    * @param:    @param currencyType
    * @param:    @param website
    * @param:    @param lendCoin
    * @param:    @return
    * @return: java.util.List<ExDmLend>
    * @Date :          2016年8月25日 下午1:31:14
    * @throws:
    */
    public List<Lend>   list(Long customerId, String userCode, String currencyType, String website, String lendCoin);
    public List<Lend> find(Map<String, String> map);
    public List<LendIntent> findintent(Map<String, String> map);
    //=================  用一个基础币代替人民币start============================//

	/**
	 * 融基础币:净资产，已借资产，还能借多少
	 * @param customerId
	 * @return
	 */
	public  Map<String, String> getLengCoinCodeCenter(Long customerId, String lendcoinCode);

	public  Map<String,String> getCoinCodeForRmb();

	/**
	 *
	 * <p>
	 * 申请变更杠杆倍数
	 * </p>
	 *
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月19日 下午4:42:17
	 * @throws:
	 */
	public String[] lendTimesApplyAdd(LendTimes LendTimes);

    public FrontPage lendTimesApplyList(Map<String, String> map);

    public List<LendCanCoinCode>getLendCanCoinCode();

    /**
     * 还款记录
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param filter
     * @param:    @return
     * @return: PageResult
     * @Date :          2016年6月27日 下午6:51:45
     * @throws:
     */
	public List<LendIntent> fildIntentlist(Long lendId);

}
