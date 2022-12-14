/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:59:41 
 */
package hry.admin.exchange.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.exchange.dao.ExDmTransactionDao;
import hry.admin.exchange.model.*;
import hry.admin.exchange.service.AppLogThirdInterfaceService;
import hry.admin.exchange.service.AppTransactionService;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.lock.model.ExDmLock;
import hry.admin.lock.model.ExDmLockRecord;
import hry.admin.lock.model.ExDmLockReleaseTime;
import hry.admin.lock.service.ExDmLockRecordService;
import hry.admin.lock.service.ExDmLockReleaseTimeService;
import hry.admin.lock.service.ExDmLockService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.admin.web.model.AppMessageCategory;
import hry.admin.web.model.AppMessageTemplate;
import hry.admin.web.model.AppOurAccount;
import hry.admin.web.service.AppMessageCategoryService;
import hry.admin.web.service.AppMessageService;
import hry.admin.web.service.AppMessageTemplateService;
import hry.admin.web.service.AppOurAccountService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import hry.util.date.DateUtil;
import hry.util.file.FileUtil;
import hry.util.http.HttpConnectionUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.md5.Md5Encrypt;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> ExDmTransactionService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:59:41  
 */
@Service("exDmTransactionService")
public class ExDmTransactionServiceImpl extends BaseServiceImpl<ExDmTransaction, Long> implements ExDmTransactionService{
	private static Logger logger = Logger.getLogger(ExDmTransactionServiceImpl.class);
	@Resource(name="exDmTransactionDao")
	@Override
	public void setDao(BaseDao<ExDmTransaction, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private AppLogThirdInterfaceService appLogThirdInterfaceService;

	@Resource
	private ExDmLockService exDmLockService;

	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private AppTransactionService appTransactionService;

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	private AppMessageCategoryService appMessageCategoryService;

	@Resource
	private AppMessageTemplateService appMessageTemplateService;

	@Resource
	private AppMessageService appMessageService;

	@Resource
	private AppOurAccountService appOurAccountService;

	@Resource
	private ExDmLockRecordService exDmLockRecordService;

	@Resource
	private RedisService redisService;

	@Resource
	private ExDmLockReleaseTimeService exDmLockReleaseTimeService;


	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// ----------------------????????????????????????------------------------------
		// ??????PageResult??????
		Page<AppCustomer> page = PageFactory.getPage(filter);

		// ----------------------????????????????????????------------------------------

		// ----------------------????????????------------------------------

		//????????????
		String coinCode = filter.getRequest().getParameter("coinCode");
		//????????????
		String createdG = filter.getRequest().getParameter("created_GT");
		//????????????
		String createdL = filter.getRequest().getParameter("created_LT");
		// ??????????????????
		String inAddress = filter.getRequest().getParameter("inAddress");
		String outAddress = filter.getRequest().getParameter("outAddress_like");
		// ???????????????
		String transactionNum = filter.getRequest().getParameter("transactionNum");

		// ????????????
		String transactionType = filter.getRequest().getParameter("transactionType");
		// ????????????
		String status = filter.getRequest().getParameter("status");
		// ??????
		String email = filter.getRequest().getParameter("email");
		// ?????????
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		// ??????
		String trueName = filter.getRequest().getParameter("trueName");

		Map<String, Object> map = new HashMap<String, Object>();
		// ????????????
		if (!StringUtils.isEmpty(transactionType)) {
			map.put("transactionType", transactionType);
		}
		// ????????????
		if (!StringUtils.isEmpty(status)) {
			map.put("status", status);
		}
		// ??????
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		// ?????????
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		// ??????
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}

		// ??????????????????
		if (!StringUtils.isEmpty(inAddress)) {
			map.put("inAddress", "%" + inAddress + "%");
		}

		if (!StringUtils.isEmpty(outAddress)) {
			map.put("outAddress", "%" + outAddress + "%");
		}
		// ???????????????
		if (!StringUtils.isEmpty(transactionNum)) {
			map.put("transactionNum", "%" + transactionNum + "%");
		}

		// ????????????
		if (!StringUtils.isEmpty(coinCode)) {
			if (!coinCode.equals("all")) {//???????????????all(??????)
				map.put("coinCode", coinCode);
			}
		}

