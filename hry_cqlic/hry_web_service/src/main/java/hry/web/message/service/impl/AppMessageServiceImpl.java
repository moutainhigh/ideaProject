/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0
 * @Date:      2016年5月30日 下午5:41:43
 */
package hry.web.message.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.customer.user.model.AppCustomer;
import hry.manage.remote.MsgTypeEnum;
import hry.message.model.MessageType;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.sys.ContextUtil;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.app.model.AppMessage;
import hry.web.app.model.AppMessageCategory;
import hry.web.app.model.MessageAsCustomer;
import hry.web.app.model.vo.MessageListVo;
import hry.web.message.dao.AppMessageDao;
import hry.web.message.service.AppMessageCategoryService;
import hry.web.message.service.AppMessageService;
import hry.web.message.service.MessageAsCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


/**
 *
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午5:41:43
 */
@Service("appMessageService")
public class AppMessageServiceImpl extends BaseServiceImpl<AppMessage, Long> implements AppMessageService{

	@Resource(name = "appMessageDao")
	@Override
	public void setDao(BaseDao<AppMessage, Long> dao) {
		super.dao = dao;
	}

	@Resource(name = "messageAsCustomerService")
	public MessageAsCustomerService messageAsCustomerService;

	@Resource
	private AppMessageCategoryService appMessageCategoryService;



	@Override
	public PageResult selectMessageListVoByList(QueryFilter filter,int state) {

		PageResult pageResult = new PageResult();
		Page<MessageListVo> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// 查询数据
		((AppMessageDao)dao).findMessageListVoList(state);

		pageResult.setRows(page.getResult());
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult ;
	}


	/**
	 *
	 * 修改并保存消息
	 *
	 * 如果选择的发送人不为空 将默认是添加用户
	 *
	 */
	@Override
	public JsonResult updateMessage(AppMessage appMessage,String[] ids){

		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);

		/*super.update(appMessage);

		if(ids.length>0){

			if(appMessage.getIsAll() == 1){
				// 删除之前保存的用户
				for(int i = 0;i<ids.length;i++){
					messageAsCustomerService.delete(ids[i]);
				}
				// 重新给所有人发
				messageAsCustomerService.sendAll(appMessage.getId());
				jsonResult.setMsg("修改成功");
				return jsonResult;

			}else{

				QueryFilter filter = new QueryFilter(MessageAsCustomer.class);
				filter.addFilter("messageId=", appMessage.getId());
				List<MessageAsCustomer> list = messageAsCustomerService.find(filter);

				if(list.size()>0){
					// 如果不是群发那就只需要删除部分重复的用户就够了
					for(MessageAsCustomer messageAsCustomer : list){
						Long id = messageAsCustomer.getId();
						for(int i=0; i<ids.length;i++ ){
							if(ids[i]==id){
								ids[i] = 0l ;
							}
						}
					}
				}
			}
			messageAsCustomerService.sendPartial(appMessage.getId(), ids);
		}
		jsonResult.setMsg("修改成功");*/
		return jsonResult;
	}


	/**
	 * 保存消息
	 */
	@Override
	public JsonResult saveMessage(AppMessage appMessage, String[] userNames) {

		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		super.save(appMessage);
		// 发送消息
		Long id = appMessage.getId();
		Integer all = appMessage.getIsAll();
		if(all==1){
			//发送全部走线程一条条发送
			ThreadPool.exe(new MessageToAllRunnable(id));
		}else if(all==0&&userNames.length>0){
			messageAsCustomerService.sendPartial(id, userNames);
		}
		jsonResult.setMsg("保存成功");
		return jsonResult;
	}
	/**
	 * 保存消息
	 */
	@Override
	public JsonResult saveMessageName(AppMessage appMessage,String receiveUserNames) {

		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		super.save(appMessage);
		// 发送消息
		Long id = appMessage.getId();
		Integer all = appMessage.getIsAll();
		if(all == 1){
			messageAsCustomerService.sendAll(id);
		}if(all == 0){
			if(null!=receiveUserNames&&!"".equals(receiveUserNames)){
				messageAsCustomerService.sendPartialName(id, receiveUserNames);
			}
		}
		jsonResult.setMsg("保存成功");
		return jsonResult;
	}

	/**
	 * 删除多个消息
	 */
	@Override
	public JsonResult removeMessage(Long[] ids) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		if(ids.length>0){
			for(Long id: ids) {
				AppMessage message = super.get(id);
				message.setIsSend(2);
				super.update(message);
			}
		}
		jsonResult.setMsg("删除成功");
		return jsonResult;
	}


	/**
	 * 发送一条或多条消息
	 *
	 */
	@Override
	public JsonResult sendMessage(Long[] ids) {

		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);

		if(ids.length>0){
			for(int i = 0; i<ids.length;i++){
				AppMessage message = super.get(ids[i]);
				QueryFilter filter = new QueryFilter(MessageAsCustomer.class);
				filter.addFilter("messageId=", ids[i]);


				List<MessageAsCustomer> list = messageAsCustomerService.find(filter);
				if(list.size()>0){
					message.setIsSend(1);
					message.setSendDate(new Date());
					message.setSendUserName(ContextUtil.getCurrentUserName());
					super.update(message);
					jsonResult.setMsg("消息发送成功");
				}
			}
			jsonResult.setMsg("所有消息发送成功");
			return jsonResult;

		}else{
			jsonResult.setSuccess(false);
			jsonResult.setMsg("所选的消息为0");
			return jsonResult;
		}
	}



