/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-29 11:30:50 
 */
package hry.admin.web.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.web.model.chatSensitive;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-29 11:30:50 
 */
@Controller
@RequestMapping("/web/chatsensitive")
public class chatSensitiveController extends BaseController<chatSensitive, Long> {
	
	@Resource(name = "chatSensitiveService")
	@Override
	public void setService(BaseService<chatSensitive, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		chatSensitive chatSensitive = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/chatsensitivesee");
		mav.addObject("model", chatSensitive);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,chatSensitive chatSensitive){
		return super.save(chatSensitive);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		chatSensitive chatSensitive = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/chatsensitivemodify");
		mav.addObject("model", chatSensitive);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,chatSensitive chatSensitive){
		return super.update(chatSensitive);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(chatSensitive.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
