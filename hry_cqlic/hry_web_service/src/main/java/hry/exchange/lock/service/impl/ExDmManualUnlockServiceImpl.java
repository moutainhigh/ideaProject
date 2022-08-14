/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-05-03 09:47:26 
 */
package hry.exchange.lock.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.idgenerate.NumConstant;
import hry.exchange.lock.dao.ExDmManualUnlockDao;
import hry.exchange.lock.model.ExDmLock;
import hry.exchange.lock.model.vo.DigitalmoneyAccountAndTransaction;
import hry.exchange.lock.service.ExDmManualUnlockService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;

/**
 * <p> ExDmManualUnlockService </p>
 * @author:         liuchenghui
 * @Date :          2018-05-03 09:47:26  
 */
@Service("exDmManualUnlockService")
public class ExDmManualUnlockServiceImpl extends BaseServiceImpl<ExDmTransaction, Long> implements ExDmManualUnlockService{
	
	private static final Logger log = LoggerFactory.getLogger(ExDmManualUnlockServiceImpl.class);
	
	@Resource
	private MessageProducer messageProducer;
	
	@Resource(name="exDmManualUnlockDao")
	@Override
	public void setDao(BaseDao<ExDmTransaction, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// ----------------------分页查询头部外壳------------------------------
		// 创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<ExDmLock> page = null;
		if (filter.getPageSize().compareTo(Integer.valueOf(-1)) == 0) {
			// pageSize = -1 时
			// pageHelper传pageSize参数传0查询全部
			page = PageHelper.startPage(filter.getPage(), 0);
		} else {
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		// ----------------------分页查询头部外壳------------------------------
		
		// ----------------------查询开始------------------------------
		// 币种代码
		String coinCode = filter.getRequest().getParameter("coinCode");
		// 邮箱
		String email = filter.getRequest().getParameter("email");
		// 手机号
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		// 姓氏
		String surname = filter.getRequest().getParameter("surname");
		// 姓名
		String trueName = filter.getRequest().getParameter("trueName");

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
		// 姓氏
		if (!StringUtils.isEmpty(surname)) {
			map.put("surname", "%" + surname + "%");
		}
		// 名字
		if (!StringUtils.isEmpty(trueName)) {
			map.put("trueName", "%" + trueName + "%");
		}
		
		List<DigitalmoneyAccountAndTransaction> resultlist = ((ExDmManualUnlockDao) dao).findPageByCondition(map);
		if (resultlist != null && resultlist.size() > 0) {
			for (DigitalmoneyAccountAndTransaction digitalmoneyAccountAndTransaction : resultlist) {
				BigDecimal accountBalance = digitalmoneyAccountAndTransaction.getHotMoney().add(digitalmoneyAccountAndTransaction.getColdMoney());
				digitalmoneyAccountAndTransaction.setAccountBalance(accountBalance);
			}
		}
		
		
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
	public void updateByAccountId(Long accountId, Long customerId) {
		// 根据账户id和客户id查询记录
		QueryFilter filter = new QueryFilter(ExDmTransaction.class);
		filter.addFilter("accountId=", accountId);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("optType=", "10");
		filter.addFilter("status=", "1");
		List<ExDmTransaction> list = super.find(filter);
		String coldTransactionNum = NumConstant.Ex_Dm_Transaction;
		if(list != null && list.size() > 0) {
			for (ExDmTransaction exDmTransaction : list) {
				// 修改锁仓记录流水状态
				exDmTransaction.setStatus(3);
				this.update(exDmTransaction);
				//将账户冻结个数返回给热账户中
				BigDecimal transactionMoney = exDmTransaction.getTransactionMoney();
				if(transactionMoney != null) {
					// 热账户增加
					Accountadd accountaddhot = new Accountadd();
					accountaddhot.setAccountId(accountId);
					accountaddhot.setMoney(transactionMoney);
					accountaddhot.setMonteyType(1);
					accountaddhot.setAcccountType(1);
					accountaddhot.setRemarks(56);
					accountaddhot.setTransactionNum(coldTransactionNum);
					
					// 冷账户减少
					Accountadd accountaddcold = new Accountadd();
					accountaddcold.setAccountId(accountId);
					accountaddcold.setMoney(transactionMoney.multiply(new BigDecimal(-1)));
					accountaddcold.setMonteyType(2);
					accountaddcold.setAcccountType(1);
					accountaddcold.setRemarks(56);
					accountaddcold.setTransactionNum(coldTransactionNum);
					
					List<Accountadd> listLock = new ArrayList<Accountadd>();
					listLock.add(accountaddhot);
					listLock.add(accountaddcold);
					messageProducer.toAccount(JSON.toJSONString(listLock));
				}
			}
		}
	}

	@Override
	public void timingUnlockAccountCold() {
		// 查询今天到期的锁仓记录
		log.info("查询今天到期的锁仓记录----------");
		List<ExDmLock> lockList = ((ExDmManualUnlockDao)dao).selectLockRecordByTime();
		if (lockList != null && lockList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				for (ExDmLock exDmLock : lockList) {
					String coinCode = exDmLock.getCoinCode();
					map.put("coinCode", coinCode);
					// 根据币种查询账户冻结信息
					log.info("根据币种查询账户冻结信息----------");
					List<DigitalmoneyAccountAndTransaction> resultlist = ((ExDmManualUnlockDao) dao).findPageByCondition(map);
					if (resultlist != null && resultlist.size() > 0) {
						for (DigitalmoneyAccountAndTransaction daat : resultlist) {
							// 修改流水记录
							// 发送账户变更消息
							log.info("修改流水记录,发送账户变更消息----------");
							updateByAccountId(new Long(daat.getId()), new Long (daat.getCustomerId()));
							log.warn("定时锁仓记录成功,accountId="+daat.getId());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("定时解锁仓记录异常:"+e.getMessage());
			}
		}
	}
	

}