		if (!StringUtils.isEmpty(createdG)) {
			map.put("createdG", createdG);
		}
		if (!StringUtils.isEmpty(createdL)) {
			map.put("createdL", createdL);
		}
		((ExDmTransactionDao) dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	/**
	 * ?????????????????????
	 * @param paraMap
	 */
	@Override
	public synchronized void lockManagementHandler (Map<String, Object> paraMap) {
		if (paraMap != null) {
			// ????????????
			String customerId = paraMap.get("customerId").toString();
			String coinCode = paraMap.get("coinCode").toString();
			String transactionNum = paraMap.get("transactionNum").toString();

			// ???????????????
			Integer keepDecimalForCoin = 8;
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

			// ???????????????
			BaseManageUser currentUser = ContextUtil.getCurrentUser();
			String optUser = currentUser.getUsername();

			// ??????????????????????????????
			QueryFilter ruleQf = new QueryFilter(ExDmLock.class);
			ruleQf.addFilter("coinCode=", coinCode);
			ruleQf.addFilter("isLock=", 1);
			ruleQf.addFilter("isValid=", 0);
			ExDmLock exDmLock = exDmLockService.get(ruleQf);
			if (exDmLock != null) {
				// ?????????????????????????????????????????????
				Date lockStartTime = exDmLock.getLockStartTime();
				BigDecimal lockDuration = exDmLock.getLockDuration();
				// ????????????
				Date currentTime = new Date();

				// ????????????????????????
				Date lockEndTime = null;
				if (lockDuration != null) {
					lockEndTime = DateUtil.addDay(lockStartTime, lockDuration.intValue());
				}

				/**
				 * 1??????????????????????????????????????????????????????????????????
				 * 2????????????????????????????????????????????????????????????????????????????????????????????????????????????
				 */
				if (lockEndTime == null || (compare_date(currentTime, lockStartTime) >= 0 && compare_date(currentTime, lockEndTime) == -1)) {
					// ???redis????????????????????????????????????
					RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
					UserRedis userRedis = redisUtil.get(customerId.toString());
					if (userRedis != null) {
						ExDigitalmoneyAccountRedis exDigitalmoneyAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
						if (exDigitalmoneyAccount != null) {
							BigDecimal hotMoney = exDigitalmoneyAccount.getHotMoney(); // ???????????????
							BigDecimal usableMoney = new BigDecimal(0); // ?????????????????????
							// ????????????????????????
							BigDecimal lockStartLimit = exDmLock.getLockStartLimit();
							// ??????????????????
							Integer lockMethod = exDmLock.getLockMethod();
							// ??????????????????
							BigDecimal lockRatio = exDmLock.getLockRatio();

							// ??????????????????????????????????????????????????????????????????
							if (hotMoney.compareTo(lockStartLimit) > 0) {
								// 1??????????????? 2??????????????????
								if (lockMethod == 1) {
									usableMoney = hotMoney;
								} else {
									usableMoney = hotMoney.subtract(lockStartLimit);
								}
								// ???????????????
								BigDecimal cold = usableMoney.multiply(lockRatio).divide(new BigDecimal(100)).setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN);

								// ??????????????????
								ExDmLockRecord record = new ExDmLockRecord();
								record.setCustomerId(new Long(customerId));
								record.setLockId(exDmLock.getId());
								record.setAccountId(Long.valueOf(paraMap.get("accountId").toString()));
								record.setCoinCode(coinCode);
								record.setAccountBalance(hotMoney.subtract(cold));
								record.setColdNum(cold);
								record.setOptUser(optUser);
								record.setReleaseMethod(exDmLock.getReleaseMethod());
								record.setReleaseMethodVal(exDmLock.getReleaseMethodVal());
								record.setOptType(1);
								record.setTransactionNum(transactionNum);
								record.setAmountReleased(new BigDecimal(0));
								record.setRemainingRelease(cold);
								record.setCreated(new Date());
								record.setModified(new Date());
								// ????????????
								Integer saveRecord = (Integer) exDmLockRecordService.save(record);
								// ????????????????????????????????????
								if (saveRecord != null && saveRecord.intValue() > 0) {
									computingTime(record.getId(), exDmLock.getReleaseMethod(), exDmLock.getReleaseMethodVal(), exDmLock.getLockCycle(), cold, exDmLock.getReleaseFrequency(), keepDecimalForCoin);

									// 7???????????????????????????????????????
									// ???????????????
									Accountadd accountaddhot = new Accountadd();
									accountaddhot.setAccountId(Long.valueOf(paraMap.get("accountId").toString()));
									accountaddhot.setMoney(cold.multiply(new BigDecimal(-1)));
									accountaddhot.setMonteyType(1);
									accountaddhot.setAcccountType(1);
									accountaddhot.setRemarks(55);
									accountaddhot.setTransactionNum(transactionNum);

									// ???????????????
									Accountadd accountaddcold = new Accountadd();
									accountaddcold.setAccountId(Long.valueOf(paraMap.get("accountId").toString()));
									accountaddcold.setMoney(cold);
									accountaddcold.setMonteyType(2);
									accountaddcold.setAcccountType(1);
									accountaddcold.setRemarks(55);
									accountaddhot.setTransactionNum(transactionNum);

									List<Accountadd> listLock = new ArrayList<Accountadd>();
									listLock.add(accountaddhot);
									listLock.add(accountaddcold);
									messageProducer.toAccount(JSON.toJSONString(listLock));
								}
							}
						}
					}
				}
			} else {
				System.out.println("??????????????????????????????????????????");
			}
		}
	}

