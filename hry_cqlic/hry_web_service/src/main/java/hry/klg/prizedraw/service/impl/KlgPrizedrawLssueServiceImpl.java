/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:53:49 
 */
package hry.klg.prizedraw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.klg.prizedraw.dao.KlgPrizedrawLssueDao;
import hry.klg.prizedraw.model.KlgPrizedrawLssue;
import hry.klg.prizedraw.service.KlgPrizedrawLssueService;

/**
 * <p> KlgPrizedrawLssueService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:53:49  
 */
@Service("klgPrizedrawLssueService")
public class KlgPrizedrawLssueServiceImpl extends BaseServiceImpl<KlgPrizedrawLssue, Long> implements KlgPrizedrawLssueService{
	
	@Resource(name="klgPrizedrawLssueDao")
	@Override
	public void setDao(BaseDao<KlgPrizedrawLssue, Long> dao) {
		super.dao = dao;
	}

	@Override
	public KlgPrizedrawLssue getIssue() {
		// TODO Auto-generated method stub
		return ((KlgPrizedrawLssueDao)dao).getIssue();
	}
	

}
