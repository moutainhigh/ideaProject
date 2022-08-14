/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.entrust.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.entrust.service.ExExOrderService;
import hry.trade.entrust.model.ExOrder;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exExOrderService")
public class ExExOrderServiceImpl extends BaseServiceImpl<ExOrder, Long>
		implements ExExOrderService {

	@Resource(name = "exExOrderDao")
	@Override
	public void setDao(BaseDao<ExOrder, Long> dao) {
		super.dao = dao;
	}

}
