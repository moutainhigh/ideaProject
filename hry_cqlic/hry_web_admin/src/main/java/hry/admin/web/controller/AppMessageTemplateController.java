/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:23:25 
 */
package hry.admin.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.web.model.AppMessageCateTemp;
import hry.admin.web.model.AppMessageTemplate;
import hry.admin.web.service.AppMessageTemplateService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:23:25 
 */
@Controller
@RequestMapping("/web/appmessagetemplate")
public class AppMessageTemplateController extends BaseController<AppMessageTemplate, Long> {
	
	@Resource(name = "appMessageTemplateService")
	@Override
	public void setService(BaseService<AppMessageTemplate, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppMessageTemplate appMessageTemplate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appmessagetemplatesee");
		mav.addObject("model", appMessageTemplate);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add (HttpServletRequest request, @RequestBody List<Map<String,Object>> paraMap) {
		List<AppMessageTemplate> list = new ArrayList<>();
		for (Map<String, Object> map : paraMap) {
			JSONObject json = new JSONObject(map);
			AppMessageTemplate template = JSON.parseObject(json.toJSONString(), AppMessageTemplate.class);
			list.add(template);
		}
		JsonResult jsonResult = ((AppMessageTemplateService) service).insertBatch(list);
		return jsonResult;
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppMessageTemplate appMessageTemplate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appmessagetemplatemodify");
		mav.addObject("model", appMessageTemplate);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppMessageTemplate appMessageTemplate){
		return super.update(appMessageTemplate);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppMessageTemplate.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/toAdd")
	@ResponseBody
	public ModelAndView toAdd(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/web/appmessagetemplateadd");
		List<AppMessageCateTemp> listKey = ((AppMessageTemplateService) service).findKey();
		mav.addObject("listKey", listKey);
		return mav;
	}
	
	
}
