/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:38:22 
 */
package hry.admin.licqb.ecology.service.impl;

import hry.admin.licqb.ecology.model.EcologPay;
import hry.admin.licqb.ecology.service.EcologPayService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> EcologPayService </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:38:22  
 */
@Service("ecologPayService")
public class EcologPayServiceImpl extends BaseServiceImpl<EcologPay, Long> implements EcologPayService{
	
	@Resource(name="ecologPayDao")
	@Override
	public void setDao(BaseDao<EcologPay, Long> dao) {
		super.dao = dao;
	}
	

}
