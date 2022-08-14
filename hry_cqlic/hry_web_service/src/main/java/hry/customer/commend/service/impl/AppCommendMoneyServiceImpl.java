/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: menwei
 * @version: V1.0
 * @Date: 2017-11-29 10:05:55
 */
package hry.customer.commend.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.commend.dao.AppCommendMoneyDao;
import hry.customer.commend.dao.AppCommendTradeDao;
import hry.customer.commend.model.AppCommendMoney;
import hry.customer.commend.model.AppCommendRebat;
import hry.customer.commend.model.AppCommendTrade;
import hry.customer.commend.service.AppCommendMoneyService;
import hry.customer.commend.service.AppCommendRebatService;
import hry.customer.commend.service.AppCommendTradeService;
import hry.customer.person.model.AppPersonInfo;
import hry.front.redis.model.UserRedis;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.sys.ContextUtil;
import hry.web.remote.RemoteAppConfigService;
import hry.web.util.TransactionNumber;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * AppCommendMoneyService
 * </p>
 * 
 * @author: menwei
 * @Date : 2017-11-29 10:05:55
 */
@Service("appCommendMoneyService")
public class AppCommendMoneyServiceImpl extends
		BaseServiceImpl<AppCommendMoney, Long> implements
		AppCommendMoneyService {

	@Resource(name = "appCommendMoneyDao")
	@Override
	public void setDao(BaseDao<AppCommendMoney, Long> dao) {
		super.dao = dao;
	}

	private static final Logger log = LoggerFactory
			.getLogger(AppCommendMoneyServiceImpl.class);
	@Resource
	private MessageProducer messageProducer;

	@Resource(name = "appCommendTradeService")
	public AppCommendTradeService appCommendTradeService;

	@Resource(name = "appCommendMoneyService")
	public AppCommendMoneyService appCommendMoneyService;

	@Resource
	private AppCommendRebatService appCommendRebatService;
	@Resource(name = "appCommendTradeDao")
	public AppCommendTradeDao appCommendTradeDao;
	@Resource(name = "appCommendMoneyDao")
	public AppCommendMoneyDao appCommendMoneyDao;

	@Override
	public List<AppCommendMoney> selectCommend() {
		// TODO Auto-generated method stub
		/*
		 * QueryFilter filter = new QueryFilter(AppCodeMirror.class,request);
		 * filter.addFilter("Id=", id); List<AppCodeMirror> find =
		 * service.find(filter);
		 */

		return null;
	}

	@Override
	public BigDecimal findOne(String userName, String fixPriceCoinCode) {
		// TODO Auto-generated method stub
		BigDecimal findOne = appCommendTradeService.findOne(userName,
				fixPriceCoinCode);
		return findOne;
	}

	@Override
	public BigDecimal findTwo(String userName, String fixPriceCoinCode) {
		// TODO Auto-generated method stub
		BigDecimal findTwo = appCommendTradeService.findTwo(userName,
				fixPriceCoinCode);
		return findTwo;
	}

	@Override
	public BigDecimal findThree(String userName, String fixPriceCoinCode) {
		// TODO Auto-generated method stub
		BigDecimal findThree = appCommendTradeService.findThree(userName,
				fixPriceCoinCode);
		return findThree;
	}

	@Override
	public BigDecimal findLater(String userName, String fixPriceCoinCode) {
		// TODO Auto-generated method stub
		BigDecimal findLater = appCommendTradeService.findLater(userName,
				fixPriceCoinCode);
		return findLater;
	}

	public JsonResult postMoneyById(Long id, BigDecimal money) {
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		QueryFilter AppCommendMoney = new QueryFilter(AppCommendMoney.class);
		AppCommendMoney.addFilter("id=", id);
		AppCommendMoney appCommendMoney = appCommendMoneyService
				.get(AppCommendMoney);

		if (null != appCommendMoney) {
			appCommendMoney.setPaidMoney(appCommendMoney.getPaidMoney().add(
					money));
			appCommendMoney.setLastPaidTime(new Date());

			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(
					UserRedis.class);
			UserRedis userRedis = redisUtil.get(appCommendMoney
					.getCustromerId().toString());
			if (null == userRedis) {
				result.setMsg("此用户的缓存账号为空，登陆会生成缓存账号");
				result.setSuccess(false);
				return result;
			}
			super.update(appCommendMoney);

			// 存明细
			AppCommendRebat appCommendRebat = new AppCommendRebat();
			try {
				appCommendRebat.setCoinCode(appCommendMoney.getCoinCode());
				appCommendRebat.setRebatMoney(money);
				// appCommendRebat.setTrueName(appCommendMoney.getCustromerName());
				appCommendRebat.setCustomerId(appCommendMoney.getCustromerId()
						.intValue());
				appCommendRebat.setTransactionNum(IdGenerate
						.transactionNum(TransactionNumber.APP_COMMOD_REBAT));
				appCommendRebat.setUserCode(appCommendMoney.getUserCode());
				String coin = getCnfigValue("language_code");
				if (appCommendMoney.getCoinCode().equals(coin)) {
					// ----发送mq消息----start
					// 热账户增加
					Accountadd accountadd = new Accountadd();
					Long accountId = userRedis.getAccountId();
					accountadd.setAccountId(accountId);
					accountadd.setMoney(money);
					accountadd.setMonteyType(1);
					accountadd.setRemarks(32);
					accountadd.setAcccountType(0);
					accountadd.setTransactionNum(appCommendRebat
							.getTransactionNum());

					List<Accountadd> list2 = new ArrayList<Accountadd>();
					list2.add(accountadd);
					messageProducer.toAccount(JSON.toJSONString(list2));
				} else {
					// ----发送mq消息----start
					// 热账户增加

					Accountadd accountadd = new Accountadd();
					Long accountId = userRedis.getDmAccountId(appCommendMoney
							.getCoinCode());
					accountadd.setAccountId(accountId);
					accountadd.setMoney(money);
					accountadd.setMonteyType(1);
					accountadd.setRemarks(32);
					accountadd.setAcccountType(1);
					accountadd.setTransactionNum(appCommendRebat
							.getTransactionNum());

					List<Accountadd> list3 = new ArrayList<Accountadd>();
					list3.add(accountadd);
					messageProducer.toAccount(JSON.toJSONString(list3));
				}
				appCommendRebat.setStatus(1);
				result.setMsg("派送成功");
				result.setSuccess(true);
			} catch (Exception e) {
				appCommendRebat.setStatus(0);
				result.setMsg("派送失败");
				log.error("消息发送失败：{}", appCommendMoney.getCustromerName(), e);
			}
			appCommendRebatService.save(appCommendRebat);
		}
		return result;
	}

	public static String transactionNum(String bussType) {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		String time = format.format(new Date(System.currentTimeMillis()));
		String randomStr = RandomStringUtils.random(4, false, true);
		if (null == bussType) {
			bussType = "00";
		}
		return bussType + time + randomStr;
	}

	public static String getCnfigValue(String type) {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil
				.getBean("remoteAppConfigService");
		return remoteAppConfigService.getValueByKey(type);
	}

	@Override
	public PageResult findPageBySqlList(String email, String mobilePhone,
			int startpage, int lengthpage) {

		// ----------------------分页查询头部外壳------------------------------
		// 创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		// ----------------------查询开始------------------------------

		Map<String, Object> map = new HashMap<String, Object>();
		Integer start = startpage * lengthpage;
		Integer end = (startpage + 1) * lengthpage;
		map.put("start", start);
		map.put("end", end);

		Map<String, Object> mapcustomer = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(email)) {

			mapcustomer.put("email", email);
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			mapcustomer.put("mobilePhone", mobilePhone);
		}
		if (mapcustomer.size() > 0) {
			List<AppPersonInfo> listpersoninfo = appCommendTradeDao
					.findCustomerIdByemailAndphone(mapcustomer);
			if (listpersoninfo.size() > 0) {
				map.put("cusomerId", listpersoninfo.get(0).getCustomerId());
			} else {
				List<AppCommendTrade> list = new ArrayList<AppCommendTrade>();
				// 设置分页数据
				pageResult.setRows(list);
				// 设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;

			}

		}

		Long count = appCommendMoneyDao.findPageBySqlCount(map);
		List<AppCommendTrade> list = appCommendMoneyDao.findPageBySqlList(map);
		// ----------------------查询结束------------------------------

		// ----------------------分页查询底部外壳------------------------------
		// 设置分页数据
		pageResult.setRows(list);
		// 设置总记录数
		pageResult.setRecordsTotal(count);

		// ----------------------分页查询底部外壳------------------------------

		return pageResult;

	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// ----------------------分页查询头部外壳------------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		// 创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCommendMoney> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// ----------------------分页查询头部外壳------------------------------

		// ----------------------查询开始------------------------------
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");

		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}
		List<AppCommendMoney> pageBySql = appCommendMoneyDao.findPageBySql(map);
		RedisService redisService = (RedisService) ContextUtil
				.getBean("redisService");

		for (AppCommendMoney am : pageBySql) {
			String retentionNumber = redisService.get("retentionNumber:"
					+ am.getCoinCode());
			am.setRetentionNumber(retentionNumber);
		}
		// ----------------------查询结束------------------------------

		// ----------------------分页查询底部外壳------------------------------
		// 设置分页数据
		pageResult.setRows(pageBySql);
		// 设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		// ----------------------分页查询底部外壳------------------------------
		return pageResult;
	}

}
