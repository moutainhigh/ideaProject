/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
package hry.exchange.lend;


import hry.core.mvc.service.base.BaseService;
import hry.exchange.lend.model.ExDmPing;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
public interface ExDmPingNorService extends BaseService<ExDmPing, Long> {
	
    /**
     * 
     * <p> 定时计算平仓</p>
     * @author:         Gao Mimi
     * @param:    @param currencyType
     * @return: void 
     * @Date :          2016年6月28日 下午5:05:12   
     * @throws:
     */
    public void jobRunTimeCulPingLendMoney();
    /**
     * 结束平仓
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    
     * @return: void 
     * @Date :          2016年7月26日 下午7:06:54   
     * @throws:
     */
    public void jobRunTimeCulEndPingLendMoney();
    
    /**
     *  对进入平仓流程的要定时进行还款
     * <p> TODO</p>
     * @author:         Gao Mimi
     * @param:    
     * @return: void 
     * @Date :          2016年7月26日 下午7:06:54   
     * @throws:
     */
    public void jobRunTimeRepayLendMoney();
    
    public void MqjobRunTimeCulPingLendMoney();
    public void MqjobRunTimeCulEndPingLendMoney();
    public void MqjobRunTimeRepayLendMoney(); 
}
