/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-04-02 11:10:41 
 */
package hry.admin.licqb.record.service.impl;

import hry.admin.licqb.record.model.PlatformTotal;
import hry.admin.licqb.record.service.platformTotalService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> platformTotalService </p>
 * @author:         zhouming
 * @Date :          2020-04-02 11:10:41  
 */
@Service("platformTotalService")
public class PlatformTotalServiceImpl extends BaseServiceImpl<PlatformTotal, Long> implements platformTotalService{
	
	@Resource(name="platformTotalDao")
	@Override
	public void setDao(BaseDao<PlatformTotal, Long> dao) {
		super.dao = dao;
	}
	

}
