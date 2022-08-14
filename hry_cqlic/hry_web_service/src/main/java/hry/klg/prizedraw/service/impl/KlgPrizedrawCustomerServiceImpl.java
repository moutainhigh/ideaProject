/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:53:18 
 */
package hry.klg.prizedraw.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.klg.prizedraw.dao.KlgPrizedrawCustomerDao;
import hry.klg.prizedraw.model.KlgPrizedrawCustomer;
import hry.klg.prizedraw.service.KlgPrizedrawCustomerService;

/**
 * <p> KlgPrizedrawCustomerService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:53:18  
 */
@Service("klgPrizedrawCustomerService")
public class KlgPrizedrawCustomerServiceImpl extends BaseServiceImpl<KlgPrizedrawCustomer, Long> implements KlgPrizedrawCustomerService{
	
	@Resource(name="klgPrizedrawCustomerDao")
	@Override
	public void setDao(BaseDao<KlgPrizedrawCustomer, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<KlgPrizedrawCustomer> findPageBySql(Map<String, String> map) {
		// TODO Auto-generated method stub
		return ((KlgPrizedrawCustomerDao)dao).findPageBySql(map);
	}

	@Override
	public int getCustomerDrawCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return ((KlgPrizedrawCustomerDao)dao).getCustomerDrawCount(map);
	}

	@Override
	public int getCustomerIssueDrawCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return ((KlgPrizedrawCustomerDao)dao).getCustomerIssueDrawCount(map);
	}

	@Override
	public int getCustomerBuyOrderCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		return ((KlgPrizedrawCustomerDao)dao).getCustomerBuyOrderCount(map);
	}
	

}
