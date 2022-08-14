/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2022-05-19 10:31:38 
 */
package hry.admin.xzsn.task.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.xzsn.task.model.Task;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2022-05-19 10:31:38 
 */
@Controller
@RequestMapping("/xzsn/task/task")
public class TaskController extends BaseController<Task, Long> {
	
	@Resource(name = "taskService")
	@Override
	public void setService(BaseService<Task, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		Task task = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/xzsn/task/tasksee");
		mav.addObject("model", task);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,Task task){
		return super.save(task);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		Task task = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/xzsn/task/taskmodify");
		mav.addObject("model", task);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,Task task){
		return super.update(task);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(Task.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
