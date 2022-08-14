/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-08 17:02:30 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.ExErc20;
import hry.admin.exchange.service.ExErc20Service;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ExErc20Service </p>
 * @author:         tianpengyu
 * @Date :          2018-08-08 17:02:30  
 */
@Service("exErc20Service")
public class ExErc20ServiceImpl extends BaseServiceImpl<ExErc20, Long> implements ExErc20Service{
	
	@Resource(name="exErc20Dao")
	@Override
	public void setDao(BaseDao<ExErc20, Long> dao) {
		super.dao = dao;
	}
	

}
