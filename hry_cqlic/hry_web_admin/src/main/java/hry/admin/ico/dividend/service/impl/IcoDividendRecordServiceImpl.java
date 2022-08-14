/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.admin.ico.dividend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.dividend.dao.IcoDividendRecordDao;
import hry.admin.ico.dividend.model.IcoDividendRecord;
import hry.admin.ico.dividend.service.IcoDividendRecordService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> IcoDividendRecordService </p>
 * @author:         houz
 * @Date :          2019-01-14 20:56:02  
 */
@Service("icoDividendRecordService")
public class IcoDividendRecordServiceImpl extends BaseServiceImpl<IcoDividendRecord, Long> implements IcoDividendRecordService{
	
	@Resource(name="icoDividendRecordDao")
	@Override
	public void setDao(BaseDao<IcoDividendRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<IcoDividendRecord> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<>();

		String customer_email = filter.getRequest().getParameter("customer_email");
		String customer_mobile = filter.getRequest().getParameter("customer_mobile");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		if(!StringUtils.isEmpty(customer_email)){
			map.put("customer_email", "%"+customer_email+"%" );
		}
		if(!StringUtils.isEmpty(customer_mobile)){
			map.put("customer_mobile", "%"+customer_mobile+"%");
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode",coinCode);
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}

		long s1=System.currentTimeMillis();
		((IcoDividendRecordDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}



	@Override
	public PageResult expenditureAccount(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<Map<String,Object>> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<>();
		String coinCode = filter.getRequest().getParameter("coinCode");
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode","%"+coinCode+"%");
		}
		((IcoDividendRecordDao)dao).expenditureAccount(map);
		PageResult pageResult = new PageResult(page, filter.getPage(), filter.getPageSize());
		return pageResult;

	}
}
