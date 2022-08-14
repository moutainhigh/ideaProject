/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月28日 下午7:00:10
 */
package hry.exchange.transaction.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.account.fund.model.AppAccountRecord;
import hry.account.fund.model.AppOurAccount;
import hry.account.fund.service.AppTransactionService;
import hry.account.remote.RemoteAppOurAccountService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.shiro.PasswordHelper;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExAmineOrderService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.account.service.ExDmColdAccountRecordService;
import hry.exchange.account.service.ExDmHotAccountRecordService;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lock.model.ExDmLock;
import hry.exchange.lock.model.ExDmLockRecord;
import hry.exchange.lock.model.ExDmLockReleaseTime;
import hry.exchange.lock.service.ExDmLockRecordService;
import hry.exchange.lock.service.ExDmLockReleaseTimeService;
import hry.exchange.lock.service.ExDmLockService;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductService;
import hry.exchange.purse.CoinInterfaceUtil;
import hry.exchange.transaction.dao.ExDmTransactionDao;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.ExDmTransactionManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.StringUtil;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.web.AppMessageTemplate.model.AppMessageTemplate;
import hry.web.AppMessageTemplate.service.AppMessageTemplateService;
import hry.web.app.model.AppMessageCategory;
import hry.web.message.dao.MessageAsCustomerDao;
import hry.web.message.service.AppMessageCategoryService;
import hry.web.message.service.AppMessageService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午7:00:10
 */
@Service("exDmTransactionService")
public class ExDmTransactionServiceImpl extends BaseServiceImpl<ExDmTransaction, Long> implements ExDmTransactionService {
	private static Logger logger = Logger.getLogger(ExDmTransactionServiceImpl.class);
	@Resource
	public AppMessageService appMessageService;

	@Resource
	public MessageAsCustomerDao messageAsCustomerDao;

	@Resource
	public AppMessageCategoryService appMessageCategoryService;

	@Resource
	public AppMessageTemplateService appMessageTemplateService;

	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource
	public ExDmColdAccountRecordService exDmColdAccountRecordService;

	@Resource
	public ExDmHotAccountRecordService exDmHotAccountRecordService;

	@Resource
	private ExDmLockService exDmLockService;

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	public ExAmineOrderService examineOrderService;

	@Resource
	private ExDmLockRecordService exDmLockRecordService;

	@Resource
	private ExDmLockReleaseTimeService exDmLockReleaseTimeService;

	@Resource
	private RedisService redisService;

	@Resource(name = "exDmTransactionDao")
	@Override
	public void setDao(BaseDao<ExDmTransaction, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		// ----------------------分页查询头部外壳------------------------------
		// 创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDmLend> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
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

		// int[] s = new int[3];
		// if (!StringUtils.isEmpty(status)) {
		// if (!status.equals("all")) {
		// String[] str = status.split(",");
		// if (str.length >= 1) {
		// }
		// map.put("status", "(" + status + ")");
		// }
		// } else {
		// s[0] = 2;
		// map.put("status", "2");
		// }

		if (!StringUtils.isEmpty(createdG)) {
			map.put("createdG", createdG);
		}
		if (!StringUtils.isEmpty(createdL)) {
			map.put("createdL", createdL);
		}
		((ExDmTransactionDao) dao).findPageBySql(map);
		// 设置分页数据
		pageResult.setRows(page.getResult());
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());

