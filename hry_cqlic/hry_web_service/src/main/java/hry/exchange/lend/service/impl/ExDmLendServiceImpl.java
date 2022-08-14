/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.lend.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.account.fund.model.AppAccount;
import hry.account.fund.service.AppAccountService;
import hry.account.remote.RemoteAppAccountService;
import hry.bean.PageResult;
import hry.core.constant.CodeConstant;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.dao.ExDmLendDao;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.exchange.lend.service.ExDmLendIntentService;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.lend.service.LendCoinAccountService;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.Lend;
import hry.manage.remote.model.base.FrontPage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.message.MessageConstant;
import hry.util.message.MessageUtil;
import hry.util.sys.ContextUtil;
import hry.web.remote.RemoteAppConfigService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exDmLendService")
public class ExDmLendServiceImpl extends BaseServiceImpl<ExDmLend, Long>
		implements ExDmLendService {
	private Logger logger = Logger.getLogger(ExDmLend.class);
	@Resource(name = "exDmLendDao")
	@Override
	public void setDao(BaseDao<ExDmLend, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private LendCoinAccountService  lendCoinAccountService;
	@Resource
	private  ExDmLendIntentService exDmLendIntentService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private AppAccountService appAccountService;
	@Override
	public PageResult listPage(RemoteQueryFilter filter) {
		 return this.findPageResult(filter.getQueryFilter());
	}
	
	public void saveCanLendPlatformMoney(ExDmLendIntent exDmLendIntent){
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
	
		String canLendPlatformMoneys=remoteAppConfigService.getBykeyfromDB("canLendPlatformMoney");
		BigDecimal  canLendPlatformMoney=new BigDecimal(StringUtil.isEmpty(canLendPlatformMoneys)?"10000000000":canLendPlatformMoneys); //
	    remoteAppConfigService.setBykeyToDB("canLendPlatformMoney", canLendPlatformMoney.add(exDmLendIntent.getRepayCount()).toString());
	}
	public BigDecimal getCanLending(String lendCoinType,String lendCoin){
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String canLendPlatformMoneys=remoteAppConfigService.getBykeyfromDB("canLendPlatformMoney");
		BigDecimal  canLendPlatformMoney=new BigDecimal(StringUtil.isEmpty(canLendPlatformMoneys)?"10000000000":canLendPlatformMoneys); //
		ExDmLendDao exDmLendDao=(ExDmLendDao)this.dao;
		Map<String,String> map =new HashMap<String,String>();
		map.put("lendCoinType", lendCoinType);
		map.put("lendCoin", lendCoin);
		List<ExDmLend> list=exDmLendDao.getLending(map);
		if(null!=list&&list.size()>0){
			ExDmLend exDmLend=list.get(0);
			if(null!=exDmLend){
				BigDecimal lending=exDmLend.getLendCount();
				return canLendPlatformMoney.subtract(lending);
			}else{
				return canLendPlatformMoney;
			}
		}else{
			return canLendPlatformMoney;
		}
		
	}
	@Override
	public String[] saveExDmLend(ExDmLend exDmLend) {
		String[] relt = new String[2];
		/*if(DifCustomer.isInClosePlateAndClose()){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="结算期间不允许融资";
			
		}*/
		
		//判断下最小借款金额
		
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String lendRates=remoteAppConfigService.getFinanceLendByKey("lendRate");
		BigDecimal  lendRate=new BigDecimal(StringUtil.isEmpty(lendRates)?"0.006":lendRates);
		exDmLend.setLendNum(IdGenerate.transactionNum(NumConstant.Ex_Dm_Lend_Intent));
		exDmLend.setLendTime(new Date());
		exDmLend.setLendRate(lendRate); //借款利率
		exDmLend.setStatus(1);
		exDmLend.setRepayLendCount(new BigDecimal("0")); 
		exDmLend.setLendDay(1);//马上就生成一天的利息
		BigDecimal interestCount=exDmLend.getLendCount().multiply(exDmLend.getLendRate()).divide(new BigDecimal("100"));
		exDmLend.setInterestCount(interestCount); //产生了一天的利息
		exDmLend.setRepayInterestCount(new BigDecimal("0"));
		exDmLend.setInterestcalEndTime(new Date());
		exDmLend.setIsPartRepay(0);
		exDmLend.setIsCreateIntent(0);
		RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService)ContextUtil.getBean("remoteAppPersonInfoService");
		AppPersonInfo appPersonInfo = remoteAppPersonInfoService.getByCustomerId(exDmLend.getCustomerId());
		exDmLend.setTrueName(appPersonInfo.getSurname()+appPersonInfo.getTrueName());
	
		//拿到平台账户的剩余可借币？？？
	//	BigDecimal  canLendPlatformMoney=getCanLending(exDmLend.getLendCoinType(),exDmLend.getLendCoin());
	/*	if(exDmLend.getLendCount().compareTo(canLendPlatformMoney)==1){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="平台"+MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
		}*/
	
		//拿到此账户可借入的金额？？？
		
		//还能借入
		BigDecimal b=null;
		String coinCodeForRmb = remoteAppConfigService.getFinanceByKey("coinCodeForRmb");
		b=lendCoinAccountService.getCanLendMoney(exDmLend.getCustomerId(),coinCodeForRmb, exDmLend.getLendCoin(),exDmLend.getCurrencyType(),exDmLend.getWebsite());
		if(exDmLend.getLendCount().compareTo(b)==1){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]=MessageUtil.getText(MessageConstant.LESS_BALANCE);
			return relt;
	   }
		if(exDmLend.getLendCoinType().equals("1")){
			
			//保存融资金额
			RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
			RpcContext.getContext().setAttachment("saasId",exDmLend.getSaasId());
			AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(exDmLend.getCustomerId(), exDmLend.getLendCoin(),exDmLend.getWebsite());
		    appAccount.setLendMoney(appAccount.getLendMoney().add(exDmLend.getLendCount()));
		    remoteAppAccountService.update(appAccount);
		    
		
		    exDmLend.setAccountId(appAccount.getId());
		    exDmLend.setCurrencyType("cny");
		    exDmLend.setWebsite("cn");
		    exDmLend.setIsPartRepay(1);
		    this.save(exDmLend);
		    
		   //热账户收入一笔
		    List<Accountadd> aaddlists =new ArrayList<Accountadd>();
			Accountadd accountadd = getAccountadd(0, appAccount.getId(), exDmLend.getLendCount(), 1, 41, exDmLend.getLendNum());
			aaddlists.add(accountadd);
			messageProducer.toAccount(JSON.toJSONString(aaddlists));
			
		}else{
			
			//保存融币个数
			ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.getByCustomerIdAndType(exDmLend.getCustomerId(),exDmLend.getLendCoin(), null,exDmLend.getWebsite());
			exDigitalmoneyAccount.setLendMoney(exDigitalmoneyAccount.getLendMoney().add(exDmLend.getLendCount()));
			exDigitalmoneyAccountService.update(exDigitalmoneyAccount);
			
		    exDmLend.setAccountId(exDigitalmoneyAccount.getId());
		    exDmLend.setIsPartRepay(1);//允许部分还款
		    exDmLend.setCurrencyType("cny");
		    exDmLend.setWebsite("cn");
		    this.save(exDmLend);
			
		    List<Accountadd> aaddlists =new ArrayList<Accountadd>();
			Accountadd accountadd = getAccountadd(1, exDigitalmoneyAccount.getId(), exDmLend.getLendCount(), 1, 41, exDmLend.getLendNum());
			aaddlists.add(accountadd);
			messageProducer.toAccount(JSON.toJSONString(aaddlists));
			
			
		}
		
		relt[0]=CodeConstant.CODE_SUCCESS;
	   
		return relt;
	}
	//偿还money还没写偿还虚拟币
	@Override
	public String[] repayment(Long id, String type, BigDecimal repaymentMoney) {
		String[] relt = new String[2];
		/*if(DifCustomer.isInClosePlateAndClose()){
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="结算期间不允许还款";
			
		}*/
		
		if("part".equals(type)){
			return partRepayment(id,type,repaymentMoney);
		}else if("all".equals(type)){
			ExDmLend exDmLend=this.get(id);
			return partRepayment(id,type,exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount()));
		}
		return relt;
	}


	/**
	 * 全部偿还
	 * @param id
	 * @param type
	 * @param repaymentMoney
	 * @return
	 */
	public synchronized String[] partRepayment(Long id, String type, BigDecimal repaymentMoney) {
		String[] relt = new String[2];
		ExDmLend exDmLend = this.get(id);
		if (exDmLend.getLendCoinType().equals("1")) { // 资金

			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(exDmLend.getCustomerId().toString());
			if (null == userRedis) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "userRedis为空";
				return relt;

			}
			AppAccountRedis appAccountredis = UserRedisUtils.getAccount(userRedis.getAccountId().toString());
			if (null == appAccountredis) {
				logger.info(exDmLend.getCustomerId() + "--appAccountredis为空");
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "appAccountredis为空";
				return relt;

				// todo如过没有就要去查数据库吧

			}
			BigDecimal hotMoney = appAccountredis.getHotMoney();
			// 利息
			BigDecimal notrepayInterestCount = exDmLend.getInterestCount().subtract(exDmLend.getRepayInterestCount());
			BigDecimal allrepaymentMoney = repaymentMoney.add(notrepayInterestCount);
			if (hotMoney.compareTo(new BigDecimal("0")) == 0) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "可用金额为0";
				return relt;
			}
			if (allrepaymentMoney.compareTo(new BigDecimal("0")) == 0) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "本借款已经还完";
				return relt;
			}
			BigDecimal notRepayLendCount = exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount());
			if (repaymentMoney.compareTo(notRepayLendCount) == 1) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "大于应还本金";
				return relt;
			}
			if (hotMoney.compareTo(BigDecimal.ZERO) == 0) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "热账户为0";
				return relt;
			}
			if (hotMoney.compareTo(allrepaymentMoney) < 0) {
				if (hotMoney.compareTo(notrepayInterestCount) <= 0) {
					notrepayInterestCount = hotMoney;
					repaymentMoney = BigDecimal.ZERO;
				} else {
					repaymentMoney = hotMoney.subtract(notrepayInterestCount);
				}
			}
			if (repaymentMoney.compareTo(BigDecimal.ZERO) == -1 || notrepayInterestCount.compareTo(BigDecimal.ZERO) == -1) {
				relt[0] = CodeConstant.CODE_FAILED;
				return relt;
			}
			RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
			List<Accountadd> aaddlists = new ArrayList<Accountadd>();
			AppAccount appAccountnew = remoteAppAccountService.getByCustomerIdAndType(exDmLend.getCustomerId(), null, null);
			if (repaymentMoney.compareTo(BigDecimal.ZERO) == 1) {
				// 本金
				ExDmLendIntent exDmLendIntent1 = exDmLendIntentService.create(exDmLend, repaymentMoney, "principal");
				exDmLendIntent1.setFactTime(new Date());
				exDmLendIntentService.save(exDmLendIntent1);

				Accountadd accountadd1 = getAccountadd(0, appAccountnew.getId(), new BigDecimal("0").subtract(exDmLendIntent1.getRepayCount()), 1, 41, exDmLend.getLendNum());
				aaddlists.add(accountadd1);
			}

			// saveCanLendPlatformMoney(exDmLendIntent1);

			if (notrepayInterestCount.compareTo(BigDecimal.ZERO) == 1) {
				// 利息
				ExDmLendIntent exDmLendIntent2 = exDmLendIntentService.create(exDmLend, notrepayInterestCount, "interest");
				exDmLendIntent2.setFactTime(new Date());
				exDmLend.setIsCreateIntent(1);
				exDmLendIntentService.save(exDmLendIntent2);

				Accountadd accountadd2 = getAccountadd(0, appAccountnew.getId(), new BigDecimal("0").subtract(exDmLendIntent2.getRepayCount()), 1, 41, exDmLend.getLendNum());
				aaddlists.add(accountadd2);
			}

			exDmLend.setRepayLendCount(exDmLend.getRepayLendCount().add(repaymentMoney));
			exDmLend.setRepayInterestCount(exDmLend.getRepayInterestCount().add(notrepayInterestCount));
			if (exDmLend.getLendCount().equals(exDmLend.getRepayLendCount())) {
				exDmLend.setStatus(3);
			} else {
				exDmLend.setStatus(2);
			}
			/*
			 * //如果剩余本金大于200才允许部分还款
			 * if((exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount()
			 * )).compareTo(new BigDecimal("200"))>=0){
			 * exDmLend.setIsPartRepay(1); }else{ exDmLend.setIsPartRepay(0); }
			 */
			this.update(exDmLend);
			// 必须要重新查过，不然就会覆盖之前的数据

			appAccountnew.setLendMoney(appAccountnew.getLendMoney().subtract(repaymentMoney));
			appAccountService.update(appAccountnew);
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "";

			messageProducer.toAccount(JSON.toJSONString(aaddlists));

		} else { // 虚拟币

			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(exDmLend.getCustomerId().toString());
			if (null == userRedis) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "账户缓存为空";
				return relt;
			}
			ExDigitalmoneyAccountRedis appAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(exDmLend.getLendCoin()).toString(), exDmLend.getLendCoin());

			BigDecimal hotMoney = appAccount.getHotMoney();
			// 利息
			BigDecimal notrepayInterestCount = exDmLend.getInterestCount().subtract(exDmLend.getRepayInterestCount());
			BigDecimal allrepaymentMoney = repaymentMoney.add(notrepayInterestCount);
			if (hotMoney.compareTo(new BigDecimal("0")) == 0) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "可用金额为0";
				return relt;
			}
			if (allrepaymentMoney.compareTo(new BigDecimal("0")) == 0) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "本借款已经还完";
				return relt;
			}
			BigDecimal notRepayLendCount = exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount());
			if (repaymentMoney.compareTo(notRepayLendCount) == 1) {
				relt[0] = CodeConstant.CODE_FAILED;
				relt[1] = "大于应还本金";
				return relt;
			}
			if (hotMoney.compareTo(allrepaymentMoney) < 0) {
				if (hotMoney.compareTo(notrepayInterestCount) <= 0) {
					notrepayInterestCount = hotMoney;
					repaymentMoney = BigDecimal.ZERO;
				} else {
					repaymentMoney = hotMoney.subtract(notrepayInterestCount);
				}
			}
			if (repaymentMoney.compareTo(BigDecimal.ZERO) == -1 || notrepayInterestCount.compareTo(BigDecimal.ZERO) == -1) {
				relt[0] = CodeConstant.CODE_FAILED;
				return relt;
			}

			List<Accountadd> aaddlists = new ArrayList<Accountadd>();
			ExDigitalmoneyAccount appAccountnew = exDigitalmoneyAccountService.getByCustomerIdAndType(exDmLend.getCustomerId(), exDmLend.getLendCoin(), null, null);
			if (repaymentMoney.compareTo(BigDecimal.ZERO) == 1) {
				// 本金
				ExDmLendIntent exDmLendIntent1 = exDmLendIntentService.create(exDmLend, repaymentMoney, "principal");
				exDmLendIntent1.setFactTime(new Date());
				exDmLendIntentService.save(exDmLendIntent1);

				Accountadd accountadd1 = getAccountadd(1, appAccountnew.getId(), new BigDecimal("0").subtract(exDmLendIntent1.getRepayCount()), 1, 41, exDmLend.getLendNum());
				aaddlists.add(accountadd1);
			}

			if (notrepayInterestCount.compareTo(BigDecimal.ZERO) == 1) {
				// 利息
				ExDmLendIntent exDmLendIntent2 = exDmLendIntentService.create(exDmLend, notrepayInterestCount, "interest");
				exDmLendIntent2.setFactTime(new Date());
				exDmLend.setIsCreateIntent(1);
				exDmLendIntentService.save(exDmLendIntent2);

				Accountadd accountadd2 = getAccountadd(1, appAccountnew.getId(), new BigDecimal("0").subtract(exDmLendIntent2.getRepayCount()), 1, 41, exDmLend.getLendNum());
				aaddlists.add(accountadd2);
			}


			exDmLend.setRepayLendCount(exDmLend.getRepayLendCount().add(repaymentMoney));
			exDmLend.setRepayInterestCount(exDmLend.getRepayInterestCount().add(notrepayInterestCount));
			if (exDmLend.getLendCount().equals(exDmLend.getRepayLendCount())) {
				exDmLend.setStatus(3);
			} else {
				exDmLend.setStatus(2);
			}
			/*
			 * //如果剩余本金大于200才允许部分还款
			 * if((exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount()
			 * )).compareTo(new BigDecimal("200"))>=0){
			 * exDmLend.setIsPartRepay(1); }else{ exDmLend.setIsPartRepay(0); }
			 */
			this.update(exDmLend);
			// 必须要重新查过，不然就会覆盖之前的数据

			appAccountnew.setLendMoney(appAccountnew.getLendMoney().subtract(repaymentMoney));
			exDigitalmoneyAccountService.update(appAccountnew);
			relt[0] = CodeConstant.CODE_SUCCESS;
			relt[1] = "";
			messageProducer.toAccount(JSON.toJSONString(aaddlists));

		}

		return relt;
	}

	public String[] allRepayment(Long id, String type) {/*
		String[] relt = new String[2];
		ExDmLend exDmLend=this.get(id);
		if(exDmLend.getLendCoinType().equals("1")){ //资金
				RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
				UserRedis userRedis = redisUtil.get(exDmLend.getCustomerId().toString());
				AppAccountRedis appAccountredis = UserRedisUtils.getAccount(userRedis.getAccountId().toString());
				
				BigDecimal hotMoney=appAccountredis.getHotMoney();
	
				
				//未偿还利息
			    BigDecimal notrepayInterestCount=	exDmLend.getInterestCount().subtract(exDmLend.getRepayInterestCount());
				//未偿还本金
			    BigDecimal notRepayLendCount=exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount());
				BigDecimal allrepaymentMoney=notRepayLendCount.add(notrepayInterestCount);
				if(hotMoney.compareTo(new BigDecimal("0"))==0){
					relt[0]=CodeConstant.CODE_FAILED;
					relt[1]="可用金额为0";
					return relt;
				}
				if(allrepaymentMoney.compareTo(new BigDecimal("0"))==0){
					relt[0]=CodeConstant.CODE_FAILED;
					relt[1]="本借款已经还完";
					return relt;
				}
				
				//可用金额不够 还所有的，哪就先还利息剩下的能还多少本金就还多少本金
				//能偿还的利息
			    BigDecimal cannotrepayInterestCount=hotMoney.compareTo(notrepayInterestCount)>0?notrepayInterestCount:hotMoney;
				//能偿还的本金
			    BigDecimal cannotRepayLendCount=hotMoney.subtract(cannotrepayInterestCount).compareTo(notRepayLendCount)>0?notRepayLendCount:hotMoney.subtract(cannotrepayInterestCount);
		
			    if(cannotrepayInterestCount.compareTo(new BigDecimal("0"))>0){
			    	
					ExDmLendIntent	exDmLendIntent2 =exDmLendIntentService.create(exDmLend,cannotrepayInterestCount,"interest");
					exDmLendIntent2.setFactTime(new Date());
					exDmLend.setIsCreateIntent(1);
					exDmLendIntentService.save(exDmLendIntent2);
				}
				
		        if(cannotRepayLendCount.compareTo(new BigDecimal("0"))>0){
		        	ExDmLendIntent	exDmLendIntent1 =exDmLendIntentService.create(exDmLend,cannotRepayLendCount,"principal");
					exDmLendIntent1.setFactTime(new Date());
					exDmLendIntentService.save(exDmLendIntent1);
					Accountadd accountadd1 = getAccountadd(0, appAccountnew.getId(),new BigDecimal("0").subtract(exDmLendIntent1.getRepayCount()), 1, 41, exDmLend.getLendNum());
					aaddlists.add(accountadd1);
				//	saveCanLendPlatformMoney(exDmLendIntent1);
				}
				
				exDmLend.setRepayLendCount(exDmLend.getRepayLendCount().add(cannotRepayLendCount));
				exDmLend.setRepayInterestCount(exDmLend.getRepayInterestCount().add(cannotrepayInterestCount));
				
				if(exDmLend.getLendCount().compareTo(exDmLend.getRepayLendCount())==0){
					exDmLend.setStatus(3);
				}else{
					if(exDmLend.getRepayLendCount().compareTo(new BigDecimal("0"))==0){
						exDmLend.setStatus(1);
					}else{
						exDmLend.setStatus(2);
					}
				
				}
				
				
		        this.update(exDmLend);
		        
		        //必须要重新查过，不然就会覆盖之前的数据
		    	AppAccount appAccountnew = remoteAppAccountService.getByCustomerIdAndType(exDmLend.getCustomerId(), exDmLend.getCurrencyType(),exDmLend.getWebsite());
		    	appAccountnew.setLendMoney(appAccountnew.getLendMoney().subtract(cannotRepayLendCount));
				remoteAppAccountService.update(appAccountnew);
				relt[0]=CodeConstant.CODE_SUCCESS;
				relt[1]="";
		}else{
			
		}
		return relt;
		
	*/
		String[] relt = new String[2];
		return relt;
	}
	@Override
	public List<ExDmLend> getMayPingCustomer(Map<String,String> map) {
		ExDmLendDao exDmLendDao= (ExDmLendDao)dao;
		return exDmLendDao.getMayPingCustomer(map);
	}
	@Override
	public void calculateLendInterest() {
	//前置付息	//每天固定晚上凌晨0点开始算,
		QueryFilter filter=new QueryFilter(ExDmLend.class);
		filter.addFilter("status<", 3);
		List<ExDmLend> list=this.find(filter);
		for(ExDmLend l:list){
            Integer lendDay = l.getLendDay();
            if(null==lendDay){
                lendDay=1;
            }
            l.setLendDay((lendDay+1));
            String nowdate=DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_YMD);
			String endtime=DateUtil.dateToString(l.getInterestcalEndTime(), StringConstant.DATE_FORMAT_YMD);
			//不想等，说明今天还没计算
			if(!endtime.equals(nowdate)){
				BigDecimal days=new BigDecimal(DateUtil.getDaysBetweenDate(endtime, nowdate));
				BigDecimal interest=l.getLendCount().subtract(l.getRepayLendCount()).multiply(l.getLendRate()).multiply(days).divide(new BigDecimal("100"));
			    l.setInterestCount(l.getInterestCount().add(interest)); 
			 
			  /*  //如果每天产生的利息超过0.1元就生成款项,小于0.1的就不生成最后一次性结算利息
			    if(interest.compareTo(new BigDecimal("0.1"))>0){
			    	sysRepayInterest(l,interest);
			    }
			    //如果累计未还利息超过10元的话就要生成一条款项
			    BigDecimal noRepayInterestCount= l.getInterestCount().subtract(l.getRepayInterestCount());
			    if(noRepayInterestCount.compareTo(new BigDecimal("10"))>0){
			    	sysRepayInterest(l,noRepayInterestCount);
			    }*/
			    l.setInterestcalEndTime(new Date());//利息截至时间
			    this.update(l);
				
			}
		}
	}
	/*public String[] sysRepayInterest(ExDmLend l,BigDecimal InterestCount){
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		ExDmLendIntent	exDmLendIntent2 =exDmLendIntentService.create(l,InterestCount,"interest");
		exDmLendIntent2.setFactTime(new Date());
    	
    	RpcContext.getContext().setAttachment("saasId",l.getSaasId());
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(l.getCustomerId(), l.getCurrencyType(),l.getWebsite());
		String[] relt = remoteAppAccountService.payFromHotAccount(appAccount.getId(), exDmLendIntent2.getRepayCount(),l.getLendNum(),"系统还款利息",null);
        if(relt[0].equals(CodeConstant.CODE_SUCCESS)){
        	exDmLendIntentService.save(exDmLendIntent2);
        	l.setIsCreateIntent(1);
        	l.setRepayInterestCount(l.getRepayInterestCount().add(exDmLendIntent2.getRepayCount()));
        }
		
        
        return relt;
	}*/
	@Override
	public ExDmLend getLendBycustmer(String lendcoin,Long customerId,String currencyType,String website) {
		ExDmLendDao exDmLendDao= (ExDmLendDao)dao;
		List<ExDmLend> edllistd=exDmLendDao.getLendBycustmer(currencyType, customerId,currencyType,website);
		ExDmLend exDmLend = null;
		if(null!=edllistd&&edllistd.size()!=0&&edllistd.get(0)!=null){
			exDmLend=edllistd.get(0);
			exDmLend.setNotrepayInterestCount(exDmLend.getInterestCount().subtract(exDmLend.getRepayInterestCount()));
			exDmLend.setNotrepayLendCount(exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount()));
		}else{
			exDmLend  = new ExDmLend();
			exDmLend.setNotrepayInterestCount(new BigDecimal("0"));
			exDmLend.setNotrepayLendCount(new BigDecimal("0")); //这个值应该是等于appcount记录里面的lendMoney
		}
		return exDmLend;
	}
	
	
	@Override
	public PageResult see(QueryFilter filter) {
		

		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDmLend> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String userName = filter.getRequest().getParameter("userName_like");
		String trueName = filter.getRequest().getParameter("trueName_like");
		String lendCoinType = filter.getRequest().getParameter("lendCoinType");
		String status = filter.getRequest().getParameter("status");
		String lendTimeG = filter.getRequest().getParameter("lendTime_GT");
		String lendTimeL = filter.getRequest().getParameter("lendTime_LT");
		
		Map<String,String> map = new HashMap<String,String>();
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(lendCoinType)){
			if(!lendCoinType.equals("all")){
				map.put("lendCoinType", lendCoinType);
			}	
			
		}
		if(!StringUtils.isEmpty(status)){
			if(!status.equals("all")){
				map.put("status", status);
			}
			
		}
		if(!StringUtils.isEmpty(lendTimeG)){
			 
			
			map.put("lendTimeG", lendTimeG);
		}
		if(!StringUtils.isEmpty(lendTimeL)){
			
			map.put("lendTimeL", lendTimeL);
		}
		
		((ExDmLendDao)dao).see(map);
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		
	  return pageResult;
	}


	@Override
	public BigDecimal getLending(String lendCoinType,String lendCoin) {
		Map<String,String> map =new HashMap<String,String>();
		map.put("lendCoinType", lendCoinType);
		map.put("lendCoin", lendCoin);
		ExDmLendDao exDmLendDao=(ExDmLendDao)this.dao;
		List<ExDmLend> list=exDmLendDao.getLending(map);
		if(null!=list&&list.size()>0){
			ExDmLend exDmLend=list.get(0);
			if(null!=exDmLend){
				BigDecimal lending=exDmLend.getLendCount();
				return lending;
			}else{
				return new BigDecimal("0");
			}
		}else{
			return new BigDecimal("0");
		}
	}

	@Override
	public FrontPage findFrontPage(Map<String, String> map) {
		//----------------------查询开始------------------------------
		
		String userName =map.get("userName_like");
		String trueName = map.get("trueName_like");
		String lendCoinType =map.get("lendCoinType");
		String status =map.get("status");
		String lendTimeG =map.get("lendTime_GT");
		String lendTimeL =map.get("lendTime_LT");

        if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(userName)){
			map.put("userName", "%"+userName+"%");
		}
		if(!StringUtils.isEmpty(lendCoinType)){
		if(!lendCoinType.equals("all")){
			map.put("lendCoinType", lendCoinType);
		}	
			
		}
		if(!StringUtils.isEmpty(status)){
			if(!status.equals("all")){
				map.put("status", status);
			}
			
		}
		if(!StringUtils.isEmpty(lendTimeG)){
			 
			
			map.put("lendTimeG", lendTimeG);
		}
		if(!StringUtils.isEmpty(lendTimeL)){
			
			map.put("lendTimeL", lendTimeL);
		}
		
		Page page = PageFactory.getPage(map);

		//查询方法
		List<Lend> list =((ExDmLendDao)dao).see(map);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	
	}
	
	public Accountadd getAccountadd(Integer acccountType, Long accountId, BigDecimal money, Integer monteyType, Integer remarks, String transactionNum) {
		Accountadd accountadd = new Accountadd();
		accountadd.setAcccountType(acccountType);
		accountadd.setMoney(money);
		accountadd.setAccountId(accountId);
		accountadd.setMonteyType(monteyType);
		accountadd.setTransactionNum(transactionNum);
		accountadd.setRemarks(remarks);
		// accountadd.setRemarks(remarks);
		return accountadd;
	}
}
