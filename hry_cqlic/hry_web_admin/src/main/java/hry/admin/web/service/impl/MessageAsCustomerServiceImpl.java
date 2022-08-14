/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年5月30日 下午5:41:43
 */
package hry.admin.web.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.web.model.MessageAsCustomer;
import hry.admin.web.service.MessageAsCustomerService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年5月30日 下午5:41:43
 */
@Service("messageAsCustomerService")
public class MessageAsCustomerServiceImpl extends BaseServiceImpl<MessageAsCustomer, Long> implements MessageAsCustomerService {

	@Resource(name = "messageAsCustomerDao")
	@Override
	public void setDao(BaseDao<MessageAsCustomer, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private AppCustomerService appCustomerService;


	// 给当前所有用户添加一条消息
	@Override
	public void sendAll(Long messagId) {
		QueryFilter filter = new QueryFilter(AppCustomer.class);
		filter.addFilter("isDelete!=", 1);
		List<AppCustomer> list = appCustomerService.find(filter);

		for (AppCustomer customer : list) {
			Long id = customer.getId();
			String name = customer.getUsername();
			MessageAsCustomer messageAsCustomer = new MessageAsCustomer();
			messageAsCustomer.setCustomerId(id);
			messageAsCustomer.setCustomerName(name);
			messageAsCustomer.setMessageId(messagId);
			super.save(messageAsCustomer);
		}
	}

	// 给部分人发送消息
	@Override
	public void sendPartial(Long messagId, String[] userNames) {
		QueryFilter filter = null ;
		for (int i = 0; i < userNames.length; i++) {
			filter = new QueryFilter(AppCustomer.class);
			filter.addFilter("id=", userNames[i]);
			AppCustomer customer = appCustomerService.get(filter);
			if (customer != null) {
				String name = customer.getUsername();

				MessageAsCustomer messageAsCustomer = new MessageAsCustomer();
				messageAsCustomer.setCustomerName(name);
				messageAsCustomer.setCustomerId(customer.getId());
				messageAsCustomer.setMessageId(messagId);
				// 给一个人保存一条消息
				super.save(messageAsCustomer);
			}
		}

	}

	@Override
	public void sendPartialName(Long messagId, String receiveUserNames) {

		String[] str = receiveUserNames.split(",");
		for (int i = 0; i < str.length; i++) {
			QueryFilter qf = new QueryFilter(AppCustomer.class);
			qf.addFilter("userName=", str[i]);
			AppCustomer customer = appCustomerService.get(qf);
			if (null != customer) {
				String name = customer.getUsername();
				MessageAsCustomer messageAsCustomer = new MessageAsCustomer();
				messageAsCustomer.setCustomerName(name);
				messageAsCustomer.setCustomerId(customer.getId());
				messageAsCustomer.setMessageId(messagId);
				// 给一个人保存一条消息
				super.save(messageAsCustomer);
			}
		}
	}

	public static Page getPage(Map<String, String> map) {
		Page page = null;
		Integer offset = 0;
		Integer limit = 10;
		if (!StringUtils.isEmpty(map.get("offset"))) {
			offset = Integer.valueOf(map.get("offset"));
		}
		if (!StringUtils.isEmpty(map.get("limit"))) {
			limit = Integer.valueOf(map.get("limit"));
		}
		if (limit == -1) {
			page = PageHelper.startPage(offset / limit + 1, 0);
		} else {
			page = PageHelper.startPage(offset / limit + 1, limit);
		}
		return page;
	}

	@Override
	public void saveMessageAsCustomer(AppCustomer customer, Long messagId) {
		//消息通知绑定用户
		MessageAsCustomer messageAsCustomer = new MessageAsCustomer();
		messageAsCustomer.setMessageId(messagId);
		messageAsCustomer.setCustomerId(customer.getId());
		messageAsCustomer.setState(1);	// 是否以查看 1未读   2已读  3删除
		messageAsCustomer.setCustomerName(customer.getUsername());
		super.save(messageAsCustomer);	
		
	}
}