/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-23 16:57:20 
 */
package hry.admin.licqb.record.service.impl;

import hry.admin.licqb.record.model.ReleaseRuleDetails;
import hry.admin.licqb.record.service.ReleaseRuleDetailsService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ReleaseRuleDetailsService </p>
 * @author:         zhouming
 * @Date :          2019-08-23 16:57:20  
 */
@Service("releaseRuleDetailsService")
public class ReleaseRuleDetailsServiceImpl extends BaseServiceImpl<ReleaseRuleDetails, Long> implements ReleaseRuleDetailsService{
	
	@Resource(name="releaseRuleDetailsDao")
	@Override
	public void setDao(BaseDao<ReleaseRuleDetails, Long> dao) {
		super.dao = dao;
	}
	

}
