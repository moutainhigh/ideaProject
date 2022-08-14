/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月30日 下午3:23:41
 */
package hry.exchange.remote.account;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppTransaction;
import hry.account.remote.RemoteAppAccountService;
import hry.account.remote.RemoteAppTransactionService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.change.remote.account.RemoteExDigitalmoneyAccountService;
import hry.core.constant.CodeConstant;
import hry.exchange.account.model.AppAccountDisable;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.ExDmColdAccountRecord;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.exchange.account.model.vo.DigitalmoneyAccountAndProduct;
import hry.exchange.account.service.AppAccountDisableService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.account.service.ExDmColdAccountRecordService;
import hry.exchange.account.service.ExDmHotAccountRecordService;
import hry.exchange.entrust.dao.ExExOrderInfoDao;
import hry.exchange.transaction.model.ExDmCustomerPublicKey;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmCustomerPublicKeyService;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.ico.model.IcoTransactionRecord;
import hry.ico.service.IcoTransactionRecordService;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.klg.enums.TriggerPointEnum;
import hry.licqb.record.model.DealRecord;
import hry.licqb.record.service.DealRecordService;
import hry.licqb.util.DealEnum;
import hry.mq.producer.service.MessageProducer;
import hry.pakgclass.OrderParameter;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.redis.model.Accountadd;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.message.MessageConstant;
import hry.util.message.MessageUtil;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月30日 下午3:23:41
 */
