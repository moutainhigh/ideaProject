/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:55:39 
 */
package hry.social.message.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.message.AppWorkOrder;
import hry.social.message.service.AppWorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppWorkOrderService </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:55:39  
 */
@Service("appWorkOrderService")
public class AppWorkOrderServiceImpl extends BaseServiceImpl<AppWorkOrder, Long> implements AppWorkOrderService {
	
	@Resource(name="appWorkOrderDao")
	@Override
	public void setDao(BaseDao<AppWorkOrder, Long> dao) {
		super.dao = dao;
	}
}
