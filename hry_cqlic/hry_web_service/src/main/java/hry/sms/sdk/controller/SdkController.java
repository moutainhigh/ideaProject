/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午5:38:24
 */
package hry.sms.sdk.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.licqb.record.model.DealRecord;
import hry.licqb.thread.ChargeRecordRunnable;
import hry.licqb.thread.PutIntoRunnable;
import hry.licqb.util.DealEnum;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hry.bean.JsonResult;
import hry.bus.model.BusCustomerTO;
import hry.bus.model.BusUpdateCustomerTO;
import hry.bus.service.BusInterface;
import hry.core.annotation.NoLogin;
import hry.core.annotation.base.MethodName;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.core.thread.ThreadPool;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExApiApply;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExApiApplyService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.klg.enums.TriggerPointEnum;
import hry.manage.remote.MsgTypeEnum;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.send.service.AppSmsSendService;
import hry.trade.model.AccountRemarkEnum;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import hry.util.sys.ContextUtil;
import hry.util.urlencode.URLEncodeUtils;
import hry.web.message.service.AppMessageService;



/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Liu Shilei
 * @Date : 2016年3月28日 下午5:38:24
 */
@Controller
@RequestMapping("/sdk")
public class SdkController {
	private static Logger logger = Logger.getLogger(SdkController.class);
	@Resource
	private MessageProducer messageProducer;

	@Resource
	private AppPersonInfoService appPersonInfoService;

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	private AppMessageService appMessageService;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

