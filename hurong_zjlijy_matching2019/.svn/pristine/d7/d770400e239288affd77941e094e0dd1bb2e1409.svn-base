/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:03:56 
 */
package hry.cm4.order.service.impl;

import hry.cm4.order.dao.Cm4OrderDao;
import hry.cm4.order.model.Cm4Order;
import hry.cm4.order.service.Cm4OrderService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> Cm4OrderService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:03:56  
 */
@Service("cm4OrderService")
public class Cm4OrderServiceImpl extends BaseServiceImpl<Cm4Order, Long> implements Cm4OrderService{
	
	@Resource(name="cm4OrderDao")
	@Override
	public void setDao(BaseDao<Cm4Order, Long> dao) {
		super.dao = dao;
	}
	@Override
	public List<Long> findCloseMinerCustomerId() {
		// TODO Auto-generated method stub
		return ((Cm4OrderDao)dao).findCloseMinerCustomerId();
	}

	@Override
	public List<Long> findWaitMinerCustomerId() {
		// TODO Auto-generated method stub
		return ((Cm4OrderDao)dao).findWaitMinerCustomerId();
	}

	@Override
	public void updateCloseOrder() {
		// TODO Auto-generated method stub
		((Cm4OrderDao)dao).updateCloseOrder();
	}

	@Override
	public void updateWaitOrder() {
		// TODO Auto-generated method stub
		((Cm4OrderDao)dao).updateWaitOrder();
	}


}
