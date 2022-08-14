/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.exchange.lend.service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.manage.remote.model.base.FrontPage;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExDmLendService extends BaseService<ExDmLend, Long> {
	/**
	 * 
	 * <p> 借款增加方法</p>
	 * @author:         Gao Mimi
	 * @param:    @param exDmLend
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年7月21日 下午2:53:12   
	 * @throws:
	 */
	public String[] saveExDmLend(ExDmLend exDmLend);
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
	 * <p> 需要检查时否需要平账的客户</p>
	 * @author:         Gao Mimi
	 * @param:    @param currencyType
	 * @param:    @return
	 * @return: List<ExDmLend> 
	 * @Date :          2016年6月28日 下午2:46:39   
	 * @throws:
	 */
	public List<ExDmLend> getMayPingCustomer(Map<String, String> map);
	/**
	 * 
	 * <p> 定时计数借款利息总和</p>
	 * @author:         Gao Mimi
	 * @param:    
	 * @return: void 
	 * @Date :          2016年7月21日 下午2:30:57   
	 * @throws:
	 */
	public void calculateLendInterest();
	/**
	 * 
	 * <p> 得到客户的在一共借了多少，还剩多少本金没还，还剩多少利息多少</p>
	 * @author:         Gao Mimi
	 * @param:    @param currencyType
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: List<ExDmLend> 
	 * @Date :          2016年7月22日 下午12:09:00   
	 * @throws:
	 */
	public ExDmLend getLendBycustmer(String lendcoin, Long customerId, String currencyType, String website);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年8月8日 下午6:32:07   
	 * @throws:
	 */
	public PageResult see(QueryFilter filter);
	/***
	 * 
	 * <p> 得到在借金额</p>
	 * @author:         Gao Mimi
	 * @param:    @return
	 * @return: List<ExDmLend> 
	 * @Date :          2016年9月9日 上午11:25:32   
	 * @throws:
	 */
	public BigDecimal  getLending(String lendCoinType, String lendCoin);
	/**
	 * 
	 * <p> TODO</p>
	 * @author:          Gao Mimi
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年8月8日 下午6:32:07   
	 * @throws:
	 */
	public FrontPage findFrontPage(Map<String, String> map);
}