		/**
		 * 防止XSS攻击，并且带去左右空格功能
		 */
		binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
	}


	@MethodName(name = "发送短信")
	@RequestMapping(value = "/test1")
	@NoLogin
	@ResponseBody
	public JsonResult test1(HttpServletRequest request) {
		return null;
	}
	
	@MethodName(name = "验证身份证")
	@RequestMapping(value = "/checkCard")
	@NoLogin
	@ResponseBody
	public JsonResult checkCard(HttpServletRequest request) throws UnsupportedEncodingException {

		String name = request.getParameter("name");
		logger.error("实名认证的name:"+name);
		String idCard = request.getParameter("idCard");
		String smsKey = request.getParameter("smsKey");
		JsonResult jsonResult = new JsonResult();
		try {
			if (!StringUtils.isEmpty(smsKey) && "hurongyuseen".equals(smsKey)) {
				
				SdkService sdkService;
				String serviceName = PropertiesUtils.APP.getProperty("app.smsServiceName");
				if(!StringUtils.isEmpty(serviceName)){
					sdkService = (SdkService) ContextUtil.getBean(serviceName);
				}else{
					sdkService = (SdkService) ContextUtil.getBean("sdkService");
				}
				return sdkService.checkCard(URLEncodeUtils.Utf8URLdecode(name),idCard);
			} else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("非法请求，密码不正确");
			} 
			return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	@MethodName(name = "发送短信")
	@RequestMapping(value = "/send")
	@NoLogin
	@ResponseBody
	public JsonResult send(HttpServletRequest request,String phone) {

		String param = request.getParameter("param");
		logger.error("发送短信，接收到的请求参数：" + param);
		SmsParam smsParam = JSON.parseObject(param, SmsParam.class);

		if (SmsSendUtil.WITHDRAW_RMBORCOIN.equals(smsParam.getHrySmstype())
				|| SmsSendUtil.WITHDRAW_RMBORCOIN_FRONT.equals(smsParam.getHrySmstype())
				|| SmsSendUtil.SMS_RMBWITHDRAW_INVALID.equals(smsParam.getHrySmstype())
				|| SmsSendUtil.SMS_COINWITHDRAW_INVALID.equals(smsParam.getHrySmstype())
				|| SmsSendUtil.SMS_RMBDEPOSIT_INVALID.equals(smsParam.getHrySmstype())) {
			// 汉字需要转码
			String code = smsParam.getHryCode();
			logger.error("发送短信，接收到的请求参数HryCode（提现）：" + code);

			if ("BTC".equals(code)) {
				smsParam.setHryCode("比特币");
			} else if ("LTC".equals(code)) {
				smsParam.setHryCode("莱特币");
			} else if ("CRTC".equals(code)) {
				smsParam.setHryCode("联合学分");
			}
		}

		// 内部验证密码
		String smsKey = smsParam.getSmsKey();
		// 获得sendId
		Long sendId = smsParam.getSendId();
		logger.error("收到短信发送请求sendId=" + sendId);
		try {
			//Thread.sleep(5000);// 休息5秒

			JsonResult jsonResult = new JsonResult();
//			MongoUtil<AppSmsSend, Long> mongoUtil = new MongoUtil<AppSmsSend, Long>(AppSmsSend.class);
			AppSmsSendService appSmsSendService=(AppSmsSendService) ContextUtil.getBean("appSmsSendService");
		//	AppSmsSend appSmsSend = appSmsSendService.get(sendId);
//			AppSmsSend appSmsSend = mongoUtil.get(sendId);
			//if (appSmsSend != null) {
			AppSmsSend appSmsSend =new AppSmsSend();
				// 标记为已接收到些条记录
				appSmsSend.setReceiveStatus("1");
				// 判断密钥
				if (!StringUtils.isEmpty(smsKey) && "hurongyuseen".equals(smsKey)) {
					appSmsSend.setPostParam(param);
					SdkService sdkService;
//					String serviceName = PropertiesUtils.APP.getProperty("app.smsServiceName");
					String serviceName ="hrySmsService";
					if (!StringUtils.isEmpty(serviceName)) {
						sdkService = (SdkService) ContextUtil.getBean(serviceName);
					} else {
						sdkService = (SdkService) ContextUtil.getBean("sdkService");
					}
					if(phone==null||phone.equals("")){
						phone=smsParam.getHryMobilephone();
					}
					boolean sendSms = sdkService.sendSms(appSmsSend, smsParam,phone);
					// 如果发送标记为成功,则标记为已发送
					if (sendSms) {
						appSmsSend.setSendStatus("1");
					}
					jsonResult.setSuccess(sendSms);
				} else {
					jsonResult.setSuccess(false);
					jsonResult.setMsg("非法请求，密码不正确");
				}
				//.update(appSmsSend);
				appSmsSendService.save(appSmsSend);
				return jsonResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 给kk提供刷新redis币账户接口
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value="/getAccountKK",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public JsonResult getAccountKK(HttpServletRequest request){
		JsonResult j = new JsonResult();
		
		String id = request.getParameter("id");
		ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
		ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(Long.valueOf(id));
		
		if(exDigitalmoneyAccount!=null){
			ExDigitalmoneyAccountRedis exar = new ExDigitalmoneyAccountRedis();
			exar.setCoinCode(exDigitalmoneyAccount.getCoinCode());
			exar.setColdMoney(exDigitalmoneyAccount.getColdMoney());
			exar.setHotMoney(exDigitalmoneyAccount.getHotMoney());
			exar.setCustomerId(exDigitalmoneyAccount.getCustomerId());
			exar.setId(exDigitalmoneyAccount.getId());

			RedisUtil<ExDigitalmoneyAccountRedis> redisUtil = new RedisUtil<ExDigitalmoneyAccountRedis>(
					ExDigitalmoneyAccountRedis.class);
			redisUtil.put(exar, exDigitalmoneyAccount.getId().toString());
			j.setSuccess(true);
			return j;
		}
		j.setSuccess(false);
		return j;
	}
	
	/**
	 * 充币
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/rechargeCodeKK",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public JsonResult rechargeCodeKK(HttpServletRequest request){
		JsonResult j = new JsonResult();
		try {
			ExDmTransactionService exDmTransactionService = (ExDmTransactionService) ContextUtil.getBean("exDmTransactionService");

			String username = request.getParameter("username");//用户名
			String transactionMoney = request.getParameter("transactionMoney");//提币数量
			String fee = request.getParameter("fee");//手续费
			String trueName = request.getParameter("trueName");//名
			String surname = request.getParameter("surname");//姓
			String coinCode = request.getParameter("coinCode");//币代码
			String CurrencyType = request.getParameter("CurrencyType");//法币代码
			String inAddress = request.getParameter("inAddress");//钱包转入地址
			String remark = request.getParameter("remark");//备注
			String remark2 = request.getParameter("remark2");//备注
			String orderNo = request.getParameter("orderNo");//txid
			String authcode = request.getParameter("authcode");//备注

			String [] params={transactionMoney,fee,username,surname,trueName,coinCode,CurrencyType,inAddress,orderNo,remark};
			logger.error("receive rechargeCodeKK param :"+Arrays.toString(params));
			String getcode = util.StringUtils.authCode(params);

			//判断
			if(null != getcode){
				QueryFilter queryFilter = new QueryFilter(ExDmTransaction.class);
				queryFilter.addFilter("customerName=",username);
				queryFilter.addFilter("orderNo=",orderNo);
				ExDmTransaction transaction = exDmTransactionService.get(queryFilter);
				if(null == transaction){
					//币位数
					Integer keepDecimalForCoin = 8 ;
					RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
					String str = redisService.get("cn:productinfoListall");
					if(!StringUtils.isEmpty(str)){
						JSONArray array = JSON.parseArray(str);
						if(array!=null){
							for(int i =0 ; i < array.size() ;i++){
								JSONObject jsonObject = array.getJSONObject(i);
								if(coinCode.equals(jsonObject.getString("coinCode"))){
									keepDecimalForCoin = jsonObject.getInteger("keepDecimalForCoin");
								}
							}
						}
					}


					logger.error("进入充币了");
					if(new BigDecimal(transactionMoney).compareTo(new BigDecimal(0))>0 && new BigDecimal(fee).compareTo(new BigDecimal(0))>=0){
						logger.error("进入第一个if");
						if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(coinCode)){
							logger.error("进入第二个if");
							RemoteManageService remoteManageService = (RemoteManageService) ContextUtil.getBean("remoteManageService");
							logger.error("1==========userName="+username+"    coinCode="+coinCode);
							Map<String, Object> map = remoteManageService.selectRechargeCoin(username,coinCode);
							logger.error(map.get("customerId") + " " + map.get("accountId"));
							if(map!=null && map.size()>0){
								if(map.get("customerId")!=null && map.get("accountId")!=null){
									ExDmTransaction exDmTransaction = new ExDmTransaction();
									exDmTransaction.setCustomerId(Long.valueOf(map.get("customerId").toString()));
									String transactionNum = NumConstant.Ex_Dm_Transaction;
									exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
									exDmTransaction.setAccountId(Long.valueOf(map.get("accountId").toString()));
									exDmTransaction.setTransactionType(1);
									exDmTransaction.setTransactionMoney(new BigDecimal(transactionMoney));
									exDmTransaction.setCustomerName(username == null ? "" : username);
									exDmTransaction.setStatus(2);
									exDmTransaction.setCoinCode(coinCode);
									exDmTransaction.setCurrencyType(CurrencyType);
									exDmTransaction.setSaasId(RpcContext.getContext().getAttachment(
											"saasId"));
									exDmTransaction.setFee(BigDecimal.ZERO);
									exDmTransaction.setInAddress(inAddress);
									exDmTransaction.setOrderNo(orderNo);
									exDmTransaction.setRemark(remark);
									exDmTransaction.setRemark2("划转李超钱包");
									exDmTransaction.setOptType(1);
									// 保存订单
									exDmTransactionService.save(exDmTransaction);

									//热账户增加
									Accountadd accountadd2 = new Accountadd();
									accountadd2.setAccountId(exDmTransaction.getAccountId());
									accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()));
									accountadd2.setMonteyType(1);
									accountadd2.setAcccountType(1);
									accountadd2.setRemarks(31);
									accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

									List<Accountadd> list = new ArrayList<Accountadd>();
									list.add(accountadd2);
									messageProducer.toAccount(JSON.toJSONString(list));
									
									// 调用锁仓管理操作
									/*Map<String, Object> paramMap = new HashMap<String, Object>();
									paramMap.put("customerId", Long.valueOf(map.get("customerId").toString()));
									paramMap.put("accountId", exDmTransaction.getAccountId());
									paramMap.put("coinCode", coinCode);
									paramMap.put("transactionNum", exDmTransaction.getTransactionNum());
									ThreadPool.exe(new LockRedisRunnable(paramMap));*/
									// -----------------------end------------------------------

									/*~~~~~~~~~~~~~~~~~~~~~~~~~李超钱包 添加充币记录---start~~~~~~~~~~~~~~~~~~~~~~~~*/
									Map<String, Object> recordMaps = new HashMap<String, Object>();
									recordMaps.put("customerId", Long.valueOf(map.get("customerId").toString()));
									recordMaps.put("accountId", exDmTransaction.getAccountId());
									recordMaps.put("coinCode", coinCode);
									recordMaps.put("money", accountadd2.getMoney());
									recordMaps.put("transactionNum", exDmTransaction.getTransactionNum());
									recordMaps.put("chargeType","1");
									ThreadPool.exe(new ChargeRecordRunnable(recordMaps));
									/*~~~~~~~~~~~~~~~~~~~~~~~~~李超钱包 添加充币记录---end~~~~~~~~~~~~~~~~~~~~~~~~~~*/

									/*********李超钱包---充币则冻结***start*************/
									/*Map<String, Object> maps = new HashMap<String, Object>();
									maps.put("customerId", Long.valueOf(map.get("customerId").toString()));
									maps.put("accountId", exDmTransaction.getAccountId());
									maps.put("coinCode", coinCode);
									maps.put("money", accountadd2.getMoney());
									maps.put("transactionNum", exDmTransaction.getTransactionNum());
									maps.put("dealType", DealEnum.TYPE8.getIndex());
									maps.put("dealRemark", DealEnum.SITE8.getName());
									ThreadPool.exe(new PutIntoRunnable(maps));*/
									/*********李超钱包---充币则冻结***end***************/


									//发送站内信
									AppCustomer customer = appCustomerService.get(Long.valueOf(map.get("customerId").toString()));
									appMessageService.sysSendMsg(customer, MsgTypeEnum.POSTMONEY);
									j.setSuccess(true);
									j.setMsg("充币成功");
								}else{
									j.setSuccess(false);
									j.setCode("1000");
									j.setMsg("账户不存在");
								}
							}else{
								j.setSuccess(false);
								j.setCode("1000");
								j.setMsg("账户不存在");
								j.setObj(map.get("customerId") + " " + map.get("accountId"));
							}
						}else{
							j.setSuccess(false);
							j.setCode("1000");
							j.setMsg("参数不正确");
						}
					}else{
						j.setSuccess(false);
						j.setCode("1000");
						j.setMsg("参数不正确");
					}

				}else{
					j.setSuccess(false);
					j.setCode("9999");
					j.setMsg("冲币重复");
				}
			}else{
				j.setSuccess(false);
				j.setCode("9998");
				j.setMsg("参数加密不一致");
			}

		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setCode("1000");
			j.setMsg("充币异常");
		}
		return j;
	}
	
	
	
	/**
	 * 提币
	 * @return
	 */
	@RequestMapping(value="/withdrawCodeKK",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public JsonResult withdrawCodeKK(HttpServletRequest request){
		JsonResult j = new JsonResult();
		
		ExDmTransactionService exDmTransactionService = (ExDmTransactionService) ContextUtil.getBean("exDmTransactionService");
		
		String customerId = request.getParameter("customerId");//用户ID
		String accountId = request.getParameter("accountId");//币账户ID
		String transactionMoney = request.getParameter("transactionMoney");//提币数量
		String fee = request.getParameter("fee");//用户ID
		String customerName = request.getParameter("customerName");//用户名
		String trueName = request.getParameter("trueName");//名
		String surname = request.getParameter("surname");//姓
		String coinCode = request.getParameter("coinCode");//币代码
		String CurrencyType = request.getParameter("CurrencyType");//法币代码
		String remark = request.getParameter("remark");//备注
		
		if(accountId!=null && !"".equals(accountId)){
			RedisUtil<ExDigitalmoneyAccountRedis>  a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
			ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = a.get(accountId);
			
			if(exDigitalmoneyAccountRedis!=null){
				if(exDigitalmoneyAccountRedis.getHotMoney().compareTo(new BigDecimal(0))>0 && exDigitalmoneyAccountRedis.getHotMoney().compareTo(new BigDecimal(transactionMoney))>=0){
					ExDmTransaction exDmTransaction = new ExDmTransaction();
					exDmTransaction.setCustomerId(Long.valueOf(customerId));
					String transactionNum = NumConstant.Ex_Dm_Transaction;
					exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
					exDmTransaction.setAccountId(Long.valueOf(accountId));
					exDmTransaction.setTransactionType(2);
					exDmTransaction.setTransactionMoney(new BigDecimal(transactionMoney));
					exDmTransaction.setCustomerName(customerName);
					exDmTransaction.setStatus(2);
					exDmTransaction.setCoinCode(coinCode);
					exDmTransaction.setCurrencyType(CurrencyType);
					//exDmTransaction.setWebsite(order.getWebsite());
					exDmTransaction.setSaasId(RpcContext.getContext().getAttachment(
							"saasId"));
					exDmTransaction.setFee(new BigDecimal(fee));
					exDmTransaction.setRemark(remark);
					exDmTransaction.setRemark2("划转至交易所");
					//exDmTransaction.setOurAccountNumber(order.getOurAccountNumber());
					//exDmTransaction.setInAddress(order.getCurrencyKey());
					//exDmTransaction.setInAddress(order.getInAddress());
					//exDmTransaction.setOutAddress(order.getOurAccountNumber());
					// 保存订单
					exDmTransactionService.save(exDmTransaction);
					
					
					//热账户减少
					Accountadd accountadd2 = new Accountadd();
					accountadd2.setAccountId(exDmTransaction.getAccountId());
					accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()).multiply(new BigDecimal(-1)));
					accountadd2.setMonteyType(1);
					accountadd2.setAcccountType(1);
					accountadd2.setRemarks(33);
					accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());
					
					List<Accountadd> list = new ArrayList<Accountadd>();
					list.add(accountadd2);
					messageProducer.toAccount(JSON.toJSONString(list));
					//发送站内信
					AppCustomer customer = appCustomerService.get(Long.valueOf(customerId));
					appMessageService.sysSendMsg(customer, MsgTypeEnum.GETMONEY);

					j.setSuccess(true);
					j.setMsg("提币成功");
				}else{
					j.setSuccess(false);
					j.setCode("1000");
					j.setMsg("余额不足");
				}
			}else{
				j.setSuccess(false);
				j.setCode("1001");
				j.setMsg("币账户不存在");
			}
		}else{
			j.setSuccess(false);
			j.setCode("1001");
			j.setMsg("币账户不存在");
		}
		return j;
	} 
	
	/**
	 * 全部撤销
	 * @return
	 */
	@RequestMapping(value="/allcancelKK",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public JsonResult allcancelKK(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		
		String accesskey = request.getParameter("accesskey");//用户key
		String coinCode = request.getParameter("coinCode");//币代码
		
		if(accesskey!=null && !"".equals(accesskey)){
			ExApiApplyService exApiApplyService = (ExApiApplyService) ContextUtil.getBean("exApiApplyService");
			
			QueryFilter qf = new QueryFilter(ExApiApply.class);
			qf.addFilter("accessKey=", accesskey);
			ExApiApply exApiApply = exApiApplyService.get(qf);
			if(exApiApply!=null){
				String[] split = coinCode.split("_");
				EntrustTrade entrustTrade=new EntrustTrade();
				entrustTrade.setCoinCode(split[0]);
				entrustTrade.setFixPriceCoinCode(split[1]);
				entrustTrade.setCustomerId(exApiApply.getCustomerId());
				RemoteManageService remoteManageService = (RemoteManageService) ContextUtil.getBean("remoteManageService");
				remoteManageService.cancelCustAllExEntrust(entrustTrade);
				jsonResult.setSuccess(true);
				jsonResult.setMsg("撤销成功");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setCode("1000");
				jsonResult.setMsg("key值不存在");
			}
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setCode("1000");
			jsonResult.setMsg("key值不存在");
		}
		return jsonResult;
	}
	
	/**
	 * 拉取交易对最新价格
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/newTransactionPrice",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public JsonResult newTransactionPrice(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		
		String coinCode = request.getParameter("coinCode");//交易对
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		if(!StringUtils.isEmpty(coinCode)){
			String price = redisService.get(coinCode + ":currentExchangPrice");
			jsonResult.setSuccess(true);
			jsonResult.setMsg(price);
		}else{
			jsonResult.setSuccess(false);
			jsonResult.setMsg("交易对不存在");
		}
		return jsonResult;
		
	}

	/**
	 * 总线推送充币---接收外部充币请求
	 * @param request
	 * @return
	 */

	@PostMapping(value="/pushCoin")
	@ResponseBody
	public JsonResult pushCoin(HttpServletRequest request){
		JsonResult j = new JsonResult();

		//币地址
		String address = request.getParameter("address");
		//币数量
		String amout = request.getParameter("amount");
		//订单编号
		String orderNumber = request.getParameter("orderNumber");

		//是否交易所调用 1：交易所转币
		String isShop = request.getParameter("isShop");

		if(StringUtils.isEmpty(address)||StringUtils.isEmpty(amout)||StringUtils.isEmpty(orderNumber)){
			return j.setMsg("参数错误！");
		}


		ExDigitalmoneyAccountService exDigitalmoneyAccountService = SpringUtil.getBean("exDigitalmoneyAccountService");
		QueryFilter queryFilter = new QueryFilter(ExDigitalmoneyAccount.class);
		queryFilter.addFilter("publicKey=",address);
		ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(queryFilter);
		if(exDigitalmoneyAccount==null){
			j.setMsg("币地址错误");
			return j;
		}
		//币种编号
		String coinCode = exDigitalmoneyAccount.getCoinCode();
		//用户信息
		AppCustomer appCustomer = appCustomerService.get(exDigitalmoneyAccount.getCustomerId());

		try {
			ExDmTransactionService exDmTransactionService = (ExDmTransactionService) ContextUtil.getBean("exDmTransactionService");

			QueryFilter filter = new QueryFilter(ExDmTransaction.class);
			filter.addFilter("orderNo=",orderNumber);
			ExDmTransaction trans = exDmTransactionService.get(filter);
			if(trans!=null){
				j.setSuccess(false);
				j.setMsg("订单号重复");
				return j;
			}

			ExDmTransaction exDmTransaction = new ExDmTransaction();
			exDmTransaction.setCustomerId(appCustomer.getId());
			String transactionNum = NumConstant.Ex_Dm_Transaction;
			exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
			exDmTransaction.setAccountId(exDigitalmoneyAccount.getId());
			exDmTransaction.setTransactionType(1);
			exDmTransaction.setTransactionMoney(new BigDecimal(amout));
			exDmTransaction.setCustomerName(appCustomer.getUserName());
			exDmTransaction.setStatus(2);
			exDmTransaction.setCoinCode(coinCode);
			exDmTransaction.setCurrencyType(exDigitalmoneyAccount.getCurrencyType());
			exDmTransaction.setFee(BigDecimal.ZERO);
			exDmTransaction.setInAddress(address);
			//exDmTransaction.setOrderNo(exDmTransaction.getTransactionNum());
			exDmTransaction.setOrderNo(orderNumber);
			if(isShop != null && isShop.equals("1")){
				exDmTransaction.setRemark("接收交易所转币"+exDmTransaction.getTransactionMoney()+"个");
				exDmTransaction.setRemark2("划转至快乐购");
			}else{
				exDmTransaction.setRemark("总线互转");
				exDmTransaction.setRemark2("划转至快乐购");
			}
			
			
			exDmTransaction.setOptType(3);
			// 保存订单
			exDmTransactionService.save(exDmTransaction);
			
			/**插入KLG流水记录*/
			try {
				KlgAssetsRecordService klgAssetsRecordService = (KlgAssetsRecordService) ContextUtil.getBean("klgAssetsRecordService");
				KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//交易流水
				klgAssetsRecord.setCustomerId(appCustomer.getId());//用户Id
				klgAssetsRecord.setAccountId(exDigitalmoneyAccount.getId());//币账户ID
				klgAssetsRecord.setSerialNumber(transactionNum);//流水号
				klgAssetsRecord.setAccountType(1);  //账户类型 1.热账户 2.冷账户
				klgAssetsRecord.setCoinCode(coinCode);//币种Code
				klgAssetsRecord.setChangeCount(new BigDecimal(amout)); //交易量
				klgAssetsRecord.setChangeType(1);////变动类型 1加 2减
				TriggerPointEnum  triggerPoint=TriggerPointEnum.ExchangeToTransfer;//默认 见点
				klgAssetsRecord.setTriggerPoint(triggerPoint.getKey());//触发点
				klgAssetsRecord.setTriggerSerialNumber(transactionNum); //触发点流水号
				klgAssetsRecord.setRemark("交易所转入");
				klgAssetsRecordService.save(klgAssetsRecord);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			//热账户增加
			Accountadd accountadd2 = new Accountadd();
			accountadd2.setAccountId(exDmTransaction.getAccountId());
			accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()));
			accountadd2.setMonteyType(1);
			accountadd2.setAcccountType(1);
			accountadd2.setRemarks(AccountRemarkEnum.TYPE31.getIndex());
			accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

			List<Accountadd> list = new ArrayList<Accountadd>();
			list.add(accountadd2);
			messageProducer.toAccount(JSON.toJSONString(list));

			//发送站内信
			appMessageService.sysSendMsg(appCustomer, MsgTypeEnum.POSTMONEY);
			j.setSuccess(true);
			j.setMsg("充币成功");


		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setCode("1000");
			j.setMsg("充币异常");
		}
		return j;
	}

	/**
	 * 外部系统查询币地址是否存在
	 * @param request
	 * @return
	 */
	@PostMapping(value="/queryAddress")
	@ResponseBody
	public JsonResult queryAddress(HttpServletRequest request){
		JsonResult j = new JsonResult();
		//币地址
		String address = request.getParameter("address");
		if(StringUtils.isEmpty(address)){
			return j.setMsg("参数错误！");
		}
		try {
			ExDigitalmoneyAccountService exDigitalmoneyAccountService = SpringUtil.getBean("exDigitalmoneyAccountService");
			QueryFilter queryFilter = new QueryFilter(ExDigitalmoneyAccount.class);
			queryFilter.addFilter("publicKey=",address);
			ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(queryFilter);
			if(exDigitalmoneyAccount == null){
				j.setSuccess(false);
				j.setCode("4444");
				j.setMsg("充币地址不存在");
			}else{
				j.setSuccess(true);
				j.setCode("8888");
				j.setMsg("充币地址存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setSuccess(false);
			j.setCode("1000");
			j.setMsg("查询充币地址异常");
		}
		return j;
	}


	/**
	 * 总线推送用户信息
	 * @param request
	 * @return
	 */
	@PostMapping(value="/pushCustomerInfo")
	@ResponseBody
	public JsonResult pushCustomerInfo(@RequestBody BusUpdateCustomerTO data){
		JsonResult j = new JsonResult();
		try {
			System.out.println("================总线推送用户信息");
			if(data!=null){
				System.out.println("444："+JSONObject.toJSONString(data));
				if(!StringUtils.isEmpty(data.getBusCustomerNumber())){
					AppCustomer appCustomer = appCustomerService.get(new QueryFilter(AppCustomer.class).addFilter("busNumber=", data.getBusCustomerNumber()));
					System.out.println("333："+JSONObject.toJSONString(appCustomer));
					if(appCustomer!=null){
						AppPersonInfo personInfo = appPersonInfoService.getByCustomerId(appCustomer.getId());
						System.out.println("222："+JSONObject.toJSONString(personInfo));
						if(personInfo!=null) {
							BusCustomerTO busCustomer = data.getBusCustomerTO();
							System.out.println("111："+JSONObject.toJSONString(busCustomer));
							personInfo.setEmail(busCustomer.getEmail());
							personInfo.setMobilePhone(busCustomer.getMobile());
							personInfo.setCountry(busCustomer.getCountry());
							personInfo.setTrueName(data.getTrueName());
							personInfo.setSurname(data.getSurName());
							personInfo.setCardId(data.getCardid());

							//如果护照不为空，证件类型设置为外国
							if(!StringUtils.isEmpty(data.getPassport())){
								personInfo.setCardId(data.getPassport());
								personInfo.setCardType(1);
							}
							//判断是否绑定邮箱
							if(!StringUtils.isEmpty(busCustomer.getEmail())){
								appCustomer.setHasEmail(1);
							}
							//判断是否绑定手机
							if(!StringUtils.isEmpty(busCustomer.getMobile())){
								appCustomer.setPhoneState(1);
							}
							
							appCustomer.setIsReal(busCustomer.getIsReal());
							if(busCustomer.getIsReal()!=null){
								if(busCustomer.getIsReal().intValue()==1){
									appCustomer.setStates(2);//已实名
								}else{
									appCustomer.setStates(0);//
								}
							}
							appCustomer.setPassWord(data.getPassword());
							appCustomer.setSalt(data.getSalt());

							System.out.println("000："+JSONObject.toJSONString(personInfo));
							appPersonInfoService.update(personInfo);
							appCustomerService.update(appCustomer);
							
						}
					}else{
						if(data.getBusCustomerTO()!=null) {
							BusInterface busInterface = SpringUtil.getBean("busInterface");
							User user = busInterface.login(data.getBusCustomerTO().getMobile(), data.getBusCustomerTO());
						}
					}
				}

			}
			j.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return j;
	}
}
