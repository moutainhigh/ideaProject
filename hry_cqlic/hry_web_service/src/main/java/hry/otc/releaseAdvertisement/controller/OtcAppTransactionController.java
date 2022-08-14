/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 18:06:52 
 */
package hry.otc.releaseAdvertisement.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.otc.releaseAdvertisement.model.*;
import hry.otc.remote.model.RedisModel;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;

import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.customer.user.model.AppCustomer;
import hry.front.redis.model.UserRedis;
import hry.otc.releaseAdvertisement.service.AppAppealService;
import hry.otc.releaseAdvertisement.service.ExCoinFeeService;
import hry.otc.releaseAdvertisement.service.OtcAppTransactionService;
import hry.otc.releaseAdvertisement.service.ReleaseAdvertisementService;
import hry.otc.remote.model.AppAppealRemote;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.web.otc.service.impl.OtcServiceImpl;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 18:06:52 
 */
@Controller
@RequestMapping("/releaseAdvertisement/otcapptransaction")
public class OtcAppTransactionController extends BaseController<OtcAppTransaction, Long> {
	
	@Resource
	private OtcServiceImpl otcService;
	
	@Resource
	private ReleaseAdvertisementService releaseAdvertisementService;
	
	@Resource
    private RedisService redisService;
	
	@Resource
	private AppAppealService appAppealService;
	
	@Resource
	private ExCoinFeeService exCoinFeeService;
	
	@Resource(name = "otcAppTransactionService")
	@Override
	public void setService(BaseService<OtcAppTransaction, Long> service) {
		super.service = service;
	}
	
	public ExDigitalmoneyAccountRedis getCoinAccountRedis(Long userId, String coinCode){
    	// 获取个人可用币总数
 		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
 		UserRedis userRedis = redisUtil.get(userId.toString());
 		// 获得缓存中所有的币账户id
 		Map<String, Long> map = userRedis.getDmAccountId();
 		Set<String> keySet = map.keySet();
 		
 		ExDigitalmoneyAccountRedis dmAccount = null;
 		for (String key : keySet) {
 			if(key.equals(coinCode)){
 				RedisUtil<ExDigitalmoneyAccountRedis>  a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
 				dmAccount = a.get(userRedis.getDmAccountId(key).toString());
 			}
 		}
 		return dmAccount;
    }
	
