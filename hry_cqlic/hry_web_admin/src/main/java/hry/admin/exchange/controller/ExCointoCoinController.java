/**
 * Copyright:
 * @author:      liushilei
 * @version:     V1.0
 * @Date:        2018-06-12 15:52:02
 */
package hry.admin.exchange.controller;


import hry.admin.exchange.model.*;
import hry.admin.exchange.service.*;
import hry.admin.lend.model.ExLendConfig;
import hry.admin.lend.service.ExLendConfigService;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0
 * @Date:        2018-06-12 15:52:02
 */
@Controller
@RequestMapping("/exchange/excointocoin")
public class ExCointoCoinController extends BaseController<ExCointoCoin, Long> {

	@Resource(name = "exCointoCoinService")
	@Override
	public void setService(BaseService<ExCointoCoin, Long> service) {
		super.service = service;
	}

	@Resource
	public ExProductService exProductService;

	@Resource
	public AppConfigService appConfigService;

	@Resource
	private ExRobotService exRobotService;
	@Resource
	private ExRobotDeepService exRobotDeepService;

	@Resource
	private ExCointoCoinService exCointoCoinService;

	@Resource
	private ExRobotLogService exRobotLogService;

	@Resource
	private RedisService redisService;
	@Resource
	private ExTradingAreaService exTradingAreaService;
	@Resource
	private ExLendConfigService exLendConfigService;



	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExCointoCoin exCointoCoin = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excointocoinsee");
		mav.addObject("model", exCointoCoin);
		return mav;
	}

	@RequestMapping(value="/toAdd/{tradeArea}")
	public ModelAndView see(@PathVariable String tradeArea){
		ModelAndView mav = new ModelAndView("/admin/exchange/excointocoinadd");
		mav.addObject("tradeArea", tradeArea);
		return mav;
	}

	@RequestMapping(value="/add")
	@ResponseBody
	@CommonLog(name = "添加交易对")
	public JsonResult add(HttpServletRequest request,ExCointoCoin exCointoCoin){
		if(exCointoCoin.getRose().intValue() >100){
			return new JsonResult().setSuccess(false).setMsg("限价涨幅不可大于100 %");
		}
		if(exCointoCoin.getDecline().intValue() >100){
			return new JsonResult().setSuccess(false).setMsg("限价跌幅不可大于100 %");
		}
		String tradeArea = request.getParameter("tradeArea");
		if (!StringUtils.isEmpty(tradeArea)) {
			exCointoCoin.setFixPriceCoinCode(tradeArea);
		}
		List<ExCointoCoin>list=exCointoCoinService.getBylist(exCointoCoin.getCoinCode(), exCointoCoin.getFixPriceCoinCode(), null);
		if(list.size()==0){
			if(null==exCointoCoin.getAveragePrice()||exCointoCoin.getAveragePrice().compareTo(new BigDecimal("0"))<=0){
				JsonResult jsonResult=new JsonResult();
				jsonResult.setSuccess(false);
				jsonResult.setMsg("发行价不能为空或者0或者负数");
				return jsonResult;
			}
			//添加机器人k线机器人
			ExRobot exRobot = new ExRobot();
			exRobot.setCoinCode(exCointoCoin.getCoinCode());
			exRobot.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
			exRobot.setFixPriceType(1);
			exRobot.setRobotType(1);
			exRobot.setAtuoPriceType(3);
			exRobotService.save(exRobot);

			//添加机器人深度机器人
			ExRobotDeep exRobot1 = new ExRobotDeep();
			exRobot1.setCoinCode(exCointoCoin.getCoinCode());
			exRobot1.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
			exRobot1.setFixPriceType(1);
			exRobot1.setRobotType(1);
			exRobotDeepService.save(exRobot1);
			exCointoCoinService.newinitRedisCode();

			return super.save(exCointoCoin);
		}else{
			JsonResult jsonResult=new JsonResult();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("已经存在");
			return jsonResult;
		}

	}

	@RequestMapping(value="/getFixPriceCoinList")
	@MethodName(name = "得到交易区类型")
	@ResponseBody
	public  List<ExTradingArea> getFixPriceCoinList(HttpServletRequest request) {
		String fixPriceType=request.getParameter("fixPriceType");
		if(null!=fixPriceType&&fixPriceType.equals("0")){
			List<ExTradingArea> list = new ArrayList<ExTradingArea>();
			String bykey = appConfigService.getBykey("language_code");
			ExTradingArea exTradingArea=new ExTradingArea();
			exTradingArea.setTradingArea(bykey);
			list.add(exTradingArea);
			return list;
		}else{
			QueryFilter filter = new QueryFilter(ExTradingArea.class);
			filter.addFilter("struts=",1);
			filter.setOrderby("sort asc");
			List<ExTradingArea> list = exTradingAreaService.find(filter);

			return list;
		}

	}

	@RequestMapping(value="/modifysee/{id}/{tradeArea}")
	public ModelAndView modifysee(@PathVariable Long id, @PathVariable String tradeArea){
		ExCointoCoin exCointoCoin = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excointocoinmodify");
		mav.addObject("model", exCointoCoin);
		mav.addObject("tradeArea", tradeArea);
		return mav;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	@CommonLog(name = "修改交易对")
	public JsonResult modify(HttpServletRequest request,ExCointoCoin exCointoCoin){
		if(exCointoCoin.getRose().intValue() >100){
			return new JsonResult().setSuccess(false).setMsg("限价涨幅不可大于100 %");
		}
		if(exCointoCoin.getDecline().intValue() >100){
			return new JsonResult().setSuccess(false).setMsg("限价跌幅不可大于100 %");
		}
		String tradeArea = request.getParameter("tradeArea");
		if (!StringUtils.isEmpty(tradeArea)) {
			exCointoCoin.setFixPriceCoinCode(tradeArea);
		}
		if(null==exCointoCoin.getAveragePrice()||exCointoCoin.getAveragePrice().compareTo(new BigDecimal("0"))<=0){
			JsonResult jsonResult=new JsonResult();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("发行价不能为空或者0或者负数");
			return jsonResult;
		}
		JsonResult update = super.update(exCointoCoin);

		//updateCoinInfoList2(exCointoCoin);
		exCointoCoinService.initRedisCode();
		exCointoCoinService.newinitRedisCode();
		return update;
	}


	/**
	 * 跳转到交易对管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/toList/{tradeArea}")
	public ModelAndView toList(@PathVariable String tradeArea, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/exchange/excointocoinlist");
		QueryFilter filter = new QueryFilter(ExTradingArea.class);
		filter.addFilter("struts=",1);
		filter.setOrderby("sort asc");
		List<ExTradingArea> list = exTradingAreaService.find(filter);
		mav.addObject("tradeAreaList", list);

		if ("none".equals(tradeArea)) {
			if (list != null && list.size() > 0) {
				ExTradingArea exTradingArea = list.get(0);
				tradeArea = exTradingArea.getTradingArea();
			}
		}
		mav.addObject("defaultTradeArea", tradeArea);
		return mav;
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String unstate = request.getParameter("unstate");
		String state = request.getParameter("state");
		String tradeArea = request.getParameter("tradeArea");
		QueryFilter filter = new QueryFilter(ExCointoCoin.class,request);
		if(!StringUtils.isEmpty(unstate)){
			filter.addFilter("state !=",unstate);
		}
		if(!StringUtils.isEmpty(state)){
			filter.addFilter("state =",state);
		}
		if(!StringUtils.isEmpty(tradeArea) && !"none".equals(tradeArea)){
			filter.addFilter("fixPriceCoinCode =",tradeArea);
		}
		filter.setOrderby("fixPriceCoinCode ASC,coinCode ASC");
		return super.findPage(filter);
	}


	@RequestMapping(value="/findall")
	@ResponseBody
	public List<ExCointoCoin> findall(HttpServletRequest request){
		return super.findAll();
	}


	@RequestMapping(value="/findpricecoin")
	@ResponseBody
	public List<ExCointoCoin> findpricecoin(HttpServletRequest request){
		List<ExCointoCoin> all = super.findAll();
		List<ExCointoCoin> list = new ArrayList<ExCointoCoin>();
		if(all!=null&&all.size()>0){
			Set<String> set = new HashSet<String>();
			for(ExCointoCoin exCointoCoin : all){
				set.add(exCointoCoin.getFixPriceCoinCode());
			}

			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()){
				ExCointoCoin exCointoCoin = new ExCointoCoin();
				exCointoCoin.setFixPriceCoinCode(iterator.next());
				list.add(exCointoCoin);
			}
		}
		return list;

	}

	@RequestMapping(value="/findpricecoining")
	@ResponseBody
	//不包含删除的交易对
	public List<ExCointoCoin> findpricecoining(HttpServletRequest request){
		List<ExCointoCoin> all = super.findAll();
		List<ExCointoCoin> list = new ArrayList<ExCointoCoin>();
		if(all!=null&&all.size()>0){
			Set<String> set = new HashSet<String>();
			for(ExCointoCoin exCointoCoin : all){
				if(exCointoCoin.getState().equals("2") || exCointoCoin.getState()==2){
					continue;
				}
				set.add(exCointoCoin.getFixPriceCoinCode());
			}

			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()){
				ExCointoCoin exCointoCoin = new ExCointoCoin();
				exCointoCoin.setFixPriceCoinCode(iterator.next());
				list.add(exCointoCoin);
			}
		}
		return list;

	}

	@MethodName(name="开启交易")
	@RequestMapping(value="/open/{id}")
	@ResponseBody
	@CommonLog(name = "开启交易对")
	public JsonResult open(@PathVariable Long id){
		JsonResult jsonResult=new JsonResult();

		ExCointoCoin exCointoCoin=this.service.get(id);

		if(exCointoCoin.getState().intValue()==1){
        	jsonResult.setSuccess(false);
    		jsonResult.setMsg("已经开启，请不要重复开启");
    		return jsonResult;
		}
		exCointoCoin.setState(1);
		jsonResult = super.update(exCointoCoin);
		exCointoCoinService.initRedisCode();
		exCointoCoinService.newinitRedisCode();

		//针对新交易对
		//生成K线 例如： BCH_BTC:klinedata:TransactionOrder_BCH_BTC_1
		exCointoCoinService.openNewCointoCoin(exCointoCoin);

		return jsonResult;
	}




	@MethodName(name="添加K线机器人")
	@RequestMapping(value="/addKlineRobot/{id}")
	@ResponseBody
	@CommonLog(name="添加k线机器人")
	public JsonResult addKlineRobot(@PathVariable Long id){
		ExCointoCoin exCointoCoin=this.service.get(id);
		JsonResult jsonResult = new JsonResult();
		QueryFilter queryFilter = new QueryFilter(ExRobot.class);
		queryFilter.addFilter("coinCode=",exCointoCoin.getCoinCode());
		queryFilter.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
		ExRobot exRobot = exRobotService.get(queryFilter);
		if( null != exRobot){
			jsonResult.setMsg("该交易对已有K线机器人");
			jsonResult.setSuccess(false);
			return jsonResult;
		}
		ExRobot exRobot1 = new ExRobot();
		exRobot1.setCoinCode(exCointoCoin.getCoinCode());
		exRobot1.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
		exRobot1.setFixPriceType(1);
		exRobotService.save(exRobot1);

		jsonResult.setSuccess(true);
		jsonResult.setMsg("添加成功");


		return jsonResult;
	}

	@MethodName(name="添加深度机器人")
	@RequestMapping(value="/addDeepRobot/{id}")
	@ResponseBody
	@CommonLog(name = "添加深度机器人")
	public JsonResult addDeepRobot(@PathVariable Long id){
		ExCointoCoin exCointoCoin=this.service.get(id);
		JsonResult jsonResult = new JsonResult();
		QueryFilter queryFilter = new QueryFilter(ExRobotDeep.class);
		queryFilter.addFilter("coinCode=",exCointoCoin.getCoinCode());
		queryFilter.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
		ExRobotDeep exRobot = exRobotDeepService.get(queryFilter);
		if( null != exRobot){
			jsonResult.setMsg("该交易对已有深度机器人");
			jsonResult.setSuccess(false);
			return jsonResult;
		}
		ExRobotDeep exRobot1 = new ExRobotDeep();
		exRobot1.setCoinCode(exCointoCoin.getCoinCode());
		exRobot1.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
		exRobot1.setFixPriceType(1);
		exRobotDeepService.save(exRobot1);

		jsonResult.setSuccess(true);
		jsonResult.setMsg("添加成功");


		return jsonResult;
	}


	@MethodName(name="关闭交易")
	@RequestMapping(value="/close/{id}")
	@ResponseBody
	@CommonLog(name = "关闭交易对")
	public JsonResult close(@PathVariable Long id){
		ExCointoCoin exCointoCoin=this.service.get(id);
		exCointoCoin.setState(0);
		JsonResult jsonResult=super.update(exCointoCoin);
		exCointoCoinService.initRedisCode();
		//关闭机器人
		ExRobotDeep exdeepRobot = closeExRobot(exCointoCoin);

		//保存机器人日志
		saveRobotLog(exCointoCoin, exdeepRobot);

		return jsonResult;
	}
	private void saveRobotLog(ExCointoCoin exCointoCoin, ExRobotDeep exdeepRobot) {
		ExRobotLog exRobotLog = new ExRobotLog();
		exRobotLog.setCloseTime(new Date());
		exRobotLog.setCoinCode(exCointoCoin.getCoinCode());
		exRobotLog.setRemark("删除交易对");
		exRobotLog.setFixCoin(exCointoCoin.getFixPriceCoinCode());
		exRobotLog.setPhone(exdeepRobot.getMobilePhone());
		exRobotLog.setEmail(exdeepRobot.getEmail());
		//查询机器人已成交委托
		String buykeycoin = exdeepRobot.getCoinCode() + "_" + exdeepRobot.getFixPriceCoinCode()+":deeprobot:buydeeprobotCounted";
		String sellkeycoin = exdeepRobot.getCoinCode() + "_" + exdeepRobot.getFixPriceCoinCode()+":deeprobot:selldeeprobotCounted";


		String buyed = redisService.get(buykeycoin);
		buyed = StringUtils.isEmpty(buyed) ? "0" : buyed;
		String selled = redisService.get(sellkeycoin);
		selled = StringUtils.isEmpty(selled) ? "0" : selled;
		exRobotLog.setBuyTotalNum(new BigDecimal(buyed));
		exRobotLog.setSellTotalNum(new BigDecimal(selled));

		exRobotLogService.save(exRobotLog);
	}

	private ExRobotDeep closeExRobot(ExCointoCoin exCointoCoin) {
		//关闭深度机器人
		QueryFilter queryFilter = new QueryFilter(ExRobotDeep.class);
		queryFilter.addFilter("coinCode=",exCointoCoin.getCoinCode());
		queryFilter.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
		ExRobotDeep exdeepRobot = exRobotDeepService.get(queryFilter);
		if(exdeepRobot!=null){
			exdeepRobot.setIsSratAuto(0);
			exRobotDeepService.update(exdeepRobot);
		}
		//关闭k线机器人

		QueryFilter queryFilter1 = new QueryFilter(ExRobot.class);
		queryFilter1.addFilter("coinCode=",exCointoCoin.getCoinCode());
		queryFilter1.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
		ExRobot exRobot = exRobotService.get(queryFilter);
		if(exRobot!=null){
			exRobot.setIsSratAuto(0);
			exRobotService.update(exRobot);
		}
		return exdeepRobot;
	}




	@MethodName(name="还原")
	@RequestMapping(value="/reset/{id}")
	@ResponseBody
	@CommonLog(name = "还原交易对")
	public JsonResult reset(@PathVariable Long id){
		JsonResult jsonResult = new JsonResult();
		ExCointoCoin exCointoCoin = this.service.get(id);
		// 获取交易币
		String coinCode = exCointoCoin.getCoinCode();
		// 获取定价币
		String fixPriceCoinCode = exCointoCoin.getFixPriceCoinCode();
		//（1）交易币XX发行状态是已下架状态（条件5）
		//提示信息：因交易币种XX是已下架状态，暂不能还原交易对请先前往币种回收站还原。
		if (!isUseable(coinCode)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("因交易币种" + coinCode + "是已下架状态，暂不能还原交易对请先前往币种回收站还原");
			return jsonResult;
		}
		//（2）定价币XX发行状态是已下架状态（条件6）
		//提示信息：因交易区XX是已下架状态，暂不能还原交易对请先前往币种回收站还原
		if (!isUseable(fixPriceCoinCode)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("因交易区" + fixPriceCoinCode + "是已下架状态，暂不能还原交易对请先前往币种回收站还原");
			return jsonResult;
		}
		exCointoCoin.setState(0);
		jsonResult = super.update(exCointoCoin);
		exCointoCoinService.initRedisCode();

		//添加机器人k线机器人
		ExRobot exRobot = new ExRobot();
		exRobot.setCoinCode(exCointoCoin.getCoinCode());
		exRobot.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
		exRobot.setFixPriceType(1);
		exRobot.setRobotType(1);
		exRobot.setAtuoPriceType(3);
		exRobotService.save(exRobot);

		//添加机器人深度机器人
		ExRobotDeep exRobot1 = new ExRobotDeep();
		exRobot1.setCoinCode(exCointoCoin.getCoinCode());
		exRobot1.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
		exRobot1.setFixPriceType(1);
		exRobot1.setRobotType(1);

		exRobotDeepService.save(exRobot1);

		return jsonResult;
	}

	/**
	 * 判断币种是否已上架
	 * @param coinCode
	 * @return
	 */
	private boolean isUseable(String coinCode) {
		QueryFilter qf = new QueryFilter(ExProduct.class);
		qf.addFilter("coinCode=", coinCode);
		ExProduct exProduct = exProductService.get(qf);
		if (exProduct.getIssueState() == 1) {
			return true;
		} else {
			return false;
		}
	}


	@MethodName(name="假删除交易对")
	@RequestMapping(value="/remove/{id}")
	@ResponseBody
	@CommonLog(name = "删除交易对")
	public JsonResult remove(@PathVariable Long id){
		ExCointoCoin exCointoCoin=this.service.get(id);
		if(1 == exCointoCoin.getState()){
			return new JsonResult().setSuccess(false).setMsg("请先关闭交易对再执行删除");
		}

		// 交易对有杠杆也不能删除
		QueryFilter lend_qf = new QueryFilter(ExLendConfig.class);
		lend_qf.addFilter("coinCode=", exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode());
		List<ExLendConfig> lendList = exLendConfigService.find(lend_qf);
		if (lendList != null && lendList.size() > 0) {
			return new JsonResult().setSuccess(false).setMsg("交易对有杠杆不能删除");
		}

		exCointoCoin.setState(2);
		JsonResult jsonResult=super.update(exCointoCoin);
		exCointoCoinService.initRedisCode();

		//删除k线机器人
		QueryFilter queryFilter = new QueryFilter(ExRobot.class);
		queryFilter.addFilter("coinCode=",exCointoCoin.getCoinCode());
		queryFilter.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
		exRobotService.delete(queryFilter);

		//删除深度机器人
		QueryFilter deepqueryFilter = new QueryFilter(ExRobotDeep.class);
		deepqueryFilter.addFilter("coinCode=",exCointoCoin.getCoinCode());
		deepqueryFilter.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
		exRobotDeepService.delete(deepqueryFilter);

		return jsonResult;
	}

	/**
	 * 查找所有交易对
	 * @return	BTC_USDT
	 */
	@RequestMapping(value = "/coinCodes")
	@ResponseBody
	public List<ExCointoCoin> coinCodes() {
		return ((ExCointoCoinService)service).findCoinCodes();
	}


}
