/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:39:39 
 */
package hry.licqb.ecology.service.impl;

import hry.licqb.ecology.model.EcologPlate;
import hry.licqb.ecology.service.EcologPlateService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> EcologPlateService </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:39:39  
 */
@Service("ecologPlateService")
public class EcologPlateServiceImpl extends BaseServiceImpl<EcologPlate, Long> implements EcologPlateService{
	
	@Resource(name="ecologPlateDao")
	@Override
	public void setDao(BaseDao<EcologPlate, Long> dao) {
		super.dao = dao;
	}
	

}
