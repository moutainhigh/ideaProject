/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 11:12:16 
 */
package hry.cm.customerminer.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.cm.customerminer.dao.CmCustomerMinerProfitDao;
import hry.cm.customerminer.model.CmCustomerMiner;
import hry.cm.customerminer.model.CmCustomerMinerProfit;
import hry.cm.customerminer.service.CmCustomerMinerProfitService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

/**
 * <p> CmCustomerMinerProfitService </p>
 * @author:         yaozh
 * @Date :          2019-07-31 11:12:16  
 */
@Service("cmCustomerMinerProfitService")
public class CmCustomerMinerProfitServiceImpl extends BaseServiceImpl<CmCustomerMinerProfit, Long> implements CmCustomerMinerProfitService{
	
	@Resource(name="cmCustomerMinerProfitDao")
	@Override
	public void setDao(BaseDao<CmCustomerMinerProfit, Long> dao) {
		super.dao = dao;
	}

	@Override
	public void insertProit(CmCustomerMiner cmCustomerMiner, Integer status) {
		// TODO Auto-generated method stub
		CmCustomerMinerProfit cmCustomerMinerProfit = new CmCustomerMinerProfit();
		cmCustomerMinerProfit.setCustomerId(cmCustomerMiner.getCustomerId());
		cmCustomerMinerProfit.setCustomerMinerId(cmCustomerMiner.getId());
		cmCustomerMinerProfit.setMinerCode(cmCustomerMiner.getMinerCode());
		cmCustomerMinerProfit.setMinerName(cmCustomerMiner.getMinerName());
		cmCustomerMinerProfit.setMinerPrice(cmCustomerMiner.getMinerPrice());
		cmCustomerMinerProfit.setOrderId(cmCustomerMiner.getOrderId());
		cmCustomerMinerProfit.setProfit1(cmCustomerMiner.getDayProfit());
		cmCustomerMinerProfit.setProfit2(cmCustomerMiner.getProfit3());
		cmCustomerMinerProfit.setStatus(status);
		cmCustomerMinerProfit.setTransactionNum(cmCustomerMiner.getTransactionNum());
		super.save(cmCustomerMinerProfit);
	}

	@Override
	public BigDecimal getTeamProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerProfitDao)dao).getTeamProfitSumByCustomerId(customerId);
	}

	@Override
	public BigDecimal getProfitSumByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return ((CmCustomerMinerProfitDao)dao).getProfitSumByCustomerId(customerId);
	}
	

}
