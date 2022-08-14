/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:45 
 */
package hry.admin.commend.service.impl;

import hry.admin.commend.model.AppCommendRank;
import hry.admin.commend.service.AppCommendRankService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppCommendRankService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:49:45  
 */
@Service("appCommendRankService")
public class AppCommendRankServiceImpl extends BaseServiceImpl<AppCommendRank, Long> implements AppCommendRankService{
	
	@Resource(name="appCommendRankDao")
	@Override
	public void setDao(BaseDao<AppCommendRank, Long> dao) {
		super.dao = dao;
	}
	

}
