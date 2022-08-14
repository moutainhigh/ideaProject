/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月5日 上午10:42:10
 */
package hry.calculate.mvc.service.impl;

import hry.calculate.mvc.dao.CalculateAppTransactionDao;
import hry.calculate.mvc.po.AppStatisticalToIndexVo;
import hry.calculate.mvc.po.CalculatePo;
import hry.calculate.mvc.po.CustromerRegisterPo;
import hry.calculate.mvc.po.PendingOrders;
import hry.calculate.mvc.service.CalculateAppTransactionService;
import hry.calculate.settlement.model.AppOrderDistributionVo;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;



/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月5日 上午10:42:10 
 */
@Service("calculateAppTransactionService")
public class CalculateAppTransactionServiceImpl implements
		CalculateAppTransactionService  {

	@Resource(name = "calculateAppTransactionDao")
	public CalculateAppTransactionDao calculateAppTransactionDao;
	
	/**
	 * status  表示审核状态( 1 表示待审核  ,2表示已审核 , 3表示已否决)
	 * style 表示充值 方式 (0 表示银行卡    1表示支付宝)
	 * transactionType 表示(1线上充值,2线上提现 3线下充值 4线下取现)
	 * 
	 */
	@Override
	public PendingOrders selectMoneyByFactor(String type) {
		
		PendingOrders pendingOrders ;
		
		if("get".equals(type)){
			pendingOrders = calculateAppTransactionDao.selectAppTransactionOfRmbGet();
		}else{
			pendingOrders = calculateAppTransactionDao.selectAppTransactionOfRmb();
		}
		
		return pendingOrders;
	}

	/**
	 * 查询第 i 天前所有产品的交易数据
	 * 
	 */
	@Override
	public List<CalculatePo> selectMoneyByOrder(Integer i) {
		
		List<CalculatePo> po = calculateAppTransactionDao.selectTransactionDayAgo(i);
		
		return po;
	}

	/**
	 * 
	 * dayAgo 从几天前开始到现在  
	 * isDelete 是否删除 (用户的状态)
	 * 
	 */
	@Override
	public CustromerRegisterPo selectCustromerByTime(Integer dayAgo,
			Integer isDelete) {
		
		CustromerRegisterPo po = calculateAppTransactionDao.selectCustromerNum(dayAgo, isDelete);
		
		return po;
	}

	
	/**
	 * 查询首页用户的分布图数据
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月12日 下午3:14:48
	 */
	@Override
	public AppStatisticalToIndexVo findUserDistribution() {
		AppStatisticalToIndexVo appStatisticalToIndexVo = calculateAppTransactionDao.findUserDistribution();
		return appStatisticalToIndexVo;
	}

	
	/**
	 * 查询成交数据的分布图
	 * 
	 */
	@Override
	public List<AppOrderDistributionVo> findOrderDistribution() {
		List<AppOrderDistributionVo> appOrderDistributionVo = calculateAppTransactionDao.findOrderDistribution();
		return appOrderDistributionVo;
	}

	/**
	 * 查询平台历史成交总额
	 * 
	 * @return
	 */
	@Override
	public Integer findTotalMoney(String website){
		Integer a = calculateAppTransactionDao.findTotalMoney(website);
		return a ;
	}

	
	
	
}
