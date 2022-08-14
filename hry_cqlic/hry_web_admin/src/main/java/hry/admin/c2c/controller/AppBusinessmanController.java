/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:33:52 
 */
package hry.admin.c2c.controller;


import hry.admin.c2c.model.AppBusinessman;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
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
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:33:52 
 */
@Controller
@RequestMapping("/c2c/appbusinessman")
public class AppBusinessmanController extends BaseController<AppBusinessman, Long> {
	
	@Resource(name = "appBusinessmanService")
	@Override
	public void setService(BaseService<AppBusinessman, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppBusinessman AppBusinessman = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/AppBusinessmansee");
		mav.addObject("model", AppBusinessman);
		return mav;
	}
	

	@CommonLog(name = "添加交易商")
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppBusinessman appBusinessman){
		AppBusinessman app=new AppBusinessman();
		app.setTrueName(appBusinessman.getTrueName());
		long count=service.count(app);
		if(count>0){
			return new JsonResult().setSuccess(false).setMsg("该交易商已经添加");
		}
		return super.save(appBusinessman);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppBusinessman AppBusinessman = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/appbusinessmanmodify");
		mav.addObject("model", AppBusinessman);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppBusinessman AppBusinessman){
		return super.update(AppBusinessman);
	}


	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppBusinessman.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/findall")
	@ResponseBody
	public List<AppBusinessman> findall(HttpServletRequest request){
		return super.findAll();
	}

	@RequestMapping(value="/lock")
	@ResponseBody
	public JsonResult lock(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		String isLock = request.getParameter("isLock");
		String id = request.getParameter("id");
		AppBusinessman appBusinessman = service.get(Long.valueOf(id));
		if(appBusinessman!=null){
			if(appBusinessman.getIsLock()==Integer.valueOf(isLock)){
				if(appBusinessman.getIsLock()==0){
					jsonResult.setMsg("该用户未禁用，不需要解禁");
				}else{
					jsonResult.setMsg("该用户已禁用,不可重复禁用");

				}
				jsonResult.setSuccess(false);
				return jsonResult;
			}
			appBusinessman.setIsLock(Integer.valueOf(isLock));
			service.update(appBusinessman);
		}
		return jsonResult.setSuccess(true);
	}
	
	
}