		return pageResult;
	}

	@Override
	public ExDmTransaction findLastTrasaction() {

		ExDmTransactionDao exDmTransactionDao = (ExDmTransactionDao) dao;
		ExDmTransaction lastTrasaction = exDmTransactionDao.findLastTrasaction();

		return lastTrasaction;
	}

	/**
	 *
	 * 使用用户名查询某种币他在某种币的提现或充值的数量
	 *
	 * type 字符串类型 表示类型 ---- 1 表示充值 2 表示提现 (1,2)
	 *
	 */
	@Override
	public BigDecimal findTransactionByCustomer(Long customerid, String coinCode, String type) {

		ExDmTransactionDao exDmTransactionDao = (ExDmTransactionDao) dao;

		BigDecimal totalNum = exDmTransactionDao.findGetNumByCustomer(customerid, coinCode, type);

		if (null == totalNum) {
			return BigDecimal.ZERO;
		}

		return totalNum;
	}

	// 之前用来刷新钱包记录方法 ，但由于每次查记录只查前10条，就会有漏掉的数据，所以这个方法暂时不用了。换成了下面的recordAll();
	@Override
	public Map<String, String> record() {
		QueryFilter f = new QueryFilter(ExProduct.class);
		f.addFilter("issueState=", "1");
		Map<String, String> map = new HashMap<String, String>();
		ExProductService exProductService = (ExProductService) ContextUtil.getBean("exProductService");
		List<ExProduct> l = exProductService.find(f);
		StringBuffer sf = new StringBuffer();
		for (ExProduct product : l) {
			// 总查询记录数
			int txTotal = 0;
			// 保存记录总数
			int saveTxTotal = 0;

			try {
				// http 请求获取交易数据
				String txStr = CoinInterfaceUtil.list(product.getCoinCode());
				if (!"".equals(txStr) && null != txStr) {
					@SuppressWarnings("unchecked")
					List<String> txList = (List<String>) JSON.parse(txStr);

					String lastTxid = null;
					RemoteAppCustomerService remoteAppCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
					ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
					ExDmTransactionService exDmTransactionService = (ExDmTransactionService) ContextUtil.getBean("exDmTransactionService");
					ExDmTransaction lastTx = exDmTransactionService.findLastTrasaction();

					// 是否是最后一条数据
					boolean isLast = false;
					if (lastTx == null) {
						lastTxid = "";
						isLast = true;
					} else {
						lastTxid = lastTx.getOrderNo();
					}
					// 查询总记录数
					txTotal = txList.size();

					for (String txs : txList) {
						// 转换为map
						Map<String, Object> tx2map = StringUtil.str2map(txs);
						String json = JSON.toJSONString(tx2map);
						json = json.replaceAll(" ", "");
						hry.exchange.coin.model.Transaction tx = JSON.parseObject(json, hry.exchange.coin.model.Transaction.class);

						// 从系统中记录的最后一条开始记录
						if (tx.getTxId().equals(lastTxid)) {
							isLast = true;
							continue;
						}

						if (isLast) {
							String name = tx.getAccount();
							String address = tx.getAddress();
							QueryFilter queryFilter = new QueryFilter(ExDigitalmoneyAccount.class);
							queryFilter.addFilter("publicKey=", address);
							ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(queryFilter);
							if (null != exDigitalmoneyAccount) {
								// 站点 中国站/国际站
								String webSite = exDigitalmoneyAccount.getWebsite();
								// 用户持币code
								String userCode = exDigitalmoneyAccount.getCoinCode();
								String currencyType = exDigitalmoneyAccount.getCurrencyType();

								RemoteQueryFilter remoteQueryFilter = new RemoteQueryFilter(AppCustomer.class);
								remoteQueryFilter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
								remoteQueryFilter.addFilter("userName=", name);

								AppCustomer appCustomer = remoteAppCustomerService.getByQueryFilter(remoteQueryFilter);
								if (null != appCustomer) {
									String category = tx.getCategory();// 交易类型
									// confirmations
									String confirmations = String.valueOf(tx.getConfirmations());// 确认节点数
									String amount = String.valueOf(tx.getAmount());
									String blocktime = "";
									if (!confirmations.equals("0")) {
										blocktime = tx.getBlockTime().toString();// 区块时间
										blocktime = DateUtil.stampToDate(blocktime + "000");

									}

									String txid = tx.getTxId();// 交易单号
									String time = tx.getTime();
									if (null != time && !"".equals(time)) {
										time = DateUtil.stampToDate(time + "000");
									}
									String timereceived = tx.getTimeReceived();
									if (null != timereceived && !"".equals(timereceived)) {
										timereceived = DateUtil.stampToDate(timereceived + "000");
									}

									Object feeobj = tx.getFee();
									String fee = "";
									if (null != feeobj) {
										fee = String.valueOf(feeobj);
									}
									// 记录 收入的金额
									if (category.equals("receive")) {
										QueryFilter filter = new QueryFilter(ExDmTransaction.class);
										filter.addFilter("transactionNum=", txid);

										ExDmTransaction transaction = exDmTransactionService.get(filter);
										if (null == transaction) {
											ExDmTransaction exDmTransaction = new ExDmTransaction();
											exDmTransaction.setAccountId(exDigitalmoneyAccount.getId());
											exDmTransaction.setCoinCode(userCode);
											exDmTransaction.setCreated(new Date());
											exDmTransaction.setCurrencyType(currencyType);
											exDmTransaction.setCustomerId(appCustomer.getId());
											exDmTransaction.setCustomerName(appCustomer.getUserName());
											exDmTransaction.setTime(time);
											exDmTransaction.setTimereceived(timereceived);
											exDmTransaction.setInAddress(address);
											exDmTransaction.setConfirmations(confirmations);
											exDmTransaction.setBlocktime(blocktime);
											exDmTransaction.setFee(new BigDecimal(0));
											exDmTransaction.setTransactionMoney(new BigDecimal(amount));
											exDmTransaction.setOptType(1);
											exDmTransaction.setStatus(1);
											exDmTransaction.setOrderNo(txid);
											exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
											// 充币
											exDmTransaction.setTransactionType(1);
											exDmTransaction.setUserId(appCustomer.getId());
											exDmTransaction.setWebsite(webSite);

											exDmTransactionService.save(exDmTransaction);
											int num = Integer.valueOf(confirmations);
											if (num >= 2) {
												QueryFilter fil = new QueryFilter(ExDmTransaction.class);
												filter.addFilter("transactionNum=", txid);

												ExDmTransaction tran = exDmTransactionService.get(fil);
												ExAmineOrderService examineOrderService = (ExAmineOrderService) ContextUtil.getBean("examineOrderService");

												String s = examineOrderService.pasePutOrder(tran.getId());
												if (s.equals("OK")) {

												} else {

												}
											}

											saveTxTotal++;
										}

									}

								}
							}

						}

					}
					sf.append("币种:" + product.getCoinCode() + "  " + "查询的总记录数:" + txTotal + "  " + "保存的记录数:" + saveTxTotal);
					map.put("code", "success");
					map.put("msg", "刷新成功");
				} else {
					sf.append("币种:" + product.getCoinCode() + "查询的记录为空");
				}

			} catch (Exception e) {
				e.printStackTrace();
				map.put("code", "err");
				sf.append("异常:" + e.getMessage());

			}
			logger.error(sf);
		}

		map.put("msg", sf.toString());

		return map;
	}

	// 更新记录。把数据库中未审核的充币 提币订单通过订单号调用钱包接口查询，如果确认节点数不小于2就更新订单状态并更新余额。
	@Override
	public Map<String, String> updateStatus() {
		ExDmTransactionService exDmTransactionService = (ExDmTransactionService) ContextUtil.getBean("exDmTransactionService");
		RemoteAppOurAccountService remoteAppOurAccountService = (RemoteAppOurAccountService) ContextUtil.getBean("remoteAppOurAccountService");

		// 查询数据库中充值订单状态为未审核的数据。
		QueryFilter filter = new QueryFilter(ExDmTransaction.class);
		// 再次修改之后 把提币状态为4的给去掉了 但由于项目已经用了一段 所以还保留对4的查询 之后可以去掉
		filter.addFilter("status_in", "1,4");
		List<ExDmTransaction> list = exDmTransactionService.find(filter);
		int total = list.size();
		int addTotal = 0;
		int number = 0;
		if (null != list) {
			for (ExDmTransaction exDmTransaction : list) {
				String orderNum = exDmTransaction.getOrderNo();
				String coinCode = exDmTransaction.getCoinCode();
				// 调用钱包接口查询订单详情。
				String txStr = CoinInterfaceUtil.row(orderNum, coinCode);
				if (null != txStr && !"".equals(txStr)) {
					txStr = txStr.replace(" ", "");
					Map<String, Object> tx2map = StringUtil.str2map(txStr);
					String confirmations = tx2map.get("confirmations").toString();
					Long num = Long.valueOf(confirmations);
					// 确认节点数大于1时 更新订单状态和账户余额
					if (num > 1) {
						// 充币
						if (exDmTransaction.getTransactionType() == 1) {
							number++;
							ExAmineOrderService examineOrderService = (ExAmineOrderService) ContextUtil.getBean("examineOrderService");
							String s = examineOrderService.pasePutOrder(exDmTransaction.getId());
							if (s.equals("OK")) {
								addTotal++;
							}
						}
						// 提币 只查询状态为4时
						else if (exDmTransaction.getTransactionType() == 2) {
							if (exDmTransaction.getStatus() == 4) {
								number++;
								ExAmineOrderService examineOrderService = (ExAmineOrderService) ContextUtil.getBean("examineOrderService");

								String s = examineOrderService.pasePutOrder(exDmTransaction.getId());
								if (s.equals("OK")) {
									addTotal++;

								}

								Object feeobj = tx2map.get("fee");
								String fee = "";
								if (null != feeobj) {
									fee = String.valueOf(feeobj);
									fee = fee.substring(1, fee.length());
								}

								// 查询我方提币账户
								AppOurAccount our = remoteAppOurAccountService.findAppOurAccount(exDmTransaction.getWebsite(), exDmTransaction.getCurrencyType(), 1);
								// 保存流水并更新我方提币账户余额(手续费)
								AppAccountRecord withdrawRecord = new AppAccountRecord();
								withdrawRecord.setAppAccountId(our.getId());
								withdrawRecord.setAppAccountNum(our.getAccountNumber());
								withdrawRecord.setRecordType(1);
								withdrawRecord.setSource(0);
								withdrawRecord.setTransactionMoney(new BigDecimal(fee));
								withdrawRecord.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
								withdrawRecord.setStatus(5);
								withdrawRecord.setRemark("钱包收取的提币手续费");
								withdrawRecord.setCurrencyName(exDmTransaction.getCurrencyType());
								withdrawRecord.setCurrencyType(exDmTransaction.getCurrencyType());
								withdrawRecord.setRemark(exDmTransaction.getOrderNo() + "-purseFee");
								boolean c = remoteAppOurAccountService.updateAccountBalance(our, withdrawRecord);
							}
						}

					}

				}

			}
		}
		return null;
	}

	// 把用户充值进来的币转到平台维护的充币地址和提币地址。
	@Override
	public JsonResult sendToOurRecharge() {
		return null;
		/*
		 *
		 * logger.error("进入转币操作==="); JsonResult jsonResult=new JsonResult();
		 * Map<String,BigDecimal> map = new HashMap<String, BigDecimal>(); //
		 * Map<String,Map<String,BigDecimal>> statesMap = new
		 * HashMap<String,Map<String,BigDecimal>>();
		 *
		 * try { // 查询已实名的用户 RemoteAppCustomerService remoteAppCustomerService =
		 * (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
		 * List<AppCustomer>
		 * customerList=remoteAppCustomerService.getRealNameCustomer();
		 *
		 * ExDigitalmoneyAccountService
		 * exDigitalmoneyAccountService=(ExDigitalmoneyAccountService)ContextUtil.
		 * getBean("exDigitalmoneyAccountService");
		 *
		 * RemoteAppOurAccountService remoteAppOurAccountService =
		 * (RemoteAppOurAccountService)
		 * ContextUtil.getBean("remoteAppOurAccountService");
		 *
		 * QueryFilter f=new QueryFilter(ExProduct.class); f.addFilter("issueState=",
		 * "1");
		 *
		 * StringBuffer sf=new StringBuffer(); for(AppCustomer customer:customerList){
		 * // 查询某个人的所有币 账户 List<ExDigitalmoneyAccount>
		 * digitalmoneyAccountList=exDigitalmoneyAccountService.getlistByCustomerId(
		 * customer.getId());
		 *
		 * // 循环所有的币账户 for(ExDigitalmoneyAccount
		 * digitalmoneyAccount:digitalmoneyAccountList){
		 *
		 * String txStr="";
		 *
		 * String coinCode = digitalmoneyAccount.getCoinCode(); String website =
		 * digitalmoneyAccount.getWebsite(); String mapKey = coinCode+"-"+website;
		 *
		 * // 查询站点中我方币种账户的余额 BigDecimal bigDecimal = map.get(mapKey);
		 *
		 * List<AppOurAccount> list =
		 * remoteAppOurAccountService.getOurAccounts(digitalmoneyAccount.getWebsite(),
		 * digitalmoneyAccount.getCoinCode()); // 我方提币账户 AppOurAccount
		 * withdrawAccount=null; // 我方充币账户 AppOurAccount rechagreAccount=null; //
		 * 需要转入我方提币账户的币的数量 BigDecimal transferWithdrawNum=new BigDecimal("0.00"); //
		 * 需要转入我方充币账户的币的数量 BigDecimal transferRechargeNum=new BigDecimal("0.00");
		 *
		 * // 应该转到提币地址的币的数量 for(AppOurAccount account:list){ // 我方充币地址账户
		 * if(account.getOpenAccountType().equals("0")){ rechagreAccount=account; } //
		 * 我方提币地址账户 if(account.getOpenAccountType().equals("1")){
		 * withdrawAccount=account; // 查询提币地址的数量 if(null == bigDecimal){ String
		 * withdrawAccountBalance=CoinInterfaceUtil.balance(withdrawAccount.
		 * getAccountName(), digitalmoneyAccount.getCoinCode()); BigDecimal
		 * withdrawBalance=new BigDecimal(withdrawAccountBalance);
		 * transferWithdrawNum=withdrawAccount.getRetainsMoney().subtract(
		 * withdrawBalance); // 如果没有保存 起来 map.put(mapKey, transferWithdrawNum); }else{
		 * transferWithdrawNum=bigDecimal; } } }
		 *
		 *
		 * //查询用户余额 //国际站 if (digitalmoneyAccount.getWebsite().equals(ContextUtil.EN)) {
		 * // 查询某个人的某个币种有多少余额 txStr=
		 * CoinInterfaceUtil.balance(customer.getUserName()+"-USD",
		 * digitalmoneyAccount.getCoinCode()); }else{ // 查询某个人的某个币种有多少余额 txStr=
		 * CoinInterfaceUtil.balance(customer.getUserName(),
		 * digitalmoneyAccount.getCoinCode()); }
		 *
		 * logger.error("转币str="+txStr);
		 *
		 * if(!"".equals(txStr)&&null!=txStr){ BigDecimal userBalance=new
		 * BigDecimal(txStr); // 判断用户的余额不为 0 if(userBalance.compareTo(new
		 * BigDecimal("0.00"))>0){
		 *
		 * //查询钱包余额 String purse=CoinInterfaceUtil.balance("",
		 * digitalmoneyAccount.getCoinCode()); BigDecimal purseBalance=new
		 * BigDecimal(purse); // 钱包服务器上的币的数量大于用户账户的币的数量
		 * if(purseBalance.compareTo(userBalance)>0){
		 *
		 * //用户账户预留0.1个币 作为旷工费 userBalance=userBalance.subtract(new BigDecimal("0.1"));
		 *
		 * // 根据币的代码查找我方账户 // List<AppOurAccount>
		 * list=remoteAppOurAccountService.getOurAccounts(digitalmoneyAccount.getWebsite
		 * (),digitalmoneyAccount.getCoinCode());
		 *
		 * if(null!=withdrawAccount){
		 *
		 * // 用户转入平台提币地址的币的数量 BigDecimal hh=new BigDecimal("0.00"); // 查询提币地址的数量 String
		 * withdrawAccountBalance=CoinInterfaceUtil.balance(withdrawAccount.
		 * getAccountName(), digitalmoneyAccount.getCoinCode()); BigDecimal
		 * withdrawBalance=new BigDecimal(withdrawAccountBalance);
		 *
		 * BigDecimal retainsMoney = withdrawAccount.getRetainsMoney(); BigDecimal
		 * subtract = withdrawBalance.subtract(retainsMoney);
		 *
		 * // 提币账户的币的数量小于提币账户保留金额时 转币到提币账户 if (BigDecimal.ZERO.compareTo(subtract)>0) {
		 *
		 * // 应该转到提币地址的币的数量 //
		 * transferWithdrawNum=withdrawAccount.getRetainsMoney().subtract(
		 * withdrawBalance);
		 *
		 * if (userBalance.compareTo(transferWithdrawNum)>=0) {
		 *
		 * //调用钱包接口转币到我方提币地址 String
		 * withdrawResult=CoinInterfaceUtil.sendTo(digitalmoneyAccount.getPublicKey(),
		 * withdrawAccount.getAccountNumber(), transferWithdrawNum.toString(),
		 * withdrawAccount.getCurrencyType(),null);
		 * if(null!=withdrawResult&&!"".equals(withdrawResult)){ Map<String, Object>
		 * tx2map=StringUtil.str2map(withdrawResult); String
		 * order=tx2map.get("msg").toString(); String
		 * code=tx2map.get("code").toString(); if(code.equals("8")){ BigDecimal decimal
		 * = map.get(mapKey); BigDecimal subtract2 =
		 * decimal.subtract(transferWithdrawNum); map.put(mapKey, subtract2);
		 * sf.append(" 转入成功 "); //保存流水并更新我方提币账户余额 AppAccountRecord withdrawRecord=new
		 * AppAccountRecord(); withdrawRecord.setAppAccountId(withdrawAccount.getId());
		 * withdrawRecord.setAppAccountNum(withdrawAccount.getAccountNumber());
		 * withdrawRecord.setRecordType(1); withdrawRecord.setSource(0);
		 * withdrawRecord.setTransactionMoney(transferWithdrawNum);
		 * withdrawRecord.setTransactionNum(IdGenerate.transactionNum(NumConstant.
		 * Ex_Dm_Transaction)); withdrawRecord.setStatus(5);
		 * withdrawRecord.setRemark("从用户账户转币到我方提币账户");
		 * withdrawRecord.setCurrencyName(withdrawAccount.getCurrencyType());
		 * withdrawRecord.setCurrencyType(withdrawAccount.getCurrencyType());
		 * withdrawRecord.setRemark(order);
		 * withdrawRecord.setCustomerId(customer.getId());
		 * withdrawRecord.setCustomerName(customer.getUserName());
		 * withdrawRecord.setWebsite(digitalmoneyAccount.getWebsite()); boolean
		 * c=remoteAppOurAccountService.updateWithdrawAccountBalance(withdrawAccount,
		 * withdrawRecord); if(c){
		 *
		 * } }
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * if(null!=rechagreAccount){ //转入充币账户的数量
		 * transferRechargeNum=userBalance.subtract(transferWithdrawNum);
		 * //调用钱包接口转币到我方充币地址 String
		 * rechargeResult=CoinInterfaceUtil.sendTo(digitalmoneyAccount.getPublicKey(),
		 * rechagreAccount.getAccountNumber(), transferRechargeNum.toString(),
		 * rechagreAccount.getCurrencyType(),null);
		 *
		 * if(null!=rechargeResult&&!"".equals(rechargeResult)){
		 *
		 * Map<String, Object> m=StringUtil.str2map(rechargeResult); String
		 * or=m.get("msg").toString(); String co=m.get("code").toString();
		 * if(co.equals("8")){
		 *
		 * //保存流水并更新我方充币账户余额 AppAccountRecord rechargeRecord=new AppAccountRecord();
		 * rechargeRecord.setAppAccountId(rechagreAccount.getId());
		 * rechargeRecord.setAppAccountNum(rechagreAccount.getAccountNumber());
		 * rechargeRecord.setRecordType(1); rechargeRecord.setSource(0);
		 * rechargeRecord.setTransactionMoney(transferRechargeNum);
		 * rechargeRecord.setTransactionNum(IdGenerate.transactionNum(NumConstant.
		 * Ex_Dm_Transaction)); rechargeRecord.setStatus(5);
		 * rechargeRecord.setRemark("从用户账户转币到我方充值账户");
		 * rechargeRecord.setCurrencyName(rechagreAccount.getCurrencyType());
		 * rechargeRecord.setCurrencyType(rechagreAccount.getCurrencyType());
		 * rechargeRecord.setRemark(or); rechargeRecord.setCustomerId(customer.getId());
		 * rechargeRecord.setCustomerName(customer.getUserName());
		 * rechargeRecord.setWebsite(digitalmoneyAccount.getWebsite()); boolean
		 * b=remoteAppOurAccountService.updateAccountBalance(rechagreAccount,
		 * rechargeRecord);
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * }
		 *
		 * } catch (Exception e) {
		 *
		 * return null ; }
		 *
		 *
		 * jsonResult.setSuccess(true); return jsonResult;
		 */}

	/**
	 * 手动刷新币账户的方法
	 *
	 */
	@Override
	public Map<String, String> recordAll() {

		return null;

		// try {
		//
		// Map<String, String> map = new HashMap<String, String>();
		// //查询已实名的用户
		// RemoteAppCustomerService remoteAppCustomerService =
		// (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
		// List<AppCustomer>
		// customerList=remoteAppCustomerService.getRealNameCustomer();
		//
		// //查询所有产品
		// QueryFilter f=new QueryFilter(ExProduct.class);
		// f.addFilter("issueState=", "1");
		// ExProductService exProductService = (ExProductService)
		// ContextUtil.getBean("exProductService");
		// List<ExProduct> l=exProductService.find(f);
		//
		// ExDigitalmoneyAccountService
		// exDigitalmoneyAccountService=(ExDigitalmoneyAccountService)ContextUtil.getBean("exDigitalmoneyAccountService");
		//
		// ExDmTransactionService
		// exDmTransactionService=(ExDmTransactionService)ContextUtil.getBean("exDmTransactionService");
		//
		// StringBuffer sf=new StringBuffer();
		//
		// logger.error("=== "+customerList.size());
		//
		// for(AppCustomer customer:customerList){
		//
		// logger.error("1======= "+customer.getUserName());
		//
		// // if("13967443365".equals(customer.getUserName())){
		//
		// List<ExDigitalmoneyAccount>
		// digitalmoneyAccountList=exDigitalmoneyAccountService.getlistByCustomerId(customer.getId());
		// logger.error("=2.1======= "+customer.getUserName());
		// if(null != digitalmoneyAccountList&& digitalmoneyAccountList.size()>0){
		// logger.error("=2.2======= "+customer.getUserName());
		// for(ExDigitalmoneyAccount digitalmoneyAccount:digitalmoneyAccountList){
		// logger.error("2======= "+customer.getUserName());
		// String txStr="";
		// //国际站
		// if (digitalmoneyAccount.getWebsite().equals(ContextUtil.EN)) {
		// txStr=
		// CoinInterfaceUtil.listByCustomerName(digitalmoneyAccount.getCoinCode(),customer.getUserName()+"-USD");
		//
		// }else{
		// logger.error("线程等待1500毫秒");
		// Thread currentThread = Thread.currentThread();
		// // currentThread.sleep(1500);
		//
		// txStr=
		// CoinInterfaceUtil.listByCustomerName(digitalmoneyAccount.getCoinCode(),customer.getUserName());
		// }
		//
		// logger.error(txStr);
		// logger.error("3======= "+customer.getUserName());
		// if(!"".equals(txStr)&&null!=txStr){
		//
		// logger.error("1==="+txStr+"===");
		// @SuppressWarnings("unchecked")
		// List<String> txList=(List<String>) JSON.parse(txStr);
		// logger.error("2==="+txStr+"===");
		// for(String txs:txList){
		// logger.error("4======= "+customer.getUserName());
		// //转换为map
		// Map<String, Object> tx2map=StringUtil.str2map(txs);
		// String json= com.alibaba.fastjson.JSON.toJSONString(tx2map);
		// json=json.replaceAll(" ", "");
		// hry.exchange.coin.model.Transaction tx=
		// com.alibaba.fastjson.JSON.parseObject(json,hry.exchange.coin.model.Transaction.class);
		// String name=customer.getUserName();
		// String address=tx.getAddress();
		// QueryFilter queryFilter=new QueryFilter(ExDigitalmoneyAccount.class);
		// queryFilter.addFilter("publicKey=", address);
		// queryFilter.addFilter("userName=", name);
		// ExDigitalmoneyAccount
		// exDigitalmoneyAccount=exDigitalmoneyAccountService.get(queryFilter);
		// if(null!=exDigitalmoneyAccount){
		// //站点 中国站/国际站
		// String webSite=exDigitalmoneyAccount.getWebsite();
		// //用户持币code
		// String userCode=exDigitalmoneyAccount.getCoinCode();
		// String currencyType=exDigitalmoneyAccount.getCurrencyType();
		//
		//
		// String category=tx.getCategory();//交易类型
		// if(category.equals("receive")){
		// //confirmations
		// String confirmations=String.valueOf(tx.getConfirmations());//确认节点数
		// String amount=String.valueOf(tx.getAmount());
		// String blocktime="";
		// if(!confirmations.equals("0")){
		// blocktime=tx.getBlockTime();//区块时间
		// blocktime= DateUtil.stampToDate(blocktime+"000");
		// }
		//
		// String txid=tx.getTxId();//交易单号
		// String time=tx.getTime().toString();
		// if(null!=time&&!"".equals(time)){
		// time= DateUtil.stampToDate(time+"000");
		// }
		// String timereceived=tx.getTimeReceived();
		// if (null!=timereceived&&!"".equals(timereceived)) {
		// timereceived= DateUtil.stampToDate(timereceived+"000");
		// }
		// Object feeobj=tx.getFee();
		// String fee="";
		// if (null!=feeobj) {
		// fee=String.valueOf(feeobj);
		// }
		// //记录 收入的金额
		// logger.error("5======= "+customer.getUserName());
		// QueryFilter filter = new QueryFilter(ExDmTransaction.class);
		// filter.addFilter("orderNo=", txid);
		//
		// ExDmTransaction transaction=exDmTransactionService.get(filter);
		// if(null == transaction ||
		// customer.getUserName()!=transaction.getCustomerName()){
		// ExDmTransaction exDmTransaction=new ExDmTransaction();
		// exDmTransaction.setAccountId(exDigitalmoneyAccount.getId());
		// exDmTransaction.setCoinCode(userCode);
		// exDmTransaction.setCreated(new Date());
		// exDmTransaction.setCurrencyType(currencyType);
		// exDmTransaction.setCustomerId(customer.getId());
		// exDmTransaction.setCustomerName(customer.getUserName());
		// exDmTransaction.setTime(time);
		// exDmTransaction.setTimereceived(timereceived);
		// exDmTransaction.setInAddress(address);
		// exDmTransaction.setConfirmations(confirmations);
		// exDmTransaction.setBlocktime(blocktime);
		// exDmTransaction.setFee(new BigDecimal(0));
		// exDmTransaction.setTransactionMoney(new BigDecimal(amount));
		// exDmTransaction.setTrueName(exDigitalmoneyAccount.getTrueName());
		// exDmTransaction.setStatus(1);
		// exDmTransaction.setOrderNo(txid);
		// exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
		// //充币
		// exDmTransaction.setTransactionType(1);
		// exDmTransaction.setUserId(customer.getId());
		// exDmTransaction.setWebsite(webSite);
		//
		// exDmTransactionService.save(exDmTransaction);
		//
		// long num=Long.valueOf(confirmations);
		// if(num>=2){
		//
		// QueryFilter fil = new QueryFilter(ExDmTransaction.class);
		// fil.addFilter("orderNo=", txid);
		//
		// ExDmTransaction tran=exDmTransactionService.get(fil);
		// ExAmineOrderService examineOrderService = (ExAmineOrderService)
		// ContextUtil.getBean("examineOrderService");
		//
		// String s = examineOrderService.pasePutOrder(tran.getId());
		// }
		// }
		// }
		// }
		// }
		// }
		// }
		// }else{
		// logger.error("用户的虚拟账户为空 ");
		// }
		//
		//
		//// }else{
		//// logger.error("不是此用户===== ");
		//// }
		//
		//
		// }
		//
		// return map;
		//
		// } catch (Exception e) {
		// return null ;
		// }
	}

	public static void main(String[] args) {
		String time = "1474183126";
		String res;
		res = DateUtil.stampToDate(time + "000");

		logger.error(res);

	}

	// 这个方式暂时不用
	@Override
	public Map<String, String> recordAllWithdraw() {
		return null;
	}

	/**
	 * 撤销成功记录
	 *
	 */
	@Override
	public synchronized JsonResult cancelTransaction(Long id) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(false);

		ExDmTransaction dmTransaction = super.get(id);
		if (dmTransaction.getStatus() == 2) {

			String code = dmTransaction.getCoinCode();
			BigDecimal money = dmTransaction.getTransactionMoney();
			BigDecimal accountMoney = BigDecimal.ZERO;
			AppOurAccount appOurAccount;

			Long customerId = dmTransaction.getCustomerId();
			ExDigitalmoneyAccount list = exDigitalmoneyAccountService.getByCustomerIdAndType(customerId, code, dmTransaction.getCurrencyType(), dmTransaction.getWebsite());

			RemoteAppOurAccountService remoteAppOurAccountService = (RemoteAppOurAccountService) ContextUtil.getBean("remoteAppOurAccountService");
			AppOurAccount appOurAccount2 = remoteAppOurAccountService.findAppOurAccount(dmTransaction.getWebsite(), code, 1);

			if (null != appOurAccount2) {
				appOurAccount = appOurAccount2;
				accountMoney = appOurAccount.getAccountMoney();
				if (accountMoney.compareTo(money) < 0) {
					jsonResult.setMsg("我方账户余额不足");
					return jsonResult;
				}

			} else {
				jsonResult.setMsg("我方账户不能为空");
				return jsonResult;
			}

			if (null != list) {
				ExDigitalmoneyAccount digitalmoneyAccount = list;

				BigDecimal hotMoney = digitalmoneyAccount.getHotMoney();

				if (hotMoney.compareTo(money) >= 0) {

					// 我方账户 减少一笔钱
					remoteAppOurAccountService.changeBitForOurAccount(dmTransaction.getTransactionMoney(), appOurAccount);
					// 给自己的钱包减一笔钱
					exDigitalmoneyAccountService.payFromHotAccount(digitalmoneyAccount.getId(), dmTransaction.getTransactionMoney(), dmTransaction.getTransactionNum(), "成功驳回订单", null, null);
					// 修改订单状态
					dmTransaction.setStatus(3);

					super.update(dmTransaction);
					jsonResult.setSuccess(true);
					jsonResult.setMsg("驳回成功  请手动对这个用户转币操作");
					return jsonResult;

					// String
					// rechargeResult=CoinInterfaceUtil.sendTo(appOurAccount.getAccountNumber(),dmTransaction.getInAddress(),
					// dmTransaction.getTransactionMoney().toString(), dmTransaction.getCoinCode());
					// Map<String, Object> map = StringUtil.str2map(rechargeResult);
					// String msg=map.get("msg").toString();
					// String codes=map.get("code").toString();
					// if(codes.equals("8")){
					// // 从我方账户减一笔钱到用户账户
					// jsonResult.setMsg("撤销成功");
					// jsonResult.setSuccess(true);
					// return jsonResult;
					// }

				} else {
					jsonResult.setMsg("用户账户可用币不足！");
				}
				return jsonResult;
			} else {
				jsonResult.setMsg("用户币账户为空");
			}
			return jsonResult;
		}
		jsonResult.setMsg("此订单已驳回了 ");
		return jsonResult;

	}

	/**
	 *
	 * @param params
	 * @return
	 */
	public FrontPage findExdmtransaction(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		List<ExDmTransactionManage> list = ((ExDmTransactionDao) dao).findExdmtransaction(params);

		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
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
								record.setOptType(1);
								record.setReleaseMethod(exDmLock.getReleaseMethod());
								record.setReleaseMethodVal(exDmLock.getReleaseMethodVal());
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
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

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
			// 根据释放次数计算释放时间
			for (int i = 1; i <= num; i++) {
				ExDmLockReleaseTime time = new ExDmLockReleaseTime();
				time.setRecordId(recordId);
				time.setReleaseVal(eachReleaseVal);
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
		messageProducer.toAccount(JSON.toJSONString(list2));


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
		RemoteAppOurAccountService remoteAppOurAccountService = (RemoteAppOurAccountService) ContextUtil.getBean("remoteAppOurAccountService");
		//我方提币账户
		AppOurAccount ourAccount=remoteAppOurAccountService.findAppOurAccount(ContextUtil.getWebsite(),exDmTransaction.getCoinCode(), Integer.valueOf("1"));
		remoteAppOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币记录", "");
		remoteAppOurAccountService.changeCountToOurAccoun(ourAccount,exDmTransaction, exDmTransaction.getOutAddress(), "提币手续费记录", "fee");


		exDmTransaction.setStatus(2);
		super.update(exDmTransaction);
		//----发送mq消息----end
		messageProducer.toAccount(JSON.toJSONString(list));
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
	public FrontPage findFrontPage(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		//查询方法
		List<ExDmTransactionManage> list = ((ExDmTransactionDao)dao).findFrontPage(params);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public RemoteResult checkPass(Map<String, String> params) {
		String ids = params.get("ids");
		String auditpassword = params.get("auditpassword");

		RemoteResult jsonResult = new RemoteResult();
		RedisService redisService = (RedisService)ContextUtil.getBean("redisService");
		String password = redisService.get("auditpassword");
		if(StringUtils.isEmpty(password)) {
			jsonResult.setMsg("请先设置审核密码!");
			return jsonResult;
		}
		if(org.springframework.util.StringUtils.isEmpty(auditpassword)) {
			jsonResult.setMsg("审核密码错误!");
			return jsonResult;
		}
		PasswordHelper passwordHelper = new PasswordHelper();
		String encryString = passwordHelper.encryString(auditpassword, "setAuditpassword");
		if(!encryString.equals(password)) {
			jsonResult.setMsg("审核密码错误!");
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

						RemoteAppCustomerService appCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
						AppCustomer customer = appCustomerService.getById(customerId);

						jsonResult = sendTo(transaction);
						if (jsonResult.getSuccess()) {
							// 发送提币短信通知
							SmsParam smsParam = new SmsParam();
							smsParam.setHryMobilephone(customer.getUserName());
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
	private RemoteResult sendTo(ExDmTransaction t) {
		String fromAddress = t.getOurAccountNumber();
		String toAddress = t.getInAddress();
		String amount = t.getTransactionMoney().subtract(t.getFee()).setScale(8, BigDecimal.ROUND_HALF_DOWN).toString();
		String coinCode = t.getCoinCode();
		String transactionNum = t.getTransactionNum();
		Long id = t.getId();
		String userName = t.getCustomerName();
		RemoteResult jsonResult = new RemoteResult();
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
					JsonResult result = JSON.parseObject(txStr, JsonResult.class);
					//成功调用
					if (result.getSuccess()) {
						ExDmTransaction transaction = this.get(id);
						transaction.setOrderNo(result.getMsg() + "_send");
						this.update(transaction);
						examineOrderService.pasePutOrder(id);
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
	public RemoteResult checkReject(Map<String, String> params) {
		RemoteResult jsonResult = new RemoteResult();
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
							RemoteAppCustomerService appCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");
							AppCustomer customer = appCustomerService.getById(customerId);

							AppTransactionService appTransactionService = (AppTransactionService) ContextUtil.getBean("appTransactionService");
							boolean flag = appTransactionService.dmWithdrawReject(exDmTransaction);
							if (flag) {
								// 发送提现驳回短信通知(提币驳回)
								SmsParam smsParam = new SmsParam();
								smsParam.setHryMobilephone(customer.getUserName());
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

	@Override
	public FrontPage findExdmtransactionRecord (Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		List<ExDmTransactionManage> list = ((ExDmTransactionDao) dao).findExdmtransactionRecord(params);

		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}

}
