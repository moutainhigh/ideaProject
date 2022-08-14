package hry.app.trade;

import com.alibaba.fastjson.JSONObject;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.lend.remote.RemoteLeverTradeService;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.RemoteLendService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.Order;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.EntrustTrade;
import hry.util.common.Constant;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trades")
@Api(value= "个人中心 --> 我的交易", description ="个人中心 --> 我的交易", tags = "个人中心 --> 我的交易")
public class TradesController {

	/**
	 * 注册类型属性编辑器
	 *
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {

		// 系统注入的只能是基本类型，如int，char，String

		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

		/**
		 * 防止XSS攻击，并且带去左右空格功能
		 */
		binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
	}

	@Resource
	private RedisService redisService;

	@Resource
	private RemoteAppTransactionManageService remoteAppTransactionManageService;

	@Resource
	private RemoteLeverTradeService remoteLeverTradeService;

	@Resource
	private RemoteManageService remoteManageService;

	@Resource
	private RemoteLendService remoteLendService;


	/**
	 * 查询交易记录
	 *
	 * @return
	 */
	@RequestMapping("/user/list")
	@ApiOperation(value = "个人中心我的交易查询", httpMethod = "POST", notes = "个人中心我的交易查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
	})
	@ResponseBody
	@RequiresAuthentication
	public FrontPage list(HttpServletRequest request,
			  @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
			  @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
			  @ApiParam(name = "type", value = "类型，全部0，1买 ，2卖", required = true) @RequestParam("type") String type,
			  @ApiParam(name = "entrustNum", value = "查询委托单号", required = false) @RequestParam(value = "entrustNum",required = false) String entrustNum
			) {
		User user = TokenUtil.getUser(request);
		Map<String, String> params = HttpServletRequestUtils.getParams(request);
		params.put("userName", user.getMobile());
		if ("0".equals(type)) {// 0查全部
			params.put("type", null);
		}
		params.put("customerId", user.getCustomerId().toString());
		params.put("entrustNum", entrustNum);
		FrontPage findTrades = remoteAppTransactionManageService.frontselectFee(params);
		List<Order> list = findTrades.getRows();
		for(int i=0;i<list.size();i++){
			Order order = list.get(i);
			if(order.getType()==1){
				order.setFeeCoin(list.get(i).getCoinCode());
			}else if(order.getType()==2) {
				order.setFeeCoin(list.get(i).getFixPriceCoinCode());
			}
			order.setCoin(order.getCoinCode());
			order.setTransactionTime_long(order.getTransactionTime().getTime());
			order.setCoinCode(order.getCoinCode() + "-" + order.getFixPriceCoinCode());
		}
		return findTrades;
	}

	@CommonLog(name = "委托下单")
	@ApiOperation(value = "高级交易大厅-委托下单", httpMethod = "POST", response = ApiJsonResult.class, notes = "高级交易大厅-委托下单")
	@PostMapping("/user/entrustOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult entrustOrder(
			@ApiParam(name = "coinCode", value = "交易对代码", required = true) @RequestParam("coinCode") String coinCode,
			@ApiParam(name = "source", value = "来源，pc端：1；app端：3", required = true) @RequestParam("source") String source,
			@ApiParam(name = "type", value = "委托类型，买：1；卖：2", required = true) @RequestParam("type") String type,
			@ApiParam(name = "entrustWay", value = "委托方式，市价：2；限价：1", required = true) @RequestParam("entrustWay") String entrustWay,
			@ApiParam(name = "entrustPrice", value = "委托价格", required = true) @RequestParam("entrustPrice") String entrustPrice,
			@ApiParam(name = "entrustCount", value = "限价委托数", required = true) @RequestParam("entrustCount") String entrustCount,
			@ApiParam(name = "entrustSum", value = "市价委托数", required = true) @RequestParam("entrustSum") String entrustSum,
			@ApiParam(name = "isType", value = "0主交易1杠杆", required = true) @RequestParam("isType") String isType,
			HttpServletRequest request) {
		User user = TokenUtil.getUser(request);
		ApiJsonResult jsonResult = new ApiJsonResult();
		if (user != null) {
			if(StringUtils.isEmpty(isType)||!(isType.equals("0")||isType.equals("1"))){
				jsonResult.setSuccess(false);
				return jsonResult;
			}

			if(isType.equals("1")) {
				boolean isExplode = remoteLeverTradeService.explode(coinCode,user.getCustomerId());
				if(!isExplode){
					jsonResult.setSuccess(false);
					jsonResult.setMsg("账户已爆仓");
					return jsonResult;
				}
			}

			String isStop = redisService.get("deal:stop");
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("tibishibai"));
				return jsonResult;
			}

			String config = redisService.get("configCache:all");
			if (!StringUtils.isEmpty(config)) {
				JSONObject parseObject = JSONObject.parseObject(config);
				String isTrade = parseObject.get("isTrade").toString();
				// isTrade 1表示没有开启实名认证，0表示开启实名认证
				if (isTrade != null && "1".equals(isTrade)) {
					if (user.getIsChange() != 1) {
						String[] split = coinCode.split("_");
						EntrustTrade exEntrust = new EntrustTrade();
						//用户id
						exEntrust.setCustomerId(user.getCustomerId());
						exEntrust.setTrueName(user.getTruename());
						exEntrust.setCoinCode(split[0]);
						exEntrust.setFixPriceCoinCode(split[1]);
						exEntrust.setEntrustWay(Integer.valueOf(entrustWay));
						exEntrust.setType(Integer.valueOf(type));
						exEntrust.setSource(Integer.valueOf(source));
						exEntrust.setUserName(user.getUsername());
						exEntrust.setSurName(user.getSurname());
						exEntrust.setIsOpenCoinFee(user.getIsOpenCoinFee());
						exEntrust.setIsType(Integer.parseInt(isType));
						if (entrustWay.equals("1")) {
							exEntrust.setEntrustPrice(new BigDecimal(entrustPrice));
							exEntrust.setEntrustCount(new BigDecimal(entrustCount));
						} else if (entrustWay.equals("2")) {
							if (exEntrust.getType().intValue() == 1) {
								exEntrust.setEntrustSum(new BigDecimal(entrustSum));
							} else {
								exEntrust.setEntrustCount(new BigDecimal(entrustCount));
							}
						}

						// 委托业务
						String[] relt = remoteManageService.addEntrust(exEntrust);
						if (relt[0].equals(Constant.CODE_SUCCESS)) {
							jsonResult.setSuccess(true);
							jsonResult.setMsg(SpringUtil.diff("delegate_success"));
						} else {
							jsonResult.setSuccess(false);
							if (relt[1].contains("不足")) {
								jsonResult.setMsg(relt[1].replace("不足", "") + SpringUtil.diff("buzhu"));
							} else {
								if (relt[1].contains("~")) {
									jsonResult.setMsg(SpringUtil.diff(relt[1].split("~")[0]) + relt[1].split("~")[1]);
								} else {
									jsonResult.setMsg(SpringUtil.diff(relt[1]));
								}
							}
						}
						return jsonResult;
					} else {
						jsonResult.setMsg(SpringUtil.diff("jinzhijiaoyi"));
					}
				} else {
					User selectByTel = remoteManageService.selectByTel(user.getUsername());
					if (selectByTel != null && selectByTel.getStates() == 2) {
						if (selectByTel.getIsReal() == 1) {
							if (user.getIsChange() != 1) {
								String[] split = coinCode.split("_");
								EntrustTrade exEntrust = new EntrustTrade();
								//用户id
								exEntrust.setCustomerId(user.getCustomerId());
								exEntrust.setTrueName(user.getTruename());
								exEntrust.setCoinCode(split[0]);
								exEntrust.setFixPriceCoinCode(split[1]);
								exEntrust.setEntrustWay(Integer.valueOf(entrustWay));
								exEntrust.setType(Integer.valueOf(type));
								exEntrust.setSource(Integer.valueOf(source));
								exEntrust.setUserName(user.getUsername());
								exEntrust.setSurName(user.getSurname());
								exEntrust.setIsOpenCoinFee(user.getIsOpenCoinFee());
								exEntrust.setIsType(Integer.parseInt(isType));
								if (entrustWay.equals("1")) {
									exEntrust.setEntrustPrice(new BigDecimal(entrustPrice));
									exEntrust.setEntrustCount(new BigDecimal(entrustCount));
								} else if (entrustWay.equals("2")) {
									if (exEntrust.getType().intValue() == 1) {
										exEntrust.setEntrustSum(new BigDecimal(entrustSum));
									} else {
										exEntrust.setEntrustCount(new BigDecimal(entrustCount));
									}
								}

								// 委托业务
								String[] relt = remoteManageService.addEntrust(exEntrust);
								if (relt[0].equals(Constant.CODE_SUCCESS)) {
									jsonResult.setSuccess(true);
									jsonResult.setMsg(SpringUtil.diff("delegate_success"));
								} else {
									jsonResult.setSuccess(false);
									if (relt[1].contains("不足")) {
										jsonResult.setMsg(relt[1].replace("不足", "") + SpringUtil.diff("buzhu"));
									} else if (relt[1].contains("~")) {
										jsonResult.setMsg(SpringUtil.diff(relt[1].split("~")[0]) + relt[1].split("~")[1]);
									} else {
										jsonResult.setMsg(SpringUtil.diff(relt[1]));
									}
								}
							} else {
								jsonResult.setMsg(SpringUtil.diff("jinzhijiaoyi"));
							}
							return jsonResult;
						}
					}
					jsonResult.setMsg(SpringUtil.diff("qingxianshimingrenzheng"));
				}
			}
			jsonResult.setSuccess(false);
			return jsonResult;
		}
		jsonResult.setSuccess(false);
		jsonResult.setMsg(SpringUtil.diff("before_login"));
		return jsonResult;
	}

	@CommonLog(name = "前台撤销委托")
	@ApiOperation(value = "高级/基础交易大厅-撤销委托下单", httpMethod = "POST", response = ApiJsonResult.class, notes = "高级/基础交易大厅-撤销委托下单")
	@PostMapping("/user/cancelExEntrust")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult cancelExEntrust (
			@ApiParam(name = "entrustNums", value = "交易订单号", required = true) @RequestParam("entrustNums") String entrustNums,
			@ApiParam(name = "entrustPrice", value = "交易价格", required = true) @RequestParam("entrustPrice") String entrustPrice,
			@ApiParam(name = "coinCode", value = "交易对代码", required = true) @RequestParam("coinCode") String coinCode,
			@ApiParam(name = "type", value = "买卖类型，买：1，卖：2", required = true) @RequestParam("type") String type,
			HttpServletRequest request) {
		ApiJsonResult jsonResult = new ApiJsonResult();
		User user = TokenUtil.getUser(request);
		if (user != null) {
			Boolean pinging = remoteLendService.isPinging(user.getCustomerId(), null, null, null);
			if (pinging) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("goingPing"));
				return jsonResult;
			}

			String[] re = remoteManageService.checkPing(user.getCustomerId());
			if (re[0].equals("0000")) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff(re[1]));
				return jsonResult;
			}

			String[] split = coinCode.split("_");
			EntrustTrade entrustTrade = new EntrustTrade();
			entrustTrade.setEntrustNum(entrustNums);
			if (split[0].contains("-")) {
				String[] splitt = split[0].split("-");
				split[0] = splitt[0];
			}
			entrustTrade.setCoinCode(split[0]);
			entrustTrade.setType(Integer.valueOf(type));
			entrustTrade.setFixPriceCoinCode(split[1]);
			if (entrustPrice.equals("市价") || entrustPrice.equals("Market Price")) {
				entrustTrade.setEntrustPrice(new BigDecimal("0"));
			} else {
				entrustTrade.setEntrustPrice(new BigDecimal(entrustPrice));
			}
			String[] cancelExEntrust = remoteManageService.cancelExEntrust(entrustTrade);
			if("8888".equals(cancelExEntrust[0])){
				jsonResult.setSuccess(true);
				jsonResult.setMsg(SpringUtil.diff("revoke_success"));
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff(cancelExEntrust[1]));
			}
			
			return jsonResult;
		}
		jsonResult.setSuccess(false);
		jsonResult.setMsg(SpringUtil.diff("before_login"));
		return jsonResult;
	}

}
