/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.change.remote.lend;


import hry.bean.PageResult;
import hry.util.RemoteQueryFilter;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;

import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface RemoteExDmLendService  {
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
	public String[] saveExDmLend(ExDmLend exDmLend);
	
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
     * 
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param filter
     * @param:    @return
     * @return: PageResult 
     * @Date :          2016年6月27日 下午6:51:45   
     * @throws:
     */
	public PageResult listPage(RemoteQueryFilter filter);
    /**
     * 
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param filter
     * @param:    @return
     * @return: PageResult 
     * @Date :          2016年6月27日 下午6:51:45   
     * @throws:
     */
	public PageResult listIntentPage(RemoteQueryFilter filter);
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
	public String repaymentInfo(Long id);
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
     * <p>客户是否进入了平账流程</p>
     * @author:         Gao Mimi
     * @param:    @param customerId
     * @param:    @return
     * @return: Boolean 
     * @Date :          2016年7月22日 下午6:03:26   
     * @throws:
     */
    public Boolean isPinging(Long customerId, String userCode, String currencyType, String website);
    
    
    
    
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
    public java.util.List<ExDmLend>   list(Long customerId, String userCode, String currencyType, String website, String lendCoin);
    public java.util.List<ExDmLend> find(RemoteQueryFilter remoteQueryFilter);
    public java.util.List<ExDmLendIntent> findintent(RemoteQueryFilter remoteQueryFilter);
}
