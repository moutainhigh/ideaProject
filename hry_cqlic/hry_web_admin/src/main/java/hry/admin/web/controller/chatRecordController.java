/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-28 16:29:34 
 */
package hry.admin.web.controller;


import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.web.model.chatRecord;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Copyright:   互融云
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-28 16:29:34 
 */
@Controller
@RequestMapping("/web/chatrecord")
public class chatRecordController extends BaseController<chatRecord, Long> {
	
	@Resource(name = "chatRecordService")
	@Override
	public void setService(BaseService<chatRecord, Long> service) {
		super.service = service;
	}
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private RedisService redisService;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		chatRecord chatRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/chatrecordsee");
		mav.addObject("model", chatRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,chatRecord chatRecord){
		return super.save(chatRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		chatRecord chatRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/chatrecordmodify");
		mav.addObject("model", chatRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,chatRecord chatRecord){
		return super.update(chatRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(chatRecord.class,request);
		return super.findPage(filter);
	}

	/**
	 * 是否管理员
	 * @return
	 */
	@RequestMapping("/isAdmin")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult isAdmin(HttpServletRequest request){
		JsonResult j = new JsonResult();

		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			String type = request.getParameter("type");
			AppCustomer appCustomer = appCustomerService.get(Long.valueOf(id));
			appCustomer.setIsAdmin(Integer.valueOf(type));
			appCustomerService.update(appCustomer);

			redisService.save("chat:admin:"+appCustomer.getUsername(), type, 43200);
			j.setSuccess(true);
			j.setMsg("设置成功");
			return j;
		}
		j.setSuccess(false);
		j.setMsg("设置失败");
		return j;
	}

	/**
	 * 是否禁言
	 * @return
	 */
	@RequestMapping("/isGag")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult isGag(HttpServletRequest request){
		JsonResult j = new JsonResult();

		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			String type = request.getParameter("type");
			AppCustomer appCustomer = appCustomerService.get(Long.valueOf(id));
			if("1".equals(type)){
				String gagTime = request.getParameter("gagTime");
				appCustomer.setIsGag(1);
				appCustomer.setGagDate(new Date());
				appCustomer.setGagTime(Integer.valueOf(gagTime));
				appCustomerService.update(appCustomer);

				redisService.save("chat:gag:"+appCustomer.getUsername(), "jinnideyan", Integer.valueOf(gagTime));
			}else if("0".equals(type)){
				redisService.delete("chat:gag:"+appCustomer.getUsername());
				appCustomer.setIsGag(0);
				appCustomer.setGagTime(0);
				appCustomerService.update(appCustomer);
			}
			j.setSuccess(true);
			j.setMsg("设置成功");
			return j;
		}
		j.setSuccess(false);
		j.setMsg("设置失败");
		return j;
	}
	
	
}
