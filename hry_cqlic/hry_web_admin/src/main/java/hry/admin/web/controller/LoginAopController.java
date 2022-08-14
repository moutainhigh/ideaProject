/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:28 
 */
package hry.admin.web.controller;


import hry.admin.web.model.LoginAop;
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

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:28 
 */
@Controller
@RequestMapping("/web/loginaop")
public class LoginAopController extends BaseController<LoginAop, Long> {
	
	@Resource(name = "loginAopService")
	@Override
	public void setService(BaseService<LoginAop, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		LoginAop loginAop = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/loginaopsee");
		mav.addObject("model", loginAop);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,LoginAop loginAop){
		return super.save(loginAop);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		LoginAop loginAop = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/loginaopmodify");
		mav.addObject("model", loginAop);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,LoginAop loginAop){
		return super.update(loginAop);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(LoginAop.class, request);
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}
	
}
