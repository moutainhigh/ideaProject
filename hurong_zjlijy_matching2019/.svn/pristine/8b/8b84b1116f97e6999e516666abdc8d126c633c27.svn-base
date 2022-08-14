package hry.trade.entrust.controller;

import hry.core.mvc.model.page.JsonResult;
import hry.customer.user.model.AppCustomer;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.EntrustTrade;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/trade")
public class TradeController {

	/**
	 * 注册类型属性编辑器
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {

		// 系统注入的只能是基本类型，如int，char，String

		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

		/**
		 * 防止XSS攻击，并且带去左右空格功能
		 */
		binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
	}

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private ExEntrustService exEntrustService;

	@Resource
	public CommonDao commonDao;

	// coinCode:MGB_CNY,entrustWay：1现价2市价，entrustPrice（价格）entrustCount（量）
	// 两种情况1，
	// coinCode:MGB_CNY,entrustWay：1现价，entrustPrice（价格）entrustCount（量）（默认情况为限价）
	// 1， coinCode:MGB_CNY,entrustWay：2市价，entrustSum总价
	@RequestMapping(value = "/testTrade", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult testTrade(EntrustTrade exEntrust, HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		// 委托之前判断
		String[] rtd = exEntrust.getCoinCode().split("_");
		if (rtd.length == 1) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("币种不正确");
			return jsonResult;
		} else {
		//	exEntrust.setFixPriceCoinCode(rtd[1]);
		//	exEntrust.setCoinCode(rtd[0]);
		}

		if (null == exEntrust.getEntrustWay()) {
			exEntrust.setEntrustWay(1); // 默认为现价
		}
		if (exEntrust.getEntrustWay().equals(1)) {
			if (null == exEntrust.getEntrustCount()) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("数量不能为空");
				return jsonResult;
			}
			if (null == exEntrust.getEntrustPrice()) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("价格不能为空");
				return jsonResult;
			}
		}
		if (exEntrust.getEntrustWay().equals(2)) {
			if (exEntrust.getType() == 1 && null == exEntrust.getEntrustSum()) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("总金额不能为空");
				return jsonResult;
			}
			if (exEntrust.getType() == 2 && null == exEntrust.getEntrustCount()) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("数量不能为空");
				return jsonResult;
			}
		}
		String userName = request.getParameter("userName");
		AppCustomer customer = commonDao.getAppUserByuserName(userName);
		exEntrustService.addExEntrust(0, exEntrust.getType(), customer.getId(), exEntrust.getEntrustPrice(), userName,
				exEntrust.getCoinCode(), exEntrust.getEntrustCount(), null, null);
		jsonResult.setSuccess(true);
		jsonResult.setMsg("");
		return jsonResult;
	}

}
