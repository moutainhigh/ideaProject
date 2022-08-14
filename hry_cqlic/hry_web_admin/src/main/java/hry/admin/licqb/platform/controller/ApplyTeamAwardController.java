/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:44:07 
 */
package hry.admin.licqb.platform.controller;


import hry.admin.licqb.platform.model.ApplyTeamAward;
import hry.admin.licqb.platform.service.ApplyTeamAwardService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
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
 * @Date:        2019-08-12 17:44:07 
 */
@Controller
@RequestMapping("/licqb/platform/applyteamaward")
public class ApplyTeamAwardController extends BaseController<ApplyTeamAward, Long> {
	
	@Resource(name = "applyTeamAwardService")
	@Override
	public void setService(BaseService<ApplyTeamAward, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ApplyTeamAward applyTeamAward = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/applyteamawardsee");
		mav.addObject("model", applyTeamAward);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ApplyTeamAward applyTeamAward){
		return super.save(applyTeamAward);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ApplyTeamAward applyTeamAward = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/applyteamawardmodify");

		mav.addObject("model", applyTeamAward);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ApplyTeamAward applyTeamAward){
		return super.update(applyTeamAward);
	}

	@RequestMapping(value="/gotoAuditFtl")
	@ResponseBody
	public ModelAndView gotoAuditFtl(Long id, String type){
		ModelAndView view = new ModelAndView("/admin/licqb/platform/applyteamawardAudit");
		ApplyTeamAward applyTeamAward = ((ApplyTeamAwardService)service).getApplyTeamAward(id);
		view.addObject("model", applyTeamAward);
		view.addObject("type", type);
		return view;
	}
	@RequestMapping(value="/audit")
	@ResponseBody
	public JsonResult audit(Long id,String type,String auditExplain ){
		ApplyTeamAward applyTeamAward = service.get(id);
		// type 1 审核通过 2 审核拒绝
		if ("1".equals(type)) {
			JsonResult result = ((ApplyTeamAwardService)service).updateReleaseRule(id,applyTeamAward.getCustomerId());
			if (result.getSuccess()) {
				return new JsonResult(true).setMsg("审核成功");
			} else {
				return new JsonResult(true).setMsg("驳回失败");
			}
		} else {
			applyTeamAward.setAuditStatus(2);
			applyTeamAward.setAuditExplain(auditExplain);
			service.update(applyTeamAward);
			return new JsonResult(true).setMsg("驳回成功");
		}

	}
	/**
	 * 批量审核通过
	 * */
	@RequestMapping(value="/allAudit")
	@ResponseBody
	public JsonResult allAudit(String ids){
		String[] idList = ids.split(",");
		for (int i = 0; i < idList.length; i++) {
			ApplyTeamAward award = ((ApplyTeamAwardService)service).getApplyTeamAward(Long.parseLong(idList[i]));
			if (award.getApplyStatus() == 3 && award.getAuditStatus() != 1) {
				JsonResult result = ((ApplyTeamAwardService)service).updateReleaseRule(award.getId(),award.getCustomerId());
			}
		}
		return new JsonResult(true).setMsg("审核成功");
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ApplyTeamAward.class,request);
		return ((ApplyTeamAwardService)service).findApplyPage(filter);
	}
}
