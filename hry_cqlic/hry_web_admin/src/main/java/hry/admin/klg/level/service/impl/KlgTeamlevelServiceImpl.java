/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-11 17:31:15 
 */
package hry.admin.klg.level.service.impl;

import hry.admin.klg.level.model.KlgTeamlevel;
import hry.admin.klg.level.service.KlgTeamlevelService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> KlgTeamlevelService </p>
 * @author:         lzy
 * @Date :          2019-04-11 17:31:15  
 */
@Service("klgTeamlevelService")
public class KlgTeamlevelServiceImpl extends BaseServiceImpl<KlgTeamlevel, Long> implements KlgTeamlevelService{
	
	@Resource(name="klgTeamlevelDao")
	@Override
	public void setDao(BaseDao<KlgTeamlevel, Long> dao) {
		super.dao = dao;
	}
	

}
