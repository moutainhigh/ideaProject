/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 17:20:52 
 */
package hry.cm2.order.service.impl;

import hry.cm2.order.dao.Cm2OrderDao;
import hry.cm2.order.model.Cm2Order;
import hry.cm2.order.service.Cm2OrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Cm2OrderService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 17:20:52  
 */
@Service("cm2OrderService")
public class Cm2OrderServiceImpl extends BaseServiceImpl<Cm2Order, Long> implements Cm2OrderService{
	
	@Resource(name="cm2OrderDao")
	@Override
	public void setDao(BaseDao<Cm2Order, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Long> findCloseMinerCustomerId() {
		// TODO Auto-generated method stub
		return ((Cm2OrderDao)dao).findCloseMinerCustomerId();
	}

	@Override
	public List<Long> findWaitMinerCustomerId() {
		// TODO Auto-generated method stub
		return ((Cm2OrderDao)dao).findWaitMinerCustomerId();
	}

	@Override
	public void updateCloseOrder() {
		// TODO Auto-generated method stub
		((Cm2OrderDao)dao).updateCloseOrder();
	}

	@Override
	public void updateWaitOrder() {
		// TODO Auto-generated method stub
		((Cm2OrderDao)dao).updateWaitOrder();
	}

}
