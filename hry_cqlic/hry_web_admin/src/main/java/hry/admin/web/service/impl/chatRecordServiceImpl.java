/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-28 16:29:34 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.chatRecord;
import hry.admin.web.service.chatRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> chatRecordService </p>
 * @author:         sunyujie
 * @Date :          2018-06-28 16:29:34  
 */
@Service("chatRecordService")
public class chatRecordServiceImpl extends BaseServiceImpl<chatRecord, Long> implements chatRecordService{
	
	@Resource(name="chatRecordDao")
	@Override
	public void setDao(BaseDao<chatRecord, Long> dao) {
		super.dao = dao;
	}
	

}
