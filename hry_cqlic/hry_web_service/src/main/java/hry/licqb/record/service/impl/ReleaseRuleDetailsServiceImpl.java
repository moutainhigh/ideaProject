/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-22 10:39:08 
 */
package hry.licqb.record.service.impl;

import hry.licqb.record.dao.ReleaseRuleDetailsDao;
import hry.licqb.record.model.ReleaseRuleDetails;
import hry.licqb.record.service.ReleaseRuleDetailsService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> ReleaseRuleDetailsService </p>
 * @author:         zhouming
 * @Date :          2019-08-22 10:39:08  
 */
@Service("releaseRuleDetailsService")
public class ReleaseRuleDetailsServiceImpl extends BaseServiceImpl<ReleaseRuleDetails, Long> implements ReleaseRuleDetailsService{
	
	@Resource(name="releaseRuleDetailsDao")
	@Override
	public void setDao(BaseDao<ReleaseRuleDetails, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<ReleaseRuleDetails> findReleaseRuleDetailsList() {
		return ((ReleaseRuleDetailsDao)dao).findReleaseRuleDetailsList();
	}
}