	@MethodName(name = "查看OtcAppTransaction")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public Map<String, Object> see(@PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		
		OtcAppTransaction otcAppTransaction = super.service
				.get(new QueryFilter(OtcAppTransaction.class).addFilter("id=", id));
		
		if (otcAppTransaction != null) {
			// 订单信息			
			if (otcAppTransaction.getStatus() == 1) {
				otcAppTransaction.setStateZHCN("待确认订单");
			} else if (otcAppTransaction.getStatus() == 2) {
				otcAppTransaction.setStateZHCN("已确认订单");
			} else if (otcAppTransaction.getStatus() == 3) {
				otcAppTransaction.setStateZHCN("已完成支付");
			} else if (otcAppTransaction.getStatus() == 4) {
				otcAppTransaction.setStateZHCN("申诉中待回复");
			} else if (otcAppTransaction.getStatus() == 5) {
				otcAppTransaction.setStateZHCN("已取消");
			} else if (otcAppTransaction.getStatus() == 6) {
				otcAppTransaction.setStateZHCN("申请退款中");
			} else if (otcAppTransaction.getStatus() == 7) {
				otcAppTransaction.setStateZHCN("退款已驳回");
			} else if (otcAppTransaction.getStatus() == 8) {
				otcAppTransaction.setStateZHCN("申诉完成");
			} else if (otcAppTransaction.getStatus() == 9) {
				otcAppTransaction.setStateZHCN("申诉成功,待确认");
			} else if (otcAppTransaction.getStatus() == 10) {
				otcAppTransaction.setStateZHCN("申诉失败,待确认");
			} else if (otcAppTransaction.getStatus() == 11) {
				otcAppTransaction.setStateZHCN("平台通过申诉");
			} else if (otcAppTransaction.getStatus() == 12) {
				otcAppTransaction.setStateZHCN("平台驳回申诉");
			} else if (otcAppTransaction.getStatus() == 13) {
				otcAppTransaction.setStateZHCN("退款成功");
			} else if (otcAppTransaction.getStatus() == 14) {
				otcAppTransaction.setStateZHCN("已完成的项目");
			}
			String str = "";
			String[] split = otcAppTransaction.getPayType().split(",");
			if (split.length > 0) {
				for (int i = 0; i < split.length; i++) {
					if ("1".equals(split[i])) {
						str += "银行转账,";
					} else if ("2".equals(split[i])) {
						str += "支付宝,";
					} else if ("3".equals(split[i])) {
						str += "微信,";
					}
				}
			}
			if (str.length() > 1) {
				str = str.substring(0, str.length() - 1);
			}
			otcAppTransaction.setPayType(str);
			map.put("app", otcAppTransaction);
			// 支付凭证
			ReleaseAdvertisement releaseAdvertisement = releaseAdvertisementService
					.get(otcAppTransaction.getAdvertisementId());
			map.put("releaseId", otcAppTransaction.getAdvertisementId());
			if (releaseAdvertisement != null) {
				map.put("payTypeRemake", releaseAdvertisement.getPayTypeRemake());
				map.put("isFixed", releaseAdvertisement.getIsFixed());
				map.put("paymentTerm", releaseAdvertisement.getPaymentTerm());

				if (releaseAdvertisement.getIsFixed() == 0) {// 市场价格
					BigDecimal yi = new BigDecimal("1");// 1
					BigDecimal yibai = new BigDecimal("100");// 100
					BigDecimal premiumBD = releaseAdvertisement.getPremium();
					BigDecimal shichangjiageBD = releaseAdvertisement.getTradeMoney();

					if (releaseAdvertisement.getTransactionMode() == 1) {// 出售
																			// 卖低
						BigDecimal price = shichangjiageBD
								.multiply(yi.subtract(premiumBD.divide(yibai, 4, BigDecimal.ROUND_DOWN))); // 市场价格*(1-溢价的百分比)这是价格

						map.put("tradeMoney", price.toString());

					} else {// 购买 买高
						BigDecimal price = shichangjiageBD
								.multiply(yi.add(premiumBD.divide(yibai, 4, BigDecimal.ROUND_DOWN)));// 市场价格*(1+溢价的百分比)这是价格

						map.put("tradeMoney", price.toString());
					}
				} else {
					map.put("tradeMoney", releaseAdvertisement.getTradeMoney().toString());
				}
			}
			// 述求信息
			AppAppeal appAppeal = appAppealService.get(
					new QueryFilter(AppAppeal.class).addFilter("transactionNum=", otcAppTransaction.getTransactionNum()));
			if (appAppeal != null) {	
				AppAppealRemote bean2bean = ObjectUtil.bean2bean(appAppeal, AppAppealRemote.class);
				//买方证据图片
				if (StringUtil.isNotEmpty(bean2bean.getThingUrl())) {
					String[] spl = bean2bean.getThingUrl().split(",");
					List<String> list = new LinkedList<String>();
					for (int i = 0; i < spl.length; i++) {
						list.add(spl[i]);
					}
					bean2bean.setImgUrl(list);
				}
				//卖方证据图片
				if (StringUtil.isNotEmpty(bean2bean.getThingUrlSell())) {
					String[] spl = bean2bean.getThingUrlSell().split(",");
					List<String> list = new LinkedList<String>();
					for (int i = 0; i < spl.length; i++) {
						list.add(spl[i]);
					}
					bean2bean.setImgUrlSell(list);
				}
				map.put("appAppealRemote", bean2bean);
			}
		}
		return map;
	}
	