	/**
	 * ??????????????????
	 * @param paramMap
	 */
	@Override
	public synchronized JsonResult manualLockHandler (Map<String, Object> paramMap) {
		JsonResult result = new JsonResult();
		if (paramMap != null) {
			// ????????????
			String coinCode = paramMap.get("coinCode").toString();
			String customerId = paramMap.get("customerId").toString();
			String coldNum = paramMap.get("coldNum").toString();
			String lockCycle = paramMap.get("lockCycle").toString();
			String releaseMethod = paramMap.get("releaseMethod").toString();
			String releaseMethodVal = paramMap.get("releaseMethodVal").toString();
			String accountId = paramMap.get("accountId").toString();

			// ???????????????
			Integer keepDecimalForCoin = 8;
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

			// ???????????????
			BaseManageUser currentUser = ContextUtil.getCurrentUser();
			String optUser = currentUser.getUsername();

			// ???redis????????????????????????????????????
			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(customerId.toString());
			if (userRedis != null) {
				ExDigitalmoneyAccountRedis exDigitalmoneyAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
				if (exDigitalmoneyAccount != null) {
					BigDecimal hotMoney = exDigitalmoneyAccount.getHotMoney(); // ???????????????
					if (hotMoney.compareTo(new BigDecimal(coldNum)) > -1) {
						// ??????????????????
						ExDmLockRecord record = new ExDmLockRecord();
						record.setCustomerId(new Long(customerId));
						record.setAccountId(Long.valueOf(accountId));
						record.setCoinCode(coinCode);
						record.setAccountBalance(hotMoney.subtract(new BigDecimal(coldNum)));
						record.setColdNum(new BigDecimal(coldNum));
						record.setOptUser(optUser);
						record.setOptType(3);
						record.setReleaseMethod(new Integer(releaseMethod));
						record.setReleaseMethodVal(releaseMethodVal);
						record.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
						record.setAmountReleased(new BigDecimal(0));
						record.setRemainingRelease(new BigDecimal(coldNum));
						record.setCreated(new Date());
						record.setModified(new Date());
                        // ????????????
                        Integer savesaRecord = (Integer) exDmLockRecordService.save(record);
                        if (savesaRecord != null && savesaRecord.intValue() > 0) {
                            // ????????????????????????????????????
                            computingTime(record.getId(), new Integer(releaseMethod), releaseMethodVal, new BigDecimal(lockCycle), new BigDecimal(coldNum), null, keepDecimalForCoin);

							// 7???????????????????????????????????????
							// ???????????????
							Accountadd accountaddhot = new Accountadd();
							accountaddhot.setAccountId(Long.valueOf(accountId));
							accountaddhot.setMoney(new BigDecimal(coldNum).multiply(new BigDecimal(-1)));
							accountaddhot.setMonteyType(1);
							accountaddhot.setAcccountType(1);
							accountaddhot.setRemarks(55);
							accountaddhot.setTransactionNum(record.getTransactionNum());

							// ???????????????
							Accountadd accountaddcold = new Accountadd();
							accountaddcold.setAccountId(Long.valueOf(accountId));
							accountaddcold.setMoney(new BigDecimal(coldNum));
							accountaddcold.setMonteyType(2);
							accountaddcold.setAcccountType(1);
							accountaddcold.setRemarks(55);
							accountaddhot.setTransactionNum(record.getTransactionNum());

							List<Accountadd> listLock = new ArrayList<Accountadd>();
							listLock.add(accountaddhot);
							listLock.add(accountaddcold);
							messageProducer.toAccount(JSON.toJSONString(listLock));

							result.setSuccess(true);
							return result;
						}
					} else {
					    result.setSuccess(false);
					    result.setMsg("??????????????????");
                        return result;
                    }
				}
			} else {
				result.setSuccess(false);
				result.setMsg("?????????????????????");
				return result;
			}
		} else {
		    result.setSuccess(false);
		    result.setMsg("????????????");
		    return result;
        }
        return result;
	}


