/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-26 10:06:47 
 */
package hry.admin.test.controller;


import hry.admin.test.model.Student;
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
 * @Date:        2018-06-26 10:06:47 
 */
@Controller
@RequestMapping("/test/student")
public class StudentController extends BaseController<Student, Long> {
	
	@Resource(name = "studentService")
	@Override
	public void setService(BaseService<Student, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		Student student = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/test/studentsee");
		mav.addObject("model", student);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,Student student){
		return super.save(student);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		Student student = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/test/studentmodify");
		mav.addObject("model", student);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,Student student){
		return super.update(student);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(Student.class,request);
		return super.findPage(filter);
	}


	@RequestMapping(value="/push")
	@ResponseBody
	public JsonResult push(HttpServletRequest request,Student student){
		return super.save(student);
	}





}
