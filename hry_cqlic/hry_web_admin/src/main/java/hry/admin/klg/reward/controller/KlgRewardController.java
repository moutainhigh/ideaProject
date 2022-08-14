/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-24 18:13:04 
 */
package hry.admin.klg.reward.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.reward.model.KlgReward;
import hry.admin.klg.reward.service.KlgRewardService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-24 18:13:04 
 */
@Controller
@RequestMapping("/klg/reward/klgreward")
public class KlgRewardController extends BaseController<KlgReward, Long> {
	
	@Resource(name = "klgRewardService")
	@Override
	public void setService(BaseService<KlgReward, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgReward klgReward = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/reward/klgrewardsee");
		mav.addObject("model", klgReward);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgReward klgReward){
		return super.save(klgReward);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgReward klgReward = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/reward/klgrewardmodify");
		mav.addObject("model", klgReward);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgReward klgReward){
		return super.update(klgReward);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		/*QueryFilter filter = new QueryFilter(KlgReward.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlgReward.class, request);
        PageResult findPageBySql = ((KlgRewardService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	
	
	
}
