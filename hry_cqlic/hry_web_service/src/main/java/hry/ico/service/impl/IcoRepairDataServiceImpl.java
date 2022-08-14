/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-01 20:14:58 
 */
package hry.ico.service.impl;

import hry.ico.model.IcoRepairData;
import hry.ico.service.IcoRepairDataService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoRepairDataService </p>
 * @author:         lzy
 * @Date :          2019-04-01 20:14:58  
 */
@Service("icoRepairDataService")
public class IcoRepairDataServiceImpl extends BaseServiceImpl<IcoRepairData, Long> implements IcoRepairDataService{
	
	@Resource(name="icoRepairDataDao")
	@Override
	public void setDao(BaseDao<IcoRepairData, Long> dao) {
		super.dao = dao;
	}
	

}
