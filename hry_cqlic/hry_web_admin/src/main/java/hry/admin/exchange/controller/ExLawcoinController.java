/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 09:57:59 
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSON;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExLawcoin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.sys.ContextUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.nutz.lang.Encoding;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 09:57:59 
 */
@Controller
@RequestMapping("/exchange/exlawcoin")
public class ExLawcoinController extends BaseController<ExLawcoin, Long> {
	
	@Resource(name = "exLawcoinService")
	@Override
	public void setService(BaseService<ExLawcoin, Long> service) {
		super.service = service;
	}

	@Resource
	private RedisService redisService;
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExLawcoin exLawcoin = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exlawcoinsee");
		mav.addObject("model", exLawcoin);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExLawcoin exLawcoin){
		exLawcoin.setCoinSymbol(HtmlUtils.htmlUnescape(exLawcoin.getCoinSymbol()));
		BaseManageUser user = ContextUtil.getCurrentUser();
		exLawcoin.setCreator(user.getUsername());
		QueryFilter queryFilter = new QueryFilter(ExLawcoin.class);
		queryFilter.or("name=",exLawcoin.getName());
		queryFilter.or("coinSymbol=",exLawcoin.getCoinSymbol());
		queryFilter.or("coinCode=",exLawcoin.getCoinCode());

		List<ExLawcoin> lawcoins = service.find(queryFilter);
		if(null != lawcoins && lawcoins.size()>0){
			return new JsonResult().setSuccess(false).setMsg("币种名称，符号，代号不可重复");
		}
		JsonResult save = super.save(exLawcoin);

		List<ExLawcoin> list = super.findAll();
		String[][] str = new String[list.size()][4];
		for(int i=0;i <list.size(); i++){
			str[i][0] = list.get(i).getName();
			str[i][1] = list.get(i).getCoinSymbol();
			str[i][2] = list.get(i).getCoinCode();
			str[i][3] = list.get(i).getCoinDecimal() + "";
		}
		redisService.save("otc:exLawcoin", JSON.toJSONString(str));

		return save;
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExLawcoin exLawcoin = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exlawcoinmodify");
		mav.addObject("model", exLawcoin);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExLawcoin exLawcoin){
		JsonResult update = super.update(exLawcoin);
		List<ExLawcoin> list = super.findAll();
		String[][] str = new String[list.size()][4];
		for(int i=0;i <list.size(); i++){
			str[i][0] = list.get(i).getName();
			str[i][1] = list.get(i).getCoinSymbol();
			str[i][2] = list.get(i).getCoinCode();
			str[i][3] = list.get(i).getCoinDecimal() + "";
		}
		redisService.save("otc:exLawcoin", JSON.toJSONString(str));
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
		QueryFilter filter = new QueryFilter(ExLawcoin.class,request);
		return super.findPage(filter);
	}


	@RequestMapping(value="/findall")
	@ResponseBody
	public List<ExLawcoin> findall(HttpServletRequest request){
		return super.findAll();
	}


	@RequestMapping(value="/findByName")
	@ResponseBody
	public JsonResult findByName(HttpServletRequest request){
		String name = request.getParameter("name");
		QueryFilter queryFilter = new QueryFilter(ExLawcoin.class);
		queryFilter.addFilter("name=",name);
		return super.get(queryFilter);
	}
}
