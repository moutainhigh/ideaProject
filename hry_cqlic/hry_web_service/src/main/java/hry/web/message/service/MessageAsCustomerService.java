/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年5月30日 下午5:30:12
 */
package hry.web.message.service;

import hry.core.mvc.service.base.BaseService;
import hry.customer.user.model.AppCustomer;
import hry.manage.remote.model.base.FrontPage;
import hry.web.app.model.MessageAsCustomer;

import java.util.Map;

/**
 * 
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午5:30:12
 */
public interface MessageAsCustomerService extends BaseService<MessageAsCustomer, Long> {
	
	public void sendAll(Long messagId);

	public void sendPartial(Long messagId, String[] userNames);
	public void sendPartialName(Long messagId, String receiveUserNames);

	/**
	 * 获取当前登录用户所有站内信
	 * @param params
	 * @return
	 * 2017年7月20日
	 * tzw
	 */
	public FrontPage findOamessage(Map<String, String> params);

	public void saveMessageAsCustomer(AppCustomer customer, Long messagId);


}


