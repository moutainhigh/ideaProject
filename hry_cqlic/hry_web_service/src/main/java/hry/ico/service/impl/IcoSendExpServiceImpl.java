/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-20 19:31:18 
 */
package hry.ico.service.impl;

import hry.ico.model.IcoSendExp;
import hry.ico.service.IcoSendExpService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> IcoSendExpService </p>
 * @author:         lzy
 * @Date :          2019-03-20 19:31:18  
 */
@Service("icoSendExpService")
public class IcoSendExpServiceImpl extends BaseServiceImpl<IcoSendExp, Long> implements IcoSendExpService{
	
	@Resource(name="icoSendExpDao")
	@Override
	public void setDao(BaseDao<IcoSendExp, Long> dao) {
		super.dao = dao;
	}
	

}
