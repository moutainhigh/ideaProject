/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:12:12 
 */
package hry.cm5.customer.service.impl;

import hry.cm5.customer.dao.Cm5CustomerLevelDao;
import hry.cm5.customer.model.Cm5CustomerLevel;
import hry.cm5.customer.service.Cm5CustomerLevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Cm5CustomerLevelService </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:12:12  
 */
@Service("cm5CustomerLevelService")
public class Cm5CustomerLevelServiceImpl extends BaseServiceImpl<Cm5CustomerLevel, Long> implements Cm5CustomerLevelService{
	
	@Resource(name="cm5CustomerLevelDao")
	@Override
	public void setDao(BaseDao<Cm5CustomerLevel, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String getCustomerAddRatio(Long customerId) {
		return ((Cm5CustomerLevelDao)dao).getCustomerAddRatio(customerId);
	}

	@Override
	public List<Cm5CustomerLevel> findNextCustomerLevel(Long customerId) {
		return ((Cm5CustomerLevelDao)dao).findNextCustomerLevel(customerId);
	}
}
