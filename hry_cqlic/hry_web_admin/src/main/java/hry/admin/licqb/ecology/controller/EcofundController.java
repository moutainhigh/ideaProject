package hry.admin.licqb.ecology.controller;

import com.alibaba.fastjson.JSON;
import hry.admin.licqb.ecology.service.EcofundService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.admin.web.service.AppMessageService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.util.QueryFilter;
import hry.admin.licqb.ecology.model.Ecofund;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-04 11:06:01 
 */
@Controller
@RequestMapping("/licqb/ecology/ecofund")
public class EcofundController extends BaseController<Ecofund, Long> {
	
	@Resource(name = "ecofundService")
	@Override
	public void setService(BaseService<Ecofund, Long> service) {
		super.service = service;
	}
	@Resource
	private MessageProducer messageProducer;


	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request){
		String id = request.getParameter("id");
		// 申请回复
		String activityReply = request.getParameter("activityReply");
		// 补充材料回复
		String againActivityReply = request.getParameter("againActivityReply");
		String activityStatus = request.getParameter("activityStatus");
		// 是否是补充材料审核拒绝 1 拒绝
		String itAgain = request.getParameter("itAgain");
		Ecofund ecofund = null;
		if (StringUtil.isNull(id)) {
			ecofund = super.service.get(Long.parseLong(id));
		}
		if (ecofund != null) {
			if (StringUtil.isNull(activityReply)) {
				ecofund.setActivityReply(activityReply);
			}
			if (StringUtil.isNull(activityStatus)) {
				ecofund.setActivityStatus(Integer.parseInt(activityStatus));
				if (StringUtil.isNull(itAgain)) {
					ecofund.setItAgain(Integer.parseInt(itAgain));
				}
			}
			if (StringUtil.isNull(againActivityReply)) {
				ecofund.setAgainActivityReply(againActivityReply);
			}
		}
		JsonResult jsonResult = null;
		if (ecofund != null) {
			jsonResult = super.update(ecofund);
			String key = null;
			// 通过
			if (ecofund.getActivityStatus() == 3 && !StringUtil.isNull(itAgain)) {
				key = MessageType.MESSAGE_LC_ECOFUND_SUCCESS.getKey();
			} else if (ecofund.getActivityStatus() == 2) {
				key = MessageType.MESSAGE_LC_ECOFUND_REFUSE.getKey();
			}
			// 发送站内信
			if (key != null) {
				Map<String, String> paramM = new HashMap<>();
				paramM.put("${orderNum}", ecofund.getOrderNum());
				RemoteMessage message=new RemoteMessage();
				message.setParam(paramM);
				message.setMsgKey(key);//消息类型 模板KEY
				message.setMsgType("1");// 1.站内信，2.短信,
				message.setUserId(ecofund.getCustomerId().toString());
				messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
			}
		} else {
			jsonResult = new JsonResult(false).setMsg("操作失败");
		}
		return jsonResult;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(Ecofund.class,request);
		return ((EcofundService)service).findPageEcofundList(filter);
	}

	/**
	 * 跳转对应页面
	 */
	@RequestMapping(value="/gotoEcoList")
	@ResponseBody
	public ModelAndView gotoEcoList(HttpServletRequest request) {
		String status = request.getParameter("status");
		if (!StringUtil.isNull(status)) {
			status = "0";
		}
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecofundlist");
		mav.addObject("status", status);
		return mav;
	}

	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id, HttpServletRequest request){
		String status = request.getParameter("status");
		Ecofund ecofund = ((EcofundService)service).getEcofund(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecofundmodify");
		mav.addObject("model", ecofund);
		mav.addObject("status",status);
		return mav;
	}

}
