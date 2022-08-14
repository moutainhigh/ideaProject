/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年5月30日 下午5:30:12
 */
package hry.admin.web.service;

import hry.admin.customer.model.AppCustomer;
import hry.admin.web.model.MessageAsCustomer;
import hry.core.mvc.service.base.BaseService;

/**
 * 
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午5:30:12
 */
public interface MessageAsCustomerService extends BaseService<MessageAsCustomer, Long> {
	
	public void sendAll (Long messagId);

	public void sendPartial (Long messagId, String[] userNames);
	public void sendPartialName (Long messagId, String receiveUserNames);

	public void saveMessageAsCustomer (AppCustomer customer, Long messagId);


}


