/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:17:16
 */
package hry.account.remote;

import java.util.List;

import hry.account.fund.model.AppBankCard;
import hry.util.RemoteQueryFilter;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月1日 上午11:17:16 
 */
public interface RemoteAppBankCardService {
	
	/**
	 * 保存
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param appBankCard
	 * @return: void 
	 * @Date :          2016年4月5日 下午2:54:03   
	 * @throws:
	 */
	public void save(AppBankCard appBankCard);
	
	/**
	 * 更新
	 * <p> TODO</p>
	 * @author:         Zhang cb
	 * @param:    @param appBankCard
	 * @return: void 
	 * @Date :          2016年4月5日 下午2:54:03   
	 * @throws:
	 */
	public void update(AppBankCard appBankCard);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @return: void 
	 * @Date :          2016年4月25日 下午8:45:16   
	 * @throws:
	 */
	public List<AppBankCard> findByCustomerId(Long id);

	/**
	 * <p> 删除银行卡</p>
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @return: void 
	 * @Date :          2016年5月20日 下午5:13:21   
	 * @throws:
	 */
	public boolean delete(Long id);
	
	/**
	 * 根据ID查询
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2016年8月18日 下午3:05:36   
	 * @throws:
	 */
	public AppBankCard get(Long id);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param remoteQueryFilter
	 * @return: void 
	 * @Date :          2016年8月31日 下午6:03:05   
	 * @throws:
	 */
	public AppBankCard get(RemoteQueryFilter remoteQueryFilter);
	
}
