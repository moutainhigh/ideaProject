/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
package hry.admin.c2c.controller;


import hry.admin.c2c.model.C2cCoin;
import hry.admin.c2c.service.C2cCoinService;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExProductService;
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
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
@Controller
@RequestMapping("/c2c/c2ccoin")
public class C2cCoinController extends BaseController<C2cCoin, Long> {

	@Resource
	private ExProductService exProductService;
	
	@Resource(name = "c2cCoinService")
	@Override
	public void setService(BaseService<C2cCoin, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		C2cCoin c2cCoin = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/c2ccoinsee");
		mav.addObject("model", c2cCoin);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,C2cCoin c2cCoin){
		String buyStr = c2cCoin.getBuyPrice().toString();
		String sellStr = c2cCoin.getSellPrice().toString();
		if(buyStr.contains(".")) {
			if(buyStr.substring(buyStr.indexOf(".")+1).length()>2) {
				JsonResult result =  new JsonResult();
				result.setSuccess(false);
				result.setMsg("买入价格式不正确");
				return result;
			}
		}
		if(sellStr.contains(".")) {
			if(sellStr.substring(sellStr.indexOf(".")+1).length()>2) {
				JsonResult result =  new JsonResult();
				result.setSuccess(false);
				result.setMsg("卖出价格式不正确");
				return result;
			}
		}
		C2cCoin _c2cCoin2 = service.get(new QueryFilter(C2cCoin.class).addFilter("coinCode=", c2cCoin.getCoinCode()));
		if(_c2cCoin2!=null) {
			JsonResult result =  new JsonResult();
			result.setSuccess(false);
			result.setMsg("币种已存在");
			return result;
		}

		JsonResult save = super.save(c2cCoin);
		((C2cCoinService)service).flushRedis();
		return save;

	}

	@RequestMapping(value="/close/{id}")
	@ResponseBody
	public JsonResult close(HttpServletRequest request,@PathVariable Long id){
		JsonResult jsonResult = new JsonResult();
		C2cCoin c2cCoin = service.get(id);
		if(c2cCoin.getIsOpen()==0){
			jsonResult.setMsg("已经是关闭状态了");
			jsonResult.setSuccess(false);
			return jsonResult;
		}
		c2cCoin.setIsOpen(0);
		service.update(c2cCoin);
		((C2cCoinService)service).flushRedis();
		return jsonResult.setSuccess(true);

	}


	@RequestMapping(value="/open/{id}")
	@ResponseBody
	public JsonResult open(HttpServletRequest request,@PathVariable Long id){
		JsonResult jsonResult = new JsonResult();
		C2cCoin c2cCoin = service.get(id);
		if(c2cCoin.getIsOpen()==1){
			jsonResult.setMsg("已经是开启状态了");
			jsonResult.setSuccess(false);
			return jsonResult;
		}
		c2cCoin.setIsOpen(1);
		service.update(c2cCoin);
		((C2cCoinService)service).flushRedis();
		return jsonResult.setSuccess(true);

	}


	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		C2cCoin c2cCoin = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/c2ccoinmodify");
		mav.addObject("model", c2cCoin);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,C2cCoin c2cCoin){
		String buyStr = c2cCoin.getBuyPrice().toString();
		String sellStr = c2cCoin.getSellPrice().toString();
		if(buyStr.contains(".")) {
			if(buyStr.substring(buyStr.indexOf(".")+1).length()>2) {
				JsonResult result =  new JsonResult();
				result.setSuccess(false);
				result.setMsg("买入价格式不正确");
				return result;
			}
		}
		if(sellStr.contains(".")) {
			if(sellStr.substring(sellStr.indexOf(".")+1).length()>2) {
				JsonResult result =  new JsonResult();
				result.setSuccess(false);
				result.setMsg("卖出价格式不正确");
				return result;
			}
		}



		JsonResult update = super.update(c2cCoin);
		((C2cCoinService)service).flushRedis();
		return update;
	}


	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(C2cCoin.class,request);
		// 查询下架币种
		List<ExProduct> plist = exProductService.findByIssueState(2);
		if (plist != null && plist.size() > 0) {
			String inStr = "";
			for (ExProduct p : plist) {
				inStr += "," + p.getCoinCode();
			}
			filter.addFilter("coinCode__notin", inStr.substring(1));
		}
		return super.findPage(filter);
	}

	@RequestMapping(value = "/findall")
	@ResponseBody
	public List<C2cCoin> findall(HttpServletRequest request) {
		return super.findAll();
	}



}
