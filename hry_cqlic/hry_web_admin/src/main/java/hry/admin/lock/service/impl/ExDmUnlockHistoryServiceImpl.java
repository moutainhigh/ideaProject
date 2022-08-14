/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-09-20 17:34:28 
 */
package hry.admin.lock.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.admin.lock.dao.ExDmUnlockHistoryDao;
import hry.admin.lock.model.ExDmLockRecord;
import hry.admin.lock.model.ExDmLockReleaseTime;
import hry.admin.lock.model.ExDmUnlockHistory;
import hry.admin.lock.service.ExDmLockRecordService;
import hry.admin.lock.service.ExDmLockReleaseTimeService;
import hry.admin.lock.service.ExDmUnlockHistoryService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> ExDmUnlockHistoryService </p>
 * @author:         liuchenghui
 * @Date :          2018-09-20 17:34:28  
 */
@Service("exDmUnlockHistoryService")
public class ExDmUnlockHistoryServiceImpl extends BaseServiceImpl<ExDmUnlockHistory, Long> implements ExDmUnlockHistoryService{

	@Resource
	private ExDmLockRecordService exDmLockRecordService;

	@Resource
	private MessageProducer messageProducer;

	@Resource
	private RedisService redisService;

	@Resource
	private ExDmLockReleaseTimeService exDmLockReleaseTimeService;


	@Resource(name="exDmUnlockHistoryDao")
	@Override
	public void setDao(BaseDao<ExDmUnlockHistory, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql (QueryFilter filter) {
		//创建PageResult对象
		Page<ExDmUnlockHistory> page = PageFactory.getPage(filter);

		// ----------------------查询开始------------------------------
		// 币种代码
		String coinCode = filter.getRequest().getParameter("coinCode");
		// 邮箱
		String email = filter.getRequest().getParameter("email");
		// 手机号
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");

		Map<String, Object> map = new HashMap<String, Object>();
		// 币种代码
		if (!StringUtils.isEmpty(coinCode)) {
			if (!coinCode.equals("all")) {//如果不等于all(全部)
				map.put("coinCode", coinCode);
			}
		}
		// 邮箱
		if (!StringUtils.isEmpty(email)) {
			map.put("email", "%" + email + "%");
		}
		// 手机号
		if (!StringUtils.isEmpty(mobilePhone)) {
			map.put("mobilePhone", "%" + mobilePhone + "%");
		}

		((ExDmUnlockHistoryDao) dao).findPageByCondition(map);

		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	/**
	 * 定时释放锁仓操作
	 */
	@Override
	public synchronized void timingUnlockAccountCold () {
		// 将定时器的信息存储到redis中
		String str = "manualUnlock#success#" + DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		redisService.saveMap("Unlock:Exception", "coinUnlock", str);
		System.out.println("*******锁仓释放开始*******");
		try {
			// 查询今天到期的锁仓释放记录
			List<ExDmLockReleaseTime> releaseTimeList = exDmLockReleaseTimeService.getReleaseTime(null);
			if (releaseTimeList != null && releaseTimeList.size() > 0) {
				for (ExDmLockReleaseTime time : releaseTimeList) {
					if (time == null) {
						break;
					}
					// 大于500条分页处理
					if(releaseTimeList.size() > 500){
						Integer totalCount = releaseTimeList.size();
						Integer requestCount = totalCount / 500;
						for (int i = 0; i <= requestCount; i++) {
							Integer fromIndex = i * 500;
							int toIndex = Math.min(totalCount, (i + 1) * 500);
							List<ExDmLockReleaseTime> subList = releaseTimeList.subList(fromIndex, toIndex);
							for (ExDmLockReleaseTime timer : subList) {
								// 调用锁仓释放操作
								timerRelease(timer);
							}
							if (toIndex == totalCount) {
								break;
							}
						}
					} else {
						// 调用锁仓释放操作
						timerRelease(time);
					}
				}
			}
			System.out.println("******今日释放"+releaseTimeList.size()+"条数据******");
			System.out.println("*******锁仓释放结束*******");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 锁仓释放功能
	 * @param releaseTime
	 */
	private synchronized void timerRelease(ExDmLockReleaseTime releaseTime) {
		try {
			// 获取对应的锁仓记录
			ExDmLockRecord record =  exDmLockRecordService.get(releaseTime.getRecordId());
			// 获取释放量
			BigDecimal releaseVal = releaseTime.getReleaseVal();
			// 计算释放前剩余释放量
			BigDecimal remainVal = record.getRemainingRelease();
			// 如果释放前剩余量为0或小于0 则忽略
			if (remainVal.compareTo(BigDecimal.ZERO) == 1) {
				// 计算释放后剩余释放量 = 释放前 - 释放
				BigDecimal reVal = remainVal.subtract(releaseVal);
				// 释放后剩余量小于0
				if (reVal.compareTo(BigDecimal.ZERO) == -1) {
					releaseVal = remainVal;
					reVal = BigDecimal.ZERO;
				}

				// 修改记录
				record.setUnlockState(2);
				record.setAmountReleased(releaseVal.add(record.getAmountReleased()));
				record.setRemainingRelease(reVal);
				// 剩余冻结数量等于0时，修改锁仓状态为已完成
				if (reVal.compareTo(BigDecimal.ZERO) == 0) {
					record.setUnlockState(3);
				}
				record.setModified(new Date());
				// 更新记录
				exDmLockRecordService.update(record);

				// 更新释放时间记录状态
				exDmLockReleaseTimeService.updReleaseTimeForAuto(record.getId());

				// 写入释放记录
				String curLoginUser = PropertiesUtils.APP.getProperty("app.admin");
				ExDmUnlockHistory history = new ExDmUnlockHistory();
				history.setAccountId(record.getAccountId());
				history.setCoinCode(record.getCoinCode());
				history.setCustomerId(record.getCustomerId());
				// 1、自动释放 2、手动释放
				history.setOptType(1);
				history.setRecordId(record.getId());
				history.setTransactionMoney(releaseVal);
				history.setTransactionNum(record.getTransactionNum());
				history.setCreated(new Date());
				history.setModified(new Date());
				history.setOptUser(curLoginUser);
				this.save(history);

				// 发送mq消息进行账户增减
				String unlockTransactionNum = NumConstant.Ex_Dm_Transaction;
				// 热账户增加
				Accountadd accountaddhot = new Accountadd();
				accountaddhot.setAccountId(record.getAccountId());
				accountaddhot.setMoney(releaseVal);
				accountaddhot.setMonteyType(1);
				accountaddhot.setAcccountType(1);
				accountaddhot.setRemarks(56);
				accountaddhot.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));

				// 冷账户减少
				Accountadd accountaddcold = new Accountadd();
				accountaddcold.setAccountId(record.getAccountId());
				accountaddcold.setMoney(releaseVal.multiply(new BigDecimal(-1)));
				accountaddcold.setMonteyType(2);
				accountaddcold.setAcccountType(1);
				accountaddcold.setRemarks(56);
				accountaddcold.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));

				List<Accountadd> listLock = new ArrayList<Accountadd>();
				listLock.add(accountaddhot);
				listLock.add(accountaddcold);
				messageProducer.toAccount(JSON.toJSONString(listLock));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}