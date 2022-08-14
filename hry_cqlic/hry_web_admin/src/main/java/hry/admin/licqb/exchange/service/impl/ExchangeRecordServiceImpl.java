/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:36:32 
 */
package hry.admin.licqb.exchange.service.impl;

import com.github.pagehelper.Page;
import hry.admin.licqb.exchange.dao.ExchangeRecordDao;
import hry.admin.licqb.exchange.model.ExchangeRecord;
import hry.admin.licqb.exchange.service.ExchangeRecordService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExchangeRecordService </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:36:32  
 */
@Service("exchangeRecordService")
public class ExchangeRecordServiceImpl extends BaseServiceImpl<ExchangeRecord, Long> implements ExchangeRecordService{
	
	@Resource(name="exchangeRecordDao")
	@Override
	public void setDao(BaseDao<ExchangeRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		Page<ExchangeRecord> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<String,Object>();
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String itemName = filter.getRequest().getParameter("itemName");
		String exchangeSn = filter.getRequest().getParameter("exchangeSn");
		String gainCoinCode = filter.getRequest().getParameter("gainCoinCode");
		if(!StringUtils.isEmpty(email)){
			map.put("email", email);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", mobilePhone);
		}
		if(!StringUtils.isEmpty(itemName)){
			map.put("itemName", itemName);
		}
		if(!StringUtils.isEmpty(exchangeSn)){
			map.put("exchangeSn", exchangeSn);
		}
		if(!StringUtils.isEmpty(gainCoinCode)){
			map.put("gainCoinCode", gainCoinCode);
		}
		List<ExchangeRecord> records = ((ExchangeRecordDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
