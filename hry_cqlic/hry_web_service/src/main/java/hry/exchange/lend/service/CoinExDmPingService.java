/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.exchange.lend.service;


import hry.core.mvc.service.base.BaseService;
import hry.exchange.lend.model.ExDmPing;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface CoinExDmPingService extends BaseService<ExDmPing, Long> {
	public ExDmPing create(Long customerId, String userCode, String currencyType, String website);
	/**
	 * 
	 * <p>平仓相关</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @return: void 
	 * @Date :          2016年5月25日 上午11:55:48   
	 * @throws:
	 */
    public void pingLendMoney(String website, String currencyType);
    /**
     * 
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param currencyType
     * @return: void 
     * @Date :          2016年5月25日 下午2:53:22   
     * @throws:
     */
    public void pingLendCoin(String currencyType, String website);
    /**
     * 
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param customerId
     * @return: void 
     * @Date :          2016年5月25日 下午1:49:06   
     * @throws:
     */
    public void pingByCustomerId(Long customerId, String userCode, String currencyType, String website);
    /**
     * 
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param customerId
     * @return: void 
     * @Date :          2016年5月25日 下午1:49:06   
     * @throws:
     */
    public Integer isPingWarehouse(Long customerId, String userCode, String currencyType, String website);
    /**
     * 
     * <p> 定时计算</p>
     * @author:         Gao Mimi
     * @param:    @param currencyType
     * @return: void 
     * @Date :          2016年6月28日 下午5:05:12   
     * @throws:
     */
    public void jobRunTimeCulPingLendMoney();
    /**
     * 
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    
     * @return: void 
     * @Date :          2016年7月26日 下午7:06:54   
     * @throws:
     */
    public void jobRunTimeCulEndPingLendMoney();
    /**
     * 
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    @param customerId
     * @param:    @param status
     * @return: void 
     * @Date :          2016年6月28日 下午6:11:29   
     * @throws:
     */
    public List<ExDmPing> getBycustomerid(Long customerId, String userCode, Integer status, String currencyType, String website);
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
}
