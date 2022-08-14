/**
 * Copyright:
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-07-05 10:21:55
 */
package hry.admin.web.controller;


import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.AppMessage;
import hry.admin.web.model.AppMessageCategory;
import hry.admin.web.model.MessageAsCustomer;
import hry.admin.web.service.AppMessageCategoryService;
import hry.admin.web.service.AppMessageService;
import hry.admin.web.service.MessageAsCustomerService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.JsoupUtil;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liuchenghui
 * @version:     V1.0
 * @Date:        2018-07-05 10:21:55
 */
@Controller
@RequestMapping("/web/appmessage")
public class AppMessageController extends BaseController<AppMessage, Long> {
	private static Logger logger = Logger.getLogger(AppMessageController.class);
	@Resource(name = "appMessageService")
	@Override
	public void setService(BaseService<AppMessage, Long> service) {
		super.service = service;
	}

	@Resource
	private AppMessageCategoryService appMessageCategoryService;

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	private AppPersonInfoService appPersonInfoService;

	@Resource
	private MessageAsCustomerService messageAsCustomerService;

	@Resource
	private AppDicService appDicService;

	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppMessage appMessage = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appmessagesee");
		mav.addObject("model", appMessage);
		return mav;
	}

	/**
	 * 跳转到站内信草稿箱
	 * @return
	 */
	@RequestMapping(value="/toDraftBox/{langCode}")
	public ModelAndView toDraftBox(@PathVariable String langCode){
		ModelAndView mav = new ModelAndView("/admin/web/appmessagesee");
		// 获取系统语种信息
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		filter.setOrderby("created");
		List<AppDic> langList = appDicService.find(filter);
		mav.addObject("langList", langList);
		if ("none".equals(langCode)) {
			if (langList != null && langList.size() > 0) {
				AppDic dic = langList.get(0);
				langCode = dic.getValue();
			}
		}
		mav.addObject("langCode", langCode);
		return mav;
	}

	// type等于1的时候是保存并发送 type等于2的时候只保存
	@MethodName(name = "增加一条消息")
	@RequestMapping("/add/{type}")
	@ResponseBody
	public JsonResult add(@PathVariable int type, AppMessage appMessage, HttpServletRequest request) {
		BaseManageUser currentUser = ContextUtil.getCurrentUser();
        String userNames = request.getParameter("receiveUserIdList");
        String content = request.getParameter("editorValue");

		appMessage.setOperator(currentUser.getUsername());
		appMessage.setContent(JsoupUtil.clean(content));

		if (type == 1) {
			// 保存发送人
			appMessage.setSendDate(new Date());
			appMessage.setSendUserName(currentUser.getUsername());
		}
		appMessage.setIsSend(type);

		AppMessageService messageService = (AppMessageService) service;
		JsonResult result = messageService.saveMessage(appMessage, userNames);
		return result;
	}

	class MyList extends ArrayList{
		@Override
		public String toString(){
			return null;
		}
	}

	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppMessage appMessage = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appmessagemodify");
		mav.addObject("model", appMessage);

		QueryFilter filter = new QueryFilter(MessageAsCustomer.class);
		filter.addFilter("messageId=", id);
		List<MessageAsCustomer> list = messageAsCustomerService.find(filter);
		mav.addObject("receiverSize",list.size());
		List<String> ids = new ArrayList<>();
		if(list !=null && list.size()>0){
			for (int i=0; i< list.size();i++){
				ids.add(list.get(i).getCustomerId().toString());
			}
			String idsStr = ids.toString();
			mav.addObject("ids",ids.toString().substring(1,idsStr.length()-1));
			logger.error(ids.toString().substring(1,idsStr.length()-1));
		}


		return mav;
	}

	@RequestMapping(value="/modifysee2/{id}")
	public ModelAndView modifysee2(@PathVariable Long id){
		AppMessage appMessage = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appmessagemodify2");
		mav.addObject("model", appMessage);

		QueryFilter filter = new QueryFilter(MessageAsCustomer.class);
		filter.addFilter("messageId=", id);
		List<MessageAsCustomer> list = messageAsCustomerService.find(filter);
		mav.addObject("receiverSize",list.size());
		List<String> ids = new ArrayList<>();
		if(list !=null && list.size()>0){
			for (int i=0; i< list.size();i++){
				ids.add(list.get(i).getCustomerId().toString());
			}
			String idsStr = ids.toString();
			mav.addObject("ids",ids.toString().substring(1,idsStr.length()-1));
			logger.error(ids.toString().substring(1,idsStr.length()-1));
		}
		return mav;
	}



	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppMessage appMessage){
		String editorValue=request.getParameter("editorValue");
		appMessage.setContent(JsoupUtil.clean(editorValue));
		return super.update(appMessage);
	}


	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}

	@MethodName(name = "分页查询所有的已发送的消息")
	@RequestMapping(value="/listMessageVo")
	@ResponseBody
	public PageResult listMessageVo(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppMessage.class, request);

		AppMessageService appMessageService = (AppMessageService) service;
		PageResult pageResult = appMessageService.selectMessageVoByPage(filter);

		return pageResult;
	}

	@MethodName(name = "级联查询消息类型")
	@RequestMapping("/ajaxSelectType/{messageCategory}")
	@ResponseBody
	public List<AppMessageCategory> ajaxSelectType(HttpServletRequest request, @PathVariable String messageCategory) {
		QueryFilter filter = new QueryFilter(AppMessageCategory.class, request);
		filter.addFilter("state!=", 0);
		filter.addFilter("isOpen=", 1);
		filter.addFilter("messageCategory=", messageCategory);
		List<AppMessageCategory> list = appMessageCategoryService.find(filter);
		return list;
	}

	@MethodName(name = "加载一条消息")
	@RequestMapping("/messageList/{state}")
	@ResponseBody
	public PageResult messageList(@PathVariable int state, HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(AppMessage.class, request);
		String categoryName = request.getParameter("categoryName");
		if(categoryName != null && !"".equals(categoryName)){
			filter.addFilter("categoryName_like", categoryName);
		}
		String languageDic = request.getParameter("languageDic");
		if(languageDic != null && !"".equals(languageDic)){
			filter.addFilter("messageCategory=", languageDic);
		}
		String content_like = request.getParameter("content_like");
		if(content_like !=null && !"".equals(content_like)){
			filter.addFilter("content_like","%"+content_like+"%");
		}
		String title_like = request.getParameter("content_like");
		if(title_like !=null && !"".equals(title_like)){
			filter.addFilter("title_like","%"+title_like+"%");
		}

		filter.addFilter("isSend=", state);
		PageResult page = super.findPage(filter);
		List<AppMessage> lisMsg = page.getRows();
		if(lisMsg!=null)
		for(AppMessage appMessage:lisMsg){
			QueryFilter filter1 = new QueryFilter(MessageAsCustomer.class);
			filter1.addFilter("messageId=", appMessage.getId());
			List<MessageAsCustomer> list = messageAsCustomerService.find(filter1);
			if(list !=null){
				appMessage.setCategoryName(list.size()+"");
			}
		}

		return page;
	}

	@MethodName(name = "发送一条消息")
	@RequestMapping("/send")
	@ResponseBody
	public JsonResult send(@RequestParam(value = "ids[]") Long[] messageIds) {
		AppMessageService appMessageService = (AppMessageService) service;
		JsonResult result = appMessageService.sendMessage(messageIds);
		return result;
	}
}
