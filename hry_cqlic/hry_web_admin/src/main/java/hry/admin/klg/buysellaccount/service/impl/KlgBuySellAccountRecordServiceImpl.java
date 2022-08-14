/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-22 17:02:55 
 */
package hry.admin.klg.buysellaccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;

import hry.admin.klg.buysellaccount.model.KlgBuySellAccountRecord;
import hry.admin.klg.buysellaccount.service.KlgBuySellAccountRecordService;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;

/**
 * <p> KlgBuySellAccountRecordService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-22 17:02:55  
 */
@Service("klgBuySellAccountRecordService")
public class KlgBuySellAccountRecordServiceImpl extends BaseServiceImpl<KlgBuySellAccountRecord, Long> implements KlgBuySellAccountRecordService{
	
	@Resource(name="klgBuySellAccountRecordDao")
	@Override
	public void setDao(BaseDao<KlgBuySellAccountRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		// TODO Auto-generated method stub
		Page<KlgSellTransactionVo> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		String accountName = filter.getRequest().getParameter("accountName");
		String orderNum = filter.getRequest().getParameter("orderNum");
		String triggered = filter.getRequest().getParameter("triggered");
		String modified_s = filter.getRequest().getParameter("created_GT");
		String modified_e = filter.getRequest().getParameter("created_LT");
		QueryFilter queryFilter = new QueryFilter(KlgBuySellAccountRecord.class);
		if(!StringUtils.isEmpty(accountName)){
			queryFilter.addFilter("accountName=", accountName);
		}
		if(!StringUtils.isEmpty(orderNum)){
			queryFilter.addFilter("orderNum_like", "%"+orderNum+"%");
		}
		if(!StringUtils.isEmpty(triggered)){
			queryFilter.addFilter("triggered_like", "%"+triggered+"%");
		}
		if(!StringUtils.isEmpty(modified_e)){
			queryFilter.addFilter("created<=", modified_e);
		}
		if(!StringUtils.isEmpty(modified_s)){
			queryFilter.addFilter("created>=", modified_s);
		}
		
		super.find(queryFilter);
		
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
	

}
