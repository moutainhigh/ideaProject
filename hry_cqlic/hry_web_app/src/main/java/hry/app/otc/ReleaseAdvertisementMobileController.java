/**
 * Copyright:    互融云
 * @author:      denghf
 * @version:     V1.0
 * @Date:        2017-12-21 17:27:59
 */
package hry.app.otc;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.manage.remote.model.ExCointoMoney;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.otc.remote.RemoteAdvertisementService;
import hry.otc.remote.model.AppOrderSpeakRemote;
import hry.otc.remote.model.OtcAppTransactionRemote;
import hry.otc.remote.model.ReleaseAdvertisementRemote;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.common.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Copyright:   互融云
 * @author:      denghf
 * @version:     V1.0
 * @Date:        2017-12-21 17:27:59
 */
@RestController
@RequestMapping(value = "/otc/user/releaseadvertisement")
@RequiresAuthentication
@Api(value = "App操作类", description = "OTC - 跟广告有关的", tags = "OTC - 跟广告有关的")
public class ReleaseAdvertisementMobileController{

	@Resource
	private  RedisService redisService;

	@Resource
	private RemoteAdvertisementService remoteAdvertisementService;

	@Resource
	private RemoteManageService remoteManageService;

	public BigDecimal usdtToRmb(){
		String val = redisService.get("configCache:all");
		JSONObject jsonv = JSON.parseObject(val);
		return jsonv.getBigDecimal("usdttormb");
	}

	@RequestMapping(value="/getPriceNow", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "拉取平台币种价格", httpMethod = "POST", response = JsonResult.class, notes = "")
	public ApiJsonResult getPriceNow(HttpServletRequest request, @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode) {
		if (coinCode != null && !"".equals(coinCode)) {
			String coinCode0 = coinCode.split(",")[0];
			String val = redisService.get(coinCode0 + "_USDT:currentExchangPrice");
			if(StringUtil.isNotEmpty(val)){
				String financeConfig = redisService.get("CointoMoney:en");
				ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
				String price = new BigDecimal(exCointoMoney.getRate()).multiply(new BigDecimal(val)).setScale(3, BigDecimal.ROUND_HALF_UP) + "";
				return new ApiJsonResult().setSuccess(true).setMsg(price);
			}
			return new ApiJsonResult().setSuccess(true).setMsg("0");
		}
		return new ApiJsonResult().setSuccess(false);
	}

