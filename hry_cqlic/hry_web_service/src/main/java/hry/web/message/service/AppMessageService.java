/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年5月30日 下午5:30:12
 */
package hry.web.message.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.message.model.MessageType;
import hry.util.QueryFilter;
import hry.customer.user.model.AppCustomer;
import hry.manage.remote.MsgTypeEnum;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.app.model.AppMessage;
import hry.web.app.model.AppMessageCategory;

import java.util.Map;

/**
 * 
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午5:30:12
 */
public interface AppMessageService extends BaseService<AppMessage, Long> {
	
	public PageResult selectMessageListVoByList(QueryFilter filter, int state);

	/**
	 * 
	 * 修改并保存消息 
	 * 
	 * 如果选择的发送人不为空 将默认是添加用户 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月17日 下午3:43:27
	 */
	public JsonResult updateMessage(AppMessage appMessage, String[] userName);
	
	/**
	 * 保存消息方法
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月17日 下午3:48:55
	 */
	public JsonResult saveMessage(AppMessage appMessage, String[] userNames);
	public JsonResult saveMessageName(AppMessage appMessage, String receiveUserNames);
	
	/**
	 * 删除多个消息 
	 */
	public JsonResult removeMessage(Long[] ids);
	
	/**
	 * 
	 * 保存一条消息
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月17日 下午4:45:32
	 */
	public JsonResult sendMessage(Long[] ids);

	public PageResult selectMessageVoByPage(QueryFilter filter);

	/**
	 * 根据提币模板信息修改
	 * @param customer
	 * @param appMessageCategory
	 * @param appMessageTemplate 
	 * @param flag 提币成功还是失败
	 * @return
	 */
	public void saveMessage(AppCustomer customer, AppMessageCategory appMessageCategory, AppMessageTemplate appMessageTemplate, Boolean flag);

	/**
	 * 根据工单受理模板信息修改
	 * @param customer
	 * @param appMessageCategory
	 * @param appMessageTemplate 
	 * @param flag 提币成功还是失败
	 * @return
	 */
	public void saveWorkOrderMessage(AppCustomer appCustomer, AppMessageCategory appMessageCategory,
                                     AppMessageTemplate appMessageTemplate, String workOrderNo);

	/**
	 * C2C交易订单信息发送前台,内容根据模板更改
	 * @param customer
	 * @param appMessageCategory
	 * @param appMessageTemplate
	 */
	public void saveC2CMessage(AppCustomer customer, AppMessageCategory appMessageCategory,
                               AppMessageTemplate appMessageTemplate, String transactionNum);

	JsonResult sysSendMsg(AppCustomer customer,MsgTypeEnum msgTypeEnum);

	JsonResult sysSendMsg(AppCustomer customer, Map<String,String> param, String triggerPoint);
}