public class RemoteExDigitalmoneyAccountServiceImpl implements
		RemoteExDigitalmoneyAccountService {
	private static Logger logger = Logger.getLogger(RemoteExDigitalmoneyAccountServiceImpl.class);
	@Resource(name = "exDigitalmoneyAccountService")
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource(name = "exDmTransactionService")
	public ExDmTransactionService exDmTransactionService;

	@Resource(name = "exDmColdAccountRecordService")
	public ExDmColdAccountRecordService exDmColdAccountRecordService;

	@Resource(name = "exDmHotAccountRecordService")
	public ExDmHotAccountRecordService exDmHotAccountRecordService;

	@Resource(name = "exDmCustomerPublicKeyService")
	public ExDmCustomerPublicKeyService exDmCustomerPublicKeyService;
	@Resource
	public ExExOrderInfoDao exExOrderInfoDao;
	@Resource
	public AppAccountDisableService appAccountDisableService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private IcoTransactionRecordService icoTransactionRecordService;
	@Resource
	private KlgAssetsRecordService klgAssetsRecordService;
	@Resource
	private DealRecordService dealRecordService;

	/**
	 * 通过用户的 id 查出用户的所有用户的虚拟账户
	 *
	 */
	public List<ExDigitalmoneyAccount> findExDigitalmoneyAccountById(Long id) {
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", id);
		List<ExDigitalmoneyAccount> list = exDigitalmoneyAccountService
				.find(filter);
		return list;
	}


	@Override
	public void save(ExDigitalmoneyAccount exDigitalmoneyAccount) {
		exDigitalmoneyAccountService.save(exDigitalmoneyAccount);
	}

	/**
	 * 生成充值订单
	 */
	@Override
	public String putOrder(OrderParameter order) {
		try {

			ExDmTransaction exDmTransaction = new ExDmTransaction();
			exDmTransaction.setCurrencyType(ContextUtil.getRemoteCurrencyType());
			exDmTransaction.setWebsite(ContextUtil.getRemoteWebsite());
			exDmTransaction.setCustomerId(order.getCustomerId());
			exDmTransaction.setTransactionNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));
			exDmTransaction.setAccountId(order.getCurrencyId());
			exDmTransaction.setTransactionType(1);
			exDmTransaction.setTransactionMoney(order.getCurrencyNum());
			exDmTransaction.setStatus(1);
			exDmTransaction.setCustomerName(order.getCustomerName());
			exDmTransaction.setCustomerName(order.getTrueName());
			exDmTransaction.setCoinCode(order.getType());
			exDmTransaction.setOptType(1);
			exDmTransaction.setSaasId(RpcContext.getContext().getAttachment(
					"saasId"));
			exDmTransactionService.save(exDmTransaction);
			return "OK";

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---------调用接口保存充值订单报异常--------- ");
		}
		return "NO";

	}

	/**
	 * 生成提现订单
	 *
	 */
	@Override
	public String setOrder(OrderParameter order) {

		ExDigitalmoneyAccount digitalmoneyAccount = this.findByCustomerType(order.getCustomerId(), order.getCurrencyType(),order.getType(),order.getWebsite());
		if (null == digitalmoneyAccount) {
			return "NO";
		}

		BigDecimal hotMoney = digitalmoneyAccount.getHotMoney();
		BigDecimal coldMoney = digitalmoneyAccount.getColdMoney();
		BigDecimal num = order.getCurrencyNum().add(order.getFee());
		int i = hotMoney.compareTo(num);
		if (i == -1) {
			return "NO";
		}
		// 冻结虚拟账户的钱
		BigDecimal cNowMoney = hotMoney.subtract(num);
		BigDecimal hNowMoney = coldMoney.add(num);

		digitalmoneyAccount.setHotMoney(cNowMoney);
		digitalmoneyAccount.setColdMoney(hNowMoney);
		exDigitalmoneyAccountService.update(digitalmoneyAccount);

		// 生成订单

		ExDmTransaction exDmTransaction = new ExDmTransaction();
		exDmTransaction.setCustomerId(order.getCustomerId());
		String transactionNum = NumConstant.Ex_Dm_Transaction;
		transactionNum = IdGenerate.transactionNum(transactionNum);
		order.setTransactionNum(transactionNum);
		exDmTransaction.setTransactionNum(transactionNum);
		exDmTransaction.setAccountId(order.getCurrencyId());
		exDmTransaction.setTransactionType(2);
		exDmTransaction.setTransactionMoney(order.getCurrencyNum().add(order.getFee()));
		exDmTransaction.setCustomerName(order.getCustomerName());
		// 2表示提现
		exDmTransaction.setStatus(1);
		exDmTransaction.setCoinCode(order.getCurrencyType());
		exDmTransaction.setCurrencyType(order.getType());
		exDmTransaction.setWebsite(order.getWebsite());
		exDmTransaction.setSaasId(RpcContext.getContext().getAttachment(
				"saasId"));

		exDmTransaction.setFee(order.getFee());
		exDmTransaction.setOurAccountNumber(order.getOurAccountNumber());
		exDmTransaction.setInAddress(order.getInAddress());
		exDmTransaction.setOutAddress(order.getOurAccountNumber());
		exDmTransaction.setOptType(2);
		//查询地址备注
		QueryFilter queryFilter = new QueryFilter(ExDmCustomerPublicKey.class);
		queryFilter.addFilter("customerId=",exDmTransaction.getCustomerId());
		queryFilter.addFilter("currencyType=",exDmTransaction.getCoinCode());
		queryFilter.addFilter("publicKey=",exDmTransaction.getInAddress());
		ExDmCustomerPublicKey exDmCustomerPublicKey = exDmCustomerPublicKeyService.get(queryFilter);
		if(exDmCustomerPublicKey != null){
			exDmTransaction.setRemark2(exDmCustomerPublicKey.getRemark());//地址备注
		}

		//内部转账备注
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("publicKey=", order.getInAddress());
		filter.addFilter("coinCode=", order.getCurrencyType());
		ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(filter);
		if(exDigitalmoneyAccount != null){
			exDmTransaction.setOptType(3);
			exDmTransaction.setRemark("系统内部转币"+order.getCurrencyNum().add(order.getFee())+"个");
		}
		// 保存订单
		exDmTransactionService.save(exDmTransaction);
		/**
		 * 流水记录
		 */
		/*~~~~~~~~~李超业务---添加提币记录~~~start~~~~~~~*/
		DealRecord record = new DealRecord();
		record.setCustomerId(order.getCustomerId());
		record.setAccountId(digitalmoneyAccount.getId());
		record.setTransactionNum(transactionNum);
		record.setCoinCode(digitalmoneyAccount.getCoinCode());
		record.setDealType(Integer.parseInt(DealEnum.TYPE14.getIndex()));
		record.setDealMoney(order.getCurrencyNum());
		record.setServiceCharge(order.getFee());
		record.setDealStatus(1);
		record.setRemark(DealEnum.SITE14.getName());
		dealRecordService.save(record);
		/*~~~~~~~~~李超业务---添加提币记录~~~end~~~~~~~*/

		//----发送mq消息----start
		//热账户减少
		logger.error(digitalmoneyAccount.getId()+"：提币消息生产者开始");
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(digitalmoneyAccount.getId());
		accountadd.setMoney(order.getCurrencyNum().multiply(new BigDecimal(-1)));
		accountadd.setMonteyType(1);
		accountadd.setAcccountType(1);
		accountadd.setRemarks(35);
		accountadd.setTransactionNum(exDmTransaction.getTransactionNum());

		//冷账户增加
		Accountadd accountadd2 = new Accountadd();
		accountadd2.setAccountId(digitalmoneyAccount.getId());
		accountadd2.setMoney(order.getCurrencyNum());
		accountadd2.setMonteyType(2);
		accountadd2.setAcccountType(1);
		accountadd2.setRemarks(35);
		accountadd2.setTransactionNum(exDmTransaction.getTransactionNum());

		//手续费 -- 热
		Accountadd accountaddf1 = new Accountadd();
		accountaddf1.setAccountId(digitalmoneyAccount.getId());
		accountaddf1.setMoney(order.getFee().multiply(new BigDecimal(-1)));
		accountaddf1.setMonteyType(1);
		accountaddf1.setAcccountType(1);
		accountaddf1.setRemarks(36);
		accountaddf1.setTransactionNum(exDmTransaction.getTransactionNum());

		//手续费 -- 冷
		Accountadd accountaddf2 = new Accountadd();
		accountaddf2.setAccountId(digitalmoneyAccount.getId());
		accountaddf2.setMoney(order.getFee());
		accountaddf2.setMonteyType(2);
		accountaddf2.setAcccountType(1);
		accountaddf2.setRemarks(36);
		accountaddf2.setTransactionNum(exDmTransaction.getTransactionNum());

		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd);
		list.add(accountadd2);
		list.add(accountaddf1);
		list.add(accountaddf2);
		messageProducer.toAccount(JSON.toJSONString(list));
		logger.error(digitalmoneyAccount.getId()+"：提币消息生产者结束");
		//----发送mq消息----end

		return "OK";
	}

	/**
	 * 根据公钥来查询当前用户
	 */
	@Override
	public ExDigitalmoneyAccount findByString(String s) {

		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.setSaasId(saasId);
		filter.addFilter("publicKey=", s);
		List<ExDigitalmoneyAccount> find = exDigitalmoneyAccountService
				.find(filter);
		if (find.size() > 0) {
			ExDigitalmoneyAccount account = find.get(0);
			return account;
		}
		return null;
	}

	@Override
	// 保存冻结流水
	public void setColdRecord(OrderParameter ordep, Integer i, String orderId) {
		ExDmColdAccountRecord exDmColdAccountRecord = new ExDmColdAccountRecord();
		exDmColdAccountRecord.setCurrencyType(ContextUtil.getRemoteCurrencyType());
		exDmColdAccountRecord.setWebsite(ContextUtil.getRemoteWebsite());
		exDmColdAccountRecord.setAccountId(ordep.getCurrencyId());
		exDmColdAccountRecord.setCustomerId(ordep.getCustomerId());
		exDmColdAccountRecord.setUserName(ordep.getCustomerName());
		exDmColdAccountRecord.setRecordType(i);
		exDmColdAccountRecord.setTransactionMoney(ordep.getCurrencyNum());
		exDmColdAccountRecord.setStatus(0);
		exDmColdAccountRecord.setRemark("可用账户流水记录成功  待处理");
		exDmColdAccountRecord.setCoinCode(ordep.getType());
		exDmColdAccountRecord.setTransactionNum(orderId);
		exDmColdAccountRecord.setSaasId(RpcContext.getContext().getAttachment(
				"saasId"));

		exDmColdAccountRecordService.save(exDmColdAccountRecord);

	}

	@Override
	// 保存可用币流水
	public void setHotRecord(OrderParameter ordep, Integer i, String orderId) {

		ExDmHotAccountRecord exDmHotAccountRecord = new ExDmHotAccountRecord();
		exDmHotAccountRecord.setCurrencyType(ContextUtil.getRemoteCurrencyType());
		exDmHotAccountRecord.setWebsite(ContextUtil.getRemoteWebsite());
		exDmHotAccountRecord.setAccountId(ordep.getCurrencyId());
		exDmHotAccountRecord.setCustomerId(ordep.getCustomerId());
		exDmHotAccountRecord.setUserName(ordep.getCustomerName());
		exDmHotAccountRecord.setRecordType(i);
		exDmHotAccountRecord.setTransactionMoney(ordep.getCurrencyNum());
		exDmHotAccountRecord.setStatus(0);
		exDmHotAccountRecord.setRemark("冻结账户冻结已成功  待处理");
		exDmHotAccountRecord.setCoinCode(ordep.getType());
		exDmHotAccountRecord.setTransactionNum(orderId);
		exDmHotAccountRecord.setSaasId(RpcContext.getContext().getAttachment(
				"saasId"));
		exDmHotAccountRecordService.save(exDmHotAccountRecord);

	}

	@Override
	public ExDigitalmoneyAccount findByCustomerType(Long id, String coinCode,String currencyType,String website) {

		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("customerId=", id);
		filter.addFilter("coinCode=", coinCode);
		filter.addFilter("status=",1);
		/*filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=",website);*/
		List<ExDigitalmoneyAccount> find = exDigitalmoneyAccountService.find(filter);
		if (find.size() > 0) {

			ExDigitalmoneyAccount account = find.get(0);
			return account;
		}
		return null;
	}

	/**
	 * 根据币的类型和交易类型返回一个订单的集合 (充值提现的交易记录)
	 */
	@Override
	public PageResult findBtcRecordByString(RemoteQueryFilter Rfilter, Long id) {

		QueryFilter queryFilter = Rfilter.getQueryFilter();
		HashMap<String, String> map = Rfilter.getRequestMap();
		String type = map.get("coinCode");
		String j = map.get("transactionType");
		Integer i = Integer.valueOf(j);
		queryFilter.addFilter("transactionType=", i);
		queryFilter.addFilter("coinCode=", type);
		queryFilter.addFilter("customerId=", id);
		queryFilter.addFilter("website=", ContextUtil.getRemoteWebsite());
		// queryFilter.setSaasId(saasId);
		PageResult result = exDmTransactionService.findPageResult(queryFilter);
		return result;
	}

	@Override
	public String savePublicManage(ExDmCustomerPublicKey e) {
		try {
			exDmCustomerPublicKeyService.save(e);
			return "OK";
		} catch (Exception e2) {
			e2.printStackTrace();
			return "NO";
		}

	}

	@Override
	public List<ExDmCustomerPublicKey> getPublicByCustomerId(Long l) {
		QueryFilter filter = new QueryFilter(ExDmCustomerPublicKey.class);
		filter.addFilter("customerId=", l);
		String saasId = RpcContext.getContext().getAttachment("saasId");
		filter.setSaasId(saasId);
		List<ExDmCustomerPublicKey> list = exDmCustomerPublicKeyService
				.find(filter);
		return list;
	}

	@Override
	public boolean judgePublicBypublicKey(String s,String customerName) {

		QueryFilter filter = new QueryFilter(ExDmCustomerPublicKey.class);
		filter.addFilter("publicKey=", s);
		filter.addFilter("publicKeyName=", customerName);
		List<ExDmCustomerPublicKey> list = exDmCustomerPublicKeyService
				.find(filter);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ExDmCustomerPublicKey> findPublicByType(Long id, String type) {

		QueryFilter filter = new QueryFilter(ExDmCustomerPublicKey.class);
		filter.addFilter("customerId=", id);
		if (type != "123") {
			filter.addFilter("currencyType=", type);
		}
		String saasId = RpcContext.getContext().getAttachment("saasId");
		filter.setSaasId(saasId);
		List<ExDmCustomerPublicKey> list = exDmCustomerPublicKeyService
				.find(filter);
		return list;
	}

	@Override
	public Boolean removePublickey(String publicKey,String customerName){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);
		QueryFilter filter = new QueryFilter(ExDmCustomerPublicKey.class);
		filter.addFilter("publicKey=", publicKey);
		filter.addFilter("publicKeyName=", customerName);
	 //	ExDmCustomerPublicKey exDmCustomerPublicKey = exDmCustomerPublicKeyService.get(filter);
		boolean b = exDmCustomerPublicKeyService.delete(filter);

		return b;

	}





	@Override
	public PageResult findRecord(RemoteQueryFilter Rfilter) {
		// TODO Auto-generated method stub
		return exDmTransactionService.findPageResult(Rfilter.getQueryFilter());
	}

	@Override
	public String[] cancelRecord(RemoteQueryFilter rfilter) {
		String[] relt=new String[2];
		QueryFilter filter=rfilter.getQueryFilter();
		ExDmTransaction exDmTransaction=exDmTransactionService.get(filter);
		if(null!=exDmTransaction){
			exDmTransaction.setStatus(4);
			exDmTransactionService.update(exDmTransaction);
		}else{
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.NOEXIST_ARG,"");;
		}
		return null;
	}

	@Override
	public HashMap<String, BigDecimal> getAllNetAsset(Long customerId,String coinCode,String currencyType,String website) {
		return exDigitalmoneyAccountService.getAllNetAsset(customerId ,currencyType ,website);
	}
	@Override
	public HashMap<String, BigDecimal> getByTypeNetAsset(Long customerId,String coinCode,String currencyType,String website) {
		return exDigitalmoneyAccountService.getByTypeNetAsset(customerId ,coinCode,currencyType ,website);
	}

	/* (non-Javadoc)
	 * @see hry.exchange.remote.account.RemoteExDigitalmoneyAccountService#getAllCanLendMoney(java.lang.Long)
	 */
	@Override
	public HashMap<String, BigDecimal> getAllCanLendMoney(Long customerId,String coinCode,String currencyType,String website) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.getAllCanLendMoney(customerId ,coinCode,currencyType ,website);
	}

	/* (non-Javadoc)
	 * @see hry.exchange.remote.account.RemoteExDigitalmoneyAccountService#getAllLendMoney(java.lang.Long)
	 */
	@Override
	public HashMap<String, BigDecimal> getAllLendMoney(Long customerId,String coinCode,String currencyType,String website) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.getAllLendMoney(customerId ,currencyType ,website);
	}

	/* (non-Javadoc)
	 * @see hry.exchange.remote.account.RemoteExDigitalmoneyAccountService#getCanLendMoney(java.lang.Long, java.lang.String)
	 */
	@Override
	public BigDecimal getCanLendMoney(Long customerId, String coinCode,String currencyType,String website) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.getCanLendMoney(customerId ,coinCode,currencyType ,website);
	}

	/* (non-Javadoc)
	 * @see hry.exchange.remote.account.RemoteExDigitalmoneyAccountService#getRMBLendMoneySum(java.lang.Long)
	 */
	@Override
	public BigDecimal getRMBLendMoneySum(Long customerId,String coinCode,String  currencyType,String website) {
		return exDigitalmoneyAccountService.getRMBLendMoneySum(customerId ,currencyType ,website);

	}

	/** (non-Javadoc)
	 * @see hry.exchange.remote.account.RemoteExDigitalmoneyAccountService#getRMBNetAsset(Long)
	 */
	@Override
	public BigDecimal getRMBNetAsset(Long customerId,String coinCode,String  currencyType,String website) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.getRMBNetAsset(customerId ,currencyType ,website);
	}

	/** (non-Javadoc)
	 * @see hry.exchange.remote.account.RemoteExDigitalmoneyAccountService#getRMBNetAsset(Long)
	 */
	@Override
	public BigDecimal getCoinCold(Long customerId,String coinCode,String  currencyType,String website) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.getCoinClod(customerId, currencyType, website);
	}

	@Override
	public List<ExDigitalmoneyAccount> findByCustomerId(Long customerId) {

		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.setSaasId(saasId);
		filter.addFilter("customerId=", customerId);
		//filter.addFilter("website=", ContextUtil.getRemoteWebsite());
		return exDigitalmoneyAccountService.find(filter);

	}


	/**
	 *
	 * 根据用户的钱与用户实际可提现的钱相比较是否小于可提现的钱
	 *
	 */
	@Override
	public Boolean judgeMoneyByCustromerId(BigDecimal money,Long customerId,String coinCode,String  currencyType,String website){

		BigDecimal money2 = exDigitalmoneyAccountService.getCanWithdrawMoney(customerId ,coinCode,currencyType ,website);
		int i = money.compareTo(money2);
		if(i<=0){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 根据用户的id 以及用户的币的类型 返回用户能否提现的这些币
	 */
	@Override
	public Boolean judgeCurrencyByCustromerId(BigDecimal currencyNum,Long customerId,String coinCode){

		String currencyType = ContextUtil.getRemoteCurrencyType();
		String website = ContextUtil.getWebsite();
		BigDecimal currencyNum2 = exDigitalmoneyAccountService.getCanWithdrawCoin(customerId, coinCode,currencyType,website);
		int i = currencyNum2.compareTo(currencyNum);
		if(i<0){
			return false;
		}
		return true;
	}


	/**
	 *
	 * 通过用户的id以及站数据查询用户的虚拟地址以及产品的信息
	 *
	 */
	@Override
	public List<DigitalmoneyAccountAndProduct> findDigtalAndProductByWebsite(
			String webstile, String customerName,Integer isMarket) {

		List<DigitalmoneyAccountAndProduct> digitalAndProduct = exDigitalmoneyAccountService.findDigitalAndProduct(webstile,customerName,isMarket);

		return digitalAndProduct;
	}



	// 保存冻结流水
	public void setColdFeeRecord(OrderParameter ordep, Integer i, String orderId) {
		ExDmColdAccountRecord exDmColdAccountRecord = new ExDmColdAccountRecord();
		exDmColdAccountRecord.setCurrencyType(ContextUtil.getRemoteCurrencyType());
		exDmColdAccountRecord.setWebsite(ContextUtil.getRemoteWebsite());
		exDmColdAccountRecord.setAccountId(ordep.getCurrencyId());
		exDmColdAccountRecord.setCustomerId(ordep.getCustomerId());
		exDmColdAccountRecord.setUserName(ordep.getCustomerName());
		exDmColdAccountRecord.setRecordType(i);
		exDmColdAccountRecord.setTransactionMoney(ordep.getFee());
		exDmColdAccountRecord.setStatus(0);
		exDmColdAccountRecord.setRemark("可用账户流水记录成功  待处理");
		exDmColdAccountRecord.setCoinCode(ordep.getType());
		exDmColdAccountRecord.setTransactionNum(orderId);
		exDmColdAccountRecord.setSaasId(RpcContext.getContext().getAttachment(
				"saasId"));

		exDmColdAccountRecordService.save(exDmColdAccountRecord);

	}


	// 保存可用币流水
	public void setHotFeeRecord(OrderParameter ordep, Integer i, String orderId) {

		ExDmHotAccountRecord exDmHotAccountRecord = new ExDmHotAccountRecord();
		exDmHotAccountRecord.setCurrencyType(ContextUtil.getRemoteCurrencyType());
		exDmHotAccountRecord.setWebsite(ContextUtil.getRemoteWebsite());
		exDmHotAccountRecord.setAccountId(ordep.getCurrencyId());
		exDmHotAccountRecord.setCustomerId(ordep.getCustomerId());
		exDmHotAccountRecord.setUserName(ordep.getCustomerName());
		exDmHotAccountRecord.setRecordType(i);
		exDmHotAccountRecord.setTransactionMoney(ordep.getFee());
		exDmHotAccountRecord.setStatus(0);
		exDmHotAccountRecord.setRemark("冻结账户冻结已成功  待处理");
		exDmHotAccountRecord.setCoinCode(ordep.getType());
		exDmHotAccountRecord.setTransactionNum(orderId);
		exDmHotAccountRecord.setSaasId(RpcContext.getContext().getAttachment(
				"saasId"));
		exDmHotAccountRecordService.save(exDmHotAccountRecord);

	}



	@Override
	public String[] freezeAccountSelf(Long id, BigDecimal freezeMoney,
			String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord ) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.freezeAccountSelf(id, freezeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}



	@Override
	public String[] unfreezeAccountSelf(Long id, BigDecimal unfreezeMoney,
			String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord ) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.unfreezeAccountSelf(id, unfreezeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}


	@Override
	public String[] unfreezeAccountThem(Long id, BigDecimal unfreezeMoney,
			String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord ) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.unfreezeAccountThem(id, unfreezeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}


	@Override
	public String[] inComeToHotAccount(Long id, BigDecimal incomeMoney,
			String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord ) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.inComeToHotAccount(id, incomeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}



	@Override
	public String[] payFromHotAccount(Long id, BigDecimal payMoney,
			String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord ) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.payFromHotAccount(id, payMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}


	@Override
	public String[] updateAccount(ExDigitalmoneyAccount account) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.updateAccount(account);
	}


	@Override
	public ExDigitalmoneyAccount getByCustomerIdAndType(Long customerId,
			String coinCode, String currencyType, String website) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.getByCustomerIdAndType(customerId, coinCode, currencyType, website);
	}

	@Override
	public List<ExDigitalmoneyAccount> getlistByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.getlistByCustomerId(customerId);
	}


	@Override
	public List<ExDigitalmoneyAccount> find(RemoteQueryFilter remoteQueryFilter) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.find(remoteQueryFilter.getQueryFilter());
	}




	@Override
	public String setOrderAppAccount(OrderParameter order) {
		RemoteAppAccountService RemoteAppAccountService=(RemoteAppAccountService)ContextUtil.getBean("remoteAppAccountService");
		ExDigitalmoneyAccount digitalmoneyAccount = this.findByCustomerType(order.getCustomerId(), order.getType(),order.getCurrencyType(),order.getWebsite());
		AppAccount appAccount=RemoteAppAccountService.getByCustomerIdAndType(order.getCustomerId(),order.getCurrencyType(),order.getWebsite());
		if (null == digitalmoneyAccount) {
			return "NO";
		}

		BigDecimal hotMoney = appAccount.getHotMoney();
		BigDecimal num = order.getCurrencyNum().add(order.getFee());
		int i = hotMoney.compareTo(num);
		if (i == -1) {
			return "NO";
		}

		// 生成订单

		ExDmTransaction exDmTransaction = new ExDmTransaction();
		exDmTransaction.setCustomerId(order.getCustomerId());
		String transactionNum = NumConstant.Ex_Dm_Transaction;
		exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
		exDmTransaction.setAccountId(order.getCurrencyId());
		exDmTransaction.setTransactionType(2);
		exDmTransaction.setTransactionMoney(order.getCurrencyNum().add(order.getFee()));
		exDmTransaction.setCustomerName(order.getCustomerName());
		// 2表示提现
		exDmTransaction.setStatus(1);
		exDmTransaction.setCoinCode(order.getType());
		exDmTransaction.setCurrencyType(ContextUtil.getCurrencyType());
		exDmTransaction.setWebsite(ContextUtil.getWebsite());
		exDmTransaction.setSaasId(RpcContext.getContext().getAttachment(
				"saasId"));
		exDmTransaction.setFee(order.getFee());
		exDmTransaction.setOurAccountNumber(order.getOurAccountNumber());
		exDmTransaction.setInAddress(order.getCurrencyKey());
		// 保存订单
		exDmTransactionService.save(exDmTransaction);

		AppTransaction appTransaction = new AppTransaction();
		appTransaction.setSaasId(exDmTransaction.getSaasId());
		appTransaction.setTransactionType(4);
		appTransaction.setStatus(1);
		appTransaction.setRemark("提币");
		exDmTransaction.setOptType(2);
		appTransaction.setCustomerId(exDmTransaction.getCustomerId());
		appTransaction.setUserName(exDmTransaction.getCustomerName());
		appTransaction.setTransactionNum(exDmTransaction.getTransactionNum());
		appTransaction.setAccountId(appAccount.getId());
		appTransaction.setCurrencyType(appAccount.getCurrencyType());
		appTransaction.setWebsite(appAccount.getWebsite());
		appTransaction.setFee(exDmTransaction.getFee());
		appTransaction.setUserId(exDmTransaction.getUserId());
		appTransaction.setTransactionMoney(exDmTransaction.getTransactionMoney());
		RemoteAppTransactionService remoteAppTransactionService=(RemoteAppTransactionService)ContextUtil.getBean("remoteAppTransactionService");
		remoteAppTransactionService.save(appTransaction);
		RemoteAppAccountService.freezeAccountSelf(appAccount.getId(), exDmTransaction.getTransactionMoney().subtract(exDmTransaction.getFee()), exDmTransaction.getTransactionNum(), "提币金额", null,null);
		RemoteAppAccountService.freezeAccountSelf(appAccount.getId(), exDmTransaction.getFee(), exDmTransaction.getTransactionNum(), "提币手续费", null,null);


		return "OK";
	}


	@Override
	public ExDigitalmoneyAccount getInfoByAccountAndAddress(String account,
			String address) {
		QueryFilter queryFilter = new QueryFilter(ExDigitalmoneyAccount.class);
		queryFilter.addFilter("publicKey=", address);
		queryFilter.addFilter("userName=", account);
		return exDigitalmoneyAccountService.get(queryFilter);
	}



	@Override
	public List<ExDigitalmoneyAccount> positionSummary(Long customerId) {

		QueryFilter queryFilter = new QueryFilter(ExDigitalmoneyAccount.class);
		queryFilter.addFilter("customerId=", customerId);
		List<ExDigitalmoneyAccount> list=	exDigitalmoneyAccountService.find(queryFilter);

		for(ExDigitalmoneyAccount l:list){
			//有币的话就算
		    if(l.getHotMoney().add(l.getColdMoney()).compareTo(new BigDecimal(0))>0){
		    	//start
		    	Map<String,Object> buymap=new HashMap<String,Object>();
		    	buymap.put("type", 1);
		    	buymap.put("customerId", customerId);
		    	List<ExDigitalmoneyAccount> listbuy=exExOrderInfoDao.getpositionAvePrice(buymap);
		    	BigDecimal buyMoney=new BigDecimal("0");
		    	if(null!=listbuy&&listbuy.size()>0&&null!=listbuy.get(0)){
		    		buyMoney=listbuy.get(0).getPositionAvePrice();
		    	}

		    	Map<String,Object> sellmap=new HashMap<String,Object>();
		    	buymap.put("type", 1);
		    	buymap.put("customerId", customerId);
		    	List<ExDigitalmoneyAccount> listsell=exExOrderInfoDao.getpositionAvePrice(sellmap);
		    	BigDecimal sellMoney=new BigDecimal("0");
		    	if(null!=listsell&&listsell.size()>0&&null!=listsell.get(0)){
		    		sellMoney=listbuy.get(0).getPositionAvePrice();
		    	}

		    	BigDecimal positionAvePrice =buyMoney.subtract(sellMoney).divide(l.getHotMoney().add(l.getColdMoney()),2,BigDecimal.ROUND_HALF_UP);
		         l.setPositionAvePrice(positionAvePrice);
		         //end
		         String header=l.getWebsite()+":"+l.getCurrencyType()+":"+l.getCoinCode();
				 String currentExchangPrice=ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.CurrentExchangPrice);
				 if(null!=currentExchangPrice){
					 BigDecimal currentExchangPricebig=new BigDecimal(currentExchangPrice);
					 l.setFloatprofitandlossMoney(currentExchangPricebig.subtract(positionAvePrice).multiply(l.getHotMoney().add(l.getColdMoney())));
			         l.setFloatprofitandlossMoneyRate(l.getFloatprofitandlossMoney().divide(buyMoney.subtract(sellMoney),2,BigDecimal.ROUND_HALF_UP));
				 }


		    }
		}
		return list;
	}


	/* (non-Javadoc)
	 * @see hry.change.remote.account.RemoteExDigitalmoneyAccountService#findAppAccountDisable(hry.util.RemoteQueryFilter)
	 */
	@Override
	public List<AppAccountDisable> findAppAccountDisable(
			RemoteQueryFilter remoteQueryFilter) {
		// TODO Auto-generated method stub
		return appAccountDisableService.find(remoteQueryFilter.getQueryFilter());
	}










}




