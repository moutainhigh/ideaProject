/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-15 11:32:09 
 */
package hry.mall.retailstore.service.impl;

import javax.annotation.Resource;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.mall.retailstore.model.MemberLevel;
import hry.mall.retailstore.service.MemberLevelService;
import org.springframework.stereotype.Service;

/**
 * <p> MemberLevelService </p>
 * @author:         luyue
 * @Date :          2019-05-15 11:32:09  
 */
@Service("memberLevelService")
public class MemberLevelServiceImpl extends BaseServiceImpl<MemberLevel, Long> implements MemberLevelService {
	
	@Resource(name="memberLevelDao")
	@Override
	public void setDao(BaseDao<MemberLevel, Long> dao) {
		super.dao = dao;
	}
	

}
