/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-01 12:19:24 
 */
package hry.mall.platform.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.platform.model.BlendPay;
import hry.mall.platform.service.BlendPayService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p> BlendPayService </p>
 * @author:         kongdebiao
 * @Date :          2018-12-01 12:19:24  
 */
@Service("blendPayService")
public class BlendPayServiceImpl extends BaseServiceImpl<BlendPay, Long> implements BlendPayService {
	
	@Resource(name="blendPayDao")
	@Override
	public void setDao(BaseDao<BlendPay, Long> dao) {
		super.dao = dao;
	}
	

}