	/**
	 * ??????????????????????????????????????????
	 * @param recordId ????????????id
	 * @param releaseMethod ????????????
	 * @param releaseMethodVal ???????????????
	 * @param lockCycle ????????????
	 * @param coldNum ????????????
	 * @param releaseFrequency ??????????????????????????????null
	 * @param keepDecimalForCoin ????????????
	 * @return
	 */
	private void computingTime (Long recordId, Integer releaseMethod, String releaseMethodVal, BigDecimal lockCycle, BigDecimal coldNum, BigDecimal releaseFrequency, Integer keepDecimalForCoin) {
		int num = 0; // ????????????
		BigDecimal eachReleaseVal = BigDecimal.ZERO; // ???????????????

		String lockfrequency = "1"; // ?????????????????? 1???
		// ???????????????????????????
		if (releaseFrequency != null) {
			// ??????????????????
			lockfrequency = releaseFrequency.setScale(0).toString();
		}
		// ???????????????????????????
		Date firstDate = DateUtil.addDay(new Date(), lockCycle.intValue());

		switch (releaseMethod.intValue()) {
			//1?????????????????? 2????????????????????? 3?????????????????????
			case 1:
				// ??????????????????
				num = new Integer(releaseMethodVal).intValue();
				// ????????????????????????
				eachReleaseVal = coldNum.divide(new BigDecimal(releaseMethodVal), 0, BigDecimal.ROUND_DOWN);
				break;
			case 2:
				// ??????????????????
				num = (coldNum.divide(new BigDecimal(releaseMethodVal), 0, BigDecimal.ROUND_UP)).intValue();
				// ????????????????????????
				eachReleaseVal = new BigDecimal(releaseMethodVal);
				break;
			case 3:
				// ???????????????
				eachReleaseVal = coldNum.multiply(new BigDecimal(releaseMethodVal)).divide(new BigDecimal(100), keepDecimalForCoin, BigDecimal.ROUND_DOWN);
				// ??????????????????
				num = (coldNum.divide(eachReleaseVal, 0, BigDecimal.ROUND_UP)).intValue();
				// ???????????????
				eachReleaseVal = eachReleaseVal.setScale(0, BigDecimal.ROUND_DOWN);
				break;
			default:
				break;
		}
		if (num != 0) {
			BigDecimal sumVal = BigDecimal.ZERO;
			// ????????????????????????????????????
			for (int i = 1; i <= num; i++) {
				ExDmLockReleaseTime time = new ExDmLockReleaseTime();
				time.setRecordId(recordId);
				if (i == num) {
					time.setReleaseVal(coldNum.subtract(sumVal));
				} else {
					sumVal = sumVal.add(eachReleaseVal);
					time.setReleaseVal(eachReleaseVal);
				}
				if (i == 1) {
					time.setReleaseTime(firstDate);
				} else {
					// ????????????????????????
					Date nextDate = DateUtil.addDay(firstDate, Integer.parseInt(lockfrequency) * (i - 1));
					time.setReleaseTime(nextDate);
 				}
				exDmLockReleaseTimeService.save(time);
			}
		}
	}

