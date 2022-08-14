package hry.app.otc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.manage.remote.model.RemoteResult;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.User;
import hry.otc.remote.RemoteAdvertisementService;
import hry.redis.common.utils.RedisService;
import hry.util.common.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/otc")
@Api(value = "App操作类", description = "OTC - 不需要登录的方法", tags = "OTC - 不需要登录的方法")
public class IndexOtcMobileController {

	@Resource
	private RedisService redisService;

	@Resource
	private RemoteAdvertisementService remoteAdvertisementService;

	@Resource
	private RemoteManageService remoteManageService;

	@RequestMapping(value="/publish", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "跳转交易大厅", httpMethod = "POST", response = JsonResult.class, notes = "randomNum为随机码，添加广告的时候请带上")
	public ApiJsonResult publish(HttpServletRequest request){
		User user = TokenUtil.getUser(request);

			if(user != null){
				if(user.getStates() != 2) {
					return new ApiJsonResult().setSuccess(false).setCode("100").setMsg(SpringUtil.diff("qingxianshimingrenzheng"));
				}else{
					Map<String, Object> map = new HashMap<String, Object>();

					String str = redisService.get("otc:coinCodeList");
					if(StringUtil.isNotEmpty(str)){
						JSONArray parseArray = JSON.parseArray(str);
						List<Coin> list = new ArrayList<Coin>();
						for(int i=0;i<parseArray.size();i++){
							JSONObject jo = JSON.parseObject(parseArray.get(i).toString());

							Coin c = new Coin();
							c.setCoinCode(jo.getString("coinCode"));
							c.setCoinPercent(jo.getString("coinPercent"));

							list.add(c);
						}
						map.put("coinCodeFind", list);
					}

					Random random = new Random();
					int randomNum = random.nextInt(1000000);

					map.put("randomNum", randomNum);

					return new ApiJsonResult().setSuccess(true).setObj(map);
				}
			}
		return new ApiJsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
	}

	@RequestMapping(value="/releaseadvertisement/advertisingHall", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "跳转交易大厅 - 左侧导航", httpMethod = "POST", response = JsonResult.class, notes = "跳转广告大厅")
	public ApiJsonResult advertisingHall(){
		Map<String, Object> map = new HashMap<String, Object>();

		String str = redisService.get("otc:coinCodeList");
		if(StringUtil.isNotEmpty(str)){
			JSONArray parseArray = JSON.parseArray(str);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			parseArray.forEach(s -> {
				JSONObject jo = JSON.parseObject(s.toString());
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("coinCode", jo.getString("coinCode"));
				m.put("picturePath", jo.getString("picturePath"));
				list.add(m);
			});
			map.put("listCoinCodeFind", list);
		}
		map.put("coinCodeOne", "BTC");
		return new ApiJsonResult().setSuccess(true).setObj(map);
	}

