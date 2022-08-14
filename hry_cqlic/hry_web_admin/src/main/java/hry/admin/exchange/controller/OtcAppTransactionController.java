/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 18:12:38 
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.exchange.model.AppAppeal;
import hry.admin.exchange.model.ExCoinFee;
import hry.admin.exchange.model.ReleaseAdvertisement;
import hry.admin.exchange.service.AppAppealService;
import hry.admin.exchange.service.ExCoinFeeService;
import hry.admin.exchange.service.OtcAppTransactionService;
import hry.admin.exchange.service.ReleaseAdvertisementService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.front.redis.model.UserRedis;
import hry.otc.remote.model.AppAppealRemote;
import hry.otc.remote.model.RedisModel;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.OssUtil;
import hry.util.QueryFilter;
import hry.admin.exchange.model.OtcAppTransaction;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;

/**
 * Copyright:   互融云
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 18:12:38 
 */
@Controller
@RequestMapping("/exchange/otcapptransaction")
public class OtcAppTransactionController extends BaseController<OtcAppTransaction, Long> {

	@Resource(name = "otcAppTransactionService")
	@Override
	public void setService(BaseService<OtcAppTransaction, Long> service) {
		super.service = service;
	}

	@Resource
	private ReleaseAdvertisementService releaseAdvertisementService;

	@Resource
	private AppAppealService appAppealService;

