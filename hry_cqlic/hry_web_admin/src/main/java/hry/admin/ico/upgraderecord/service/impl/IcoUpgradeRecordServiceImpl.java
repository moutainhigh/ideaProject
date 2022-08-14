/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-17 10:48:13 
 */
package hry.admin.ico.upgraderecord.service.impl;

import com.github.pagehelper.Page;
import hry.admin.commend.dao.AppCommendUserDao;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.ico.upgraderecord.dao.IcoUpgradeRecordDao;
import hry.admin.ico.upgraderecord.model.IcoUpgradeRecord;
import hry.admin.ico.upgraderecord.service.IcoUpgradeRecordService;
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
 * <p> IcoUpgradeRecordService </p>
 * @author:         houz
 * @Date :          2019-01-17 10:48:13  
 */
@Service("icoUpgradeRecordService")
public class IcoUpgradeRecordServiceImpl extends BaseServiceImpl<IcoUpgradeRecord, Long> implements IcoUpgradeRecordService{
	
	@Resource(name="icoUpgradeRecordDao")
	@Override
	public void setDao(BaseDao<IcoUpgradeRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {


//----------------------分页查询头部外壳------------------------------
		Page<IcoUpgradeRecord> page = PageFactory.getPage(filter);

		Map<String,Object> map = new HashMap<String,Object>();

		String customer_email = filter.getRequest().getParameter("customer_email");
		String customer_mobile = filter.getRequest().getParameter("customer_mobile");
		String oldLevel = filter.getRequest().getParameter("oldLevel");
		String newLevel = filter.getRequest().getParameter("newLevel");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		if(!StringUtils.isEmpty(customer_email)){
			map.put("customer_email", "%"+customer_email+"%" );

		}
		if(!StringUtils.isEmpty(customer_mobile)){
			map.put("customer_mobile", "%"+customer_mobile+"%");
		}
		if(!StringUtils.isEmpty(oldLevel)){
			map.put("oldLevel",oldLevel);
		}
		if(!StringUtils.isEmpty(newLevel)){
			map.put("newLevel", newLevel);
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}


		long s1=System.currentTimeMillis();
		((IcoUpgradeRecordDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}

}
