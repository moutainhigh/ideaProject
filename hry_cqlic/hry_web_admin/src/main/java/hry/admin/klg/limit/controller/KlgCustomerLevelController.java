/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 19:48:29 
 */
package hry.admin.klg.limit.controller;


import hry.admin.klg.limit.service.KlgCustomerLevelService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.limit.model.KlgCustomerLevel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 19:48:29 
 */
@Controller
@RequestMapping("/klg/limit/klgcustomerlevel")
public class KlgCustomerLevelController extends BaseController<KlgCustomerLevel, Long> {
	
	@Resource(name = "klgCustomerLevelService")
	@Override
	public void setService(BaseService<KlgCustomerLevel, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgCustomerLevel klgCustomerLevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/limit/klgcustomerlevelsee");
		mav.addObject("model", klgCustomerLevel);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgCustomerLevel klgCustomerLevel){
		return super.save(klgCustomerLevel);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgCustomerLevel klgCustomerLevel = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/limit/klgcustomerlevelmodify");
		mav.addObject("model", klgCustomerLevel);
		return mav;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateReward")
	@ResponseBody
	public JsonResult updateReward(HttpServletRequest request,@RequestParam(required = true) String listIds,
								   String gradation,String rewardNum,String pointAlgebra){
		HashMap hashMap=new HashMap();
		BaseManageUser manageUser= (BaseManageUser)request.getSession().getAttribute("user");
		if(gradation!=null){
			hashMap.put("gradation",gradation);
		}if(rewardNum!=null&&!"".equals(rewardNum)){
			hashMap.put("rewardNum",rewardNum);
		}if(pointAlgebra!=null){
			hashMap.put("pointAlgebra",pointAlgebra);
		}if(hashMap.isEmpty()){
			return  new JsonResult().setSuccess(true).setMsg("成功");//什么也不做
		}if(listIds!=null){
			hashMap.put("listIds",listIds);
			hashMap.put("operatorId",manageUser.getId().toString());
			hashMap.put("operatorIdName",manageUser.getUsername());
			return ((KlgCustomerLevelService)service).addReward(hashMap);
//			return  new JsonResult().setSuccess(true).setMsg("成功");
		}
		return  new JsonResult().setSuccess(false).setMsg("无选择用户");
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgCustomerLevel.class,request);
		PageResult findPageBySql = ((KlgCustomerLevelService) service).findPageBySql(filter);
		return findPageBySql;
	}

}
