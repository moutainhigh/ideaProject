/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 13:37:26 
 */
package hry.admin.klg.level.service.impl;

import com.github.pagehelper.Page;
import hry.admin.klg.level.dao.KlgUpgradeRecordDao;
import hry.admin.klg.level.model.KlgUpgradeRecord;
import hry.admin.klg.level.service.KlgUpgradeRecordService;
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
 * <p> KlgUpgradeRecordService </p>
 * @author:         lzy
 * @Date :          2019-05-17 13:37:26  
 */
@Service("klgUpgradeRecordService")
public class KlgUpgradeRecordServiceImpl extends BaseServiceImpl<KlgUpgradeRecord, Long> implements KlgUpgradeRecordService{
	
	@Resource(name="klgUpgradeRecordDao")
	@Override
	public void setDao(BaseDao<KlgUpgradeRecord, Long> dao) {
		super.dao = dao;
	}
	@Override
	public PageResult findPageBySql(QueryFilter filter) {

//----------------------分页查询头部外壳------------------------------
		Page<KlgUpgradeRecord> page = PageFactory.getPage(filter);

		Map<String,Object> map = new HashMap<String,Object>();

		String customer_email = filter.getRequest().getParameter("email");
		String customer_mobile = filter.getRequest().getParameter("mobilePhone");
		String oldLevel = filter.getRequest().getParameter("oldLevel");
		String newLevel = filter.getRequest().getParameter("newLevel");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		if(!StringUtils.isEmpty(customer_email)){
			map.put("email", "%"+customer_email+"%" );
		}
		if(!StringUtils.isEmpty(customer_mobile)){
			map.put("mobilePhone", "%"+customer_mobile+"%");
		}
		if(!StringUtils.isEmpty(oldLevel)){
			map.put("oldLevelId",oldLevel);
		}
		if(!StringUtils.isEmpty(newLevel)){
			map.put("newLevelId", newLevel);
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}
		((KlgUpgradeRecordDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}

}
