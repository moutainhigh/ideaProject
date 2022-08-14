/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-22 11:33:10 
 */
package hry.admin.ico.dividend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.dividend.dao.IcoDividendManualRecordDao;
import hry.admin.ico.dividend.model.IcoDividendManualRecord;
import hry.admin.ico.dividend.service.IcoDividendManualRecordService;
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
 * <p> IcoDividendManualRecordService </p>
 * @author:         lzy
 * @Date :          2019-03-22 11:33:10  
 */
@Service("icoDividendManualRecordService")
public class IcoDividendManualRecordServiceImpl extends BaseServiceImpl<IcoDividendManualRecord, Long> implements IcoDividendManualRecordService{
	
	@Resource(name="icoDividendManualRecordDao")
	@Override
	public void setDao(BaseDao<IcoDividendManualRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<IcoDividendManualRecord> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<>();

		String customer_email = filter.getRequest().getParameter("email");
		String customer_mobile = filter.getRequest().getParameter("mobilePhone");
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
		((IcoDividendManualRecordDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}
}