	private int compare_date(Date DATE1, Date DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(df.format(DATE1));
			Date dt2 = df.parse(df.format(DATE2));
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	@Override
	public JsonResult checkPass(Map<String, String> params) {
		String ids = params.get("ids");
		String googleCode = params.get("googleCode");
		JsonResult jsonResult = new JsonResult();

		if(googleCode ==null ){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("???????????????????????????");
			return jsonResult;
		}

		long t = System.currentTimeMillis();
		GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
		long code = Long.parseLong(googleCode);
		String googleKey = FileUtil.getGoogleKey();
		boolean r = ga.check_code( googleKey,code, t);
		if(!r){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("?????????????????????");
			return jsonResult;
		}

		if (!"".equals(ids) && ids != null) {
			String[] list = ids.split(",");
			Long id = null;
			for (String l : list) {
				id = Long.valueOf(l);
				try {
					ExDmTransaction transaction = this.get(id);
					// transactionType=2?????????status=1?????????
					if (transaction.getTransactionType().intValue() == 2 && transaction.getStatus().intValue() == 1) {
						String coinCode = transaction.getCoinCode();
						Long customerId = transaction.getCustomerId();

						AppCustomer customer = appCustomerService.get(customerId);

						jsonResult = sendTo(transaction);
						if (jsonResult.getSuccess()) {

							//???????????????
							appMessageService.sysSendMsg(customer, MsgTypeEnum.GETMONEY);

							// ????????????????????????
							SmsParam smsParam = new SmsParam();
							smsParam.setHryMobilephone(customer.getUsername());
							smsParam.setHrySmstype("sms_withdraw_rmbOrCoin");
							smsParam.setHryCode(coinCode);
							SmsSendUtil.sendSmsCode(smsParam);
							//???????????????????????????
							this.sendFrontMessage(customer,true);
						} else {
							return jsonResult;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					jsonResult.setSuccess(false);
					jsonResult.setMsg("??????????????????????????????");
					return jsonResult;
				}
			}
			jsonResult.setSuccess(true);
		}

		return jsonResult;
	}

	@Override
	public void sendFrontMessage(AppCustomer customer,Boolean flag) {
		//????????????????????????
		QueryFilter amcQueryFilter = new QueryFilter(AppMessageCategory.class);
		amcQueryFilter.addFilter( "keywords=", "5" );//5?????????
		AppMessageCategory appMessageCategory = appMessageCategoryService.get(amcQueryFilter);
		if (appMessageCategory == null) {
			return;
		}
		QueryFilter amtQueryFilter = new QueryFilter(AppMessageTemplate.class);
		amtQueryFilter.addFilter( "templateId=", appMessageCategory.getId() );//5?????????
		AppMessageTemplate appMessageTemplate = appMessageTemplateService.get(amtQueryFilter);
		if (appMessageTemplate == null) {
			return;
		}
		//?????????????????????
		appMessageService.saveMessage(customer,appMessageCategory,appMessageTemplate,flag);

	}

	/**
	 *
	 * ???????????????????????????
	 *
	 * @author: Zhang Xiaofang
	 * @param: @param
	 *             account ??????????????????(??????????????????)
	 * @param: @param
	 *             address ????????????(??????????????????)
	 * @param: @param
	 *             amount ??????
	 * @param: @param
	 *             coinCode ????????????
	 * @param: @param
	 *             id
	 * @param: @return
	 * @return: String
	 * @Date : 2016???9???3??? ??????3:59:00
	 * @throws:
	 */
	private JsonResult sendTo(ExDmTransaction t) {
		String fromAddress = t.getOurAccountNumber();
		String toAddress = t.getInAddress();
		String amount = t.getTransactionMoney().subtract(t.getFee()).setScale(8, BigDecimal.ROUND_HALF_DOWN).toString();
		String coinCode = t.getCoinCode();
		String transactionNum = t.getTransactionNum();
		Long id = t.getId();
		String userName = t.getCustomerName();
		JsonResult jsonResult = new JsonResult();
		try {
			String coinInterFace = PropertiesUtils.APP.getProperty("app.coinInterFace");
			String txStr = null;

			QueryFilter queryFilter = new QueryFilter(ExDigitalmoneyAccount.class);
			queryFilter.addFilter("customerId=",t.getCustomerId());
			queryFilter.addFilter("coinCode=",coinCode);
			ExDigitalmoneyAccount moneyAccount = exDigitalmoneyAccountService.get(queryFilter);

			QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
			if(moneyAccount.getPublicKey().contains(":")){
				String memo = t.getMemo();
				if("".equals(memo) || null == memo){
					jsonResult.setSuccess(false);
					jsonResult.setMsg("?????????????????????");
				}
				filter.addFilter("publicKey_like", "%"+memo+"%");
				filter.addFilter("coinCode=", t.getCoinCode());
			}else{
				filter.addFilter("publicKey=", t.getInAddress());
				filter.addFilter("coinCode=", t.getCoinCode());
			}

			ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(filter);

			if(exDigitalmoneyAccount!=null){
				this.sendOurCustomer(t,exDigitalmoneyAccount);

				jsonResult.setSuccess(true);
				jsonResult.setMsg("??????????????????!");
			}else {
				// ???????????????????????????
				if ("LMC".equals(coinInterFace)) {// ?????????????????????
					txStr = CoinInterfaceUtil.lmcSendTo(fromAddress, toAddress, amount, coinCode, transactionNum, userName);
				} else {// ??????????????????
					txStr = CoinInterfaceUtil.sendTo(t.getCustomerName(), toAddress, amount, coinCode, transactionNum);
				}
				if (StringUtils.isNotEmpty(txStr)) {
					logger.error("?????????????????????????????????" + txStr);
					JsonResult result = com.alibaba.fastjson.JSON.parseObject(txStr, JsonResult.class);
					//????????????
					if (result.getSuccess()) {
						ExDmTransaction transaction = this.get(id);
						transaction.setOrderNo(result.getMsg() + "_send");
						this.update(transaction);
						this.pasePutOrder(id);
						jsonResult.setSuccess(true);
						jsonResult.setMsg("??????????????????!");
					} else {
						jsonResult.setSuccess(false);
						jsonResult.setMsg(result.getMsg());
					}
				} else {
					jsonResult.setSuccess(false);
					jsonResult.setMsg("??????????????????!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("??????????????????");
			return jsonResult;
		}
		return jsonResult;
	}

	@Override
	public String pasePutOrder(Long id) {
		ExDmTransaction exDmTransaction = this.get(id);
		Integer i = exDmTransaction.getStatus();
		if (i.intValue() == 2) {
			return "NO";
		}
		Long accountId=exDmTransaction.getAccountId();
		if(accountId!=null){
			ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(accountId);
			Integer tp = exDmTransaction.getTransactionType();
			//??????
			if (tp == 1) {
				//????????????
				return "NO";

			}
			//??????
			if (tp == 2) {

				BigDecimal transactionMoney = exDmTransaction.getTransactionMoney();
				BigDecimal coldMoney = exDigitalmoneyAccount.getColdMoney();
				BigDecimal l = coldMoney.subtract(transactionMoney);
				exDigitalmoneyAccount.setColdMoney(l);



				//----??????mq??????----start
				//???????????????
				Accountadd accountadd2 = new Accountadd();
				accountadd2.setAccountId(exDigitalmoneyAccount.getId());
				accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()).multiply(new BigDecimal(-1)));
				accountadd2.setMonteyType(2);
				accountadd2.setAcccountType(1);
				accountadd2.setRemarks(33);
				accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

				//????????? -- ???
				Accountadd accountaddf2 = new Accountadd();
				accountaddf2.setAccountId(exDigitalmoneyAccount.getId());
				accountaddf2.setMoney(exDmTransaction.getFee().multiply(new BigDecimal(-1)));
				accountaddf2.setMonteyType(2);
				accountaddf2.setAcccountType(1);
				accountaddf2.setRemarks(34);
				accountaddf2.setTransactionNum(exDmTransaction.getTransactionNum());

				List<Accountadd> list = new ArrayList<Accountadd>();
				list.add(accountadd2);
				list.add(accountaddf2);

				//----??????mq??????----end

				// ??????????????????
				//exDigitalmoneyAccountDao.updateByPrimaryKeySelective(exDigitalmoneyAccount);

				// ????????????
				exDmTransaction.setStatus(2);
				exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
				this.update(exDmTransaction);
				//??????????????????
				AppOurAccount ourAccount=appOurAccountService.getAppOurAccount(ContextUtil.getWebsite(),exDmTransaction.getCoinCode(), Integer.valueOf("1"));

				appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "????????????", "");

				appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "?????????????????????", "fee");


				messageProducer.toAccount(JSON.toJSONString(list));
				return "OK";
			} else {
				return "NO";
			}
		}else{
			return "NO";
		}
	}

	@Override
	public void sendOurCustomer(ExDmTransaction exDmTransaction, ExDigitalmoneyAccount exDigitalmoneyAccount) {
		//??????
		AppCustomer appCustomer = appCustomerService.get(exDigitalmoneyAccount.getCustomerId());
		ExDmTransaction ex = new ExDmTransaction();
		ex.setCustomerId(exDigitalmoneyAccount.getCustomerId());
		ex.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
		ex.setAccountId(exDigitalmoneyAccount.getId());
		ex.setTransactionType(1);
		ex.setTransactionMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()));
		ex.setCustomerName(exDigitalmoneyAccount.getUserName());
		ex.setTrueName(appCustomer.getTrueName());
		ex.setSurname(appCustomer.getSurname());
		ex.setStatus(2);
		ex.setSaasId(RpcContext.getContext().getAttachment(
				"saasId"));
		ex.setCoinCode(exDigitalmoneyAccount.getCoinCode());
		ex.setCurrencyType("CNY");
		ex.setFee(new BigDecimal(0));
		ex.setInAddress(exDigitalmoneyAccount.getPublicKey());
		ex.setOrderNo(exDmTransaction.getTransactionNum());
		ex.setRemark("????????????");
		ex.setOptType(3);
		exDmTransaction.setOptType(3);
		// ????????????
		super.save(ex);


		//???????????????
		Accountadd accountadd3 = new Accountadd();
		accountadd3.setAccountId(ex.getAccountId());
		accountadd3.setMoney(ex.getTransactionMoney());
		accountadd3.setMonteyType(1);
		accountadd3.setAcccountType(1);
		accountadd3.setRemarks(31);
		accountadd3.setTransactionNum(ex.getTransactionNum());

		List<Accountadd> list2 = new ArrayList<Accountadd>();
		list2.add(accountadd3);
		messageProducer.toAccount(com.alibaba.fastjson.JSON.toJSONString(list2));


		//----??????
		//???????????????
		Accountadd accountadd2 = new Accountadd();
		accountadd2.setAccountId(exDmTransaction.getAccountId());
		accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()).multiply(new BigDecimal(-1)));
		accountadd2.setMonteyType(2);
		accountadd2.setAcccountType(1);
		accountadd2.setRemarks(33);
		accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

		//????????? -- ???
		Accountadd accountaddf1 = new Accountadd();
		accountaddf1.setAccountId(exDmTransaction.getAccountId());
		accountaddf1.setMoney(exDmTransaction.getFee().multiply(new BigDecimal(-1)));
		accountaddf1.setMonteyType(2);
		accountaddf1.setAcccountType(1);
		accountaddf1.setRemarks(34);
		accountaddf1.setTransactionNum(exDmTransaction.getTransactionNum());

		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd2);
		list.add(accountaddf1);

		// ????????????
		exDmTransaction.setStatus(2);
		super.update(exDmTransaction);
		//??????????????????
		AppOurAccount ourAccount=appOurAccountService.getAppOurAccount(ContextUtil.getWebsite(),exDmTransaction.getCoinCode(), Integer.valueOf("1"));
		appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "????????????", "");
		appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "?????????????????????", "fee");


		exDmTransaction.setStatus(2);
		super.update(exDmTransaction);
		//----??????mq??????----end
		messageProducer.toAccount(com.alibaba.fastjson.JSON.toJSONString(list));
	}

	@Override
	public String[] lmcTransfer(LmcTransfer transfer) {
		String[] result = new String[2];

		try {
			String Secret = PropertiesUtils.APP.getProperty("app.LMC_Secret");
			String App_key = PropertiesUtils.APP.getProperty("app.LMC_App_key");
			String strUrl = PropertiesUtils.APP.getProperty("app.LMC_Url") + PropertiesUtils.APP.getProperty("app.LMC_Wallet_Transfer");
			transfer.setApp_key(App_key);
			transfer.setRequest_time(System.currentTimeMillis() / 1000L + "");
			String app_key = transfer.getApp_key();
			String request_time = transfer.getRequest_time();
			String transfer_type = transfer.getTransfer_type();
			String symbol = transfer.getSymbol();
			String type = transfer.getType();
			String coins = transfer.getCoins();
			String from_wallet = transfer.getFrom();
			String to_wallet = transfer.getTo();
			String transfer_id = transfer.getTransfer_id();
			String validation_type = transfer.getValidation_type();
			String validation_code = transfer.getValidation_code();
			String validation_phone = transfer.getValidation_phone();
			String[] s = new String[]{app_key, request_time, transfer_type, symbol, type, coins, from_wallet, to_wallet, transfer_id, validation_type, validation_code, validation_phone, Secret};
			if (StringUtil.containEmpty(s)) {
				result[0] = "0";
				result[1] = "????????????";
				return result;
			}

			String auth_code = StringUtil.stringSort(s, "_");
			auth_code = Md5Encrypt.md5(auth_code);
			Map<String, String> map = new HashMap();
			map.put("auth_code", auth_code);
			map.put("app_key", app_key);
			map.put("request_time", request_time);
			map.put("transfer_type", transfer_type);
			map.put("symbol", symbol);
			map.put("type", type);
			map.put("coins", coins);
			map.put("from_wallet", from_wallet);
			map.put("to_wallet", to_wallet);
			map.put("transfer_id", transfer_id);
			map.put("validation_type", validation_type);
			map.put("validation_code", validation_code);
			map.put("validation_phone", validation_phone);
			String param = StringUtil.string2params(map);
			logger.error(strUrl + "?" + param);
			String back = HttpConnectionUtil.postSend(strUrl, param);
			logger.error("???????????????????????????" + back);
			back = StringUtil.string2json(back);
			AppLogThirdInterface log = new AppLogThirdInterface();
			log.setIp("127.0.0.1");
			log.setUrl(strUrl);
			log.setParams(param);
			log.setResult(back);
			appLogThirdInterfaceService.save(log);
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(back)) {
				JSONObject rs = JSON.parseObject(back);
				if (rs != null) {
					JSONObject data = rs.getJSONObject("data");
					if (data != null) {
						result[0] = "8";
						result[1] = data.getString("transfer_id");
					} else {
						result[0] = rs.getString("code");
						result[1] = rs.getString("msg");
					}

					return result;
				}
			}
		} catch (Exception var26) {
			logger.error("???????????????????????????????????????????????????");
			var26.printStackTrace();
		}

		result[0] = "404";
		result[1] = "????????????";
		return result;
	}

	@Override
	public JsonResult checkReject(Map<String, String> params) {
		JsonResult jsonResult = new JsonResult();
		if(!params.isEmpty()) {
			String ids = params.get("ids");
			String str = params.get("reason");
			if (!"".equals(ids) && ids != null) {
				String[] list = ids.split(",");
				String reason = "????????????";

				reason = str;
				Long id = null;
				for (String l : list) {
					id = Long.valueOf(l);
					try {
						ExDmTransaction exDmTransaction = this.get(id);
						if (exDmTransaction.getStatus().intValue() == 1) {
							exDmTransaction.setRejectionReason(reason);

							Long customerId = exDmTransaction.getCustomerId();
							AppCustomer customer = appCustomerService.get(customerId);

							boolean flag = appTransactionService.dmWithdrawReject(exDmTransaction);
							if (flag) {
								// ??????????????????????????????(????????????)
								SmsParam smsParam = new SmsParam();
								smsParam.setHryMobilephone(customer.getUsername());
								smsParam.setHrySmstype("sms_coinwithdraw_invalid");
								smsParam.setHryCode(exDmTransaction.getCoinCode());
								SmsSendUtil.sendSmsCode(smsParam);

								//???????????????????????????
								this.sendFrontMessage(customer,false);
							}
						}
					} catch (Exception e) {
						jsonResult.setSuccess(false);
						e.printStackTrace();
						return jsonResult;
					}
				}
				jsonResult.setSuccess(true);
				return jsonResult;
			}
		}
		jsonResult.setSuccess(false);
		jsonResult.setMsg("????????????");
		return jsonResult;
	}

	/**
	 * ??????????????????????????????
	 * @param optType
	 * @return
	 */
	@Override
	public BigDecimal getYesterdayPostOrGet (String optType) {
		Map<String, String> map = new HashMap<>();
		map.put("optType", optType);
		BigDecimal sum = new BigDecimal(0);
		List<ExDmTransaction> tList = ((ExDmTransactionDao)dao).getYesterdayPostOrGet(map);
		if (tList != null && tList.size() > 0) {
			for (ExDmTransaction edt : tList) {
				if ("USDT".equals(edt.getCoinCode())) {
					sum = sum.add(edt.getTransactionMoney());
				} else {
					BigDecimal usdtM = getCurrentExchangPrice(edt.getCoinCode(), "USDT");
					sum = sum.add(usdtM.multiply(edt.getTransactionMoney()));
				}
			}
		}
		return sum.setScale(0, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Function: MiningController.java
	 * @Description: ??????????????????usdt?????????????????????????????????
	 * @author: zjj
	 * @date: 2018???8???9??? ??????11:43:12
	 */
	private BigDecimal getCurrentExchangPrice(String coincode, String fixPriceCoinCode) {
		String coinStr = redisService.get("cn:coinInfoList2");
		String coinCode = coincode + "_" + fixPriceCoinCode;
		BigDecimal yesterdayPrice = new BigDecimal(0);
		if (!StringUtils.isEmpty(coinStr)) {
			List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
			for (Coin2 c : coins) {
				if (coinCode.equals(c.getCoinCode() + "_" + c.getFixPriceCoinCode())) {
					if (!StringUtils.isEmpty(c.getYesterdayPrice())) {
						yesterdayPrice = new BigDecimal(c.getYesterdayPrice());
					}
				}
			}
		}
		return yesterdayPrice;
	}

}
