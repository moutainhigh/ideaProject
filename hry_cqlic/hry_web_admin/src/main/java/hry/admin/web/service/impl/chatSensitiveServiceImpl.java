/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-29 11:30:50 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.chatSensitive;
import hry.admin.web.service.chatSensitiveService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> chatSensitiveService </p>
 * @author:         sunyujie
 * @Date :          2018-06-29 11:30:50  
 */
@Service("chatSensitiveService")
public class chatSensitiveServiceImpl extends BaseServiceImpl<chatSensitive, Long> implements chatSensitiveService{
	
	@Resource(name="chatSensitiveDao")
	@Override
	public void setDao(BaseDao<chatSensitive, Long> dao) {
		super.dao = dao;
	}
	

}
