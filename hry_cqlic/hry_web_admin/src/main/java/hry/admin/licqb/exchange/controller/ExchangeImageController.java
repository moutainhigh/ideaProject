/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:38:08 
 */
package hry.admin.licqb.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.exchange.model.ExchangeImage;

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
 * @Date:        2020-12-17 16:38:08 
 */
@Controller
@RequestMapping("/licqb/exchange/exchangeimage")
public class ExchangeImageController extends BaseController<ExchangeImage, Long> {
	
	@Resource(name = "exchangeImageService")
	@Override
	public void setService(BaseService<ExchangeImage, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExchangeImage exchangeImage = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangeimagesee");
		mav.addObject("model", exchangeImage);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExchangeImage exchangeImage){
		return super.save(exchangeImage);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExchangeImage exchangeImage = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/exchange/exchangeimagemodify");
		mav.addObject("model", exchangeImage);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExchangeImage exchangeImage){
		return super.update(exchangeImage);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExchangeImage.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}
