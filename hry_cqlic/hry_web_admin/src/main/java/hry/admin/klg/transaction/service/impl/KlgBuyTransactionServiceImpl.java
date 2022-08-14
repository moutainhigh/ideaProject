/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 11:40:59 
 */
package hry.admin.klg.transaction.service.impl;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import hry.admin.klg.forecast.model.KlgForecast;
import hry.admin.klg.forecast.service.KlgForecastService;
import hry.admin.klg.transaction.dao.KlgBuyTransactionDao;
import hry.admin.klg.transaction.dao.KlgSellTransactionDao;
import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.admin.klg.transaction.service.KlgBuyTransactionService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.front.redis.model.UserRedis;
import hry.klg.remote.RemoteKlgService;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.UserRedisUtils;

/**
 * <p>
 * KlgBuyTransactionService
 * </p>
 * 
 * @author: yaozhuo
 * @Date : 2019-04-16 11:40:59
 */
@Service("klgBuyTransactionService")
public class KlgBuyTransactionServiceImpl extends BaseServiceImpl<KlgBuyTransaction, Long>
		implements KlgBuyTransactionService {

	@Resource(name = "klgBuyTransactionDao")
	@Override
	public void setDao(BaseDao<KlgBuyTransaction, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	private MessageProducer messageProducer;
	
	@Resource
	private RemoteKlgService remoteKlgService;
	
	@Resource
	private KlgForecastService klgForecastService;

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		// ----------------------分页查询头部外壳------------------------------
		Page<KlgBuyTransactionVo> page = PageFactory.getPage(filter);
		// ----------------------分页查询头部外壳------------------------------

		// ----------------------查询开始------------------------------

		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String email = filter.getRequest().getParameter("email");
		String trueName = filter.getRequest().getParameter("trueName");
		String surName = filter.getRequest().getParameter("surName");
		String status = filter.getRequest().getParameter("status");
		String interestStatus = filter.getRequest().getParameter("interestStatus");
		String rushOrders = filter.getRequest().getParameter("rushOrders");
		String transactionNum = filter.getRequest().getParameter("transactionNum");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		String modified_sm = filter.getRequest().getParameter("created_GTM");
		String modified_em = filter.getRequest().getParameter("created_LTM");

		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(transactionNum)) {
			map.put("transactionNum", "%" + transactionNum + "%");
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		if (!StringUtils.isEmpty(surName)) {
			map.put("surName", "%" + surName + "%");
		}
		if (!StringUtils.isEmpty(status)) {
			map.put("status", Integer.valueOf(status));
		}
		if (!StringUtils.isEmpty(interestStatus)) {
			map.put("interestStatus", Integer.valueOf(interestStatus));
		}
		if (!StringUtils.isEmpty(rushOrders)) {
			map.put("rushOrders", Integer.valueOf(rushOrders));
		}
		if (!StringUtils.isEmpty(modified_s)) {
			map.put("modified_s", modified_s);
		}
		if (!StringUtils.isEmpty(modified_e)) {
			map.put("modified_e", modified_e);
		}
		if (!StringUtils.isEmpty(modified_sm)) {
			map.put("modified_sm", modified_sm);
		}
		if (!StringUtils.isEmpty(modified_em)) {
			map.put("modified_em", modified_em);
		}
		((KlgBuyTransactionDao) dao).findPageBySql(map);

		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public BigDecimal getBuyTransactionByIdINIds(String ids) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String[] strarray = ids.split(",");
		List<String> strsToList = Arrays.asList(strarray);
		map.put("ids", strsToList);
		return ((KlgBuyTransactionDao) dao).getBuyTransactionByIdINIds(map);
	}

	@Override
	public KlgBuyTransactionVo getUsdtMoneySumByStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ((KlgBuyTransactionDao) dao).getUsdtMoneySumByStatus(map);
	}

	@Override
	public List<KlgBuyTransactionVo> findBuyTransactionByIdINIds(String ids, Integer trusteeshipStatus) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String[] strarray = ids.split(",");
		List<String> strsToList = Arrays.asList(strarray);
		map.put("ids", strsToList);
		map.put("trusteeshipStatus", trusteeshipStatus);
		return ((KlgBuyTransactionDao) dao).findBuyTransactionByIdINIds(map);
	}

	@Override
	public void updateStatus(Map<String, Object> map) {
		// TODO Auto-generated method stub
		((KlgBuyTransactionDao) dao).updateStatus(map);
	}

	@Override
	public List<KlgBuyTransactionVo> findListBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		// ----------------------查询开始------------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		if(filter.getRequest()==null){
			map.put("status", 1);
			return ((KlgBuyTransactionDao) dao).findPageBySql(map);
		}
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String email = filter.getRequest().getParameter("email");
		String trueName = filter.getRequest().getParameter("trueName");
		String surName = filter.getRequest().getParameter("surName");
		String status = filter.getRequest().getParameter("status");
		String transactionNum = filter.getRequest().getParameter("transactionNum");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");

		
		if (!StringUtils.isEmpty(transactionNum)) {
			map.put("transactionNum", "%" + transactionNum + "%");
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		if (!StringUtils.isEmpty(surName)) {
			map.put("surName", "%" + surName + "%");
		}
		if (!StringUtils.isEmpty(status)) {
			map.put("status", Integer.valueOf(status));
		}
		if (!StringUtils.isEmpty(modified_s)) {
			map.put("modified_s", modified_s);
		}
		if (!StringUtils.isEmpty(modified_e)) {
			map.put("modified_e", modified_e);
		}
		return ((KlgBuyTransactionDao) dao).findPageBySql(map);
	}

	@Override
	public List<KlgBuyTransactionVo> findBuyTransactionByIdINIds(List<Long> array) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", array);
		return ((KlgBuyTransactionDao) dao).findBuyTransactionByIdINIds(map);
	}

	@Override
	public void updateStatusByDate(Integer timeHour) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("timeHour", timeHour);
		((KlgBuyTransactionDao) dao).updateStatusByDate(map);
	}

	@Override
	public BigDecimal getDiscardFirstMoneySum(Integer timeHour) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("timeHour", timeHour);
		return ((KlgBuyTransactionDao) dao).getDiscardFirstMoneySum(map);
	}

	@Override
	public List<KlgBuyTransactionVo> findDiscardList(Integer timeHour) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("timeHour", timeHour);
		return ((KlgBuyTransactionDao) dao).findDiscardList(map);
	}

	@Override
	public List<KlgBuyTransactionVo> findDiscardListByDay(Integer marginDays) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("marginDays", marginDays);
		return ((KlgBuyTransactionDao) dao).findDiscardListByDay(map);
	}

	@Override
	public void updateInterestMoney(BigDecimal interestMoney, Long id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("interestMoney", interestMoney);
		map.put("id", id);
		((KlgBuyTransactionDao) dao).updateInterestMoney(map);
	}

	@Override
	public PageResult findPageGroupBydaySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		// ----------------------分页查询头部外壳------------------------------
		Page<KlgBuyTransactionVo> page = PageFactory.getPage(filter);
		// ----------------------分页查询头部外壳------------------------------
		((KlgBuyTransactionDao) dao).findBuyListGroupByDay();

		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public JsonResult jiedongSubmit(String ids) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		List<KlgBuyTransactionVo> listBuy = this.findBuyTransactionByIdINIds(ids, null);
		/** 查询平台币Code */
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		List<Accountadd> list = null;
		if (listBuy != null && listBuy.size() > 0) {
			BigDecimal interestMoney = new BigDecimal(0);
			for (KlgBuyTransactionVo klgBuyTransaction : listBuy) {
				if (klgBuyTransaction != null) {
					// 必须为已完成的订单,未解冻状态订单,利息大于0的订单
					if (klgBuyTransaction.getStatus() == 4 && klgBuyTransaction.getInterestStatus() == 1
							&& klgBuyTransaction.getInterestMoney().compareTo(new BigDecimal(0)) > 0) {
						list = new ArrayList<Accountadd>();
						//查询用户SME币账户信息，冻结减少  可用增加
						ExDigitalmoneyAccountRedis exaccount = this.selectAccount(klgBuyTransaction.getCustomerId(),
								ptbCoinCode);
						list.add(getAccountAdd(exaccount.getId(),
								new BigDecimal("-" + klgBuyTransaction.getInterestMoney()), 2, 1, 2,
								klgBuyTransaction.getTransactionNum()));
						list.add(getAccountAdd(exaccount.getId(),klgBuyTransaction.getInterestMoney(), 1, 1, 2,
								klgBuyTransaction.getTransactionNum()));
						
						//修改订单利息状态
						klgBuyTransaction.setInterestStatus(2);
						super.update(klgBuyTransaction);
						messageProducer.toAccount(JSON.toJSONString(list));

					}
				}
			}
		}

		return jsonResult.setSuccess(true).setMsg("解冻成功");
	}
	
	/**
	 * 查询币账户信息
	 * 
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	private ExDigitalmoneyAccountRedis selectAccount(Long customerId, String coinCode) {
		// 查redis缓存
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		// 获得币账户
		ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),
				coinCode);
		return exaccount;
	}

	/**
	 * 插入发送消息信息
	 * 
	 * @param accountId
	 *            币账户id
	 * @param Money
	 *            金额
	 * @param monteyType
	 *            //1热账户，2，冷账号
	 * @param acccountType
	 *            //0资金账号，1币账户
	 * @param remark
	 *            //备注 必须填 安照AccountRemarkEnum.java
	 * @param transactionNum
	 *            订单号
	 * @return
	 */
	private Accountadd getAccountAdd(Long accountId, BigDecimal Money, Integer monteyType, Integer acccountType,
			Integer remark, String transactionNum) {
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(accountId);
		accountadd.setMoney(Money);
		accountadd.setMonteyType(monteyType);
		accountadd.setAcccountType(acccountType);
		accountadd.setRemarks(remark);
		accountadd.setTransactionNum(transactionNum);
		return accountadd;
	}

	@Override
	public List<KlgBuyTransactionVo> getForecastSum() {
		// TODO Auto-generated method stub
		//查询排单间隔时长
		Integer lineUpTime = Integer.valueOf((String) remoteKlgService.getPlatformRule1sConfig("lineUpTime"));
		Date today = new Date();
	    Map<String,Object> map = new HashMap<>();
	    List<KlgBuyTransactionVo> list = new ArrayList<>();
	    KlgBuyTransactionVo klgBuyTransaction = null;
	    BigDecimal sum = new BigDecimal(0);
		for(int i=0;i<lineUpTime;i++){
			BigDecimal smeMoneySum = new BigDecimal(0);
			map.clear();
			map.put("dayp", lineUpTime-i);
			smeMoneySum = ((KlgBuyTransactionDao) dao).getForecastSum(map);
			klgBuyTransaction= new KlgBuyTransactionVo();
			Calendar c = Calendar.getInstance();
	        c.setTime(today);
	        c.add(Calendar.DAY_OF_MONTH, i);
	        Date day = c.getTime();
			klgBuyTransaction.setCreated(day);
			if(smeMoneySum==null){
				smeMoneySum= new BigDecimal(0);
			}
			
			
			
			if(i!=0){
				klgBuyTransaction.setSmeMoneySum(smeMoneySum.subtract(sum));
				klgBuyTransaction.setYesterdaySurplus(new BigDecimal(0));
				klgBuyTransaction.setTodaySum(new BigDecimal(0));
				sum = sum.add(klgBuyTransaction.getSmeMoneySum());
			}else{
				QueryFilter klgForecastFileter = new QueryFilter(KlgForecast.class);
				klgForecastFileter.setOrderby("created desc");
				KlgForecast klgForecast = klgForecastService.get(klgForecastFileter);
				klgBuyTransaction.setCreated(klgForecast.getForecastTime());
				klgBuyTransaction.setSmeMoneySum(klgForecast.getTodayForecast());
				klgBuyTransaction.setYesterdaySurplus(klgForecast.getYesterdaySurplus());
				klgBuyTransaction.setTodaySum(smeMoneySum);
				sum = sum.add(smeMoneySum);
			}
			
			list.add(klgBuyTransaction);
		}
		
		return list;
	}

	@Override
	public BigDecimal getForecastSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return ((KlgBuyTransactionDao) dao).getForecastSum(map);
	}

}