	@MethodName(name="增加OtcAppTransaction")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,OtcAppTransaction otcAppTransaction){
		return super.save(otcAppTransaction);
	}
	
	@MethodName(name="修改OtcAppTransaction")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,OtcAppTransaction otcAppTransaction){
		return super.update(otcAppTransaction);
	}
	
	@MethodName(name="删除OtcAppTransaction")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "全部订单查询")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OtcAppTransaction.class,request);
		PageResult pageResult=((OtcAppTransactionService)service).findPageBySql(filter,1);
		return pageResult;
	}
	
	@MethodName(name = "进行中交易查询")
	@RequestMapping("/listing")
	@ResponseBody
	public PageResult listing(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OtcAppTransaction.class,request);
		PageResult pageResult=((OtcAppTransactionService)service).findPageBySql(filter,2);
		return pageResult;
	}

	@MethodName(name = "已完成交易查询")
	@RequestMapping("/listOver")
	@ResponseBody
	public PageResult listOver(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OtcAppTransaction.class,request);
		PageResult pageResult=((OtcAppTransactionService)service).findPageBySql(filter,3);
		return pageResult;
	}
	
	@MethodName(name = "已取消交易查询")
	@RequestMapping("/listCancel")
	@ResponseBody
	public PageResult listCancel(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OtcAppTransaction.class,request);
		PageResult pageResult=((OtcAppTransactionService)service).findPageBySql(filter,4);
		return pageResult;
	}
	
	@MethodName(name = "申诉中交易查询")
	@RequestMapping("/listAppeal")
	@ResponseBody
	public PageResult listAppeal(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OtcAppTransaction.class,request);
		PageResult pageResult=((OtcAppTransactionService)service).findPageBySql(filter,5);
		return pageResult;
	}
	
	
	@RequestMapping(value="/orderReplay")
	@ResponseBody
	public JsonResult orderReplay(HttpServletRequest request){
		JsonResult j = new JsonResult();
		
		String transactionNum = request.getParameter("transactionNum");
		String platFormContent = request.getParameter("platFormContent");
		if(StringUtil.isNotEmpty(transactionNum)){
			QueryFilter qf = new QueryFilter(AppAppeal.class);
			qf.addFilter("transactionNum=", transactionNum);
			AppAppeal appAppeal = appAppealService.get(qf);
			if (appAppeal != null) {
				appAppeal.setPlatFormContent(platFormContent);
				appAppealService.update(appAppeal);
				
				j.setSuccess(true);
				j.setMsg("订单平台已回复");
				return j;
			}
		}
		j.setSuccess(false);
		return j;
	}
	
	/**
	 * 申诉 - 平台 取消订单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/cancleOrder")
	@ResponseBody
	public synchronized JsonResult cancleOrder(HttpServletRequest request){
		JsonResult j = new JsonResult();
		
		String transactionNum = request.getParameter("transactionNum");
		if(StringUtil.isNotEmpty(transactionNum)){
			QueryFilter qf = new QueryFilter(OtcAppTransaction.class);
			qf.addFilter("transactionNum=", transactionNum);
			OtcAppTransaction otcAppTransaction = super.service.get(qf);
			if (otcAppTransaction != null && (otcAppTransaction.getStatus() == 15 || otcAppTransaction.getStatus() == 16)) {
				otcAppTransaction.setStatus(5);
				super.service.update(otcAppTransaction);

				RedisModel rm = new RedisModel();
				rm.setUserId(otcAppTransaction.getCustomerId());
				rm.setCoinCode(otcAppTransaction.getCoinCode());
				rm.setTransactionMode(otcAppTransaction.getTransactionMode());
				rm.setTradeNum(otcAppTransaction.getTransactionNum());
				//先入队列
				redisService.rpush("otc:queue", JSON.toJSONString(rm));
				//发送消息
				redisService.publish("otcCompletionRate", JSON.toJSONString(rm));

				// 取后台coin百分比汇率
				String coinPercent = "";// 币价格百分比
				
				String productinfoListall = redisService.get("otc:coinCodeList");
				JSONArray parseArray = JSON.parseArray(productinfoListall);
				for(int k=0;k<parseArray.size();k++){
					JSONObject jo = JSON.parseObject(parseArray.get(k).toString());
					if(jo.getString("coinCode").equals(otcAppTransaction.getCoinCode())){
						if(jo.getString("coinPercent")!=null){
							coinPercent = jo.getString("coinPercent");
						}
					}
				}
				
				// 广告恢复
				ReleaseAdvertisement releaseAdvertisement = releaseAdvertisementService
						.get(otcAppTransaction.getAdvertisementId());
				if (releaseAdvertisement != null) {
					if(otcAppTransaction.getTradeMoney().compareTo(releaseAdvertisement.getTradeMoney().multiply(new BigDecimal(coinPercent).divide(new BigDecimal(100))))>=0){
						releaseAdvertisement.setStatus(1);
						releaseAdvertisement.setState(0);
						releaseAdvertisement.setTradeMoneyMax(releaseAdvertisement.getTradeMoneyMax().add(otcAppTransaction.getTradeMoney()));
						//取消之后加上吃单的数量
						releaseAdvertisement.setCoinNumMax(releaseAdvertisement.getCoinNumMax().add( otcAppTransaction.getTradeNum() ));
						//交易量减少
						releaseAdvertisement.setTransactionNum( releaseAdvertisement.getTransactionNum() - 1);
						releaseAdvertisementService.update(releaseAdvertisement);
						//挂单者 可用币数减少 冻结增加
						ExDigitalmoneyAccountRedis coinAccountRedis = getCoinAccountRedis(releaseAdvertisement.getCustomerId(), releaseAdvertisement.getCoinCode());
						String s = redisService.get("otc:ReleaseAdvertisementAfterColse:" + transactionNum);
						if(! StringUtils.isEmpty(s) ){
							BigDecimal bs = new BigDecimal(s);
							otcService.publish(coinAccountRedis.getId(), bs, 2, 1, "", 64);
							otcService.publish(coinAccountRedis.getId(), bs.multiply(new BigDecimal(-1)), 1, 1, "", 64);
							redisService.delete("otc:ReleaseAdvertisementAfterColse:" + transactionNum);
						}
					}else{
						//挂单者 可用币数增加 冻结减少
						ExDigitalmoneyAccountRedis coinAccountRedis = getCoinAccountRedis(releaseAdvertisement.getCustomerId(), releaseAdvertisement.getCoinCode());
						
						otcService.publish(coinAccountRedis.getId(), otcAppTransaction.getTradeNum().multiply(new BigDecimal(-1)), 2, 1, "", 64);
						otcService.publish(coinAccountRedis.getId(), otcAppTransaction.getTradeNum(), 1, 1, "", 64);
					}
				}
				//挂的是出售广告不会出现冻币的操作
				if(otcAppTransaction.getTransactionMode() == 2){//购买广告
					//给卖家解冻币
					BigDecimal all = otcAppTransaction.getSellfee().add(otcAppTransaction.getTradeNum());
					
					ExDigitalmoneyAccountRedis coinAccountRedis = getCoinAccountRedis(otcAppTransaction.getSellUserId(), otcAppTransaction.getCoinCode());
					
					otcService.publish(coinAccountRedis.getId(), all.multiply(new BigDecimal(-1)), 2, 1, "", 64);
					otcService.publish(coinAccountRedis.getId(), all, 1, 1, "", 64);
				}
				j.setSuccess(true);
				j.setMsg("订单取消成功");
                return j;
			}
		}
		j.setSuccess(false);
		j.setMsg("订单取消失败");
		return j;
	}
	
	
	/**
	 * 申诉 - 平台 订单成立
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/orderCompleted")
	@ResponseBody
	public JsonResult orderCompleted(HttpServletRequest request){
		JsonResult j = new JsonResult();
		
		String transactionNum = request.getParameter("transactionNum");
		//String type = request.getParameter("type");
		if(StringUtil.isNotEmpty(transactionNum)){
			QueryFilter qf = new QueryFilter(OtcAppTransaction.class);
			qf.addFilter("transactionNum=", transactionNum);
			OtcAppTransaction otcAppTransaction = super.service.get(qf);
			
			//判断订单是否失效
			if(otcAppTransaction != null && otcAppTransaction.getStatus() == 5){//订单已经被取消
				j.setSuccess(true);
				j.setMsg("订单已失效");
				return j;
			}
			
			if (otcAppTransaction != null) {
				otcAppTransaction.setStatus(14);// 完成订单
				otcAppTransaction.setPayTime(new Date());
				super.service.update(otcAppTransaction);

				RedisModel rm = new RedisModel();
				rm.setUserId(otcAppTransaction.getCustomerId());
				rm.setCoinCode(otcAppTransaction.getCoinCode());
				rm.setTransactionMode(otcAppTransaction.getTransactionMode());
				rm.setTradeNum(otcAppTransaction.getTransactionNum());

				//先入队列
				redisService.rpush("otc:queue", JSON.toJSONString(rm));
				//发送消息
				redisService.publish("otcCompletionRate", JSON.toJSONString(rm));

				// 把冻结的钱解冻，把钱打给出售币的人，把花钱的人的钱解冻并减去
				// 无论谁 卖出的都要解冻 并减少币
				// 卖方
				// ex_coin_account
				ExDigitalmoneyAccountRedis coinAccountRedisSell = getCoinAccountRedis(otcAppTransaction.getSellUserId(), otcAppTransaction.getCoinCode());
				// 把冻结的币 根据广告的解冻

				ReleaseAdvertisement r = releaseAdvertisementService.get(otcAppTransaction.getAdvertisementId());
				if (r.getCustomerId().equals(otcAppTransaction.getSellUserId())) {// 冻结挂单的时候
					if (r.getIsFixed() == 1) {// 是固定价格
						//解冻交易的币数
						otcService.publish(coinAccountRedisSell.getId(), otcAppTransaction.getTradeNum().multiply(new BigDecimal(-1)), 2, 1, "", 65);
					} else {
						BigDecimal yi = new BigDecimal("1");// 1
						BigDecimal yibai = new BigDecimal("100");// 100
						BigDecimal price = r.getTradeMoney()
								.multiply(yi.subtract(r.getPremium().divide(yibai, 4, BigDecimal.ROUND_HALF_UP))); // 市场价格*(1-溢价的百分比)这是价格

						//解冻交易的币数
						otcService.publish(coinAccountRedisSell.getId(), otcAppTransaction.getTradeNum().multiply(new BigDecimal(-1)), 2, 1, "", 65);
					}

				} else {
					//购买广告，卖方的冻币和可用币
					//冻结币减去交易的币和手续费
					otcService.publish(coinAccountRedisSell.getId(), (otcAppTransaction.getTradeNum().add(otcAppTransaction.getSellfee())).multiply(new BigDecimal(-1)), 2, 1, "", 65);
					//可用币加上手续费，下面减去手续费
					otcService.publish(coinAccountRedisSell.getId(), otcAppTransaction.getSellfee(), 1, 1, "", 65);
				}

				// 删除掉卖出的数量
				//sell.setHotMoney(sell.getHotMoney().subtract(appTransaction.getTradeNum()));

				// 无论谁 买方都要得到币
				// 买方
				ExDigitalmoneyAccountRedis coinAccountRedisBuy = getCoinAccountRedis(otcAppTransaction.getBuyUserId(), otcAppTransaction.getCoinCode());
				// 可用的钱 //交易数量
				otcService.publish(coinAccountRedisBuy.getId(), otcAppTransaction.getTradeNum(), 1, 1, "", 65);

				// 无论谁appTransaction.getCustomerId()都要扣手续的
				
				ExDigitalmoneyAccountRedis coinAccountRedisCustomer = getCoinAccountRedis(otcAppTransaction.getCustomerId(), otcAppTransaction.getCoinCode());
				// 把手续费删除掉
				otcService.publish(coinAccountRedisCustomer.getId(), otcAppTransaction.getSellfee().multiply(new BigDecimal(-1)), 1, 1, "", 65);

				// 手续费台账
				// ex_coin_fee
				ExCoinFee exCoinFee = new ExCoinFee();
				exCoinFee.setCoinCode(otcAppTransaction.getCoinCode());
				exCoinFee.setCoinName(otcAppTransaction.getCoinCode());
				exCoinFee.setCustomerId(otcAppTransaction.getCustomerId());
				AppCustomer appCustomer = otcService.getAppCustomerById(otcAppTransaction.getCustomerId());
				if (appCustomer != null) {
					exCoinFee.setUserName(appCustomer.getUserName());
				}
				exCoinFee.setFee(otcAppTransaction.getSellfee());
				
				int eatFeeType = 0;// 吃单交易手续费类型
				
				String productinfoListall = redisService.get("otc:coinCodeList");
				JSONArray parseArray = JSON.parseArray(productinfoListall);
				for(int k=0;k<parseArray.size();k++){
					JSONObject jo = JSON.parseObject(parseArray.get(k).toString());
					if(jo.getString("coinCode").equals(otcAppTransaction.getCoinCode())){
						if(jo.getInteger("eatFeeType")!=null){
							eatFeeType = jo.getInteger("eatFeeType");
						}
					}
				}
				
				exCoinFee.setFeeType(eatFeeType);
				exCoinFee.setVolume(otcAppTransaction.getTradeNum());
				exCoinFee.setStatus(2);
				exCoinFeeService.save(exCoinFee);
				
				j.setSuccess(true);
				j.setMsg("订单已完成");
				return j;
			}
			j.setSuccess(true);
			j.setMsg("订单不存在");
			return j;
		
		}
		j.setSuccess(false);
		return j;
	}
	
	/**
	 * 申诉 - 平台 通过、驳回
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/examineAndreject")
	@ResponseBody
	public JsonResult examineAndreject(HttpServletRequest request){
		JsonResult j = new JsonResult();
		
		String transactionNum = request.getParameter("transactionNum");
		String type = request.getParameter("type");
		String platFormContent= request.getParameter("platFormContent");
		if(StringUtil.isNotEmpty(transactionNum)){
			QueryFilter filter = new QueryFilter(OtcAppTransaction.class);
			filter.addFilter("transactionNum=", transactionNum);
			OtcAppTransaction otcAppTransaction = (OtcAppTransaction) super.get(filter).getObj();
			if(otcAppTransaction!=null){
				otcAppTransaction.setStatus(Integer.valueOf(type));
				super.update(otcAppTransaction);
				if(! StringUtils.isEmpty(platFormContent)){
					//更新平台回复
					QueryFilter filter1 = new QueryFilter(AppAppeal.class);
					filter1.addFilter("transactionNum=", transactionNum);

					AppAppeal appAppeal = appAppealService.get(filter1);
					if(appAppeal != null){
						appAppeal.setPlatFormContent(platFormContent);
						//appAppealService.delete(appAppeal.getId());
						appAppealService.update(appAppeal);
					}
				}


				if("11".equals(type)){
					j.setSuccess(true);
					j.setMsg("已通过");
					return j;
				//}else if("3".equals(type)){
				}else if("12".equals(type)){
					j.setSuccess(true);
					j.setMsg("已驳回");
					return j;
				}
			}
		}
		j.setSuccess(false);
		return j;
	}
}
