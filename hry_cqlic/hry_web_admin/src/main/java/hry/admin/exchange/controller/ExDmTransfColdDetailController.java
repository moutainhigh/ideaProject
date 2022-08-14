/**
 * Copyright:
 * @author:      tianpengyu
 * @version:     V1.0
 * @Date:        2018-06-27 18:01:50
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSON;
import hry.admin.exchange.model.ExDmTransfColdDetail;
import hry.admin.exchange.service.ExDmTransfColdDetailService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.listener.ServerManagement;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.util.http.HttpConnectionUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.security.Check;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0
 * @Date:        2018-06-27 18:01:50
 */
@Controller
@RequestMapping("/exchange/exdmtransfcolddetail")
public class ExDmTransfColdDetailController extends BaseController<ExDmTransfColdDetail, Long> {
	private static Logger logger = Logger.getLogger(ExDmTransfColdDetailController.class);
	@Resource(name = "exDmTransfColdDetailService")
	@Override
	public void setService(BaseService<ExDmTransfColdDetail, Long> service) {
		super.service = service;
	}


	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExDmTransfColdDetail exDmTransfColdDetail = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmtransfcolddetailsee");
		mav.addObject("model", exDmTransfColdDetail);
		return mav;
	}


	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExDmTransfColdDetail exDmTransfColdDetail){
		return super.save(exDmTransfColdDetail);
	}

	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExDmTransfColdDetail exDmTransfColdDetail = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmtransfcolddetailmodify");
		mav.addObject("model", exDmTransfColdDetail);
		return mav;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExDmTransfColdDetail exDmTransfColdDetail){
		return super.update(exDmTransfColdDetail);
	}


	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExDmTransfColdDetail.class,request);
		return super.findPage(filter);
	}

	@MethodName(name = "查询钱包余额列表")
	@RequestMapping("/listWalletBalance")
	@ResponseBody
	public PageResult listWalletBalance(HttpServletRequest request){
		String url = PropertiesUtils.APP.getProperty("app.coinip");
		url = url + "/coin/listWalletBalance";
		String result = "" ;
		try {
			result = HttpConnectionUtil.getSend(url, null);
		}catch (Exception e){
			//e.printStackTrace();
            logger.error("查询钱包余额列表超时");
		}

		List<Object> list = new ArrayList<>();
		if (StringUtils.isNotEmpty(result)) {
			list = JSON.parseArray(result, Object.class);
		}

		PageResult pageResult = new PageResult();
		pageResult.setRows(list);
		pageResult.setPage(1);
		pageResult.setPageSize(100);
		pageResult.setRecordsTotal(100L);
		pageResult.setTotalPage(1L);
		return pageResult;
	}

	@MethodName(name = "充值旷工费")
	@RequestMapping(value="/rechargeTxFee2TokenAddress",method=RequestMethod.POST)
	@ResponseBody
	@CommonLog(name = "充值矿工费")
	public JsonResult rechargeTxFee2TokenAddress(HttpServletRequest request) {
		JsonResult result = new JsonResult();
		String address=request.getParameter("address");
		String amount=request.getParameter("amount");
		String coinType=request.getParameter("coinType");
		Map<String, String> params = new HashMap<String, String>();
		params.put("coinType", coinType);
		params.put("address", address);
		params.put("amount", amount);

		String url = PropertiesUtils.APP.getProperty("app.coinip");
		url = url + "/coin/rechargeTxFee2TokenAddress";
		String[] args = { address ,amount,coinType};
		String authcode = Check.authCode(args);
		params.put("authcode", authcode);
		String param = StringUtil.string2params(params);

		String data = "";
		try {
			data = HttpConnectionUtil.postSend(url, param);
		}catch (Exception e){
			logger.error("充值矿工费接口调用异常");
		}


		if (StringUtils.isNotEmpty(data)) {
			result = JSON.parseObject(data, JsonResult.class);
			if (result.getSuccess()) {
				result.setMsg("充值成功");
			}
		} else {
			result.setMsg("网络错误");
		}

		return result;
	}


    /**
     * 转入冷钱包
     * @param request
     * @return
     */
    @MethodName(name = "toColdAccount")
    @RequestMapping(value="/toColdAccount",method= RequestMethod.POST)
    @ResponseBody
    public JsonResult toColdAccount(HttpServletRequest request) {
        String type = request.getParameter("type");
        String amount = request.getParameter("amount");
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("amount", amount);
        return ((ExDmTransfColdDetailService)service).toColdAccount(params);
    }

	@MethodName(name = "查询代币资产")
	@RequestMapping("/listTokenAssets")
	@ResponseBody
	public PageResult listTokenAssets(HttpServletRequest request){
		String coinType = request.getParameter("coinType");
		List<Object> list = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(coinType)) {
			String url = PropertiesUtils.APP.getProperty("app.coinip");
			url = url + "/coin/listTokenAddressAssets";
			Map<String, String> map = new HashMap<String, String>();
			map.put("coinType", coinType);
			String param = StringUtil.string2params(map);

			String result ="" ;
			try {
				result = HttpConnectionUtil.postSend(url, param);
			}catch (Exception e){
				//e.printStackTrace();
                logger.error("查询代币资产超时");
			}

			if (StringUtils.isNotEmpty(result)) {
				JsonResult data = JSON.parseObject(result, JsonResult.class);
				if (data.getSuccess() && data.getObj() != null && StringUtils.isNotEmpty(data.getObj().toString())) {
					list = JSON.parseArray(data.getObj().toString(), Object.class);
				}
			}
		}
		PageResult pageResult = new PageResult();
		pageResult.setRows(list);
		pageResult.setPage(1);
		pageResult.setPageSize(100);
		pageResult.setRecordsTotal(100L);
		pageResult.setTotalPage(1L);
		return pageResult;
	}
	@MethodName(name = "代币归集")
	@RequestMapping(value="/tokenCollect",method=RequestMethod.POST)
	@ResponseBody
	@CommonLog(name = "归集")
	public JsonResult tokenCollect(HttpServletRequest request) {
		String coinType=request.getParameter("coinType");
		String address=request.getParameter("address");
		String amount=request.getParameter("amount");
		Map<String, String> params = new HashMap<String, String>();
		params.put("coinType", coinType);
		params.put("address", address);
		params.put("amount", amount);

		JsonResult result = new JsonResult();
		String url = PropertiesUtils.APP.getProperty("app.coinip");
		url = url + "/coin/tokenCollect";
		String param = StringUtil.string2params(params);
		String data = HttpConnectionUtil.postSend(url, param);
		result = JSON.parseObject(data, JsonResult.class);
		if (result.getSuccess()) {
			result.setMsg("归集成功");
		}
		return result;
	}

}
