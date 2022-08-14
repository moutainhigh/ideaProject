/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:32:39 
 */
package hry.admin.klg.prizedraw.service.impl;

import hry.admin.klg.buysellaccount.model.KlgBuySellAccountRecord;
import hry.admin.klg.prizedraw.model.KlPrizedrawIssue;
import hry.admin.klg.prizedraw.service.KlPrizedrawIssueService;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;

/**
 * <p> KlPrizedrawIssueService </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:32:39  
 */
@Service("klPrizedrawIssueService")
public class KlPrizedrawIssueServiceImpl extends BaseServiceImpl<KlPrizedrawIssue, Long> implements KlPrizedrawIssueService{
	
	@Resource(name="klPrizedrawIssueDao")
	@Override
	public void setDao(BaseDao<KlPrizedrawIssue, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		Page<KlgSellTransactionVo> page = PageFactory.getPage(filter);
		
		String issueNumber = filter.getRequest().getParameter("issueNumber");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		String modified_sm = filter.getRequest().getParameter("created_GTM");
		String modified_em = filter.getRequest().getParameter("created_LTM");
		QueryFilter queryFilter = new QueryFilter(KlPrizedrawIssue.class);
		if(!StringUtils.isEmpty(issueNumber)){
			queryFilter.addFilter("issueNumber=", issueNumber);
		}
		if(!StringUtils.isEmpty(modified_e)){
			queryFilter.addFilter("created<=", modified_e);
		}
		if(!StringUtils.isEmpty(modified_s)){
			queryFilter.addFilter("created>=", modified_s);
		}
		if(!StringUtils.isEmpty(modified_em)){
			queryFilter.addFilter("startDate<=", modified_em);
		}
		if(!StringUtils.isEmpty(modified_sm)){
			queryFilter.addFilter("startDate>=", modified_sm);
		}
		queryFilter.setOrderby("startDate desc");
		super.find(queryFilter);
		
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
	

}