	@Resource
	private RedisService redisService;

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	private ExCoinFeeService exCoinFeeService;

	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		OtcAppTransaction otcAppTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/otcapptransactionsee");
		mav.addObject("model", otcAppTransaction);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,OtcAppTransaction otcAppTransaction){
		return super.save(otcAppTransaction);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		OtcAppTransaction otcAppTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/otcapptransactionmodify");
		mav.addObject("model", otcAppTransaction);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,OtcAppTransaction otcAppTransaction){
		return super.update(otcAppTransaction);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
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

	@RequestMapping(value="/look/{id}")
	public ModelAndView look(@PathVariable String id){

		ModelAndView mav = new ModelAndView("/admin/exchange/otcapptransactionlook");
		OtcAppTransaction appTransaction = super.service.get(new QueryFilter(OtcAppTransaction.class).addFilter("id=", id));

		if (appTransaction != null) {
			// 订单信息
			if (appTransaction.getStatus() == 1) {
				appTransaction.setStateZHCN("待确认订单");
			} else if (appTransaction.getStatus() == 2) {
				appTransaction.setStateZHCN("已确认订单");
			} else if (appTransaction.getStatus() == 3) {
				appTransaction.setStateZHCN("已完成支付");
			} else if (appTransaction.getStatus() == 4) {
				appTransaction.setStateZHCN("申诉中待回复");
			} else if (appTransaction.getStatus() == 5) {
				appTransaction.setStateZHCN("已取消");
			} else if (appTransaction.getStatus() == 6) {
				appTransaction.setStateZHCN("申请退款中");
			} else if (appTransaction.getStatus() == 7) {
				appTransaction.setStateZHCN("退款已驳回");
			} else if (appTransaction.getStatus() == 8) {
				appTransaction.setStateZHCN("申诉完成");
			} else if (appTransaction.getStatus() == 9) {
				appTransaction.setStateZHCN("申诉成功,待确认");
			} else if (appTransaction.getStatus() == 10) {
				appTransaction.setStateZHCN("申诉失败,待确认");
			} else if (appTransaction.getStatus() == 11) {
				appTransaction.setStateZHCN("平台通过申诉");
			} else if (appTransaction.getStatus() == 12) {
				appTransaction.setStateZHCN("平台驳回申诉");
			} else if (appTransaction.getStatus() == 13) {
				appTransaction.setStateZHCN("退款成功");
			} else if (appTransaction.getStatus() == 14) {
				appTransaction.setStateZHCN("已完成的项目");
			}
			String str = "";
			String[] split = appTransaction.getPayType().split(",");
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
			appTransaction.setPayType(str);
			mav.addObject("app", appTransaction);
			// 支付凭证
			ReleaseAdvertisement releaseAdvertisement = releaseAdvertisementService
					.get(appTransaction.getAdvertisementId());
			mav.addObject("releaseId", appTransaction.getAdvertisementId());
			if (releaseAdvertisement != null) {
				mav.addObject("payTypeRemake", releaseAdvertisement.getPayTypeRemake());
				mav.addObject("isFixed", releaseAdvertisement.getIsFixed());
				mav.addObject("paymentTerm", releaseAdvertisement.getPaymentTerm());

				if (releaseAdvertisement.getIsFixed() == 0) {// 市场价格
					BigDecimal yi = new BigDecimal("1");// 1
					BigDecimal yibai = new BigDecimal("100");// 100
					BigDecimal premiumBD = releaseAdvertisement.getPremium();
					BigDecimal shichangjiageBD = releaseAdvertisement.getTradeMoney();

					if (releaseAdvertisement.getTransactionMode() == 1) {// 出售
						// 卖低
						BigDecimal price = shichangjiageBD
								.multiply(yi.subtract(premiumBD.divide(yibai, 4, BigDecimal.ROUND_DOWN))); // 市场价格*(1-溢价的百分比)这是价格

						mav.addObject("tradeMoney", price.toString());

					} else {// 购买 买高
						BigDecimal price = shichangjiageBD
								.multiply(yi.add(premiumBD.divide(yibai, 4, BigDecimal.ROUND_DOWN)));// 市场价格*(1+溢价的百分比)这是价格

						mav.addObject("tradeMoney", price.toString());
					}
				} else {
					mav.addObject("tradeMoney", releaseAdvertisement.getTradeMoney().toString());
				}
			}
			// 述求信息
			AppAppeal appAppeal = appAppealService.get(
					new QueryFilter(AppAppeal.class).addFilter("transactionNum=", appTransaction.getTransactionNum()));
			if (appAppeal != null) {
				AppAppealRemote bean2bean = ObjectUtil.bean2bean(appAppeal, AppAppealRemote.class);
				//买方证据图片
				if (StringUtil.isNotEmpty(bean2bean.getThingUrl())) {
					String[] spl = bean2bean.getThingUrl().split(",");
					List<String> list = new LinkedList<String>();
					for (int i = 0; i < spl.length; i++) {
						list.add(OssUtil.getUrl(spl[i]));
					}
					bean2bean.setImgUrl(list);
				}
				//卖方证据图片
				if (StringUtil.isNotEmpty(bean2bean.getThingUrlSell())) {
					String[] spl = bean2bean.getThingUrlSell().split(",");
					List<String> list = new LinkedList<String>();
					for (int i = 0; i < spl.length; i++) {
						list.add(OssUtil.getUrl(spl[i]));
					}
					bean2bean.setImgUrlSell(list);
				}
				mav.addObject("appAppealRemote", bean2bean);
			}
		}
		return mav;
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
				}else if("5".equals(type)){
					j.setSuccess(true);
					j.setMsg("订单已取消");
					return j;
				}
			}
		}
		j.setSuccess(false);
		return j;
	}

	/**
	 * 回复orderReplay
	 */
	@RequestMapping(value="/orderReplay")
	@ResponseBody
	public JsonResult orderReplay(HttpServletRequest request){
		String transactionNum = request.getParameter("transactionNum");
		String platFormContent = request.getParameter("platFormContent");
		if(StringUtil.isNotEmpty(transactionNum)){
			QueryFilter qf = new QueryFilter(AppAppeal.class);
			qf.addFilter("transactionNum=", transactionNum);
			AppAppeal appAppeal = appAppealService.get(qf);
			if (appAppeal != null) {
				appAppeal.setPlatFormContent(platFormContent);
				appAppealService.update(appAppeal);
				return new JsonResult().setSuccess(true).setMsg("订单平台已回复");
			}
		}
		return new JsonResult().setSuccess(false);
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
							publish(coinAccountRedis.getId(), bs, 2, 1, "", 64);
							publish(coinAccountRedis.getId(), bs.multiply(new BigDecimal(-1)), 1, 1, "", 64);
							redisService.delete("otc:ReleaseAdvertisementAfterColse:" + transactionNum);
						}
					}else{
						//挂单者 可用币数增加 冻结减少
						ExDigitalmoneyAccountRedis coinAccountRedis = getCoinAccountRedis(releaseAdvertisement.getCustomerId(), releaseAdvertisement.getCoinCode());

						publish(coinAccountRedis.getId(), otcAppTransaction.getTradeNum().multiply(new BigDecimal(-1)), 2, 1, "", 64);
						publish(coinAccountRedis.getId(), otcAppTransaction.getTradeNum(), 1, 1, "", 64);
					}
				}
				//挂的是出售广告不会出现冻币的操作
				if(otcAppTransaction.getTransactionMode() == 2){//购买广告
					//给卖家解冻币
					BigDecimal all = otcAppTransaction.getSellfee().add(otcAppTransaction.getTradeNum());

					ExDigitalmoneyAccountRedis coinAccountRedis = getCoinAccountRedis(otcAppTransaction.getSellUserId(), otcAppTransaction.getCoinCode());

					publish(coinAccountRedis.getId(), all.multiply(new BigDecimal(-1)), 2, 1, "", 64);
					publish(coinAccountRedis.getId(), all, 1, 1, "", 64);
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
						publish(coinAccountRedisSell.getId(), otcAppTransaction.getTradeNum().multiply(new BigDecimal(-1)), 2, 1, "", 65);
					} else {
						BigDecimal yi = new BigDecimal("1");// 1
						BigDecimal yibai = new BigDecimal("100");// 100
						BigDecimal price = r.getTradeMoney()
								.multiply(yi.subtract(r.getPremium().divide(yibai, 4, BigDecimal.ROUND_HALF_UP))); // 市场价格*(1-溢价的百分比)这是价格

						//解冻交易的币数
						publish(coinAccountRedisSell.getId(), otcAppTransaction.getTradeNum().multiply(new BigDecimal(-1)), 2, 1, "", 65);
					}

				} else {
					//购买广告，卖方的冻币和可用币
					//冻结币减去交易的币和手续费
					publish(coinAccountRedisSell.getId(), (otcAppTransaction.getTradeNum().add(otcAppTransaction.getSellfee())).multiply(new BigDecimal(-1)), 2, 1, "", 65);
					//可用币加上手续费，下面减去手续费
					publish(coinAccountRedisSell.getId(), otcAppTransaction.getSellfee(), 1, 1, "", 65);
				}

				// 删除掉卖出的数量
				//sell.setHotMoney(sell.getHotMoney().subtract(appTransaction.getTradeNum()));

				// 无论谁 买方都要得到币
				// 买方
				ExDigitalmoneyAccountRedis coinAccountRedisBuy = getCoinAccountRedis(otcAppTransaction.getBuyUserId(), otcAppTransaction.getCoinCode());
				// 可用的钱 //交易数量
				publish(coinAccountRedisBuy.getId(), otcAppTransaction.getTradeNum(), 1, 1, "", 65);

				// 无论谁appTransaction.getCustomerId()都要扣手续的

				ExDigitalmoneyAccountRedis coinAccountRedisCustomer = getCoinAccountRedis(otcAppTransaction.getCustomerId(), otcAppTransaction.getCoinCode());
				// 把手续费删除掉
				publish(coinAccountRedisCustomer.getId(), otcAppTransaction.getSellfee().multiply(new BigDecimal(-1)), 1, 1, "", 65);

				// 手续费台账
				// ex_coin_fee
				ExCoinFee exCoinFee = new ExCoinFee();
				exCoinFee.setCoinCode(otcAppTransaction.getCoinCode());
				exCoinFee.setCoinName(otcAppTransaction.getCoinCode());
				exCoinFee.setCustomerId(otcAppTransaction.getCustomerId());
				AppCustomer appCustomer = appCustomerService.get(otcAppTransaction.getCustomerId());
				if (appCustomer != null) {
					exCoinFee.setUserName(appCustomer.getUsername());
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

	public void publish(Long accountId, BigDecimal money, Integer monteyType, Integer acccountType, String transactionNum, Integer remarks){
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(accountId);
		accountadd.setMoney(money);
		accountadd.setMonteyType(monteyType);
		accountadd.setAcccountType(acccountType);
		accountadd.setRemarks(remarks);
		if(StringUtil.isNotEmpty(transactionNum)){
			accountadd.setTransactionNum(transactionNum);
		}
		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(list));
	}
}
