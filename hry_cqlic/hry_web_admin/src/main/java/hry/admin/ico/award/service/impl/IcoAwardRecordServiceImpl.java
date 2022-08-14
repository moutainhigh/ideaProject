/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 17:16:18 
 */
package hry.admin.ico.award.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.award.dao.IcoAwardRecordDao;
import hry.admin.ico.award.model.IcoAwardRecord;
import hry.admin.ico.award.service.IcoAwardRecordService;
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
 * <p> IcoAwardRecordService </p>
 * @author:         houz
 * @Date :          2019-01-14 17:16:18  
 */
@Service("icoAwardRecordService")
public class IcoAwardRecordServiceImpl extends BaseServiceImpl<IcoAwardRecord, Long> implements IcoAwardRecordService{
	
	@Resource(name="icoAwardRecordDao")
	@Override
	public void setDao(BaseDao<IcoAwardRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<IcoAwardRecord> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<String,Object>();

		String award_type = filter.getRequest().getParameter("awardType");
		String customer_email = filter.getRequest().getParameter("customer_email");
		String customer_mobile = filter.getRequest().getParameter("customer_mobile");
		String referrals_email = filter.getRequest().getParameter("referrals_email");
		String referrals_mobile = filter.getRequest().getParameter("referrals_mobile");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		if(!StringUtils.isEmpty(award_type)){
			map.put("award_type",award_type);
		}
		if(!StringUtils.isEmpty(customer_email)){
			map.put("customer_email", "%"+customer_email+"%" );

		}
		if(!StringUtils.isEmpty(customer_mobile)){
			map.put("customer_mobile", "%"+customer_mobile+"%");
		}
		if(!StringUtils.isEmpty(referrals_email)){
			map.put("referrals_email", "%"+referrals_email+"%" );

		}
		if(!StringUtils.isEmpty(referrals_mobile)){
			map.put("referrals_mobile", "%"+referrals_mobile+"%");
		}

		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}

		long s1=System.currentTimeMillis();
		List<IcoAwardRecord> pageBySql = ((IcoAwardRecordDao) dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}
}
