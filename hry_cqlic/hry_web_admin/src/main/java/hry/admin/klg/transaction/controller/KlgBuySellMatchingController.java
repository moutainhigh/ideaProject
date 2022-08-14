package hry.admin.klg.transaction.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.transaction.model.KlgSellTransaction;
import hry.admin.klg.transaction.service.KlgBuySellMatchingService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.util.QueryFilter;

@Controller
@RequestMapping("/klg/transaction/matching")
public class KlgBuySellMatchingController {
	
	@Resource
	private KlgBuySellMatchingService klgBuySellMatchingService;
	
	/**
	 * 匹配按钮，查询对应的卖单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pipeiSelect")
	@ResponseBody
	public PageResult pipei(HttpServletRequest request, HttpServletResponse response){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
        PageResult findPageBySql = klgBuySellMatchingService.pipeiSelectPage(filter);
        return findPageBySql;
	}
	/**
	 * 卖单列表初始化
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sellList")
	@ResponseBody
	public PageResult sellList(HttpServletRequest request){
		PageResult findPageBySql = new PageResult();
		return findPageBySql;
	}
	
	/**
	 * 买卖单上方的数据信息
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/buysellData")
	@ResponseBody
	public JsonResult buysellData(){
		return klgBuySellMatchingService.selectBuySellData();
	}
	
	/**
	 * 买单匹配信息展示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pipei")
	@ResponseBody
	public ModelAndView pipei(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/matchingbuy_pipei");
		String ids = request.getParameter("ids");
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		Map<String , Object> map = klgBuySellMatchingService.selectPipeiData(filter, ids);
		mav.addObject("map", map);
		return mav;
	}
	
	/**
	 * 买单匹配弹框确认
	 * @return
	 */
	@RequestMapping(value="/peipeiSubmit")
	@ResponseBody
	public JsonResult peipeiSubmit(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		String ids = request.getParameter("ids");
		return klgBuySellMatchingService.peipeiSubmit(ids,filter);
	}
	
	/**
	 * 买单调控按钮
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tiaokong")
	@ResponseBody
	public ModelAndView tiaokong(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/matching_tiaokong");
		Map<String , Object> map = klgBuySellMatchingService.selectTiaokongData();
		mav.addObject("map", map);
		return mav;
	}
	
	/**
	 * 买单调控弹框确认
	 * @return
	 */
	@RequestMapping(value="/tiaokongSubmit")
	@ResponseBody
	public JsonResult tiaokongSubmit(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		String tiaokongSme = request.getParameter("tiaokongSme");
		String tiaokongUsdt = request.getParameter("tiaokongUsdt");
		return klgBuySellMatchingService.tiaokongSubmit(tiaokongSme,tiaokongUsdt,filter);
	}
	
	/**
	 * 买单吃单确认按钮
	 * @return
	 */
	@RequestMapping(value="/chidanSubmit")
	@ResponseBody
	public JsonResult chidanSubmit(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		String  ids = request.getParameter("ids");
		return klgBuySellMatchingService.chidanSubmit(ids);
	}
	
	/**
	 * 卖单匹配信息展示
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pipeisell")
	@ResponseBody
	public ModelAndView pipeimai(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/matchingsell_pipei");
		String ids = request.getParameter("ids");
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		Map<String , Object> map = klgBuySellMatchingService.selectPipeiSellData(filter, ids);
		mav.addObject("map", map);
		return mav;
	}
	
	/**
	 * 匹配按钮，查询对应的买单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pipeiSellSelect")
	@ResponseBody
	public PageResult pipeiSell(HttpServletRequest request, HttpServletResponse response){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
        PageResult findPageBySql = klgBuySellMatchingService.pipeiSellSelectPage(filter);
        return findPageBySql;
	}
	
	/**
	 * 买单匹配弹框确认
	 * @return
	 */
	@RequestMapping(value="/peipeiSellSubmit")
	@ResponseBody
	public JsonResult peipeiSellSubmit(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		String ids = request.getParameter("ids");
		return klgBuySellMatchingService.peipeiSellSubmit(ids,filter);
	}
	
	/**
	 * 卖单吃单确认按钮
	 * @return
	 */
	@RequestMapping(value="/chidanSellSubmit")
	@ResponseBody
	public JsonResult chidanSellSubmit(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		String  ids = request.getParameter("ids");
		return klgBuySellMatchingService.chidanSellSubmit(ids);
	}
	
	
	/**
	 * 剩余转出按钮
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/outSurplus")
	@ResponseBody
	public ModelAndView outSurplus(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/matching_surplusout");
		return mav;
	}
	
	/**
	 * 剩余转出控弹框确认
	 * @return
	 */
	@RequestMapping(value="/outSurplusSubmit")
	@ResponseBody
	public JsonResult outSurplusSubmit(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		String outSme = request.getParameter("outSme");
		String outUsdt = request.getParameter("outUsdt");
		return klgBuySellMatchingService.outSurplusSubmit(outSme,outUsdt,filter);
	}
	
	
	/**
	 * 买单单独匹配
	 * @return
	 */
	@RequestMapping(value="/buyAlonePipeiSubmit")
	@ResponseBody
	public JsonResult buyAlonePipeiSubmit(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		String ids = request.getParameter("ids");
		return klgBuySellMatchingService.buyAlonePipeiSubmit(ids,filter);
	}
	/**
	 * 卖单单独匹配
	 * @return
	 */
	@RequestMapping(value="/sellAlonePipeiSubmit")
	@ResponseBody
	public JsonResult sellAlonePipeiSubmit(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
		String ids = request.getParameter("ids");
		return klgBuySellMatchingService.sellAlonePipeiSubmit(ids,filter);
	}

}
