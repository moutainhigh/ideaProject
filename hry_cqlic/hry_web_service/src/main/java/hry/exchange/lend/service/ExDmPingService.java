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
public interface ExDmPingService extends BaseService<ExDmPing, Long> {
	public ExDmPing create(Long customerId, String userCode, String currencyType, String website);
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
    
    public Boolean isLend();
    
    public void pingRepayflow(Long customerId);
    public List<ExDmPing> getPingBystatus(Integer status, String currencyType, String website);
    
    public void cancelCustAllExEntrust(Long customerId);
    public void endPing(ExDmPing l);
    public String[] checkPing(Long customerId);
    public String[] checkLending(Long customerId);
}
