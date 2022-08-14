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
		// ----------------------分页查询头部外壳------------------------------
		// 创建PageResult对象
		Page<AppCustomer> page = PageFactory.getPage(filter);

		// ----------------------分页查询头部外壳------------------------------

		// ----------------------查询开始------------------------------

		//币钟类型
		String coinCode = filter.getRequest().getParameter("coinCode");
		//开始时间
		String createdG = filter.getRequest().getParameter("created_GT");
		//结束时间
		String createdL = filter.getRequest().getParameter("created_LT");
		// 转入钱包地址
		String inAddress = filter.getRequest().getParameter("inAddress");
		String outAddress = filter.getRequest().getParameter("outAddress_like");
		// 交易订单号
		String transactionNum = filter.getRequest().getParameter("transactionNum");

		// 交易类型
		String transactionType = filter.getRequest().getParameter("transactionType");
		// 交易状态
		String status = filter.getRequest().getParameter("status");
		// 邮箱
		String email = filter.getRequest().getParameter("email");
		// 手机号
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		// 名字
		String trueName = filter.getRequest().getParameter("trueName");

		Map<String, Object> map = new HashMap<String, Object>();
		// 交易类型
		if (!StringUtils.isEmpty(transactionType)) {
			map.put("transactionType", transactionType);
		}
		// 交易状态
		if (!StringUtils.isEmpty(status)) {
			map.put("status", status);
		}
		// 邮箱
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		// 手机号
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		// 名字
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}

		// 钱包转入地址
		if (!StringUtils.isEmpty(inAddress)) {
			map.put("inAddress", "%" + inAddress + "%");
		}

		if (!StringUtils.isEmpty(outAddress)) {
			map.put("outAddress", "%" + outAddress + "%");
		}
		// 交易订单号
		if (!StringUtils.isEmpty(transactionNum)) {
			map.put("transactionNum", "%" + transactionNum + "%");
		}

		// 币钟类型
		if (!StringUtils.isEmpty(coinCode)) {
			if (!coinCode.equals("all")) {//如果不等于all(全部)
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
	 * 新版的锁仓功能
	 * @param paraMap
	 */
	@Override
	public synchronized void lockManagementHandler (Map<String, Object> paraMap) {
		if (paraMap != null) {
			// 参数获取
			String customerId = paraMap.get("customerId").toString();
			String coinCode = paraMap.get("coinCode").toString();
			String transactionNum = paraMap.get("transactionNum").toString();

			// 获取币位数
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

			// 当前登录人
			BaseManageUser currentUser = ContextUtil.getCurrentUser();
			String optUser = currentUser.getUsername();

			// 获取该币种的锁仓信息
			QueryFilter ruleQf = new QueryFilter(ExDmLock.class);
			ruleQf.addFilter("coinCode=", coinCode);
			ruleQf.addFilter("isLock=", 1);
			ruleQf.addFilter("isValid=", 0);
			ExDmLock exDmLock = exDmLockService.get(ruleQf);
			if (exDmLock != null) {
				// 获取锁仓开始时间和锁仓持续周期
				Date lockStartTime = exDmLock.getLockStartTime();
				BigDecimal lockDuration = exDmLock.getLockDuration();
				// 当前时间
				Date currentTime = new Date();

				// 计算锁仓结束时间
				Date lockEndTime = null;
				if (lockDuration != null) {
					lockEndTime = DateUtil.addDay(lockStartTime, lockDuration.intValue());
				}

				/**
				 * 1、如果锁仓持续周期为空，则可一直进行锁仓操作
				 * 2、如果当前时间大于锁仓开始时间并且小于锁仓结束时间，则也可以进行锁仓操作
				 */
				if (lockEndTime == null || (compare_date(currentTime, lockStartTime) >= 0 && compare_date(currentTime, lockEndTime) == -1)) {
					// 从redis中获取本人的资金账户信息
					RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
					UserRedis userRedis = redisUtil.get(customerId.toString());
					if (userRedis != null) {
						ExDigitalmoneyAccountRedis exDigitalmoneyAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
						if (exDigitalmoneyAccount != null) {
							BigDecimal hotMoney = exDigitalmoneyAccount.getHotMoney(); // 可用币总数
							BigDecimal usableMoney = new BigDecimal(0); // 冻结币基数币数
							// 获取锁仓起点额度
							BigDecimal lockStartLimit = exDmLock.getLockStartLimit();
							// 获取锁仓方式
							Integer lockMethod = exDmLock.getLockMethod();
							// 获取锁仓比例
							BigDecimal lockRatio = exDmLock.getLockRatio();

							// 如果可用币总数大于锁仓额度数，则计算冻结额度
							if (hotMoney.compareTo(lockStartLimit) > 0) {
								// 1、持有数量 2、起点额度外
								if (lockMethod == 1) {
									usableMoney = hotMoney;
								} else {
									usableMoney = hotMoney.subtract(lockStartLimit);
								}
								// 冻结币个数
								BigDecimal cold = usableMoney.multiply(lockRatio).divide(new BigDecimal(100)).setScale(keepDecimalForCoin, BigDecimal.ROUND_DOWN);

								// 记录冻结流水
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
								// 保存记录
								Integer saveRecord = (Integer) exDmLockRecordService.save(record);
								// 获取释放时间和每次释放值
								if (saveRecord != null && saveRecord.intValue() > 0) {
									computingTime(record.getId(), exDmLock.getReleaseMethod(), exDmLock.getReleaseMethodVal(), exDmLock.getLockCycle(), cold, exDmLock.getReleaseFrequency(), keepDecimalForCoin);

									// 7、修改充值账户可用和冻结数
									// 热账户减少
									Accountadd accountaddhot = new Accountadd();
									accountaddhot.setAccountId(Long.valueOf(paraMap.get("accountId").toString()));
									accountaddhot.setMoney(cold.multiply(new BigDecimal(-1)));
									accountaddhot.setMonteyType(1);
									accountaddhot.setAcccountType(1);
									accountaddhot.setRemarks(55);
									accountaddhot.setTransactionNum(transactionNum);

									// 冷账户增加
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
				System.out.println("该币种的锁仓规则无效或不存在");
			}
		}
	}

	/**
	 * 手动锁仓功能
	 * @param paramMap
	 */
	@Override
	public synchronized JsonResult manualLockHandler (Map<String, Object> paramMap) {
		JsonResult result = new JsonResult();
		if (paramMap != null) {
			// 参数获取
			String coinCode = paramMap.get("coinCode").toString();
			String customerId = paramMap.get("customerId").toString();
			String coldNum = paramMap.get("coldNum").toString();
			String lockCycle = paramMap.get("lockCycle").toString();
			String releaseMethod = paramMap.get("releaseMethod").toString();
			String releaseMethodVal = paramMap.get("releaseMethodVal").toString();
			String accountId = paramMap.get("accountId").toString();

			// 获取币位数
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

			// 当前登录人
			BaseManageUser currentUser = ContextUtil.getCurrentUser();
			String optUser = currentUser.getUsername();

			// 从redis中获取本人的资金账户信息
			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(customerId.toString());
			if (userRedis != null) {
				ExDigitalmoneyAccountRedis exDigitalmoneyAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
				if (exDigitalmoneyAccount != null) {
					BigDecimal hotMoney = exDigitalmoneyAccount.getHotMoney(); // 可用币总数
					if (hotMoney.compareTo(new BigDecimal(coldNum)) > -1) {
						// 记录冻结流水
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
                        // 保存记录
                        Integer savesaRecord = (Integer) exDmLockRecordService.save(record);
                        if (savesaRecord != null && savesaRecord.intValue() > 0) {
                            // 保存释放时间和每次释放值
                            computingTime(record.getId(), new Integer(releaseMethod), releaseMethodVal, new BigDecimal(lockCycle), new BigDecimal(coldNum), null, keepDecimalForCoin);

							// 7、修改充值账户可用和冻结数
							// 热账户减少
							Accountadd accountaddhot = new Accountadd();
							accountaddhot.setAccountId(Long.valueOf(accountId));
							accountaddhot.setMoney(new BigDecimal(coldNum).multiply(new BigDecimal(-1)));
							accountaddhot.setMonteyType(1);
							accountaddhot.setAcccountType(1);
							accountaddhot.setRemarks(55);
							accountaddhot.setTransactionNum(record.getTransactionNum());

							// 冷账户增加
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
					    result.setMsg("可用余额不足");
                        return result;
                    }
				}
			} else {
				result.setSuccess(false);
				result.setMsg("该用户未登录过");
				return result;
			}
		} else {
		    result.setSuccess(false);
		    result.setMsg("参数错误");
		    return result;
        }
        return result;
	}


	/**
	 * 计算每次释放值和释放时间入库
	 * @param recordId 锁仓记录id
	 * @param releaseMethod 释放方式
	 * @param releaseMethodVal 释放方式值
	 * @param lockCycle 锁仓周期
	 * @param coldNum 冻结数量
	 * @param releaseFrequency 锁仓释放频率，没有传null
	 * @param keepDecimalForCoin 小数位数
	 * @return
	 */
	private void computingTime (Long recordId, Integer releaseMethod, String releaseMethodVal, BigDecimal lockCycle, BigDecimal coldNum, BigDecimal releaseFrequency, Integer keepDecimalForCoin) {
		int num = 0; // 释放次数
		BigDecimal eachReleaseVal = BigDecimal.ZERO; // 每次释放值

		String lockfrequency = "1"; // 默认执行频率 1天
		// 获取定时器执行频率
		if (releaseFrequency != null) {
			// 获取执行频率
			lockfrequency = releaseFrequency.setScale(0).toString();
		}
		// 获取第一次释放时间
		Date firstDate = DateUtil.addDay(new Date(), lockCycle.intValue());

		switch (releaseMethod.intValue()) {
			//1、总释放次数 2、每次释放数量 3、每次释放比例
			case 1:
				// 获取释放次数
				num = new Integer(releaseMethodVal).intValue();
				// 定时器每次释放值
				eachReleaseVal = coldNum.divide(new BigDecimal(releaseMethodVal), 0, BigDecimal.ROUND_DOWN);
				break;
			case 2:
				// 获取释放次数
				num = (coldNum.divide(new BigDecimal(releaseMethodVal), 0, BigDecimal.ROUND_UP)).intValue();
				// 定时器每次释放值
				eachReleaseVal = new BigDecimal(releaseMethodVal);
				break;
			case 3:
				// 每次释放值
				eachReleaseVal = coldNum.multiply(new BigDecimal(releaseMethodVal)).divide(new BigDecimal(100), keepDecimalForCoin, BigDecimal.ROUND_DOWN);
				// 获取释放次数
				num = (coldNum.divide(eachReleaseVal, 0, BigDecimal.ROUND_UP)).intValue();
				// 设置释放值
				eachReleaseVal = eachReleaseVal.setScale(0, BigDecimal.ROUND_DOWN);
				break;
			default:
				break;
		}
		if (num != 0) {
			BigDecimal sumVal = BigDecimal.ZERO;
			// 根据释放次数计算释放时间
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
					// 计算下次释放时间
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
			jsonResult.setMsg("谷歌验证码不能为空");
			return jsonResult;
		}

		long t = System.currentTimeMillis();
		GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
		long code = Long.parseLong(googleCode);
		String googleKey = FileUtil.getGoogleKey();
		boolean r = ga.check_code( googleKey,code, t);
		if(!r){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("谷歌验证码错误");
			return jsonResult;
		}

		if (!"".equals(ids) && ids != null) {
			String[] list = ids.split(",");
			Long id = null;
			for (String l : list) {
				id = Long.valueOf(l);
				try {
					ExDmTransaction transaction = this.get(id);
					// transactionType=2提币、status=1待审核
					if (transaction.getTransactionType().intValue() == 2 && transaction.getStatus().intValue() == 1) {
						String coinCode = transaction.getCoinCode();
						Long customerId = transaction.getCustomerId();

						AppCustomer customer = appCustomerService.get(customerId);

						jsonResult = sendTo(transaction);
						if (jsonResult.getSuccess()) {

							//发送站内信
							appMessageService.sysSendMsg(customer, MsgTypeEnum.GETMONEY);

							// 发送提币短信通知
							SmsParam smsParam = new SmsParam();
							smsParam.setHryMobilephone(customer.getUsername());
							smsParam.setHrySmstype("sms_withdraw_rmbOrCoin");
							smsParam.setHryCode(coinCode);
							SmsSendUtil.sendSmsCode(smsParam);
							//发送前台的消息通知
							this.sendFrontMessage(customer,true);
						} else {
							return jsonResult;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					jsonResult.setSuccess(false);
					jsonResult.setMsg("提币操作后台处理异常");
					return jsonResult;
				}
			}
			jsonResult.setSuccess(true);
		}

		return jsonResult;
	}

	@Override
	public void sendFrontMessage(AppCustomer customer,Boolean flag) {
		//获取提币模板消息
		QueryFilter amcQueryFilter = new QueryFilter(AppMessageCategory.class);
		amcQueryFilter.addFilter( "keywords=", "5" );//5为提币
		AppMessageCategory appMessageCategory = appMessageCategoryService.get(amcQueryFilter);
		if (appMessageCategory == null) {
			return;
		}
		QueryFilter amtQueryFilter = new QueryFilter(AppMessageTemplate.class);
		amtQueryFilter.addFilter( "templateId=", appMessageCategory.getId() );//5为提币
		AppMessageTemplate appMessageTemplate = appMessageTemplateService.get(amtQueryFilter);
		if (appMessageTemplate == null) {
			return;
		}
		//封装通知的消息
		appMessageService.saveMessage(customer,appMessageCategory,appMessageTemplate,flag);

	}

	/**
	 *
	 * 调用钱包接口转出币
	 *
	 * @author: Zhang Xiaofang
	 * @param: @param
	 *             account 我方币种账户(转出币的账户)
	 * @param: @param
	 *             address 提币账户(转入币的地址)
	 * @param: @param
	 *             amount 数量
	 * @param: @param
	 *             coinCode 币种类型
	 * @param: @param
	 *             id
	 * @param: @return
	 * @return: String
	 * @Date : 2016年9月3日 下午3:59:00
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
					jsonResult.setMsg("提币地址不合格");
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
				jsonResult.setMsg("提币申请成功!");
			}else {
				// 调用钱包接口转出币
				if ("LMC".equals(coinInterFace)) {// 邻萌宝提币接口
					txStr = CoinInterfaceUtil.lmcSendTo(fromAddress, toAddress, amount, coinCode, transactionNum, userName);
				} else {// 默认提币接口
					txStr = CoinInterfaceUtil.sendTo(t.getCustomerName(), toAddress, amount, coinCode, transactionNum);
				}
				if (StringUtils.isNotEmpty(txStr)) {
					logger.error("提币接口调用返回结果：" + txStr);
					JsonResult result = com.alibaba.fastjson.JSON.parseObject(txStr, JsonResult.class);
					//成功调用
					if (result.getSuccess()) {
						ExDmTransaction transaction = this.get(id);
						transaction.setOrderNo(result.getMsg() + "_send");
						this.update(transaction);
						this.pasePutOrder(id);
						jsonResult.setSuccess(true);
						jsonResult.setMsg("提币申请成功!");
					} else {
						jsonResult.setSuccess(false);
						jsonResult.setMsg(result.getMsg());
					}
				} else {
					jsonResult.setSuccess(false);
					jsonResult.setMsg("接口调用错误!");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg("后台处理异常");
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
			//充币
			if (tp == 1) {
				//要发消息
				return "NO";

			}
			//提币
			if (tp == 2) {

				BigDecimal transactionMoney = exDmTransaction.getTransactionMoney();
				BigDecimal coldMoney = exDigitalmoneyAccount.getColdMoney();
				BigDecimal l = coldMoney.subtract(transactionMoney);
				exDigitalmoneyAccount.setColdMoney(l);



				//----发送mq消息----start
				//冷账户增加
				Accountadd accountadd2 = new Accountadd();
				accountadd2.setAccountId(exDigitalmoneyAccount.getId());
				accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()).multiply(new BigDecimal(-1)));
				accountadd2.setMonteyType(2);
				accountadd2.setAcccountType(1);
				accountadd2.setRemarks(33);
				accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

				//手续费 -- 冷
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

				//----发送mq消息----end

				// 修改可用余额
				//exDigitalmoneyAccountDao.updateByPrimaryKeySelective(exDigitalmoneyAccount);

				// 修改订单
				exDmTransaction.setStatus(2);
				exDmTransaction.setUserId(exDigitalmoneyAccount.getCustomerId());
				this.update(exDmTransaction);
				//我方提币账户
				AppOurAccount ourAccount=appOurAccountService.getAppOurAccount(ContextUtil.getWebsite(),exDmTransaction.getCoinCode(), Integer.valueOf("1"));

				appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币记录", "");

				appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币手续费记录", "fee");


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
		//充币
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
		ex.setRemark("内部互转");
		ex.setOptType(3);
		exDmTransaction.setOptType(3);
		// 保存订单
		super.save(ex);


		//热账户增加
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


		//----提币
		//冷账户减少
		Accountadd accountadd2 = new Accountadd();
		accountadd2.setAccountId(exDmTransaction.getAccountId());
		accountadd2.setMoney(exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()).multiply(new BigDecimal(-1)));
		accountadd2.setMonteyType(2);
		accountadd2.setAcccountType(1);
		accountadd2.setRemarks(33);
		accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

		//手续费 -- 冷
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

		// 修改订单
		exDmTransaction.setStatus(2);
		super.update(exDmTransaction);
		//我方提币账户
		AppOurAccount ourAccount=appOurAccountService.getAppOurAccount(ContextUtil.getWebsite(),exDmTransaction.getCoinCode(), Integer.valueOf("1"));
		appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币记录", "");
		appOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币手续费记录", "fee");


		exDmTransaction.setStatus(2);
		super.update(exDmTransaction);
		//----发送mq消息----end
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
				result[1] = "参数无效";
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
			logger.error("提币接口返回结果：" + back);
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
			logger.error("提币业务处理服务器错误，信息如下：");
			var26.printStackTrace();
		}

		result[0] = "404";
		result[1] = "未知错误";
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
				String reason = "批量驳回";

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
								// 发送提现驳回短信通知(提币驳回)
								SmsParam smsParam = new SmsParam();
								smsParam.setHryMobilephone(customer.getUsername());
								smsParam.setHrySmstype("sms_coinwithdraw_invalid");
								smsParam.setHryCode(exDmTransaction.getCoinCode());
								SmsSendUtil.sendSmsCode(smsParam);

								//发送前台的消息通知
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
		jsonResult.setMsg("参数异常");
		return jsonResult;
	}

	/**
	 * 获取昨日提币或充币量
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
	 * @Description: 去对应币种与usdt的昨日收盘价格进行兑换
	 * @author: zjj
	 * @date: 2018年8月9日 上午11:43:12
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
