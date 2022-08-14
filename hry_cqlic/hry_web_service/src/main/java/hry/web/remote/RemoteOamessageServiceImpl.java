package hry.web.remote;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.rabbitmq.client.LongString;
import hry.bean.JsonResult;
import hry.manage.remote.RemoteOamessageService;
import hry.manage.remote.model.Oamessage;
import hry.manage.remote.model.base.FrontPage;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.web.app.model.AppMessage;
import hry.web.app.model.MessageAsCustomer;
import hry.web.message.service.AppMessageService;
import hry.web.message.service.MessageAsCustomerService;

/**
 * 站内信消息
 * @author tzw
 *
 * 2017年7月19日
 */
public class RemoteOamessageServiceImpl implements RemoteOamessageService{

	@Resource
	private MessageAsCustomerService messageAsCustomerService;
	@Resource
	private AppMessageService appMessageService;
	
	/**
	 * 获取当前登录用户所有站内信
	 * @param params
	 * @return
	 * 2017年7月19日
	 * tzw
	 */
	@Override
	public FrontPage findOamessage(Map<String, String> params) {
		
		return messageAsCustomerService.findOamessage(params);
	}

	
	/**
	 * 根据id获取站内信并且设置已读
	 * @param sid
	 * @return
	 * 2017年7月21日
	 * tzw
	 */
	@Override
	public Oamessage read(Long sid) {
		Oamessage oamessage = new Oamessage();
		MessageAsCustomer message = messageAsCustomerService.get(sid);
		//设置消息为已读
		if (message.getState() == 1) {
			message.setState(2);
			message.setReadDate(new Date());
			messageAsCustomerService.update(message);
		}
		//获取消息详情
		if (message.getMessageId() != null) {
			AppMessage appMessage = appMessageService.get(message.getMessageId());
			if (appMessage != null) {
				oamessage.setContent(appMessage.getContent());
				oamessage.setSendDate(appMessage.getSendDate());
				oamessage.setSortTitle(oamessage.getSortTitle());
				oamessage.setTitle(appMessage.getTitle());
			}

		}
		return oamessage;
	}

	@Override
	public JsonResult update(Long customerId, String type, String ids) {
		if (StringUtil.isNull(ids)) {
			String[] idList = ids.split(",");
			for (String id : idList) {
				QueryFilter filter = new QueryFilter(MessageAsCustomer.class);
				filter.addFilter("customerId= ", customerId);
				filter.addFilter("id=", Long.parseLong(id));
				MessageAsCustomer message = messageAsCustomerService.get(filter);
				if (message != null) {
					if ("1".equals(type)) {
						message.setState(2); // 已读
						message.setReadDate(new Date());
					} else {
						message.setState(3); // 删除
						message.setDeleteDate(new Date());
					}
					messageAsCustomerService.update(message);
				}
			}
			return new JsonResult(true);
		} else {
			return new JsonResult(false);
		}
	}

	@Override
	public JsonResult count(Long customerId) {
		QueryFilter filter = new QueryFilter(MessageAsCustomer.class);
		filter.addFilter("customerId= ", customerId);
		filter.addFilter("state= ", 1);
		List<MessageAsCustomer> list = messageAsCustomerService.find(filter);
		int count = 0;
		if (list != null) {
			count = list.size();
		}
		return new JsonResult(true).setObj(count);
	}

	/**
	 * 根据id获取消息(用户操作时校验是否本人操作)
	 * @param sid
	 * @return
	 * 2017年7月21日
	 * tzw
	 */
	@Override
	public Oamessage get(Long sid) {
		Oamessage oamessage = new Oamessage();
		MessageAsCustomer message = messageAsCustomerService.get(sid);
		if (message != null) {
			oamessage.setCustomerId(message.getCustomerId());
			oamessage.setCustomerName(message.getCustomerName());
			oamessage.setState(message.getState());
		}
		//获取消息详情
		if (message.getMessageId() != null) {
			AppMessage appMessage = appMessageService.get(message.getMessageId());
			if (appMessage != null) {
				oamessage.setContent(appMessage.getContent());
				oamessage.setSendDate(appMessage.getSendDate());
				oamessage.setSortTitle(oamessage.getSortTitle());
				oamessage.setTitle(appMessage.getTitle());
			}
		}
		return oamessage;
	}
}