	/**
	 * 返回当前价格
	 * @return
	 */
	public String returnPrice(String bype){
		RedisService redisService = SpringUtil.getBean("redisService");
		JsonResult jsonResult = new JsonResult();
		if(bype != null){
			//获得USDT对人民币的汇率
			jsonResult = remoteAdvertisementService.getExchangeRateByCode("USDT");
		}
		if(bype.toUpperCase().equals("BTC")){
			String bithmb=	redisService.get("bithumb_btc_price");
			String huobi=	redisService.get("huobi_btc_price");
			if(bithmb!=null&&huobi!=null){
				BigDecimal b1=new BigDecimal(bithmb);
				BigDecimal b2=new BigDecimal(huobi);
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					b1 = b1.multiply(new BigDecimal(jsonResult.getObj().toString()));
					b2 = b2.multiply(new BigDecimal(jsonResult.getObj().toString()));
				}
				return (b1.add(b2)).multiply(new BigDecimal("2")).toString();

			}else if(bithmb==null&&huobi!=null){
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					return new BigDecimal(huobi).multiply(new BigDecimal(jsonResult.getObj().toString())).toString();
				}
				return huobi;
			}else if(bithmb!=null&&huobi==null){
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					return new BigDecimal(bithmb).multiply(new BigDecimal(jsonResult.getObj().toString())).toString();
				}
				return bithmb;
			}else {
				return "0";
			}

		}else if(bype.toUpperCase().equals("LTC")){
			String bithmb=	redisService.get("bithumb_ltc_price");
			String huobi=	redisService.get("huobi_ltc_price");
			if(bithmb!=null&&huobi!=null){
				BigDecimal b1=new BigDecimal(bithmb);
				BigDecimal b2=new BigDecimal(huobi);
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					b1 = b1.multiply(new BigDecimal(jsonResult.getObj().toString()));
					b2 = b2.multiply(new BigDecimal(jsonResult.getObj().toString()));
				}
				return (b1.add(b2)).multiply(new BigDecimal("2")).toString();
			}else if(bithmb==null&&huobi!=null){
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					return new BigDecimal(huobi).multiply(new BigDecimal(jsonResult.getObj().toString())).toString();
				}
				return huobi;
			}else if(bithmb!=null&&huobi==null){
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					return new BigDecimal(bithmb).multiply(new BigDecimal(jsonResult.getObj().toString())).toString();
				}
				return bithmb;
			}else {
				return "0";
			}
		}else if(bype.toUpperCase().equals("ETH")){
			String bithmb=	redisService.get("bithumb_eth_price");
			String huobi=	redisService.get("huobi_eth_price");
			if(bithmb!=null&&huobi!=null){
				BigDecimal b1=new BigDecimal(bithmb);
				BigDecimal b2=new BigDecimal(huobi);
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					b1 = b1.multiply(new BigDecimal(jsonResult.getObj().toString()));
					b2 = b2.multiply(new BigDecimal(jsonResult.getObj().toString()));
				}
				return (b1.add(b2)).multiply(new BigDecimal("2")).toString();
			}else if(bithmb==null&&huobi!=null){
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					return new BigDecimal(huobi).multiply(new BigDecimal(jsonResult.getObj().toString())).toString();
				}
				return huobi;
			}else if(bithmb!=null&&huobi==null){
				if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
					return new BigDecimal(bithmb).multiply(new BigDecimal(jsonResult.getObj().toString())).toString();
				}
				return bithmb;
			}else {
				return "0";
			}
		}else if(bype.toUpperCase().equals("USDT")){
			if(jsonResult != null && jsonResult.getSuccess() != null && jsonResult.getSuccess()){//如果存在汇率，则返回人民币
				return new BigDecimal(jsonResult.getObj().toString()).toString();
			}
			return "1";
		}
		return "0";
	}



	/**
	 * 保存发布广告
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/addreleaseadvertisement", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "保存发布广告", httpMethod = "POST", response = JsonResult.class, notes = "password：资金密码, transactionMode：1在线出售2在线购买, "
			+ "coinCode：币种, coinName：币种, nationality：国籍, isFixed：固定价格是否开启 0否 1是, tradeMoney：交易金额, premium：溢价, "
			+ "paymentTerm：付款期限, tradeMoneyMix：最低交易金额, tradeMoneyMax：最高交易金额, remark：备注, "
			+ "payType：交易类型(1银行转账,2支付宝,3微信支付), isSecurity：是否启用安全选项 0否 1是, isBeTrusted：是否启用仅限受信任的交易者 0否 1是, "
			+ "payTypeRemake：上传的资料, hidshichangjiage：市价, coinNumMin：币种最小数量, coinNumMax：币种最大数量")
	public ApiJsonResult addReleaseAdvertisement(HttpServletRequest request,
												 @ApiParam(name = "password", value = "资金密码", required = true) @RequestParam(required = false) String password,
												 @ApiParam(name = "transactionMode", value = "1在线出售2在线购买", required = true) @RequestParam(required = false) String transactionMode,
												 @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam(required = false) String coinCode,
												 @ApiParam(name = "coinName", value = "币种", required = true) @RequestParam(required = false) String coinName,
												 @ApiParam(name = "nationality", value = "国籍", required = true) @RequestParam(required = false) String nationality,
												 @ApiParam(name = "isFixed", value = "固定价格是否开启 0否 1是", required = true) @RequestParam(required = false) String isFixed,
												 @ApiParam(name = "tradeMoney", value = "交易金额", required = true) @RequestParam(required = false) String tradeMoney,
												 @ApiParam(name = "premium", value = "溢价", required = true) @RequestParam(required = false) String premium,
												 @ApiParam(name = "paymentTerm", value = "废弃", required = true) @RequestParam(required = false) String paymentTerm,
												 @ApiParam(name = "tradeMoneyMix", value = "最低交易金额", required = true) @RequestParam(required = false) String tradeMoneyMix,
												 @ApiParam(name = "tradeMoneyMax", value = "最高交易金额", required = true) @RequestParam(required = false) String tradeMoneyMax,
												 @ApiParam(name = "remark", value = "备注", required = true) @RequestParam(required = false) String remark,
												 @ApiParam(name = "isAutomatic", value = "废弃", required = true) @RequestParam(required = false) String isAutomatic,
												 @ApiParam(name = "payType", value = "交易类型(1银行转账,2支付宝,3微信支付)", required = true) @RequestParam(required = false) String payType,
												 @ApiParam(name = "isSecurity", value = "废弃", required = true) @RequestParam(required = false) String isSecurity,
												 @ApiParam(name = "isBeTrusted", value = "废弃", required = true) @RequestParam(required = false) String isBeTrusted,
												 @ApiParam(name = "payTypeRemake", value = "废弃", required = true) @RequestParam(required = false) String payTypeRemake,
												 @ApiParam(name = "hidshichangjiage", value = "市价", required = true) @RequestParam(required = false) String hidshichangjiage,
												 @ApiParam(name = "coinNumMin", value = "币种最小数量", required = true) @RequestParam(required = false) BigDecimal coinNumMin,
												 @ApiParam(name = "coinNumMax", value = "币种最大数量", required = true) @RequestParam(required = false) BigDecimal coinNumMax,
												 @ApiParam(name = "legalCurrency", value = "法币", required = true) @RequestParam(required = false) String legalCurrency){
		if(StringUtils.isEmpty(hidshichangjiage)){
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("laqujiageshibaibunengfabuguanggao"));
		}
		if( coinNumMax == null ||  "".equals(coinNumMax) || coinNumMin == null ||  "".equals(coinNumMin) ){
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("jiaoyishuliangbunengweikong"));
		}
		if( paymentTerm != null){
			paymentTerm=paymentTerm.replaceAll("min","");
		}
		User user = TokenUtil.getUser(request);
		//RemoteManageService remoteManageService = (RemoteManageService) SpringUtil.getBean("remoteManageService");
		if(user != null){
			//判断是否完成了实名认证
			if(user.getIsReal() != 1){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianwanchengshimingrenzheng"));
			}
			if("".equals(user.getAccountPassWord()) || user.getAccountPassWord()==null){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshezhizijinmima"));
			}
			Boolean b = remoteManageService.isAccountPassword(user.getCustomerId(), password);
			if (!b) {
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("zijinmimabuzhengque"));
			}
			/*if (transactionMode.equals("1")) {//出售判断下是否有币

				JsonResult jsonResult=	remoteCustomerService.getCoinAccount(user.getId(), coinCode);
				BigDecimal hotMoney= (BigDecimal)jsonResult.getObj();
				if(hotMoney.compareTo(BigDecimal.ZERO)==-1||hotMoney.compareTo(BigDecimal.ZERO)==0){
					return new JsonResult().setSuccess(false).setMsg("您可用币不足");
				}

			}*/
			if(isFixed.equals("1")){
				if(StringUtil.isEmpty(tradeMoney)){
					tradeMoney = hidshichangjiage;
					//return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("jiaoyijiageweikong"));
				}
			}else if(isFixed.equals("0")){
				/*String price=returnPrice(coinCode);

				if(price.equals("0")){
					return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("huoqushichangjiageshibai"));
				}else{
					tradeMoney=price;
				}*/

				tradeMoney = hidshichangjiage;
				System.out.println("市场价格："+tradeMoney);
				if(StringUtil.isEmpty(tradeMoney)){
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("huoqushichangjiageshibai"));
				}
				if(premium.equals("")||premium==null){
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("yijiaweikong"));
				}
			}

			//判断当前的支付方式是否存在(出售广告才判断)
			if(transactionMode != null && "1".equals(transactionMode)){
				if(payType != null && !"".equals(payType)){
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("customerId", user.getCustomerId());
					String[] split = payType.split(",");
					for(String s : split){
						params.put("type", s);
						JsonResult result = remoteAdvertisementService.getPersonalAsset(user.getCustomerId(), s, "1");
						//没有记录
						if(!result.getSuccess()){
							if("1".equals(s)){
								return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshezhiyinghangka"));
							}else if("2".equals(s)){
								return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshezhizhifubao"));
							}else if("3".equals(s)){
								return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshezhiweixin"));
							}
						}
					}
				}
			}
			/*System.out.println("transactionMode:" + transactionMode);
			System.out.println("coinCode:" + coinCode);
			System.out.println("coinName:" + coinName);
			System.out.println("nationality:" + nationality);
			System.out.println("isFixed:" + isFixed);
			System.out.println("tradeMoney:" + tradeMoney);
			System.out.println("premium:" + premium);
			System.out.println("paymentTerm:" + paymentTerm);
			System.out.println("tradeMoneyMix:" + tradeMoneyMix);
			System.out.println("tradeMoneyMax:" + tradeMoneyMax);
			System.out.println("remark:" + remark);
			System.out.println("isAutomatic:" + isAutomatic);
			System.out.println("isSecurity:" + isSecurity);
			System.out.println("isBeTrusted:" + isBeTrusted);
			System.out.println("user.getCustomerId():" + user.getCustomerId());
			System.out.println("payType:" + payType);
			System.out.println("payTypeRemake:" + payTypeRemake);
			System.out.println("coinNumMin:" + coinNumMin);
			System.out.println("coinNumMax:" + coinNumMax);
			System.out.println("hidshichangjiage:" + hidshichangjiage);*/



			JsonResult jsonResult = remoteAdvertisementService.addReleaseAdvertisement(transactionMode, coinCode,coinName, nationality,
			isFixed, tradeMoney, premium, paymentTerm, tradeMoneyMix, tradeMoneyMax,remark,
			isAutomatic, isSecurity, isBeTrusted, user.getCustomerId(), payType, payTypeRemake, "", coinNumMin, coinNumMax,hidshichangjiage, legalCurrency);

			//如果返回false,则不删除 randomNum
			if(!jsonResult.getSuccess()){
				return new ApiJsonResult().setSuccess(jsonResult.getSuccess()).setMsg(SpringUtil.diff(jsonResult.getMsg()));
			}
			request.getSession().removeAttribute("randomNum");
			return new ApiJsonResult().setSuccess(jsonResult.getSuccess()).setMsg(SpringUtil.diff(jsonResult.getMsg()));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/calculationCoin", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "计算自身币是否足够，并返回手续费", httpMethod = "POST", response = JsonResult.class, notes = "premium：溢价, shichangjiage：拉取价格, coinCode：币种, "
			+ "tradeMoneyMax：最高价格, transactionMode：交易类型, isFixeds：是否固定, tradeMoney：固定价格")
	public ApiJsonResult calculationCoin(HttpServletRequest request,
										 @ApiParam(name = "premium", value = "溢价", required = true) @RequestParam String premium,
										 @ApiParam(name = "shichangjiage", value = "拉取价格", required = true) @RequestParam String shichangjiage,
										 @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode,
										 @ApiParam(name = "tradeMoneyMax", value = "最高价格", required = true) @RequestParam String tradeMoneyMax,
										 @ApiParam(name = "transactionMode", value = "交易类型", required = true) @RequestParam String transactionMode,
										 @ApiParam(name = "isFixeds", value = "是否固定", required = true) @RequestParam String isFixeds,
										 @ApiParam(name = "tradeMoney", value = "固定价格", required = true) @RequestParam String tradeMoney){
		if( Boolean.parseBoolean(isFixeds) ){
			//固定价格
			if( ! StringUtils.isEmpty(tradeMoney)){
				BigDecimal b=new BigDecimal(tradeMoney);
				if( b.compareTo(new BigDecimal("0")) <=0 ){
					return  new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("gudingjiagebixudayueling"));
				}
			}else{
				return  new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("gudingjiagebixudayueling"));
			}
		}else{
			if( ! StringUtils.isEmpty(shichangjiage)){
				BigDecimal b=new BigDecimal(shichangjiage);
				if( b.compareTo(new BigDecimal("0")) <=0 ){
					return  new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("laqujiagexiaoyulingbunengxiadan"));
				}
			}else{
				return  new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("laqujiagexiaoyulingbunengxiadan"));
			}
		}
		BigDecimal moneyMax=new BigDecimal(tradeMoneyMax);
		if( moneyMax.compareTo(new BigDecimal("0")) <=0 ){
			return  new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("zuixiaojiaoyishuliangbixudayuling"));
		}
		User user = TokenUtil.getUser(request);
		if(user != null){
			JsonResult jsonResult =	remoteAdvertisementService.userExCoinParameter(user.getCustomerId(), premium, shichangjiage, coinCode, tradeMoneyMax, transactionMode, isFixeds, tradeMoney);
			jsonResult.setMsg(SpringUtil.diff(jsonResult.getMsg()));
			return new ApiJsonResult().setSuccess(jsonResult.getSuccess()).setMsg(jsonResult.getMsg());
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/selldetail", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "广告大厅-我要卖出页面", httpMethod = "POST", response = JsonResult.class, notes = "customerId：发布广告者的ID, coinCode：币种, id：广告id, transactionMode：交易方式1在线购买,2在线出售")
	public ApiJsonResult selldetail(HttpServletRequest request,
									@ApiParam(name = "customerId", value = "发布广告者的ID", required = true) @RequestParam String customerId,
									@ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode,
									@ApiParam(name = "id", value = "广告id", required = true) @RequestParam String id,
									@ApiParam(name = "transactionMode", value = "交易方式1在线购买,2在线出售", required = true) @RequestParam String transactionMode){
		User user = TokenUtil.getUser(request);
		if(user != null){
			Map<String, Object> map1 = new HashMap<String, Object>();
			if (customerId != null && customerId.equals(user.getCustomerId().toString())) {
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("bunenggoumaizijideguanggao"));
			}

			map1.put("coinCode", coinCode);
			map1.put("releaseId", id);
			if(StringUtil.isNotEmpty(customerId)){
				//左侧导航
				RemoteResult jsonResult = remoteManageService.getById(customerId);
				map1.put("aduser", jsonResult.getObj());
				map1.put("customerId", customerId);
				map1.put("transactionMode", transactionMode);

				JsonResult adUserInfor = remoteAdvertisementService.adUserInfor(Long.valueOf(customerId), coinCode);
				if(adUserInfor.getSuccess()){
					Map<Object, Object> map = (Map<Object, Object>)adUserInfor.getObj();
					map1.put("tradeNum", map.get("tradeNum"));
					map1.put("tradeNumAll", map.get("tradeNumAll"));
					map1.put("adUserAll", map.get("adUserAll"));
					map1.put("keepDecimalFixPrice", map.get("keepDecimalFixPrice"));
				}
			}
			//信任 - 频闭
			JsonResult trustShield = remoteAdvertisementService.selectTrustShield(user.getCustomerId(), Long.valueOf(customerId));
			if(trustShield.getSuccess()){
				Map<String, Object> map = (Map<String, Object>) trustShield.getObj();
				map1.put("trust", map.get("trust"));
				map1.put("shield", map.get("shield"));
			}

			//该币种小数位
			int keepDecimalForCoin = remoteAdvertisementService.keepDecimalForCoin(coinCode);

			//右侧
			JsonResult byId = remoteAdvertisementService.getById(Long.valueOf(id));
			if(byId!=null){
				ReleaseAdvertisementRemote obj = (ReleaseAdvertisementRemote) byId.getObj();


				/*if(obj.getPremium()!=null){//如果有溢价 那么把溢价的算到价格里去
					BigDecimal yi = new BigDecimal("1");// 1
					BigDecimal yibai = new BigDecimal("100");// 100
					BigDecimal price=obj.getTradeMoney().multiply(yi.add(obj.getPremium().divide(yibai))).setScale(2,BigDecimal.ROUND_HALF_DOWN); // 市场价格*(1-溢价的百分比)这是价格
					obj.setTradeMoney(price);
				}*/

				String[] split = obj.getPayType().split(",");
				String str = "";
				String span="";
				String li="";

				for(int i=0;i<split.length;i++) {
					if ("1".equals(split[i])) {//银行转账
						str += SpringUtil.diff("yinhangzhuanzhang");
					} else if ("2".equals(split[i])) {//支付宝
						str += SpringUtil.diff("zhifubao2");
					} else if ("3".equals(split[i])) {//微信支付
						str += SpringUtil.diff("weixinzhifu");
					}
				}

				if(!"".equals(str)){
					str = str.substring(0,str.length()-1);
					obj.setPayTypeString(str);
				}

				//根据后台配置得保留位数显示
				obj.setTradeMoney(obj.getTradeMoney().setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN));

				map1.put("release", obj);
				map1.put("span",span);
				map1.put("li",li);
			}

			ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = remoteAdvertisementService.getCoinAccountRedis(user.getCustomerId(), coinCode);

			if(exDigitalmoneyAccountRedis!=null){
				//根据后台配置得保留位数显示
				BigDecimal coinHotMoney=new BigDecimal("0");
				coinHotMoney = exDigitalmoneyAccountRedis.getHotMoney().setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN);
				map1.put("coinHotMoney", coinHotMoney);
			}
			return new ApiJsonResult().setSuccess(true).setObj(map1);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/buydetail", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "广告大厅-我要买入页面", httpMethod = "POST", response = JsonResult.class, notes = "customerId：发布广告者的ID, coinCode：币种, id：广告id, transactionMode：交易方式1在线购买,2在线出售")
	public ApiJsonResult buydetail(HttpServletRequest request,
								   @ApiParam(name = "customerId", value = "发布广告者的ID", required = true) @RequestParam String customerId,
								   @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode,
								   @ApiParam(name = "id", value = "广告id", required = true) @RequestParam String id,
								   @ApiParam(name = "transactionMode", value = "交易方式1在线购买,2在线出售", required = true) @RequestParam String transactionMode){
		User user = TokenUtil.getUser(request);
		if(user != null){
			Map<String, Object> map1 = new HashMap<String, Object>();
			if (customerId != null && customerId.equals(user.getCustomerId().toString())) {
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("bunenggoumaizijideguanggao"));
			}

			map1.put("coinCode", coinCode);
			map1.put("releaseId", id);
			if(StringUtil.isNotEmpty(customerId)){
				//左侧导航
				RemoteResult jsonResult = remoteManageService.getById(customerId);
				map1.put("aduser", jsonResult.getObj());
				map1.put("customerId", customerId);
				map1.put("transactionMode", transactionMode);

				JsonResult adUserInfor = remoteAdvertisementService.adUserInfor(Long.valueOf(customerId), coinCode);
				if(adUserInfor.getSuccess()){
					Map<Object, Object> map = (Map<Object, Object>)adUserInfor.getObj();
					map1.put("tradeNum", map.get("tradeNum"));
					map1.put("tradeNumAll", map.get("tradeNumAll"));
					map1.put("adUserAll", map.get("adUserAll"));
					map1.put("keepDecimalFixPrice", map.get("keepDecimalFixPrice"));
				}
			}
			//信任 - 频闭
			JsonResult trustShield = remoteAdvertisementService.selectTrustShield(user.getCustomerId(), Long.valueOf(customerId));
			if(trustShield.getSuccess()){
				Map<String, Object> map = (Map<String, Object>) trustShield.getObj();
				map1.put("trust", map.get("trust"));
				map1.put("shield", map.get("shield"));
			}
			//右侧
			JsonResult byId = remoteAdvertisementService.getById(Long.valueOf(id));
			if(byId!=null){
				ReleaseAdvertisementRemote obj = (ReleaseAdvertisementRemote) byId.getObj();

				String[] split = obj.getPayType().split(",");
				String str = "";
				for(int i=0;i<split.length;i++){
					if("1".equals(split[i])){//银行转账
						str += SpringUtil.diff("yinhangzhuanzhang");
					}else if("2".equals(split[i])){//支付宝
						str += SpringUtil.diff("zhifubao2");
					}else if("3".equals(split[i])){//微信支付
						str += SpringUtil.diff("weixinzhifu");
					}
				}
				if(!"".equals(str)){
					str = str.substring(0,str.length()-1);
					obj.setPayTypeString(str);
				}
				/*if(obj.getPremium()!=null){//如果有溢价 那么把溢价的算到价格里去
					BigDecimal yi = new BigDecimal("1");// 1
					BigDecimal yibai = new BigDecimal("100");// 100
					BigDecimal price=obj.getTradeMoney().multiply(yi.subtract(obj.getPremium().divide(yibai))).setScale(2,BigDecimal.ROUND_HALF_DOWN); // 市场价格*(1-溢价的百分比)这是价格
					obj.setTradeMoney(price);
				}*/
				map1.put("release", obj);
			}
			return new ApiJsonResult().setSuccess(true).setObj(map1);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/isCanTransaction", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "是否可以进行交易（即是否已经有当前类型的交易处于进行中）", httpMethod = "POST", response = JsonResult.class, notes = "coinCode：币种, id：广告id, transactionMode：交易方式1--出售广告  2--购买广告 ")
	public ApiJsonResult isCanTransaction(HttpServletRequest request,
										  @ApiParam(name = "transactionMode", value = "交易方式1--出售广告  2--购买广告", required = true) @RequestParam String transactionMode,
										  @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode){
		User user = TokenUtil.getUser(request);
		if(user!=null){
			JsonResult jsonResult = remoteAdvertisementService.isCanTransaction(user.getCustomerId() + "", transactionMode, coinCode);
			jsonResult.setMsg(SpringUtil.diff(jsonResult.getMsg()));
			return new ApiJsonResult().setSuccess(jsonResult.getSuccess()).setMsg(jsonResult.getMsg());
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	/**
	 * 判断是否实名了
	 *
	 * @return
	 */
	@RequestMapping(value="/isTrueName", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "判断是否实名了", httpMethod = "POST", response = JsonResult.class, notes = "")
	public ApiJsonResult isTrueName(HttpServletRequest request) {
		User user = TokenUtil.getUser(request);
		if(user != null){
			if (user.getIsReal() != 1) {
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshimingrenzheng"));
			}
			return new ApiJsonResult().setSuccess(true);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/tradedetail", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "买入 - 卖出", httpMethod = "POST", response = JsonResult.class, notes = "releaseId：广告ID, tradeMoney：交易金额, coinCodeMoney：交易数量 - 币, "
			+ "remark：备注, customerId：transactionMode为1，此id为卖方，为2，就为买方, transactionMode：1买入2卖出, coinCode：币种, "
			+ "payType：支付方式, editor：上传的资料 ")
	public ApiJsonResult tradedetail(HttpServletRequest request,
								  @ApiParam(name = "releaseId", value = "广告ID", required = true) @RequestParam String releaseId,
								  @ApiParam(name = "tradeMoney", value = "交易金额", required = true) @RequestParam String tradeMoney,
								  @ApiParam(name = "coinCodeMoney", value = "交易数量 - 币", required = true) @RequestParam String coinCodeMoney,
								  @ApiParam(name = "remark", value = "备注", required = true) @RequestParam String remark,
								  @ApiParam(name = "customerId", value = "transactionMode为1，此id为卖方，为2，就为买方", required = true) @RequestParam String customerId,
								  @ApiParam(name = "transactionMode", value = "1买入2卖出", required = true) @RequestParam String transactionMode,
								  @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode,
								  @ApiParam(name = "payType", value = "支付方式", required = true) @RequestParam String payType,
								  @ApiParam(name = "editor", value = "废弃", required = true) @RequestParam(required = false) String editor){
		if( coinCodeMoney == null ||  "".equals(coinCodeMoney)  ){
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("jiaoyishuliangbunengweikong"));
		}
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isEmpty(coinCodeMoney)){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("jiaoyishuliangbunengweikong"));
			}
			String sellId = customerId; //如果transactionMode为1，此id为卖方，为2，就为买方
			if(StringUtil.isEmpty(tradeMoney)){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("jinebunengweikong"));
			}

			//为卖-吃单的时候，判断当前用户是否添加了支付方式的相关信息
			if("2".equals(transactionMode) && payType != null && !"".equals(payType)){
				String[] split = payType.split(",");
				for(String s : split){
					JsonResult assetResult = remoteAdvertisementService.getPersonalAsset(user.getCustomerId(), s, "1");
					//没有记录
					if(!assetResult.getSuccess()){
						if("1".equals(s)){
							return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshezhiyinghangka"));
						}else if("2".equals(s)){
							return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshezhizhifubao"));
						}else if("3".equals(s)){
							return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianshezhiweixin"));
						}
					}
					break;
				}
			}


			if("1".equals(transactionMode)){//买
				JsonResult buydetail = remoteAdvertisementService.buydetail(Long.valueOf(releaseId), tradeMoney, coinCodeMoney, remark, user.getCustomerId(), Long.valueOf(sellId), transactionMode, coinCode, payType,user.getCustomerId(),editor);
				if(buydetail.getSuccess()){
					Map<String, Object> mapdetail = (Map<String, Object>) buydetail.getObj();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("releaseId", releaseId);
					map.put("tradeNum", mapdetail.get("transactionNum"));
					map.put("referenceNum", mapdetail.get("referenceNum"));

					//获得对方的信息记录
					User result = remoteManageService.getCustomerById(Long.valueOf(sellId));
					if(result == null){
						return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("meiyouciyonghu"));
					}
					//接单发送短信
					//RedisService redisService = SpringUtil.getBean("redisService");
					// 设置短信验证码到session中
					/*SmsParamRemote smsParam = new SmsParamRemote();
					String areaCode = u.getAreaCode();
					if(u.getMobile() != null && !"".equals(u.getMobile())){
						if(StringUtils.isEmpty(areaCode)){
							smsParam.setHryMobilephone(u.getMobile());
						}else{
							smsParam.setHryMobilephone(areaCode+" "+u.getMobile());
						}
						smsParam.setHrySmstype(SmsSendUtil.TRADE_FOUNDED);
						String code=SmsSendUtil.sendSmsCode(smsParam);
						System.out.println(u.getMobile()+"提示已经发送");
						if(code != null){
							map.put("tradeFoundedMsg","接单提醒发送成功");
						}
						//redisService.save(user.getMobile()+":TradeCode", code+"",120);
					}*/

					return new ApiJsonResult().setSuccess(true).setObj(map);
				}else{
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff(buydetail.getMsg()));
				}
			}else if("2".equals(transactionMode)){//卖
				if (null==coinCodeMoney||coinCodeMoney.equals("")) {
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingshurumaichudeshuliang"));
				}
				JsonResult buydetail = remoteAdvertisementService.buydetail(Long.valueOf(releaseId), tradeMoney, coinCodeMoney, remark, Long.valueOf(sellId), user.getCustomerId(), transactionMode, coinCode, payType,user.getCustomerId(),editor);
				if(buydetail.getSuccess()){
					Map<String, Object> mapdetail = (Map<String, Object>) buydetail.getObj();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("releaseId", releaseId);
					map.put("tradeNum", mapdetail.get("transactionNum"));
					map.put("referenceNum", mapdetail.get("referenceNum"));

					//获得对方的信息记录
					User result = remoteManageService.getCustomerById(Long.valueOf(sellId));
					if(result == null){
						return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("meiyouciyonghu"));
					}
					//接单发送短信
					//RedisService redisService = SpringUtil.getBean("redisService");
					// 设置短信验证码到session中
					/*SmsParamRemote smsParam = new SmsParamRemote();
					String areaCode = u.getAreaCode();
					if(u.getMobile() != null && !"".equals(u.getMobile())){
						if(StringUtils.isEmpty(areaCode)){
							smsParam.setHryMobilephone(u.getMobile());
						}else{
							smsParam.setHryMobilephone(areaCode+" "+u.getMobile());
						}
						smsParam.setHrySmstype(SmsSendUtil.TRADE_FOUNDED);
						String code=SmsSendUtil.sendSmsCode(smsParam);
						System.out.println(u.getMobile()+"提示已经发送");
						if(code != null){
							map.put("tradeFoundedMsg","接单提醒发送成功");
						}
						//redisService.save(user.getMobile()+":TradeCode", code+"",120);
					}*/
					return new ApiJsonResult().setSuccess(true).setObj(map);
				}else{
					if( "xinrenbenguanggao".equals( buydetail.getMsg() ) ){
						return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("xinrenbenguanggaofabuzhecaikeyijixujiaoyi"));
					}
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff(buydetail.getMsg()));
				}
			}
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/sellOrderAccounting", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "卖出第二步所需要的参数信息（该方法已经不用了）", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号, releaseId：广告Id")
	public JsonResult sellOrderAccounting(HttpServletRequest request, @RequestParam String tradeNum, @RequestParam String releaseId){
		User user = TokenUtil.getUser(request);
		if(user != null){
			Map<String, Object> map1 = new HashMap<String, Object>();

			if(StringUtil.isNotEmpty(tradeNum) && StringUtil.isNotEmpty(releaseId)){
				JsonResult orderAccounting = remoteAdvertisementService.orderAccounting(tradeNum, Long.valueOf(releaseId));
				Map<String, Object> map = (Map<String, Object>) orderAccounting.getObj();
				if(map!=null && map.size()>0){
					map1.put("app", map.get("app"));
					map1.put("payTypeRemake", map.get("payTypeRemake"));
					map1.put("list", map.get("list"));
					map1.put("paymentTerm", map.get("paymentTerm"));
					map1.put("releaseRemark", map.get("releaseRemark"));
					map1.put("releasePrice", map.get("releasePrice"));

					//判断当前用户是否为交易发起用户
					map1.put("bank", map.get("bank"));
					map1.put("alipay", map.get("alipay"));
					map1.put("wechat", map.get("wechat"));

					//设置订单redis有效时间
					JsonResult timeOrder = remoteAdvertisementService.redisTimeOrder(tradeNum, map.get("paymentTerm").toString());
					if(timeOrder != null ){
						map1.put("paymentTermTime",timeOrder.getObj());
					}else{//为空，则订单已经不存在，时间设置为0
						map1.put("paymentTermTime",0);
					}
				}
				map1.put("tradeNum", tradeNum);
				map1.put("releaseId",releaseId);

				return new JsonResult().setSuccess(true).setObj(map1);
			}
		}
		return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/orderAccounting", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "跳转订单核算界面---买入操作", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号, releaseId：广告Id")
	public ApiJsonResult orderAccounting(HttpServletRequest request,
										 @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum,
										 @ApiParam(name = "releaseId", value = "广告ID", required = true) @RequestParam String releaseId){
		User user = TokenUtil.getUser(request);
		if(user != null){
			Map<String, Object> map1 = new HashMap<String, Object>();

			if(StringUtil.isNotEmpty(tradeNum) && StringUtil.isNotEmpty(releaseId)){
				JsonResult orderAccounting = remoteAdvertisementService.orderAccounting(tradeNum, Long.valueOf(releaseId));
				Map<String, Object> map = (Map<String, Object>) orderAccounting.getObj();
				if(map!=null && map.size()>0){
					map1.put("app", map.get("app"));
					map1.put("payTypeRemake", map.get("payTypeRemake"));

					List<String> listNew = new ArrayList<String>();
					List<String> listOld = (List<String>)map.get("list");
					listOld.forEach(str -> {
						listNew.add(SpringUtil.diff(str));
					});
					listNew.set(listNew.size() - 1, listNew.get(listNew.size() - 1).toString().substring(0, listNew.get(listNew.size() - 1).toString().length() - 1));
					if(listNew.size() > 0){
						map1.put("payTypeList", listNew);
					}
					map1.put("paymentTerm", map.get("paymentTerm"));
					map1.put("releaseRemark", map.get("releaseRemark"));
					map1.put("releasePrice", map.get("releasePrice"));

					map1.put("bank", map.get("bank"));
					map1.put("alipay", map.get("alipay"));
					map1.put("wechat", map.get("wechat"));

					//设置订单redis有效时间
					JsonResult timeOrder = remoteAdvertisementService.redisTimeOrder(tradeNum, map.get("paymentTerm").toString());
					if(timeOrder != null ){
						map1.put("paymentTermTime",timeOrder.getObj());
					}else{//为空，则订单已经不存在，时间设置为0
						map1.put("paymentTermTime",0);
					}
				}
				map1.put("tradeNum", tradeNum);
				map1.put("releaseId",releaseId);

				return new ApiJsonResult().setSuccess(true).setObj(map1);
			}
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/userExCoinAccount", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "获取个人资产（可用资产、冻结、合计）", httpMethod = "POST", response = JsonResult.class, notes = "coinCode：币种")
	public ApiJsonResult userExCoinAccount(HttpServletRequest request,
										@ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode) {
		User user = TokenUtil.getUser(request);
		if(user != null){
			if (StringUtil.isNotEmpty(coinCode)) {
				ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = remoteAdvertisementService.getCoinAccountRedis(user.getCustomerId(), coinCode);

				if (exDigitalmoneyAccountRedis!=null) {
					//根据后台配置得保留位数显示
					int keepDecimalForCoin = remoteAdvertisementService.keepDecimalForCoin(coinCode);

					exDigitalmoneyAccountRedis.setColdMoney(exDigitalmoneyAccountRedis.getColdMoney().setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN));
					exDigitalmoneyAccountRedis.setHotMoney(exDigitalmoneyAccountRedis.getHotMoney().setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN));
				}
				return new ApiJsonResult().setSuccess(true).setObj(exDigitalmoneyAccountRedis);
			}
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("bizhongbucunzai"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}


	@RequestMapping(value="/selectOrderSpeak", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查询聊天显示", httpMethod = "POST", response = JsonResult.class, notes = "orderId：订单Id")
	public ApiJsonResult selectOrderSpeak(HttpServletRequest request,
									   @ApiParam(name = "orderId", value = "订单Id", required = true) @RequestParam String orderId){
		List<AppOrderSpeakRemote> list = remoteAdvertisementService.selectOrderSpeak(Long.valueOf(orderId));
		return new ApiJsonResult().setObj(list);
	}

	@RequestMapping(value="/addSellOrderSpeak", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "卖家保存聊天消息", httpMethod = "POST", response = JsonResult.class, notes = "orderId：订单Id, buyId：买方id, sellSpeak：卖方聊天记录")
	public ApiJsonResult addSellOrderSpeak(HttpServletRequest request,
										   @ApiParam(name = "orderId", value = "订单Id", required = true) @RequestParam String orderId,
										   @ApiParam(name = "buyId", value = "买方id", required = true) @RequestParam String buyId,
										   @ApiParam(name = "sellSpeak", value = "卖方聊天记录", required = true) @RequestParam String sellSpeak){
		User user = TokenUtil.getUser(request);
		Long sellId = user.getCustomerId();
		remoteAdvertisementService.addSellOrderSpeak(orderId, Long.valueOf(buyId), sellId, sellSpeak);
		return new ApiJsonResult().setSuccess(true);
	}

	@RequestMapping(value="/userapptransaction", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "获取个人买单卖单列表", httpMethod = "POST", response = JsonResult.class, notes = "transactionMode：1为购买 2为出售")
	public ApiJsonResult userAppTransaction(HttpServletRequest request,
											@ApiParam(name = "transactionMode", value = "1为购买 2为出售", required = true) @RequestParam String transactionMode,
											@ApiParam(name = "page", value = "当前页码", required = true) @RequestParam String page) {
		User user = TokenUtil.getUser(request);
		if(user != null){
			Map<String, String> params = HttpServletRequestUtils.getParams(request);
            String offset = Integer.toString((Integer.valueOf(request.getParameter("page"))-1) * Integer.valueOf(request.getParameter("limit")));
            params.put("offset", offset);
			if(StringUtil.isNotEmpty(transactionMode) && "1".equals(transactionMode)){
				params.put("buyUserId", user.getCustomerId().toString());
				params.put("buyIsDeleted", "1");
			}
			if(StringUtil.isNotEmpty(transactionMode) && "2".equals(transactionMode)){
				params.put("sellUserId", user.getCustomerId().toString());
				params.put("sellIsDeleted", "1");
			}
			FrontPage frontPage = remoteAdvertisementService.userAppTransaction(params);
			List<OtcAppTransactionRemote> rows = frontPage.getRows();
			for(int i=0;i<rows.size();i++){
				rows.get(i).setIdNow(user.getCustomerId());
				//根据后台配置得保留位数显示
				int keepDecimalForCoin = remoteAdvertisementService.keepDecimalForCoin(rows.get(i).getCoinCode());

				rows.get(i).setTradeMoney(rows.get(i).getTradeMoney().setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN));
				rows.get(i).setTradeNum(rows.get(i).getTradeNum().setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN));
			}
			return new ApiJsonResult().setSuccess(true).setObj(frontPage);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}


	@RequestMapping(value="/refundAndReject", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "申请退款 - 确认退款或驳回", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号, type订单状态, transactionMode：1买2卖, content：内容")
	public ApiJsonResult refundAndReject(HttpServletRequest request,
										 @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum,
										 @ApiParam(name = "type", value = "订单状态", required = true) @RequestParam String type,
										 @ApiParam(name = "transactionMode", value = "1为购买 2为出售", required = true) @RequestParam String transactionMode,
										 @ApiParam(name = "content", value = "内容", required = true) @RequestParam String content){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isNotEmpty(tradeNum)){
				JsonResult refund = remoteAdvertisementService.refundAndReject(tradeNum, type,transactionMode,content,user.getCustomerId());
				if(refund.getSuccess()){
					return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff(refund.getMsg()));
				}
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff(refund.getMsg()));
			}
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/cancleOrder", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "取消订单", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号")
	public ApiJsonResult cancleOrder(HttpServletRequest request,
									 @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum){
		User user = TokenUtil.getUser(request);
		if(user != null){
			remoteAdvertisementService.cancleOrder(tradeNum);
			return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("dingdanyiquxiao"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/orderCompleted", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "完成订单", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号")
	public ApiJsonResult orderCompleted(HttpServletRequest request,
										@ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isNotEmpty(tradeNum)){
				JsonResult orderCompleted = remoteAdvertisementService.orderCompleted(tradeNum, "");
				if(orderCompleted.getSuccess()){
					if(orderCompleted.getSuccess()){
						return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("wanchengdingdan"));
					}
					return new ApiJsonResult().setSuccess(false).setMsg(orderCompleted.getMsg());
				}else{
					if(orderCompleted.getObj()!=null && "1".equals(orderCompleted.getObj().toString())){

					}else{
						remoteAdvertisementService.cancleOrder(tradeNum);
					}
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff(orderCompleted.getMsg()));
				}

			}
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("dingdanyichang"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/orderPayment", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "完成支付", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号")
	public ApiJsonResult orderPayment(HttpServletRequest request,
								   @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isNotEmpty(tradeNum)){
				JsonResult OrderPayment = remoteAdvertisementService.orderPayment(tradeNum, "");
				if(OrderPayment.getSuccess()){
					if(OrderPayment.getSuccess()){
						return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff(OrderPayment.getMsg()));
					}
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff(OrderPayment.getMsg()));
				}else{
					if(OrderPayment.getObj()!=null && "1".equals(OrderPayment.getObj().toString())){

					}else{
						remoteAdvertisementService.cancleOrder(tradeNum);
					}
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff(OrderPayment.getMsg()));
				}
			}
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("dingdanyichang"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/deleteByTransactionNum", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "删除订单 1-买方  2-卖方", httpMethod = "POST", response = JsonResult.class, notes = "transactionNum：订单号, transactionMode：1买方  2卖方")
	public ApiJsonResult deleteByTransactionNum(HttpServletRequest request,
												@ApiParam(name = "transactionNum", value = "订单号", required = true) @RequestParam String transactionNum,
												@ApiParam(name = "transactionMode", value = "1为购买 2为出售", required = true) @RequestParam String transactionMode){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(transactionNum != null && transactionNum != "" && transactionMode != null && transactionMode != ""){
				JsonResult result = new JsonResult();
				result = remoteAdvertisementService.updateIsDeleted(transactionNum, transactionMode);
			if(result.getSuccess()){
				return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("shanchuchenggong"));
			}
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("dingdanyichang"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/updateStatus", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "更新广告状态", httpMethod = "POST", response = JsonResult.class, notes = "id：广告Id, status：传1即可")
	public ApiJsonResult updateStatus(HttpServletRequest request,
								   @ApiParam(name = "id", value = "广告Id", required = true) @RequestParam String id,
								   @ApiParam(name = "status", value = "传1即可", required = true) @RequestParam String status){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(status!=null&&("1".equals(status) || "0".equals(status))) {
				//解除冻结，并关闭此条广告
				//判断该广告是否有未完成的订单
				Boolean flag=remoteAdvertisementService.findOrderByStatus(Long.valueOf(id));
				if(! flag ){
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("bunengguanbi"));
				}
				JsonResult closeReleaseAdvertisement = remoteAdvertisementService.closeReleaseAdvertisement(Long.valueOf(id));
				return new ApiJsonResult().setSuccess(closeReleaseAdvertisement.getSuccess()).setMsg(SpringUtil.diff(closeReleaseAdvertisement.getMsg()));
			}else{
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("caozuoshiwu"));
			}
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/releaseAdvertisementlist", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "获取广告列表", httpMethod = "POST", response = JsonResult.class, notes = "")
	public ApiJsonResult releaseAdvertisementlist(HttpServletRequest request) {
		User user = TokenUtil.getUser(request);
		if(user != null){
			String offset = Integer.toString((Integer.valueOf(request.getParameter("page"))-1)*10);
			String limit = "10";

			Map<String, String> params = HttpServletRequestUtils.getParams(request);
			params.put("customerId", user.getCustomerId().toString());
			params.put("offset", offset);
			params.put("limit", limit);
			FrontPage frontPage = remoteAdvertisementService.queryReleaseAdvertisement(params);

			return new ApiJsonResult().setSuccess(true).setObj(frontPage);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/batchStatus", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "关闭开启所有广告", httpMethod = "POST", response = JsonResult.class, notes = "transactionMode: 数组 1为出售 2为购买")
	public ApiJsonResult batchUpdateStatus(HttpServletRequest request,
										   @ApiParam(name = "transactionMode", value = "1为购买 2为出售", required = true) @RequestParam String transactionMode){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isNotEmpty(transactionMode)){
				String[] split = transactionMode.split(",");

				Map<String,String> map = new HashMap<>();
				boolean flag = true;
                for(int i=0;i<split.length;i++){
                    //根据customer和mode关闭所有的广告
                    JsonResult jsonResult = remoteAdvertisementService.batchCloseAd(user.getCustomerId(), split[i]);
                    flag = flag || jsonResult.getSuccess();
                }
				return new ApiJsonResult().setSuccess(flag);
			}
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("qingxianxuanze"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/updateNickName", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "OTC昵称设置", httpMethod = "POST", response = JsonResult.class, notes = "nName：昵称")
	public ApiJsonResult updateNickName(HttpServletRequest request,
									 @ApiParam(name = "nName", value = "昵称", required = true) @RequestParam String nName){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isEmpty(nName)){
				return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("nichengbunengweikong"));
			}
			remoteAdvertisementService.updateNickName(nName, user.getCustomerId());
			user.setNickNameOtc(nName);
			return new ApiJsonResult().setSuccess(true).setMsg(SpringUtil.diff("shezhichenggong"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/addOrderSpeak", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "买家保存聊天消息", httpMethod = "POST", response = JsonResult.class, notes = "orderId：订单Id, sellId：卖方id, buySpeak：买方聊天记录")
	public ApiJsonResult addOrderSpeak(HttpServletRequest request,
									@ApiParam(name = "orderId", value = "订单Id", required = true) @RequestParam String orderId,
									@ApiParam(name = "sellId", value = "卖方id", required = true) @RequestParam String sellId,
									@ApiParam(name = "buySpeak", value = "买方聊天记录", required = true) @RequestParam String buySpeak){
		User user = TokenUtil.getUser(request);
		if(user != null){
			remoteAdvertisementService.addOrderSpeak(orderId, Long.valueOf(user.getCustomerId()), Long.valueOf(sellId), buySpeak);
			return new ApiJsonResult().setSuccess(true);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/getExCoinFee", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "买单页面输入文本框自动算出手续费", httpMethod = "POST", response = JsonResult.class, notes = "coinCodeMoney：钱, coinCodeL：币种")
	public ApiJsonResult getExCoinFee(HttpServletRequest request,
								   @ApiParam(name = "coinCodeMoney", value = "钱", required = true) @RequestParam String coinCodeMoney,
								   @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode){
		int keepDecimalForCoin = 0; // 小数位数
		BigDecimal eatFee = new BigDecimal(0); // 吃单交易手续费
		int eatFeeType = 0; // 吃单交易手续费类型 0固定费率 1百分比费率

		String productinfoListall = redisService.get("otc:coinCodeList");
		JSONArray parseArray = JSON.parseArray(productinfoListall);
		for(int k=0;k<parseArray.size();k++){
			JSONObject jo = JSON.parseObject(parseArray.get(k).toString());
			if(jo.getString("coinCode").equals(coinCode)){
				if (jo.getInteger("keepDecimalForCoin")!=null) {
					keepDecimalForCoin = jo.getInteger("keepDecimalForCoin");
				}
				if (jo.getBigDecimal("eatFee")!=null) {
					eatFee = jo.getBigDecimal("eatFee");
				}
				if (jo.getInteger("eatFeeType")!=null) {
					eatFeeType = jo.getInteger("eatFeeType");
				}
			}
		}

		if(eatFeeType == 0){
			return new ApiJsonResult().setSuccess(true).setObj(eatFee);
		}else if(eatFeeType == 1){
			BigDecimal setScale = new BigDecimal(coinCodeMoney).multiply((eatFee.divide(new BigDecimal(100)))).setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN);
			return new ApiJsonResult().setSuccess(true).setObj(setScale);
		}
		return new ApiJsonResult().setSuccess(false);
	}

	@RequestMapping(value="/trustShield", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "信任 - 屏蔽", httpMethod = "POST", response = JsonResult.class, notes = "userId：广告所属者, type：1信任 2屏蔽")
	public ApiJsonResult trustShield(HttpServletRequest request,
								  @ApiParam(name = "userId", value = "广告所属者", required = true) @RequestParam String userId,
								  @ApiParam(name = "type", value = "1信任 2屏蔽", required = true) @RequestParam String type){
		User user = TokenUtil.getUser(request);
		if(user != null){
			if(StringUtil.isNotEmpty(userId) && StringUtil.isNotEmpty(type)){
				JsonResult trustShield = remoteAdvertisementService.trustShield(user.getCustomerId(), Long.valueOf(userId), type);
				if(trustShield.getSuccess()){
					return new ApiJsonResult().setSuccess(true).setObj(trustShield.getObj());
				}
				return new ApiJsonResult().setSuccess(false);
			}
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/orderOk", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "确认订单", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号, releaseId：广告ID")
	public ApiJsonResult orderOk(HttpServletRequest request,
								 @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum,
								 @ApiParam(name = "releaseId", value = "广告ID", required = true) @RequestParam String releaseId){
		User user = TokenUtil.getUser(request);
		if(user != null){
			//String paymentTerm = request.getParameter("paymentTerm");
			if(StringUtil.isNotEmpty(tradeNum)){
				JsonResult jsonResult = remoteAdvertisementService.getById(Long.valueOf(releaseId));
				if (jsonResult!=null) {
					//解除冻结，并关闭此条广告
					//remoteTradeService.closeReleaseAdvertisement(releaseId);
					//确认订单，只关闭该广告  -- 不用关广告了  2018-5-16 14:35:41
					//JsonResult js = remoteTradeService.temporaryCloseReleaseAdvertisement(releaseId);

					//if(js.getSuccess()){
							ReleaseAdvertisementRemote releaseAdvertisementRemote=  (ReleaseAdvertisementRemote)jsonResult.getObj();
							JsonResult redisTimeOrder = remoteAdvertisementService.redisTimeOrder(tradeNum,releaseAdvertisementRemote.getPaymentTerm());
							if(redisTimeOrder!=null){
								return new ApiJsonResult().setSuccess(true).setObj(redisTimeOrder.getObj());
							}else{
								remoteAdvertisementService.cancleOrder(tradeNum);
								return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("dingdanyishixiao"));
							}
					/*}else{
						return new JsonResult().setSuccess(false).setMsg("关闭广告失败");
					}*/
				}else {
					return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("ciguanggaoyiguoqi"));
				}
			}
			return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("dingdanyichang"));
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/aduser", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "用户主页 - 广告者主页", httpMethod = "POST", response = JsonResult.class, notes = "id：id, coinCode：币种")
	public ApiJsonResult aduser(HttpServletRequest request,
							 @ApiParam(name = "id", value = "广告ID", required = true) @RequestParam String id,
							 @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode){
		User user = TokenUtil.getUser(request);
		if(user != null){
			Map<String, Object> map1 = new HashMap<String, Object>();
			JsonResult jsonResult = remoteAdvertisementService.getUserById(Long.valueOf(id));
			if(jsonResult.getSuccess()){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String, Object> map = (Map<String, Object>) jsonResult.getObj();
				User user_ = (User)map.get("user");
				map1.put("aduser", user_);
				map1.put("logoutTime", map.get("logoutTime")!=null?sdf.format(map.get("logoutTime")):"");
				map1.put("tradeTime", map.get("tradeTime")!=null?sdf.format(map.get("tradeTime")):"");
			}
			map1.put("avgTime", remoteAdvertisementService.avgTime(Long.valueOf(id)));

			JsonResult trustShield = remoteAdvertisementService.selectTrustShield(user.getCustomerId(), Long.valueOf(id));
			if(trustShield.getSuccess()){
				Map<String, Object> map = (Map<String, Object>) trustShield.getObj();
				map1.put("trust", map.get("trust"));
				map1.put("shield", map.get("shield"));
			}
			map1.put("coinCode", coinCode);

			return new ApiJsonResult().setSuccess(true).setObj(map1);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	/**
	 * 取一个币种的小数位
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getKeepDecimalForCoin",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "取一个币种的小数位", httpMethod = "POST", response = JsonResult.class, notes = "")
	public ApiJsonResult getKeepDecimalForCoin(HttpServletRequest request,
											   @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode){
		int keepDecimalForCoin = 0; // 小数位数

		String productinfoListall = redisService.get("otc:coinCodeList");
		JSONArray parseArray = JSON.parseArray(productinfoListall);
		for(int k=0;k<parseArray.size();k++){
			JSONObject jo = JSON.parseObject(parseArray.get(k).toString());
			if(jo.getString("coinCode").equals(coinCode)){
				if (jo.getInteger("keepDecimalForCoin")!=null) {
					keepDecimalForCoin = jo.getInteger("keepDecimalForCoin");
				}
			}
		}
		return new ApiJsonResult().setSuccess(true).setObj(keepDecimalForCoin);
	}

	/**
	 * 获取后台设置的挂单范围比例
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getOtcPercent" ,method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "获取后台设置的挂单范围比例", httpMethod = "POST", response = JsonResult.class, notes = "coinCode：币种 ,marketPrice :获取的市场价格")
	public ApiJsonResult getOtcPercent(HttpServletRequest request,
									   @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode,
									   @ApiParam(name = "marketPrice", value = "获取的市场价格", required = true) @RequestParam String marketPrice){
		Map<String,Object> map=new HashMap<String,Object>();
		BigDecimal minPrice = new BigDecimal("0");
		BigDecimal maxPrice = new BigDecimal("0");
		String otcMinPercent = "0";//otc广告价格的最低百分比
		String otcMaxPercent = "0";//otc广告价格的最高百分比
		try {
			String coinCode0=coinCode;
			if( coinCode.contains(",")){
				coinCode0 = coinCode.split(",")[0];
			}

			String productinfoListall = redisService.get("otc:coinCodeList");
			JSONArray parseArray = JSON.parseArray(productinfoListall);
			for (int k = 0; k < parseArray.size(); k++) {
				JSONObject jo = JSON.parseObject(parseArray.get(k).toString());
				if (jo.getString("coinCode").equals(coinCode0)) {
					if (jo.getString("otcMinPercent") != null) {
						otcMinPercent = jo.getString("otcMinPercent");
					}
					if (jo.getString("otcMaxPercent") != null) {
						otcMaxPercent = jo.getString("otcMaxPercent");
					}

				}
			}

			BigDecimal yi1 = new BigDecimal("1");// 1
			BigDecimal yibai1 = new BigDecimal("100");// 100
			BigDecimal shichangjiage = new BigDecimal(marketPrice);

			//最低限额判断
			if (otcMinPercent != null && !"".equals(otcMinPercent) && !"0".equals(otcMinPercent)) {
				BigDecimal minPercent = new BigDecimal(otcMinPercent);
				minPrice = shichangjiage.multiply(yi1.subtract(minPercent.divide(yibai1))).setScale(3, BigDecimal.ROUND_DOWN);

			}
			//最高限额判断
			if (otcMaxPercent != null && !"".equals(otcMaxPercent) && !"0".equals(otcMaxPercent)) {
				BigDecimal maxPercent = new BigDecimal(otcMaxPercent);
				maxPrice = shichangjiage.multiply(yi1.add(maxPercent.divide(yibai1))).setScale(3, BigDecimal.ROUND_DOWN);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		map.put("minPrice",minPrice);
		map.put("maxPrice",maxPrice);
		map.put("otcMinPercent",otcMinPercent);
		map.put("otcMaxPercent",otcMaxPercent);
		return new ApiJsonResult().setSuccess(true).setObj(map);
	}

    /**
     * 查询订单状态
     * @return
     */
    @RequestMapping(value="/findOtcOrderById", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ApiOperation(value = "查询订单状态", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号")
	public ApiJsonResult findOtcOrderById(HttpServletRequest request,
										  @ApiParam(name = "tradeNum", value = "订单号", required = true) @RequestParam String tradeNum){
        User user = TokenUtil.getUser(request);
        if(user != null){
            JsonResult jsonResult = remoteAdvertisementService.findOtcOrderById(tradeNum);
            if(jsonResult.getSuccess()){
                return new ApiJsonResult().setSuccess(true).setObj(jsonResult.getObj());
            }
            return new ApiJsonResult().setSuccess(false);
        }
        return new ApiJsonResult().setSuccess(false);
    }

	@RequestMapping(value="/findLegalCurrency", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查询系统法币配置", httpMethod = "POST", response = JsonResult.class, notes = "tradeNum：订单号")
	public ApiJsonResult findLegalCurrency(HttpServletRequest request){
		User user = TokenUtil.getUser(request);
		if(user != null){
			String s = redisService.get("otc:exLawcoin");
			return new ApiJsonResult().setSuccess(true).setObj(s);
		}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}
}