// ---------------------------------------------------------------------------------



	@Override
	@SuppressWarnings("rawtypes")
	public PageResult selectMessageVoByPage(QueryFilter filter){
		PageResult page = this.selectMessageByPage(filter);
		List list = page.getRows();
		if(list.size()>0){
			for(int i = 0; i<list.size();i++){
				MessageListVo messageVo = (MessageListVo)list.get(i);
				List<MessageAsCustomer> list2 = ((AppMessageDao)dao).selectMessageAsCustromer(messageVo.getId());
				messageVo.setList(list2);
			}
		}
		return page;
	}







	public PageResult selectMessageByPage(QueryFilter filter){
		Map<String,Object> map = new HashMap<String,Object>();
		PageResult pageResult = new PageResult();
		Page<MessageListVo> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// 查询数据
		String messageType = filter.getRequest().getParameter("messageType_EQ");
		if(!StringUtils.isEmpty(messageType)){
			map.put("messageType", messageType);
		}
		String title = filter.getRequest().getParameter("title_like");
		if(!StringUtils.isEmpty(title)){
			map.put("title", title);
		}
		String categoryName = filter.getRequest().getParameter("name");
		if(!StringUtils.isEmpty(categoryName)){
			map.put("categoryName", categoryName);
		}
		((AppMessageDao)dao).selectMessageVo(map);


		pageResult.setRows(page.getResult());
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult ;
	}


	/**
	 * 发送提币成功/失败的消息
	 */
	@Override
	public void saveMessage(AppCustomer customer,AppMessageCategory appMessageCategory, AppMessageTemplate appMessageTemplate,
							Boolean flag) {
		AppMessage appMessage = new AppMessage();
		if(flag){	//提币成功
			appMessage.setContent(appMessageTemplate.getContent().replaceAll("成功/失败", "成功"));
			appMessage.setTitle(appMessageTemplate.getTitle().replaceAll("成功/失败", "成功"));
			appMessage.setSortTitle("成功！");
		}else{
			appMessage.setContent(appMessageTemplate.getContent().replace("成功/失败", "被驳回"));
			appMessage.setTitle(appMessageTemplate.getTitle().replaceAll("成功/失败", "失败"));
			appMessage.setSortTitle("失败！");
		}
		setAppMessage(customer, appMessageCategory, appMessage);
	}


	@Override
	public void saveWorkOrderMessage(AppCustomer customer, AppMessageCategory appMessageCategory,
									 AppMessageTemplate appMessageTemplate, String workOrderNo) {
		AppMessage appMessage = new AppMessage();

		appMessage.setContent(appMessageTemplate.getContent().replaceAll("workNum", workOrderNo));
		appMessage.setTitle(appMessageTemplate.getTitle());
		appMessage.setSortTitle("受理成功！");

		setAppMessage(customer, appMessageCategory, appMessage);

	}


	@Override
	public void saveC2CMessage(AppCustomer customer, AppMessageCategory appMessageCategory,
							   AppMessageTemplate appMessageTemplate,String transactionNum) {
		AppMessage appMessage = new AppMessage();

		appMessage.setContent(appMessageTemplate.getContent().replaceAll("entrustNum", transactionNum));
		appMessage.setTitle(appMessageTemplate.getTitle());
		appMessage.setSortTitle("交易成功！");

		setAppMessage(customer, appMessageCategory, appMessage);

	}



	private void setAppMessage(AppCustomer customer, AppMessageCategory appMessageCategory, AppMessage appMessage) {
		appMessage.setCategoryName(appMessageCategory.getName());	//设置消息类型
		appMessage.setCategoryId(appMessageCategory.getId()+"");	//设置消息类别id
		appMessage.setIsSend(1);	//是否已发送(0 : 表示没有    1  表示已发送)
		appMessage.setSendDate(new Date());
		super.save(appMessage);
		Long id = appMessage.getId();
		messageAsCustomerService.saveMessageAsCustomer(customer, id);
	}


	@Override
	public JsonResult sysSendMsg(AppCustomer customer, Map<String,String> param,String triggerPoint){
		ThreadPool.exe(new SendMessage(customer,param,triggerPoint));
		return new JsonResult().setSuccess(true);
	}
	@Override
	public JsonResult sysSendMsg(AppCustomer customer, MsgTypeEnum msgTypeEnum){
		ThreadPool.exe(new SendMessage(customer,msgTypeEnum));
		return new JsonResult().setSuccess(true);
	}
	//异步发送站内信
	private class SendMessage extends Thread {
		private AppCustomer customer;
		private MsgTypeEnum msgTypeEnum;
		private Map<String,String> param;
		private String triggerPoint;

		SendMessage(AppCustomer appCustomer,MsgTypeEnum msgTypeEnum){
			this.customer=appCustomer;
			this.msgTypeEnum = msgTypeEnum;
		}
		SendMessage(AppCustomer appCustomer,Map param,String triggerPoint){
			this.customer=appCustomer;
			this.param = param;
			this.triggerPoint = triggerPoint;
		}
		public Map getParam() {
			return param;
		}
		public void setParam(Map param) {
			this.param = param;
		}

		@Override
		public void run() {


			Integer trigger = null ;
			if(triggerPoint!=null){
				trigger=Integer.valueOf(triggerPoint);
			}else{
				switch (msgTypeEnum){
					case REGIST: trigger=1; break;
					case MODIFYPWD: trigger=2; break;
					case REALNAME: trigger=3; break;
					case POSTMONEY: trigger=4; break;
					case GETMONEY: trigger =5; break;
					case C2C: trigger =6; break;
					case OTC: trigger =7; break;
					case WORKORDER: trigger = 8; break;
					default: trigger=1;
				}
			}
			QueryFilter queryFilter1 = new QueryFilter(AppMessageCategory.class);
			queryFilter1.addFilter("triggerPoint=",trigger);
			queryFilter1.addFilter("isOpen=",1);//打开状态
			if(customer.getCommonLanguage()!=null&&!"".equals(customer.getCommonLanguage())){
				queryFilter1.addFilter("messageCategory=",customer.getCommonLanguage());
			}
			AppMessageCategory appMessageCategory = appMessageCategoryService.get(queryFilter1);
			if(appMessageCategory == null){
				return ;
			}
			AppMessageService appMessageService = SpringUtil.getBean("appMessageService");
			AppMessage appMessage = new AppMessage();
			appMessage.setSendDate(new Date());
			appMessage.setSendUserName("System");
			appMessage.setIsSend(1);
			appMessage.setIsAuto(1);
			appMessage.setMessageCategory(customer.getCommonLanguage());
			String content=appMessageCategory.getDescribes();
			if(param!=null){
				Iterator<Map.Entry<String, String>> iterator = param.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, String> entry = iterator.next();
					content=content.replace(entry.getKey(),entry.getValue());
				}
			}
			appMessage.setContent(content);
			appMessage.setTitle(appMessageCategory.getName());
			try
			{
				appMessageService.save(appMessage);
			}catch (Exception e){
				e.printStackTrace();
			}

			messageAsCustomerService.sendPartialName(appMessage.getId(),customer.getUserName());

		}
	}



}