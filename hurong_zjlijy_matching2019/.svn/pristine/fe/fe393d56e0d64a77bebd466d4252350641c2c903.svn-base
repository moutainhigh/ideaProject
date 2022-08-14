/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-30 10:08:52 
 */
package hry.cm.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.cm.order.dao.CmOrderDao;
import hry.cm.order.model.CmOrder;
import hry.cm.order.service.CmOrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

/**
 * <p> CmOrderService </p>
 * @author:         yaozh
 * @Date :          2019-07-30 10:08:52  
 */
@Service("cmOrderService")
public class CmOrderServiceImpl extends BaseServiceImpl<CmOrder, Long> implements CmOrderService{
	
	@Resource(name="cmOrderDao")
	@Override
	public void setDao(BaseDao<CmOrder, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Long> findCloseMinerCustomerId() {
		// TODO Auto-generated method stub
		return ((CmOrderDao)dao).findCloseMinerCustomerId();
	}

	@Override
	public List<Long> findWaitMinerCustomerId() {
		// TODO Auto-generated method stub
		return ((CmOrderDao)dao).findWaitMinerCustomerId();
	}

	@Override
	public void updateCloseOrder() {
		// TODO Auto-generated method stub
		((CmOrderDao)dao).updateCloseOrder();
	}

	@Override
	public void updateWaitOrder() {
		// TODO Auto-generated method stub
		((CmOrderDao)dao).updateWaitOrder();
	}
	

}
