/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hry.account.fund.model.AppTransaction;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.manage.remote.model.base.FrontPage;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
public interface AppTransactionService extends BaseService<AppTransaction, Long>{

	/**
	 * <p>确认充值</p>
	 * @author:         Liu Shilei
	 * @param:    @param request
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2016年4月6日 上午10:12:08   
	 * @throws:
	 */
	boolean confirm(Long id, String userName);

	/**
	 * <p>确认提现</p>
	 * @author:         Liu Shilei
	 * @param:    @param valueOf
	 * @param:    @return
	 * @return: boolean 
	 * @throws Exception 
	 * @Date :          2016年4月6日 下午3:30:12   
	 * @throws:
	 */
	boolean confirmwithdraw(Long id, String userName);
	
	/**
	 * 确认提现
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月19日 下午4:15:00
	 */
	boolean readyWithdraw(Long id);

	/**
	 * 通过订单id 给我方账号添加或减少money
	 * 返回true/false
	 */
	boolean inOurFavour(Long id, String auditor);
	
	
	/**
	 * 资金充值提现报表分页方法
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param filter
	 * @param:    @return
	 * @return: PageResult 
	 * @Date :          2016年8月18日 下午6:14:13   
	 * @throws:
	 */
    PageResult  findPageBySql(QueryFilter filter);
    
    /**
     * 
     * <p> TODO</p>
     * @author:         Shangxl
     * @param:    @param filter
     * @param:    @return
     * @return: PageResult 
     * @Date :          2017年7月10日 上午9:38:29   
     * @throws:
     */
    PageResult  listPageBySql(QueryFilter filter);
    
	

    
    BigDecimal    countByDate(String[] type, String beginDate, String endDate, String[] status, String userName);

	/**
	 * 撤销方法
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param valueOf
	 * @param:    @param name
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2016年10月31日 下午6:33:26   
	 * @throws:
	 */
	boolean undo(Long id, String name);
	
	/**
	 * 定时任务去查询闪付接口的提现订单
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    
	 * @return: void 
	 * @Date :          2016年12月8日 下午5:51:09   
	 * @throws:
	 */
	void queryShanfuWithdrawOrder();
	
	FrontPage findTransaction(Map<String, String> params);
	
	/**
	 * 人民币提现驳回
	 * @param appTransaction
	 * @return
	 */
	public boolean withdrawReject(AppTransaction appTransaction);
	
	/**
	 * 提币驳回
	 * @param exDmTransaction
	 * @return
	 */
	public boolean dmWithdrawReject(ExDmTransaction exDmTransaction);

}
