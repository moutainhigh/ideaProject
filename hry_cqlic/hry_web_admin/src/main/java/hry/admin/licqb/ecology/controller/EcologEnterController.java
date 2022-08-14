/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:53 
 */
package hry.admin.licqb.ecology.controller;


import hry.admin.licqb.ecology.model.Ecofund;
import hry.admin.licqb.ecology.model.EcologEnter;
import hry.admin.licqb.ecology.model.EcologPlate;
import hry.admin.licqb.ecology.service.EcologEnterService;
import hry.admin.licqb.ecology.service.EcologPayService;
import hry.admin.licqb.ecology.service.EcologPlateService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:53 
 */
@Controller
@RequestMapping("/licqb/ecology/ecologenter")
public class EcologEnterController extends BaseController<EcologEnter, Long> {

	@Autowired
	private EcologPlateService ecologPlateService;
	@Autowired
	private EcologPayService ecologPayService;

	@Resource(name = "ecologEnterService")
	@Override
	public void setService(BaseService<EcologEnter, Long> service) {
		super.service = service;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,EcologEnter ecologEnter){
		// 校验是否能修改
		EcologEnter enter = ((EcologEnterService)service).get(ecologEnter.getId());
		Map<String, Object> paramMap = new HashMap<>();
		// 是否校验
		boolean flag = false;
		// 校验对应版块是否有A的位置
		if (!enter.getPlateId().equals(ecologEnter.getId())) {
			// 判断要加入的版块 入驻等级A 还否已满
			if ("A".equals(ecologEnter.getEnterLevel())) {
				flag = true;
			}
		} else {
			// 相同版块中 原本是B 现在要改成A 判断是否允许修改
			if ("B".equals(enter.getEnterLevel()) && "A".equals(ecologEnter.getEnterLevel())) {
				flag = true;
			}
		}
		if (flag) {
			paramMap.put("plateId", ecologEnter.getPlateId());
			paramMap.put("enterLevel", ecologEnter.getEnterLevel());
			paramMap.put("enterStatus", "enterStatusA");
		}
		if (paramMap.size() > 0) {
			int count = ((EcologEnterService)service).countEnter(paramMap);
			if (count >= 3) {
				return new JsonResult("false").setMsg("该版块入驻等级A已满");
			}
		}
		return super.update(ecologEnter);
	}

	/**
	 * 申请审核 和 支付核实
	 * */
	@RequestMapping(value="/handleStatus")
	@ResponseBody
	public JsonResult handleStatus(HttpServletRequest request){
		String id = request.getParameter("id");
		String enterReply = request.getParameter("enterReply");
		String enterStatus = request.getParameter("enterStatus");
		EcologEnter ecologEnter = null;
		if (StringUtil.isNull(id)) {
			ecologEnter = super.service.get(Long.parseLong(id));
		}
		if (ecologEnter != null) {
			if (StringUtil.isNull(enterReply)) {
				ecologEnter.setEnterReply(enterReply);
			}
			if (StringUtil.isNull(enterStatus)) {
				ecologEnter.setEnterStatus(Integer.parseInt(enterStatus));
			}
			return ((EcologEnterService)service).updateEcologEnter(ecologEnter);
		} else {
			return new JsonResult(false).setMsg("操作失败");
		}
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(EcologEnter.class,request);
		return ((EcologEnterService)service).findPageEcologEnterList(filter);
	}
	/**
	 * 跳转对应页面
	 */
	@RequestMapping(value="/gotoEcoLogEnterList")
	@ResponseBody
	public ModelAndView gotoEcoLogEnterList(HttpServletRequest request) {
		String status = request.getParameter("status");
		if (!StringUtil.isNull(status) || "3".equals(status)) {
			status = "0";
		}
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecologenterlist");
		mav.addObject("status", status);
		return mav;
	}

	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id, HttpServletRequest request){
		String status = request.getParameter("status");
		EcologEnter ecologEnter = ((EcologEnterService)service).getEcologEnter(id);
		List<EcologPlate> ecologPlateList = ecologPlateService.findAll();
		ModelAndView mav = new ModelAndView("/admin/licqb/ecology/ecologentermodify");
		mav.addObject("model", ecologEnter);
		mav.addObject("status",status);
		mav.addObject("ecologPlateList", ecologPlateList);
		return mav;
	}
}