	/**
	 * 广告大厅交易详情 -- 不登录可以查看广告大厅交易详情
	 * @return
	 */
	@RequestMapping(value="/releaseadvertisement/advertisingHallDetail", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "跳转交易大厅 - 左侧内容", httpMethod = "POST", response = JsonResult.class, notes = "payType：交易类型(1银行转账,2支付宝,3微信支付), nationality: 国籍"
			+ "coinCode：币种, transactionMode：交易方式(1在线购买,2在线出售,3本地购买), page：当前页数")
	public ApiJsonResult advertisingHallDetail(HttpServletRequest request,
											   @ApiParam(name = "payType", value = "交易类型(1银行转账,2支付宝,3微信支付)", required = true) @RequestParam String payType,
											   @ApiParam(name = "nationality", value = "国籍", required = true) @RequestParam String nationality,
											   @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam String coinCode,
											   @ApiParam(name = "transactionMode", value = "交易方式(1在线购买,2在线出售,3本地购买)", required = true) @RequestParam String transactionMode,
											   @ApiParam(name = "page", value = "当前页数", required = true) @RequestParam String page,
											   @ApiParam(name = "legalCurrency", value = "法币", required = false) @RequestParam(required = false) String legalCurrency){
		if("请选择".equals(nationality) || "Please select".equals(nationality)){
			nationality = "";
		}
		String offset=Integer.toString((Integer.valueOf(request.getParameter("page"))-1)*10);
		String limit="10";//Integer.toString(Integer.valueOf(request.getParameter("page"))*10);
		if(StringUtil.isNotEmpty(coinCode) && StringUtil.isNotEmpty(transactionMode)){
			FrontPage advertisingHallDetail = remoteAdvertisementService.advertisingHallDetail(payType,nationality,coinCode, transactionMode,offset,limit, legalCurrency);
			Map<String, Object> map = new HashMap<String, Object>();
			if(advertisingHallDetail.getPageSize()>0){
				List<hry.otc.remote.model.ReleaseAdvertisementRemote> list = advertisingHallDetail.getRows();
				for(int j=0;j<list.size();j++){
					RemoteResult jsonResult = remoteManageService.getById(list.get(j).getCustomerId() + "");
					if(jsonResult.getSuccess()){
						map.put("user", jsonResult.getObj());
					}

					String[] split = list.get(j).getPayType().split(",");
					String str = "";
					for(int i=0;i<split.length;i++){
						if("1".equals(split[i])){//银行转账
							//str += SpringUtil.diff("yinhangzhuanzhang");
							str += "1,";
						}else if("2".equals(split[i])){//支付宝
							//str += SpringUtil.diff("zhifubao2");
							str += "2,";
						}else if("3".equals(split[i])){//微信支付
							//str += SpringUtil.diff("weixinzhifu");
							str += "3,";
						}
					}
					if(!"".equals(str)){
						str = str.substring(0,str.length()-1);
						list.get(j).setPayType(str);

					}
					/*if(null!=list.get(j).getIsFixed()&&list.get(j).getIsFixed()==0){//市场价格
						BigDecimal yi = new BigDecimal("1");// 1
						BigDecimal yibai = new BigDecimal("100");// 100
						BigDecimal premiumBD = list.get(j).getPremium();
						BigDecimal shichangjiageBD = list.get(j).getTradeMoney();

						if (list.get(j).getTransactionMode()==1) {// 出售 卖低
							BigDecimal price = shichangjiageBD.multiply(yi.subtract(premiumBD.divide(yibai,4,BigDecimal.ROUND_HALF_DOWN))).setScale(2,BigDecimal.ROUND_HALF_DOWN); // 市场价格*(1-溢价的百分比)这是价格

							list.get(j).setTradeMoney(price);

						} else {// 购买 买高
							BigDecimal price = shichangjiageBD.multiply(yi.add(premiumBD.divide(yibai,4,BigDecimal.ROUND_HALF_DOWN))).setScale(2,BigDecimal.ROUND_HALF_DOWN);// 市场价格*(1+溢价的百分比)这是价格

							list.get(j).setTradeMoney(price);
						}
					}*/

					// 完成率
					BigDecimal completionRate = remoteAdvertisementService.getCompletionRate(list.get(j).getCustomerId(), list.get(j).getCoinCode());
					list.get(j).setCompletionRate(completionRate);
					map.put("list", list);
				}

				int keepDecimalForCoin = remoteAdvertisementService.keepDecimalForCoin(coinCode);
				for(int n=0;n<list.size();n++){
					list.get(n).setTradeMoney(list.get(n).getTradeMoney().setScale(3, BigDecimal.ROUND_DOWN));
				}
				advertisingHallDetail.setRows(list);
			}
			return new ApiJsonResult().setSuccess(true).setObj(map);
		}
		return new ApiJsonResult().setSuccess(false).setMsg("币种和交易方式不能为空");
	}

	@RequestMapping(value="/isPossible", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ApiOperation(value = "获取一条广告信息", httpMethod = "POST", response = JsonResult.class, notes = "")
	public JsonResult isPossible(HttpServletRequest request, @ApiParam(name = "id", value = "广告Id", required = true) @RequestParam String id) {
		JsonResult byId = remoteAdvertisementService.getById(Long.valueOf(id));
		if(byId!=null){
			return new JsonResult().setSuccess(true);
		}else {
			return new JsonResult().setSuccess(false);
		}
	}
}
